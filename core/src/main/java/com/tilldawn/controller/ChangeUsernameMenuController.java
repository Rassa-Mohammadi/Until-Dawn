package com.tilldawn.controller;

import com.badlogic.gdx.graphics.Color;
import com.tilldawn.Main;
import com.tilldawn.model.*;
import com.tilldawn.view.ChangeUsernameMenuView;
import com.tilldawn.view.ProfileMenuView;

public class ChangeUsernameMenuController {
    private ChangeUsernameMenuView view;

    public void setView(ChangeUsernameMenuView view) {
        this.view = view;
    }

    public Result changeUsername(String newUsername) {
        User user = App.getLoggedInUser();
        if (App.getUser(newUsername) != null)
            return new Result(Output.UsernameExists.getString(), Color.RED);
        user.setUsername(newUsername);
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getInstance().getSkin()));
        return new Result("Username changed successfully", Color.GREEN, false);
    }

    public void back() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getInstance().getSkin()));
    }
}
