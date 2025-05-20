package com.tilldawn.controller;

import com.badlogic.gdx.graphics.Color;
import com.tilldawn.Main;
import com.tilldawn.model.*;
import com.tilldawn.view.AppMenuView;
import com.tilldawn.view.LoginMenuView;
import com.tilldawn.view.MainMenuView;

public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public void handleLoginMenuButtons() {
        if (view == null)
            return;
        if (view.getBackButton().isPressed()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new AppMenuView(new AppMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
        else if (view.getSubmitButton().isPressed()) {
            String username = view.getUsernameField().getText();
            String password = view.getPasswordField().getText();
            Result result = login(username, password);
            if (result.getError())
                view.setResult(result);
            else {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    private Result login(String username, String password) {
        User user = App.getUser(username);
        if (user == null)
            return new Result(Output.UsernameNotFound.getString(), Color.RED);
        if (!user.getPassword().equals(password))
            return new Result(Output.IncorrectPassword.getString(), Color.RED);
        App.setLoggedInUser(user);
        return new Result("Login Successful", Color.GREEN, false);
    }
}
