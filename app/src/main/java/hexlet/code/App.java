package hexlet.code;

import hexlet.code.controller.SessionController;
import hexlet.code.repository.BaseRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    /**
     * @return Application.
     */
    public static Javalin getApp() {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        BaseRepository.implementSchema();
        app.get(NamedRoutes.rootPath(), SessionController::root);
        return app;
    }

    /**
     * Start application.
     */
    public static void start() {
        Javalin app = getApp();
        BaseRepository.implementSchema();
        app.start(getPort());
    }

    /**
     * @return Application port from system env.
     */
    public static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.parseInt(port);
    }

    public static void main(String[] args) {
        start();
    }
}
