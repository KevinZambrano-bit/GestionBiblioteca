package repositorio;

import modelos.Usuario;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositorioUsuario {
    private static final List<Usuario> usuarios = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    public static List<Usuario> obtenerTodos() {
        return new ArrayList<>(usuarios);
    }

    public static Optional<Usuario> obtenerPorId(int id) {
        return usuarios.stream().filter(u -> u.getId() == id).findFirst();
    }

    public static Usuario agregar(Usuario usuario) {
        usuario.setId(autoId.getAndIncrement());
        usuarios.add(usuario);
        return usuario;
    }

    public static Optional<Usuario> actualizar(int id, Usuario usuarioActualizado) {
        Optional<Usuario> existente = obtenerPorId(id);
        existente.ifPresent(u -> {
            u.setNombre(usuarioActualizado.getNombre());
            u.setEmail(usuarioActualizado.getEmail());
            u.setTipoUsuario(usuarioActualizado.getTipoUsuario());
        });
        return existente;
    }

    public static boolean eliminar(int id) {
        return usuarios.removeIf(u -> u.getId() == id);
    }
}
