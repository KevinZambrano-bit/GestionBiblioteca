package controladores;

import com.google.gson.Gson;
import modelos.Libro;
import repositorio.RepositorioLibro;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class LibroController {
    private static final Gson gson = new Gson();

    public static void rutas() {
        path("/libros", () -> {

            // Obtener todos los libros
            get("", (req, res) -> {
                res.type("application/json");
                return gson.toJson(RepositorioLibro.obtenerTodos());
            });

            // Obtener un libro por ID
            get("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                return RepositorioLibro.obtenerPorId(id)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            Map<String, String> error = new HashMap<>();
                            error.put("error", "Libro no encontrado");
                            return gson.toJson(error);
                        });
            });

            // Crear nuevo libro
            post("", (req, res) -> {
                Libro nuevoLibro = gson.fromJson(req.body(), Libro.class);
                Libro agregado = RepositorioLibro.agregar(nuevoLibro);
                res.status(201);
                return gson.toJson(agregado);
            });

            // Actualizar un libro existente
            put("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                Libro actualizado = gson.fromJson(req.body(), Libro.class);
                return RepositorioLibro.actualizar(id, actualizado)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            Map<String, String> error = new HashMap<>();
                            error.put("error", "No se pudo actualizar");
                            return gson.toJson(error);
                        });
            });

            // Eliminar un libro
            delete("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = RepositorioLibro.eliminar(id);
                Map<String, String> respuesta = new HashMap<>();
                if (eliminado) {
                    respuesta.put("mensaje", "Libro eliminado");
                } else {
                    res.status(404);
                    respuesta.put("error", "Libro no encontrado");
                }
                return gson.toJson(respuesta);
            });
        });
    }
}
