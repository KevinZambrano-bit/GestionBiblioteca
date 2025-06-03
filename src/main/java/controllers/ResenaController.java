package controllers;

import io.javalin.Javalin;
import models.Resena;
import repository.RepositoryResena;

public class ResenaController {
    public static void configureRoutes(Javalin app) {
        app.get("/resenas", ctx -> {
            ctx.json(RepositoryResena.getALL());
        });

        app.get("/resenas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            RepositoryResena.getResenaId(id)
                    .ifPresentOrElse(
                            resena -> ctx.json(resena),
                            () -> ctx.status(404).result("Reseña no encontrada")
                    );
        });

        app.post("/resenas", ctx -> {
            Resena resena = ctx.bodyAsClass(Resena.class);
            RepositoryResena.addResena(resena);
            ctx.status(201).json(resena);
        });

        app.put("/resenas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Resena resenaUpdate = ctx.bodyAsClass(Resena.class);
            RepositoryResena.updateResena(id, resenaUpdate);
            ctx.status(200).result("Reseña actualizada");
        });

        app.delete("/resenas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            RepositoryResena.deleteResena(id);
            ctx.status(204);
        });
    }
}
