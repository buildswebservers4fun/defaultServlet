package router;

import dynamic.IPluginRouter;
import dynamic.IServlet;
import handlers.DefaultHandler;
import protocol.HttpRequest;

public class Router implements IPluginRouter {
	IServlet defaultServlet;

	public Router() {
		defaultServlet = DefaultHandler.createDefaultHandler(DefaultHandler.ROOT_DIR);
	}

	public void forwardRequest(HttpRequest request) {
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

		defaultServlet.handle(request);
	}

}
