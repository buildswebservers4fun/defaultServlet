import defaultHandlers.DefaultHandler;
import dynamic.IPluginLoader;
import dynamic.IServlet;
import dynamic.PluginRouter;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by CJ on 2/7/2017.
 */
public class PluginLoader implements IPluginLoader {
    public void init(PluginRouter router, String rootDirectory) {
        Path route = Paths.get("/");
        IServlet servlet = DefaultHandler.createDefaultHandler(rootDirectory);

        router.addRoute(route, servlet);
    }
}
