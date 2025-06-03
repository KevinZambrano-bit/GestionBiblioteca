package repository;

import models.Editorial;
import models.Prestamo;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositoryEditorial {
    private static final List<Editorial> editoriales = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    public static List<Editorial> getALL() {
        return editoriales;
    }

    public static Optional<Editorial> getEditorialId(int id) {
        return editoriales.stream().filter(e -> e.getId() == id).findFirst();
    }

    public static void addEditorial(Editorial editorial) {
        editorial.setId(autoId.getAndIncrement());
        editoriales.add(editorial);
    }

    public static void updateEditorial(int id, Editorial editorialUpdate) {
        getEditorialId(id).ifPresent(editorial -> {
            editorial.setNombre(editorialUpdate.getNombre());
            editorial.setPais(editorialUpdate.getPais());
            editorial.setAñoFundacion(editorialUpdate.getAñoFundacion());
        });
    }

    public static void deleteEditorial(int id) {
        editoriales.removeIf(editorial -> editorial.getId() == id);
    }

}
