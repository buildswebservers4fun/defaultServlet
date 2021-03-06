package defaultHandlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import dynamic.handler.IPostHandler;
import protocol.HttpRequest;
import protocol.Protocol;
import protocol.response.HttpResponseBuilder;
import protocol.response.IHttpResponse;

import static defaultHandlers.StaticResponceBuilder.build200Response;
import static defaultHandlers.StaticResponceBuilder.build400Response;

public class PostHandler implements IPostHandler {
	
	private String rootDirectory;

	public PostHandler(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	public IHttpResponse handlePost(HttpRequest request) {
		String uri = request.getUri();
		File file = new File(rootDirectory, uri);

		if (file.exists()) {
			if(file.isDirectory()){
				return build400Response();
			} else {
				// Append data to existing file
				try {
					char[] information = request.getBody();
					FileWriter writer = new FileWriter(file, true);
					writer.write(information);
					writer.close();
				} catch (IOException e) {
					return build400Response();
				}
				return build200Response(file);
			}
		} else {
			// Create parent directories and new file
			try {
				file.getParentFile().mkdirs();
				char[] information = request.getBody();
				file.createNewFile();
				FileWriter writer = new FileWriter(file);
				writer.write(information);
				writer.close();
			} catch (IOException e) {
				return build400Response();
			}
			return build201Response(file);
		}
	}
	
	private IHttpResponse build201Response(File file) {
		HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
		responseBuilder.setStatus(Protocol.CREATED_CODE);
		responseBuilder.setPhrase(Protocol.OK_TEXT);
		responseBuilder.setHeaders(new HashMap<String, String>());
		responseBuilder.setFileBody(file);
		responseBuilder.setConnection(Protocol.CLOSE);
		
		return responseBuilder.build();
	}
}