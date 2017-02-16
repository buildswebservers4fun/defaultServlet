package defaultHandlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import dynamic.handler.IPutHandler;
import protocol.HttpRequest;
import protocol.Protocol;
import protocol.response.HttpResponseBuilder;
import protocol.response.IHttpResponse;

import static defaultHandlers.StaticResponceBuilder.build200Response;
import static defaultHandlers.StaticResponceBuilder.build400Response;

public class PutHandler implements IPutHandler {

	private String rootDirectory;

	public PutHandler(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	public IHttpResponse handlePut(HttpRequest request) {
		String uri = request.getUri();
		boolean exists = false;

		File file = new File(rootDirectory, uri);

		if (file.exists()) {
			if(file.isDirectory()){
				return build400Response();
			}
			exists = true;
		}
		
		try {
			char[] information = request.getBody();
			file.getParentFile().mkdirs();
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(information);
			writer.close();
		} catch (IOException e) {
			// TODO Make this server error
			return build400Response();
		}

		if (exists){
			return build200Response(file);
		}
		return build201Response(file);
	}

	private IHttpResponse build201Response(File file) {
		HttpResponseBuilder responseBuilder = new HttpResponseBuilder();
		responseBuilder.setStatus(Protocol.CREATED_CODE);
		responseBuilder.setPhrase(Protocol.CREATED_TEXT);
		responseBuilder.setHeaders(new HashMap<String, String>());
		responseBuilder.setFileBody(file);
		responseBuilder.setConnection(Protocol.CLOSE);
		return responseBuilder.build();
	}
}
