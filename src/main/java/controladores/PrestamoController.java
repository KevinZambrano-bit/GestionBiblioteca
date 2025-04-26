package controladores;

import com.google.gson.Gson;
import modelos.Prestamo;
import repositorio.RepositorioPrestamo;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class PrestamoController {
    private static final Gson gson = new Gson();

    public static void rutas() {
        path("/prestamos", () -> {

            get("", (req, res) -> {
                res.type("application/json");
                return gson.toJson(RepositorioPrestamo.obtenerTodos());
            });

            get("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                return RepositorioPrestamo.obtenerPorId(id)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            Map<String, String> error = new HashMap<>();
                            error.put("error", "Préstamo no encontrado");
                            return gson.toJson(error);
                        });
            });

            post("", (req, res) -> {
                Prestamo nuevo = gson.fromJson(req.body(), Prestamo.class);
                Prestamo agregado = RepositorioPrestamo.agregar(nuevo);
                res.status(201);
                return gson.toJson(agregado);
            });

            put("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                Prestamo actualizado = gson.fromJson(req.body(), Prestamo.class);
                return RepositorioPrestamo.actualizar(id, actualizado)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            Map<String, String> error = new HashMap<>();
                            error.put("error", "No se pudo actualizar el préstamo");
                            return gson.toJson(error);
                        });
            });

            delete("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = RepositorioPrestamo.eliminar(id);
                Map<String, String> respuesta = new HashMap<>();
                if (eliminado) {
                    respuesta.put("mensaje", "Préstamo eliminado");
                } else {
                    res.status(404);
                    respuesta.put("error", "Préstamo no encontrado");
                }
                return gson.toJson(respuesta);
            });
        });
    }
}
