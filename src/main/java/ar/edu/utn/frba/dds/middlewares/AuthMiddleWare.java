package ar.edu.utn.frba.dds.middlewares;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class AuthMiddleWare {

        public static void apply(Javalin app) {
            app.beforeMatched(ctx -> {
                var userRole = getUserRoleType(ctx);
                if (!ctx.routeRoles().isEmpty() && !ctx.routeRoles().contains(userRole)) {
                    //throw new AccessDeniedException();
                }
            });
        }

        private static Permiso getUserRoleType(Context context) {
            return context.sessionAttribute("tipo_rol") != null?
                    Permiso.valueOf(context.sessionAttribute("tipo_rol")) : null;
        }
    }
