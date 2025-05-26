package com.tilldawn.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tilldawn.Main;
import com.tilldawn.model.App;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Player;
import com.tilldawn.view.GameView;
import com.tilldawn.view.menus.PauseMenuView;

public class GameController {
    private GameView view;
    private WorldController worldController;
    private PlayerController playerController;
    private WeaponController weaponController;
    private Player player;

    public void setView(GameView view) {
        this.view = view;
        this.player = new Player(App.getLoggedInUser());
        worldController = new WorldController(player);
        playerController = new PlayerController(player);
        weaponController = new WeaponController(player);
    }

    public void updateGame() {
        if (view == null)
            return;
        worldController.update();
        playerController.update();
        weaponController.update();
    }

    public void goToPauseMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new PauseMenuView(new PauseMenuController(), view, GameAssetManager.getInstance().getSkin()));
    }
}
