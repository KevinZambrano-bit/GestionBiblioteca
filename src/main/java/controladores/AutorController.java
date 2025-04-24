package controladores;

import com.google.gson.Gson;
import modelos.Autor;
import repositorio.RepositorioAutor;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

public class AutorController {
    private static final Gson gson = new Gson();

    public static void rutas() {
        path("/autores", () -> {

            get("", (req, res) -> {
                res.type("application/json");
                return gson.toJson(RepositorioAutor.obtenerTodos());
            });

            get("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                return RepositorioAutor.obtenerPorId(id)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            Map<String, String> error = new HashMap<>();
                            error.put("error", "Autor no encontrado");
                            return gson.toJson(error);
                        });
            });


            post("", (req, res) -> {
                Autor nuevo = gson.fromJson(req.body(), Autor.class);
                Autor agregado = RepositorioAutor.agregar(nuevo);
                res.status(201);
                return gson.toJson(agregado);
            });

            put("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                Autor actualizado = gson.fromJson(req.body(), Autor.class);
                return RepositorioAutor.actualizar(id, actualizado)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            Map<String, String> error = new HashMap<>();
                            error.put("error", "Autor no encontrado");
                            return gson.toJson(error);
                        });
            });

            delete("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = RepositorioAutor.eliminar(id);
                Map<String, String> respuesta = new HashMap<>();
                if (eliminado) {
                    respuesta.put("mensaje", "Autor eliminado");
                } else {
                    res.status(404);
                    respuesta.put("error", "Autor no encontrado");
                }
                return gson.toJson(respuesta);
            });
        });
    }
}
