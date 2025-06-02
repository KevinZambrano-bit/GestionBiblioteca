package repository;

import models.Categoria;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositoryCategoria {
    private static final List<Categoria> categorias = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    //Get all the books
    public static List<Categoria> getALL() {
        return new ArrayList<>(categorias);
    }

    //Get book by ID
    public static Optional<Categoria> categoriaId(int id) {
        return categorias.stream()
                .filter(l -> l.getId() == id)
                .findFirst();
    }

    //Add user
    public static Categoria agregarCategoria(Categoria categoria) {
        categoria.setId(autoId.getAndIncrement());
        categorias.add(categoria);
        return categoria;
    }

    //Update book
    public static Optional<Categoria> actuCategoria(int id, Categoria categoriaAct) {
        Optional<Categoria> existing = categoriaId(id);
        existing.ifPresent(categoria -> {
            categoria.setNombre(categoriaAct.getNombre());
            categoria.setDescripcion(categoriaAct.getDescripcion());
        });
        return existing;
    }

    //Delete book
    public static boolean eliminarCategoria(int id) {
        return categorias.removeIf(l -> l.getId() == id);
    }

}