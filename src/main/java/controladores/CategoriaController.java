package controladores;

import com.google.gson.Gson;
import modelos.Categoria;
import repositorio.RepositorioCategoria;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class CategoriaController {
    private static final Gson gson = new Gson();

    public static void rutas() {
        path("/categorias", () -> {

            get("", (req, res) -> {
                res.type("application/json");
                return gson.toJson(RepositorioCategoria.obtenerTodos());
            });

            get("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                return RepositorioCategoria.obtenerPorId(id)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            Map<String, String> error = new HashMap<>();
                            error.put("error", "Categoría no encontrada");
                            return gson.toJson(error);
                        });
            });

            post("", (req, res) -> {
                Categoria nueva = gson.fromJson(req.body(), Categoria.class);
                Categoria agregada = RepositorioCategoria.agregar(nueva);
                res.status(201);
                return gson.toJson(agregada);
            });

            put("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                Categoria actualizada = gson.fromJson(req.body(), Categoria.class);
                return RepositorioCategoria.actualizar(id, actualizada)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            Map<String, String> error = new HashMap<>();
                            error.put("error", "No se pudo actualizar");
                            return gson.toJson(error);
                        });
            });

            delete("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = RepositorioCategoria.eliminar(id);
                Map<String, String> respuesta = new HashMap<>();
                if (eliminado) {
                    respuesta.put("mensaje", "Categoría eliminada");
                } else {
                    res.status(404);
                    respuesta.put("error", "Categoría no encontrada");
                }
                return gson.toJson(respuesta);
            });
        });
    }
}
