package com.tilldawn.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tilldawn.model.App;
import com.tilldawn.model.Player;
import com.tilldawn.view.GameView;

public class GameController {
    private GameView view;
    private WorldController worldController;
    private PlayerController playerController;
    private Player player;

    public void setView(GameView view) {
        this.view = view;
        this.player = new Player(App.getLoggedInUser());
        worldController = new WorldController();
        playerController = new PlayerController(this.player);
    }

    public void updateGame() {
        if (view == null)
            return;
        worldController.update(player);
        playerController.update();
    }
}
