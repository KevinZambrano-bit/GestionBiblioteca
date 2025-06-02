package repository;

import models.Autor;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
public class RepositoryAutor {
    private static final List<Autor> autores = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);
    public static List<Autor> getALL() {
        return new ArrayList<>(autores);
    }
    public static Optional<Autor> getAutorId(int id) {
        return autores.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }
    public static Autor addAutor(Autor autor) {
        autor.setId(autoId.getAndIncrement());
        autores.add(autor);
        return autor;
    }
    public static Optional<Autor> updateAutor(int id, Autor autorUpdate) {
        Optional<Autor> existing = getAutorId(id);
        existing.ifPresent(autor -> {
            autor.setName(autorUpdate.getName());
            autor.setNationality(autorUpdate.getNationality());
        });
        return existing;
    }
    public static boolean deleteAutor(int id) {
        return autores.removeIf(a -> a.getId() == id);
    }
}
