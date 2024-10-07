package ar.edu.utn.frba.dds.middlewares;
import ar.edu.utn.frba.dds.exceptions.AccessDeniedException;
import ar.edu.utn.frba.dds.modelo.entidades.acceso.Permiso;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthMiddleWare {

        public static void apply(Javalin app) {
            app.beforeMatched(ctx -> {
                var userRoles = getUserRoles(ctx);
                if (!ctx.routeRoles().isEmpty() && (userRoles.stream().allMatch(role -> !ctx.routeRoles().contains(role)) || !Objects.equals(Long.valueOf(ctx.pathParam("id")), ctx.sessionAttribute("colaborador_id")))) {
                    throw new AccessDeniedException();
                }
            });
        }

        private static List<Permiso> getUserRoles(Context context) {
            return context.sessionAttribute("permisos") != null?
                    context.sessionAttribute("permisos") : null;
        }
    }
