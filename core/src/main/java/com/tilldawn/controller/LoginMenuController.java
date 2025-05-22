package com.tilldawn.controller;

import com.badlogic.gdx.graphics.Color;
import com.tilldawn.Main;
import com.tilldawn.model.*;
import com.tilldawn.view.AppMenuView;
import com.tilldawn.view.LoginMenuView;
import com.tilldawn.view.MainMenuView;
import com.tilldawn.view.RecoverPasswordMenu;

public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public void handleLoginMenuButtons() {
        if (view == null)
            return;
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();
        if (view.getBackButton().isPressed()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new AppMenuView(new AppMenuController(), GameAssetManager.getInstance().getSkin()));
        }
        else if (view.getSubmitButton().isPressed()) {
            Result result = login(username, password);
            if (result.hasError())
                view.setResult(result);
            else {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getInstance().getSkin()));
            }
        }
        else if (view.getForgetPasswordButton().isPressed()) {
            User user = App.getUser(username);
            if (user == null)
                view.setResult(new Result(Output.UsernameNotFound.getString(), Color.RED));
            else {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new RecoverPasswordMenu(user, new RecoverPasswordController(), GameAssetManager.getInstance().getSkin()));
            }
        }
    }

    private Result checkUsername(String username) {
        User user = App.getUser(username);
        if (user == null)
            return new Result(Output.UsernameNotFound.getString(), Color.RED);
        return new Result("go to recovery menu", Color.GREEN, false);
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
