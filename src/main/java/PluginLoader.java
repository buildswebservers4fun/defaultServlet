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
    public static final Path basePath = Paths.get("/");

    public void init(PluginRouter router, String rootDirectory) {
        IServlet servlet = DefaultHandler.createDefaultHandler(rootDirectory);

        router.addRoute(basePath, servlet);
    }

    @Override
    public void unload(PluginRouter router) {
        router.removeRoute(basePath);
    }
}
