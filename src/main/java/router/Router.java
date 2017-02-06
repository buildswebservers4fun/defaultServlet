package router;

import java.io.IOException;
import java.io.OutputStream;

import defaultHandlers.DefaultHandler;
import dynamic.IPluginRouter;
import dynamic.IServlet;
import protocol.HttpRequest;
import protocol.response.IHttpResponse;

public class Router implements IPluginRouter {
	private IServlet defaultServlet;
	private OutputStream outStream;

	public Router() {
		defaultServlet = DefaultHandler.createDefaultHandler(DefaultHandler.ROOT_DIR);
	}

	public void forwardRequest(HttpRequest request, OutputStream outStream) {
		// TODO use this code for the users servlet
		// String[] split = request.getUri().split("/");
		// // TODO test this
		// if(split.length < 1) {
		// build404Response();
		// }
		//
		// String relativeRoot = split[1];
		//
		// IServlet servlet = uriToServlet.get(relativeRoot);
		//
		// if(servlet != null) {
		// servlet.handle(request);
		// } else {
		// build404Response();
		// }

		System.out.println("NEW FORWARD");
		
		try {
			defaultServlet.handle(request, outStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void forwardRequest(HttpRequest request) {
		// TODO Auto-generated method stub
		System.out.println("OLD FORWARD");
	}

}
