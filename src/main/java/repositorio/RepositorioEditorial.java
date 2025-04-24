package repositorio;

import modelos.Editorial;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositorioEditorial {
    private static final List<Editorial> editoriales = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    public static List<Editorial> obtenerTodos() {
        return new ArrayList<>(editoriales);
    }

    public static Optional<Editorial> obtenerPorId(int id) {
        return editoriales.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static Editorial agregar(Editorial editorial) {
        editorial.setId(autoId.getAndIncrement());
        editoriales.add(editorial);
        return editorial;
    }

    public static Optional<Editorial> actualizar(int id, Editorial editorialActualizada) {
        Optional<Editorial> existente = obtenerPorId(id);
        existente.ifPresent(e -> {
            e.setNombre(editorialActualizada.getNombre());
            e.setPais(editorialActualizada.getPais());
            e.setAñoFundacion(editorialActualizada.getAñoFundacion());
        });
        return existente;
    }

    public static boolean eliminar(int id) {
        return editoriales.removeIf(e -> e.getId() == id);
    }
}
