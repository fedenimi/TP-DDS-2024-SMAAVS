package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.middlewares.AuthMiddleWare;
import ar.edu.utn.frba.dds.modelo.cronJobs.MainPuntos;
import ar.edu.utn.frba.dds.modelo.entidades.utils.Initializer;
import ar.edu.utn.frba.dds.modelo.entidades.utils.JavalinRenderer;
import ar.edu.utn.frba.dds.modelo.entidades.utils.PrettyProperties;
import ar.edu.utn.frba.dds.server.handlers.AppHandlers;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Server {
    private static Javalin app = null;

    public static Javalin app() {
        if (app == null)
            throw new RuntimeException("App no inicializada");
        return app;
    }

    public static void init() {
        if (app == null) {
            Integer port = Integer.parseInt(PrettyProperties.getInstance().propertyFromName("server_port"));
            System.out.println("Iniciando servidor en puerto " + port);
            app = Javalin.create(config()).start(port);

            AuthMiddleWare.apply(app);
            AppHandlers.applyHandlers(app);
            Router.init(app);


            if (Boolean.parseBoolean(PrettyProperties.getInstance().propertyFromName("dev_mode"))) {
                System.out.println("Inicializando datos de prueba...");
                //Initializer.init();
            }
        }
    }
    private static Consumer<JavalinConfig> config() {
        return config -> {
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/";
                staticFiles.directory = "/public";
            });

            config.fileRenderer(new JavalinRenderer().register("hbs", (path, model, context) -> {
                Handlebars handlebars = new Handlebars();
                Template template = null;
                try {
                    template = handlebars.compile(
                            "templates/" + path.replace(".hbs", ""));
                    return template.apply(model);
                } catch (IOException e) {
                    e.printStackTrace();
                    context.status(HttpStatus.NOT_FOUND);
                    return "No se encuentra la página indicada...";
                }
            }));
        };
    }

}
