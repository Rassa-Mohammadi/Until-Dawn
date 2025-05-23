package com.tilldawn.controller;

import com.tilldawn.Main;
import com.tilldawn.model.App;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.view.*;

public class ProfileMenuController {
    private ProfileMenuView view;

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void goToChangeUsernameMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ChangeUsernameMenuView(new ChangeUsernameMenuController(), GameAssetManager.getInstance().getSkin()));
    }

    public void goToChangePasswordMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ChangePasswordMenuView(new ChangePasswordMenuController(), GameAssetManager.getInstance().getSkin()));
    }

    public void goToAvatarMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new AvatarMenuView(new AvatarMenuController(), GameAssetManager.getInstance().getSkin()));
    }

    public void back() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getInstance().getSkin()));
    }

    public void deleteAccount() {
        App.deleteUser(App.getLoggedInUser());
        App.setLoggedInUser(null);
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new AppMenuView(new AppMenuController(), GameAssetManager.getInstance().getSkin()));
    }
}
