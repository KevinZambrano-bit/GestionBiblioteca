package controllers;

import io.javalin.Javalin;
import models.Autor;
import repository.RepositoryAutor;

public class AutorController {
    public static void configureRoutes(Javalin app) {
        app.get("/autores", ctx -> {
            ctx.json(RepositoryAutor.getALL());
        });
        app.get("/autores/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            RepositoryAutor.getAutorId(id)
                    .ifPresentOrElse(
                            autor -> ctx.json(autor),
                            () -> ctx.status(404).result("Autor no encontrado")
                    );
        });
        app.post("/autores", ctx -> {
            Autor autor = ctx.bodyAsClass(Autor.class);
            RepositoryAutor.addAutor(autor);
            ctx.status(201).json(autor);
        });
        app.put("/autores/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Autor autorUpdate = ctx.bodyAsClass(Autor.class);
            RepositoryAutor.updateAutor(id, autorUpdate)
                    .ifPresentOrElse(
                            autor -> ctx.json(autor),
                            () -> ctx.status(404).result("Autor no encontrado")
                    );
        });
        app.delete("/autores/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean deleted = RepositoryAutor.deleteAutor(id);
            if (deleted) {
                ctx.status(204); // No Content
            } else {
                ctx.status(404).result("Autor no encontrado");
            }
        });
    }
}
