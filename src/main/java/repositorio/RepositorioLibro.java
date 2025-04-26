package repositorio;

import modelos.Libro;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositorioLibro {
    private static final List<Libro> libros = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    public static List<Libro> obtenerTodos() {
        return new ArrayList<>(libros);
    }

    public static Optional<Libro> obtenerPorId(int id) {
        return libros.stream().filter(l -> l.getId() == id).findFirst();
    }

    public static Libro agregar(Libro libro) {
        libro.setId(autoId.getAndIncrement());
        libros.add(libro);
        return libro;
    }

    public static Optional<Libro> actualizar(int id, Libro libroActualizado) {
        Optional<Libro> existente = obtenerPorId(id);
        existente.ifPresent(libro -> {
            libro.setTitulo(libroActualizado.getTitulo());
            libro.setIsbn(libroActualizado.getIsbn());
            libro.setAño(libroActualizado.getAño());
            libro.setAutor(libroActualizado.getAutor());
            libro.setEditorial(libroActualizado.getEditorial());
            libro.setCategoria(libroActualizado.getCategoria());
        });
        return existente;
    }

    public static boolean eliminar(int id) {
        return libros.removeIf(l -> l.getId() == id);
    }
}
