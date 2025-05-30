package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tilldawn.Main;
import com.tilldawn.model.*;
import com.tilldawn.view.GameView;
import com.tilldawn.view.menus.PauseMenuView;

public class GameController {
    private GameView view;
    private WorldController worldController;
    private PlayerController playerController;
    private WeaponController weaponController;
    private StatusController statusController;
    private MonsterController monsterController;
    private Player player;
    private float timePassed = 0f;

    public void setView(GameView view) {
        this.view = view;
        this.player = new Player(App.getLoggedInUser());
        worldController = new WorldController(player);
        playerController = new PlayerController(player);
        weaponController = new WeaponController(player);
        monsterController = new MonsterController(player);
        statusController = new StatusController(player);
    }

    public void updateGame() {
        if (view == null)
            return;
        timePassed += Gdx.graphics.getDeltaTime();
        checkMonsterCollision();
        checkBulletCollision();
        worldController.update();
        monsterController.update(timePassed);
        playerController.update();
        weaponController.update();
        statusController.update();
    }

    public void goToPauseMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new PauseMenuView(new PauseMenuController(), view, GameAssetManager.getInstance().getSkin()));
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    public StatusController getStatusController() {
        return statusController;
    }

    private void checkMonsterCollision() {
        for (Monster monster : monsterController.getMonsters()) {
            if (monster.getCollisionRect().collide(player.getCollisionRect())) {
                player.reduceHp(1);
                player.setInvincible();
            }
        }
    }

    private void checkBulletCollision() {
        for (Bullet bullet : weaponController.getBullets()) {
            for (Monster monster : monsterController.getMonsters()) {
                if (bullet.getCollisionRect().collide(monster.getCollisionRect()))
                    monster.reduceHp(player.getWeapon().getDamage());
            }
        }
    }
}
