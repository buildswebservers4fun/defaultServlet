package defaultHandlers;

import java.io.File;
import java.util.HashMap;

import dynamic.handler.IGetHandler;
import protocol.HttpRequest;
import protocol.Protocol;
import protocol.response.HttpResponseBuilder;
import protocol.response.IHttpResponse;

import static defaultHandlers.StaticResponceBuilder.build200Response;
import static defaultHandlers.StaticResponceBuilder.build404Response;

public class GetHandler implements IGetHandler {

	private String rootDirectory;

	public GetHandler(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	public IHttpResponse handleGet(HttpRequest request) {
		IHttpResponse response;
		// Map<String, String> header = request.getHeader();
		// String date = header.get("if-modified-since");
		// String hostName = header.get("host");
		//
		// Handling GET request here
		// Get relative URI path from request
		String uri = request.getUri();
		// Get root directory path from server
		// Combine them together to form absolute file path
		File file = new File(rootDirectory, uri);
		// Check if the file exists
		if (file.exists()) {
			if (file.isDirectory()) {
				// Look for default index.html file in a directory
				String location = rootDirectory + uri + System.getProperty("file.separator") + Protocol.DEFAULT_FILE;
				file = new File(location);
				if (file.exists()) {
					// Lets create 200 OK response
					response = build200Response(file);
				} else {
					// File does not exist so lets create 404 file not found
					// code
					response = build404Response();
				}
			} else { // Its a file
						// Lets create 200 OK response
				response = build200Response(file);
			}
		} else {
			// File does not exist so lets create 404 file not found code
			response = build404Response();
		}
		return response;
	}
}
