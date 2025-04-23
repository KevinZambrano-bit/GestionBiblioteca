package app;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(333);

        get("/hola", (req, res) -> "¡Hola desde SparkJava!");

    }
}
