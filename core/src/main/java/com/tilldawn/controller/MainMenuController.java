package com.tilldawn.controller;

import com.tilldawn.Main;
import com.tilldawn.model.App;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.view.AppMenuView;
import com.tilldawn.view.MainMenuView;
import com.tilldawn.view.ProfileMenuView;
import com.tilldawn.view.SettingMenuView;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void goToProfile() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getInstance().getSkin()));
    }

    public void goToSetting() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new SettingMenuView(new SettingMenuController(), GameAssetManager.getInstance().getSkin()));
    }

    public void logout() {
        App.setLoggedInUser(null);
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new AppMenuView(new AppMenuController(), GameAssetManager.getInstance().getSkin()));
    }
}
