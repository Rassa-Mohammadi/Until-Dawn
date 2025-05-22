package com.tilldawn.controller;

import com.badlogic.gdx.graphics.Color;
import com.tilldawn.Main;
import com.tilldawn.model.*;
import com.tilldawn.view.ChangePasswordMenuView;
import com.tilldawn.view.ProfileMenuView;

public class ChangePasswordMenuController {
    private ChangePasswordMenuView view;

    public void setView(ChangePasswordMenuView view) {
        this.view = view;
    }

    public Result changePassword(String oldPassword, String newPassword) {
        if (!App.getLoggedInUser().getPassword().equals(oldPassword))
            return new Result(Output.IncorrectPassword.getString(), Color.RED);
        if (oldPassword.equals(newPassword))
            return new Result(Output.SamePassword.getString(), Color.RED);
        Result result = User.isPasswordWeak(newPassword);
        if (result.hasError())
            return result;
        back();
        return new Result("Password changed successfully", Color.GREEN, false);
    }

    public void back() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getInstance().getSkin()));
    }
}
