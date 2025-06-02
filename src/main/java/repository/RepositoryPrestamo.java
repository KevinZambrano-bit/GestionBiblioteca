package repository;

import models.Prestamo;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositoryPrestamo {
    private static final List<Prestamo> prestamos = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger(1);

    public static List<Prestamo> obtenerTodos() {
        return new ArrayList<>(prestamos);
    }

    public static Optional<Prestamo> obtenerPorId(int id) {
        return prestamos.stream().filter(p -> p.getId() == id).findFirst();
    }

    public static Prestamo agregar(Prestamo prestamo) {
        prestamo.setId(autoId.getAndIncrement());
        prestamos.add(prestamo);
        return prestamo;
    }

    public static Optional<Prestamo> actualizar(int id, Prestamo prestamoActualizado) {
        Optional<Prestamo> existente = obtenerPorId(id);
        existente.ifPresent(p -> {
            p.setIdUsuario(prestamoActualizado.getIdUsuario());
            p.setNombreUsuario(prestamoActualizado.getNombreUsuario());
            p.setIdLibro(prestamoActualizado.getIdLibro());
            p.setTituloLibro(prestamoActualizado.getTituloLibro());
            p.setFechaPrestamo(prestamoActualizado.getFechaPrestamo());
            p.setFechaDevolucion(prestamoActualizado.getFechaDevolucion());
            p.setEstado(prestamoActualizado.getEstado());
        });
        return existente;
    }

    public static boolean eliminar(int id) {
        return prestamos.removeIf(p -> p.getId() == id);
    }
}
