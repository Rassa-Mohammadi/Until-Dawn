package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.tilldawn.Main;
import com.tilldawn.model.Player;
import com.tilldawn.model.enums.KeyBind;

public class PlayerController {
    private Player player;
    private boolean isFacedRight = true;

    public PlayerController(Player player) {
        this.player = player;
    }

    public void update() {
        player.updateInvincibleTime();
        player.updateSprite();
        handleWalk();
        handleFlip();
        player.getPlayerSprite().draw(Main.getBatch());
    }

    private void handleWalk() {
        player.setRunning(false);
        if (KeyBind.Up.isPressed()) {
            player.addY(player.getSpeed());
            player.setRunning(true);
        }
        if (KeyBind.Down.isPressed()) {
            player.addY(-player.getSpeed());
            player.setRunning(true);
        }
        if (KeyBind.Left.isPressed()) {
            player.addX(-player.getSpeed());
            isFacedRight = false;
            player.setRunning(true);
        }
        if (KeyBind.Right.isPressed()) {
            player.addX(player.getSpeed());
            isFacedRight = true;
            player.setRunning(true);
        }
    }

    private void handleFlip() {
        if (!isFacedRight)
            player.getPlayerSprite().setFlip(true, false);
    }

}
