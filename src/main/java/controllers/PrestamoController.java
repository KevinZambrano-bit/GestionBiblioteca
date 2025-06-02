package controllers;

import com.google.gson.Gson;
import io.javalin.Javalin;
import models.Book;
import models.Prestamo;
import repository.RepositoryPrestamo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PrestamoController {

    public static void configureRoutes(Javalin app) {


        app.get("/prestamo", ctx -> {
            ctx.json(RepositoryPrestamo.obtenerTodos());
        });

        //Get book by ID
        app.get("/prestamo/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repository.RepositoryPrestamo.obtenerPorId(id)
                    .ifPresentOrElse(
                            prestamo -> ctx.json(prestamo),
                            () -> ctx.status(404).result("No se encontro el prestamo")
                    );
        });

        app.post("/prestamo", ctx -> {
            Prestamo nuevoPrestamo = ctx.bodyAsClass(Prestamo.class);
            repository.RepositoryPrestamo.agregar(nuevoPrestamo);
            ctx.status(201).json(nuevoPrestamo);
        });


        app.put("/prestamo/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Prestamo prestamoActualizado = ctx.bodyAsClass(Prestamo.class);
            repository.RepositoryPrestamo.actualizar(id, prestamoActualizado)
                    .ifPresentOrElse(
                            prestamo -> ctx.json(prestamo),
                            () -> ctx.status(404).result("Prestamo no encontrado")
                    );
        });

        app.delete("/prestamo/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean eliminar = repository.RepositoryPrestamo.eliminar(id);
            if(eliminar) {
                ctx.status(202).result("Prestamo eliminado");
            } else {
                ctx.status(404).result("Prestamo no encontrado");
            }


        });
    }
}
