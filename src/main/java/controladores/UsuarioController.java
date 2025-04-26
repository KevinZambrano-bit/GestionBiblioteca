package controladores;

import com.google.gson.Gson;
import modelos.Usuario;
import repositorio.RepositorioUsuario;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class UsuarioController {
    private static final Gson gson = new Gson();

    public static void rutas() {
        path("/usuarios", () -> {  // 👈  ¡¡¡Mira aquí!!! /usuarios

            get("", (req, res) -> {
                res.type("application/json");
                return gson.toJson(RepositorioUsuario.obtenerTodos());
            });

            get("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                return RepositorioUsuario.obtenerPorId(id)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            Map<String, String> error = new HashMap<>();
                            error.put("error", "Usuario no encontrado");
                            return gson.toJson(error);
                        });
            });

            post("", (req, res) -> { // 👈 post("")
                Usuario nuevo = gson.fromJson(req.body(), Usuario.class);
                Usuario agregado = RepositorioUsuario.agregar(nuevo);
                res.status(201);
                return gson.toJson(agregado);
            });

            put("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                Usuario actualizado = gson.fromJson(req.body(), Usuario.class);
                return RepositorioUsuario.actualizar(id, actualizado)
                        .map(gson::toJson)
                        .orElseGet(() -> {
                            Map<String, String> error = new HashMap<>();
                            error.put("error", "No se pudo actualizar el usuario");
                            return gson.toJson(error);
                        });
            });

            delete("/:id", (req, res) -> {
                int id = Integer.parseInt(req.params(":id"));
                boolean eliminado = RepositorioUsuario.eliminar(id);
                Map<String, String> respuesta = new HashMap<>();
                if (eliminado) {
                    respuesta.put("mensaje", "Usuario eliminado");
                } else {
                    res.status(404);
                    respuesta.put("error", "Usuario no encontrado");
                }
                return gson.toJson(respuesta);
            });
        });
    }
}
