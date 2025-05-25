package repository;

import models.User;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositoryUser {
    private static final List<User> users = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger autoId = new AtomicInteger();

    //Get all the books
    public static List<User> getALL() {
        return  new ArrayList<>(users);
    }

    //Get book by ID
    public static Optional<User> getUserId(int id) {
        return users.stream()
                .filter(l -> l.getId() == id)
                .findFirst();
    }

    //Add user
    public static User addUser(User user) {
        user.setId(autoId.getAndIncrement());
        users.add(user);
        return user;
    }

    //Update book
    public static Optional<User> updateUser(int id, User userUpdate) {
        Optional<User> existing = getUserId(id);
        existing.ifPresent(user -> {
            user.setName(userUpdate.getName());
            user.setEmail(userUpdate.getEmail());
            user.setUserType(userUpdate.getUserType());
            user.setPhone(userUpdate.getPhone());
        });
        return existing;
    }

    //Delete book
    public static boolean deleteUser(int id) {
        return users.removeIf(l -> l.getId() == id);
    }


}