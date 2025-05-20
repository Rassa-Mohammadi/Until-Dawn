package com.tilldawn.model;

import java.util.ArrayList;

public class App {
    private static ArrayList<User> users = new ArrayList<>();

    public static User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static void addUser(User User) {
        users.add(User);
    }
}
