package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Main;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.view.AppMenuView;
import com.tilldawn.view.LoginMenuView;
import com.tilldawn.view.MainMenuView;
import com.tilldawn.view.RegisterMenuView;

public class AppMenuController {
    private AppMenuView view;

    public void setView(AppMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if (view == null)
            return;
        Main.getMain().getScreen().dispose();
        if (view.getRegisterButton().isChecked())
            Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        else if (view.getLoginButton().isChecked())
            Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        else if (view.getExitButton().isChecked())
            Gdx.app.exit();
    }
}
