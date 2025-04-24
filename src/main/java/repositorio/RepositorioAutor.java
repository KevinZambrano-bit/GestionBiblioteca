package repositorio;

import modelos.Autor;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositorioAutor {
    private static final List<Autor> autores = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    public static List<Autor> obtenerTodos() {
        return new ArrayList<>(autores);
    }

    public static Optional<Autor> obtenerPorId(int id) {
        return autores.stream().filter(a -> a.getId() == id).findFirst();
    }

    public static Autor agregar(Autor autor) {
        autor.setId(autoId.getAndIncrement());
        autores.add(autor);
        return autor;
    }

    public static Optional<Autor> actualizar(int id, Autor autorActualizado) {
        Optional<Autor> existente = obtenerPorId(id);
        existente.ifPresent(autor -> {
            autor.setNombre(autorActualizado.getNombre());
            autor.setNacionalidad(autorActualizado.getNacionalidad());
            autor.setFechaNacimiento(autorActualizado.getFechaNacimiento());
        });
        return existente;
    }

    public static boolean eliminar(int id) {
        return autores.removeIf(a -> a.getId() == id);
    }
}

