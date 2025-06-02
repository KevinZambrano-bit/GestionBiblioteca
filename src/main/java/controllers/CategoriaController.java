package controllers;

import io.javalin.Javalin;
import models.Book;
import models.Categoria;

public class CategoriaController {
    public static void configureRoutes(Javalin app) {
        app.get("/categorias", ctx -> {
            ctx.json(repository.RepositoryCategoria.getALL());
        });

        //Get book by ID
        app.get("/categorias/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repository.RepositoryCategoria.categoriaId(id)
                    .ifPresentOrElse(
                            categoria -> ctx.json(categoria),
                            () -> ctx.status(404).result("Categoria no encontrada")
                    );
        } );

        //Add book
        app.post("/categorias", ctx -> {
            Categoria categoria = ctx.bodyAsClass(Categoria.class);
            repository.RepositoryCategoria.agregarCategoria(categoria);
            ctx.status(201).json(categoria);
        });

        //Update book
        app.put("/categorias/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Categoria  categoriaAct= ctx.bodyAsClass(Categoria.class);
            repository.RepositoryCategoria.actuCategoria(id, categoriaAct)
                    .ifPresentOrElse(
                            categoria -> ctx.json(categoria),
                            () -> ctx.status(404).result("Categoria no actualizada")
                    );
        });

        //Delete book
        app.delete("/categorias/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean categoriaEliminada = repository.RepositoryCategoria.eliminarCategoria(id);
            if(categoriaEliminada) {
                ctx.status(202).result("Categoria eliminada");
            } else {
                ctx.status(404).result("Categoria no encontrada");
            }
        });
    }
}
