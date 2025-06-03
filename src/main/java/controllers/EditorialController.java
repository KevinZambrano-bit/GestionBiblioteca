package controllers;

import io.javalin.Javalin;
import models.Editorial;
import repository.RepositoryEditorial;

public class EditorialController {
    public static void configureRoutes(Javalin app) {
        app.get("/editoriales", ctx -> {
            ctx.json(RepositoryEditorial.getALL());
        });

        app.get("/editoriales/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            RepositoryEditorial.getEditorialId(id)
                    .ifPresentOrElse(
                            editorial -> ctx.json(editorial),
                            () -> ctx.status(404).result("Editorial no encontrada")
                    );
        });

        app.post("/editoriales", ctx -> {
            Editorial editorial = ctx.bodyAsClass(Editorial.class);
            RepositoryEditorial.addEditorial(editorial);
            ctx.status(201).json(editorial);
        });

        app.put("/editoriales/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Editorial editorialUpdate = ctx.bodyAsClass(Editorial.class);
            RepositoryEditorial.updateEditorial(id, editorialUpdate);
            ctx.status(200).result("Editorial actualizada");
        });

        app.delete("/editoriales/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            RepositoryEditorial.deleteEditorial(id);
            ctx.status(204);
        });

    }
}