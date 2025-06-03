package repository;

import models.Resena;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositoryResena {
    private static final List<Resena> resenas = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    public static List<Resena> getALL() {
        return resenas;
    }

    public static Optional<Resena> getResenaId(int id) {
        return resenas.stream().filter(r -> r.getId() == id).findFirst();
    }

    public static void addResena(Resena resena) {
        resena.setId(autoId.getAndIncrement());
        resenas.add(resena);
    }

    public static void updateResena(int id, Resena resenaUpdate) {
        getResenaId(id).ifPresent(resena -> {
            resena.setLibro(resenaUpdate.getLibro());
            resena.setCalificacion(resenaUpdate.getCalificacion());
            resena.setComentario(resenaUpdate.getComentario());
        });
    }

    public static void deleteResena(int id) {
        resenas.removeIf(resena -> resena.getId() == id);
    }
}
