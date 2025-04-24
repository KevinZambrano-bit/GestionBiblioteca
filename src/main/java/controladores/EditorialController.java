package controladores;

import com.google.gson.Gson;
import modelos.Editorial;
import repositorio.RepositorioEditorial;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class EditorialController {
    private static final Gson gson = new Gson();

    public static void rutas() {
        path("/editoriales", () -> {

            get("", (req, res) -> {
                res.type("application/json");
                return gson.toJson(RepositorioEditorial.obtenerTodos());
            });

            get("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                return RepositorioEditorial.obtenerPorId(id)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            Map<String, String> error = new HashMap<>();
                            error.put("error", "Editorial no encontrada");
                            return gson.toJson(error);
                        });
            });

            post("", (req, res) -> {
                Editorial nueva = gson.fromJson(req.body(), Editorial.class);
                Editorial agregada = RepositorioEditorial.agregar(nueva);
                res.status(201);
                return gson.toJson(agregada);
            });

            put("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                Editorial actualizada = gson.fromJson(req.body(), Editorial.class);
                return RepositorioEditorial.actualizar(id, actualizada)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            Map<String, String> error = new HashMap<>();
                            error.put("error", "No se pudo actualizar");
                            return gson.toJson(error);
                        });
            });

            delete("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = RepositorioEditorial.eliminar(id);
                Map<String, String> respuesta = new HashMap<>();
                if (eliminado) {
                    respuesta.put("mensaje", "Editorial eliminada");
                } else {
                    res.status(404);
                    respuesta.put("error", "Editorial no encontrada");
                }
                return gson.toJson(respuesta);
            });
        });
    }
}
