package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tilldawn.Main;
import com.tilldawn.controller.menus.EndGameMenuController;
import com.tilldawn.model.*;
import com.tilldawn.view.GameView;
import com.tilldawn.view.XpDrop;
import com.tilldawn.view.menus.EndGameMenuView;
import com.tilldawn.view.menus.PauseMenuView;

import java.util.ArrayList;

public class GameController {
    private GameView view;
    private WorldController worldController;
    private PlayerController playerController;
    private WeaponController weaponController;
    private StatusController statusController;
    private MonsterController monsterController;
    private Player player;

    public void setView(GameView view) {
        this.view = view;
        this.player = new Player(App.getLoggedInUser());
        worldController = new WorldController(player);
        playerController = new PlayerController(player);
        weaponController = new WeaponController(this, player);
        monsterController = new MonsterController(player);
        statusController = new StatusController(player);
    }

    public void updateGame() {
        if (view == null)
            return;
        player.addSurvivedTime(Gdx.graphics.getDeltaTime());
        if (player.getSurvivedTime() >= player.getGameDuration() * 60)
            goToEndGameMenu(true);
        if (player.getHp() <= 0)
            goToEndGameMenu(false);
        checkMonsterCollision();
        checkBulletCollision();
        harvestXp();
        worldController.update();
        monsterController.update();
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

    public MonsterController getMonsterController() {
        return monsterController;
    }

    public Player getPlayer() {
        return player;
    }

    private void goToEndGameMenu(boolean hasWon) {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new EndGameMenuView(
            new EndGameMenuController(),
            GameAssetManager.getInstance().getSkin(),
            hasWon,
            player
        ));
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
        ArrayList<Bullet> removableBullets = new ArrayList<>();
        for (Bullet bullet : weaponController.getBullets()) {
            // bullet collision with monsters
            for (Monster monster : monsterController.getMonsters()) {
                if (bullet.getCollisionRect().collide(monster.getCollisionRect()) && bullet.isFriendly()) {
                    monster.reduceHp(player.getWeapon().getDamage());
                    monster.setKnockback();
                    removableBullets.add(bullet);
                }
            }
            // enemy bullet collision with player
            // TODO:
        }
        weaponController.getBullets().removeAll(removableBullets);
    }

    private void harvestXp() {
        ArrayList<XpDrop> removableXpDrops = new ArrayList<>();
        for (XpDrop xpDrop : monsterController.getXpDrops()) {
            if (xpDrop.getCollisionRect().collide(player.getCollisionRect())) {
                removableXpDrops.add(xpDrop);
                player.addXp(3);
                statusController.getLevelBar().setValue(player.getXps());
                if (player.hasLevelUp()) {
                    if (App.isSfxEnabled())
                        GameAssetManager.getInstance().getLevelUpSfx().play(1f);
                    statusController.getLevelBar().setRange(0, player.getLevel() * 20);
                    statusController.getLevelBar().setValue(player.getXps());
                    // TODO: menu entekhab ability
                }
            }
        }
        monsterController.getXpDrops().removeAll(removableXpDrops);
    }
}
