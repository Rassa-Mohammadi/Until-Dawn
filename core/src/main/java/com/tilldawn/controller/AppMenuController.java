package com.tilldawn.controller;

import com.tilldawn.Main;
import com.tilldawn.model.App;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.view.*;

public class AppMenuController {
    private AppMenuView view;

    public void setView(AppMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if (view == null)
            return;
        if (view.getRegisterButton().isChecked()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getInstance().getSkin()));
        }
        else if (view.getLoginButton().isChecked()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getInstance().getSkin()));
        }
        else if (view.getChangeLanguageButton().isPressed()) {
            App.changeLanguage();
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new AppMenuView(new AppMenuController(), GameAssetManager.getInstance().getSkin()));
        }
        else if (view.getExitButton().isChecked()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SettingMenuView(new SettingMenuController(), GameAssetManager.getInstance().getSkin()));
//            Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getInstance().getSkin()));
//            Main.getMain().setScreen(new AvatarMenuView(new AvatarMenuController(), GameAssetManager.getInstance().getSkin()));
//            Gdx.app.exit();
        }
    }
}
