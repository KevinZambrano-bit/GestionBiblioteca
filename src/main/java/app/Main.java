package app;

import controllers.AutorController;
import controllers.BookController;
import controllers.UserController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        var app = Javalin.create(/*config*/)
                .get("/", ctx -> ctx.result("Hello World"))
                .start(7070);

        BookController.configureRoutes(app);
        UserController.configureRoutes(app);
        AutorController.configureRoutes(app);



    }
}
