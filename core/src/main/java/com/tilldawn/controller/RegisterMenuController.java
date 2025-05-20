package com.tilldawn.controller;

import com.badlogic.gdx.graphics.Color;
import com.tilldawn.Main;
import com.tilldawn.model.App;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Result;
import com.tilldawn.model.User;
import com.tilldawn.view.AppMenuView;
import com.tilldawn.view.GameView;
import com.tilldawn.view.MainMenuView;
import com.tilldawn.view.RegisterMenuView;

public class RegisterMenuController {
    private RegisterMenuView view;

    public void setView(RegisterMenuView view) {
        this.view = view;
    }

    public void handleRegisterMenuButtons() {
        if (view == null)
            return;
        if (view.getBackButton().isChecked()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new AppMenuView(new AppMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
        else if (view.getSubmitButton().isPressed()) {
            String username = view.getUsername().getText();
            String password = view.getPassword().getText();
            String confirmedPassword = view.getConfirmedPassword().getText();
            Result result = register(username, password, confirmedPassword);
            if (result.getError())
                view.setResult(result);
            else {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new AppMenuView(new AppMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    private Result register(String username, String password, String confirmedPassword) {
        if (App.getUser(username) != null)
            return new Result("Username already exists", Color.RED);
        if (!password.equals(confirmedPassword))
            return new Result("Reentered password doesn't match", Color.RED);
        Result passwordResult = isPasswordWeak(password);
        if (passwordResult.getError())
            return passwordResult;
        User user = new User(username, password);
        App.addUser(user);
        return new Result("Successful registration", Color.GREEN, false);
    }

    private Result isPasswordWeak(String password) {
        if (password.length() < 8)
            return new Result("Password must be at least 8 characters", Color.RED);
        if (!password.matches(".*[@_()*&%$#].*"))
            return new Result("Password must contain special characters", Color.RED);
        if (!password.matches(".*[0-9].*"))
            return new Result("Password must contain numbers", Color.RED);
        if (!password.matches(".*[A-Z].*"))
            return new Result("Password must contain capital letters", Color.RED);
        return new Result("Strong password", Color.GREEN, false);
    }
}
