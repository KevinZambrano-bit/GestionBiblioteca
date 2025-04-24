package repositorio;

import modelos.Categoria;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositorioCategoria {
    private static final List<Categoria> categorias = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    public static List<Categoria> obtenerTodos() {
        return new ArrayList<>(categorias);
    }

    public static Optional<Categoria> obtenerPorId(int id) {
        return categorias.stream().filter(c -> c.getId() == id).findFirst();
    }

    public static Categoria agregar(Categoria categoria) {
        categoria.setId(autoId.getAndIncrement());
        categorias.add(categoria);
        return categoria;
    }

    public static Optional<Categoria> actualizar(int id, Categoria categoriaActualizada) {
        Optional<Categoria> existente = obtenerPorId(id);
        existente.ifPresent(c -> {
            c.setNombre(categoriaActualizada.getNombre());
            c.setDescripcion(categoriaActualizada.getDescripcion());
        });
        return existente;
    }

    public static boolean eliminar(int id) {
        return categorias.removeIf(c -> c.getId() == id);
    }
}
