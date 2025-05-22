package com.tilldawn.controller;

import com.badlogic.gdx.graphics.Color;
import com.tilldawn.Main;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Output;
import com.tilldawn.model.Result;
import com.tilldawn.model.User;
import com.tilldawn.view.LoginMenuView;
import com.tilldawn.view.RecoverPasswordMenu;

public class RecoverPasswordController {
    private RecoverPasswordMenu view;
    private User user = null;

    public void setView(RecoverPasswordMenu view) {
        this.view = view;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void handleRecoverPasswordMenuButtons() {
        if (view == null)
            return;
        if (view.getBackButton().isPressed()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getInstance().getSkin()));
        }
        else if (view.getSubmitButton().isPressed()) {
            String answer = view.getQuestionAnswer().getText();
            String newPassword = view.getNewPassword().getText();
            Result result = recoverPassword(answer, newPassword);
            if (result.hasError())
                view.setResult(result);
            else {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getInstance().getSkin()));
            }
        }
    }

    private Result recoverPassword(String answer, String newPassword) {
        if (!user.getSecurityQuestion().getAnswer().equals(answer))
            return new Result(Output.IncorrectAnswer.getString(), Color.RED);
        Result passwordResult = User.isPasswordWeak(newPassword);
        if (passwordResult.hasError())
            return passwordResult;
        user.setPassword(newPassword);
        return new Result("Password changed successfully", Color.GREEN, false);
    }
}
