package com.tilldawn.model;

import com.tilldawn.model.enums.Output;

import java.util.ArrayList;

public class App {
    public static final int playerMovementCoefficient = 70;
    public static final int friendlyBulletMovementCoefficient = 800;
    public static final int nonFriendlyBulletMovementCoefficient = 200;
    private static boolean isFrench = false;
    private static boolean sfxEnabled = true;
    private static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser = null;
    private static ArrayList<Output> questions;

    static {
        loggedInUser = new User("rassa", "R@Ssa1384"); // TODO
        questions = new ArrayList<>();
        questions.add(Output.FatherName);
        questions.add(Output.Turk);
    }

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

    public static void changeLanguage() {
        isFrench = !isFrench;
    }

    public static boolean isFrench() {
        return isFrench;
    }

    public static void setSfxEnabled(boolean sfxEnabled) {
        App.sfxEnabled = sfxEnabled;
    }

    public static boolean isSfxEnabled() {
        return sfxEnabled;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }

    public static void deleteUser(User user) {
        users.remove(user);
    }
}
