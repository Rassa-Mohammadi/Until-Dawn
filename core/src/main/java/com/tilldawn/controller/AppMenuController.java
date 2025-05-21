package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Main;
import com.tilldawn.model.App;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.view.AppMenuView;
import com.tilldawn.view.LoginMenuView;
import com.tilldawn.view.RegisterMenuView;
import com.tilldawn.view.SettingMenuView;

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
            Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
        else if (view.getLoginButton().isChecked()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
        else if (view.getChangeLanguageButton().isPressed()) {
            App.changeLanguage();
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new AppMenuView(new AppMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
        else if (view.getExitButton().isChecked()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SettingMenuView(new SettingMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
//            Gdx.app.exit();
        }
    }
}
