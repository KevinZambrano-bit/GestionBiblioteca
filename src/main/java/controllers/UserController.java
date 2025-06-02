package controllers;

import io.javalin.Javalin;
import models.User;

public class UserController {
    public static void configureRoutes(Javalin app) {
        app.get("/users", ctx -> {
            ctx.json(repository.RepositoryUser.getALL());
        });

        //Get user by ID
        app.get("/users/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repository.RepositoryUser.getUserId(id)
                    .ifPresentOrElse(
                            user -> ctx.json(user),
                            () -> ctx.status(404).result("User not found")
                    );
        });

        //Add user
        app.post("/users", ctx -> {
            User user = ctx.bodyAsClass(User.class);
            repository.RepositoryUser.addUser(user);
            ctx.status(201).json(user);
        });

        //Update user
        app.put("/users/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            User userUpdate = ctx.bodyAsClass(User.class);
            repository.RepositoryUser.updateUser(id, userUpdate)
                    .ifPresentOrElse(
                            user -> ctx.json(user),
                            () -> ctx.status(404).result("User not found")
                    );
        });

        //Delete user
        app.delete("/users/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean userDelete = repository.RepositoryUser.deleteUser(id);
            if (userDelete) {
                ctx.status(202).result("Deleted user ");
            } else {
                ctx.status(404).result("User no found");
            }
        });
    }
}
