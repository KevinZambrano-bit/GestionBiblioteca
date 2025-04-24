package app;

import static spark.Spark.*;

import controladores.AutorController;
import controladores.CategoriaController;
import controladores.EditorialController;
import controladores.LibroController;

public class Main {
    public static void main(String[] args) {
        port(333);
        get("/hola", (req, res) -> "¡Hola desde SparkJava!");

        LibroController.rutas();
        AutorController.rutas();
        CategoriaController.rutas();
        EditorialController.rutas();
    }
}



