package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tilldawn.Main;
import com.tilldawn.model.Bullet;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Player;
import com.tilldawn.model.enums.KeyBind;

import java.util.ArrayList;

public class WeaponController {
    private Player player;
    private ArrayList<Bullet> bullets;
    private float currentAngle;
    private float reloadTime = 0f;
    private boolean isReloading = false;

    public WeaponController(Player player) {
        this.player = player;
        this.bullets = new ArrayList<>();
        this.currentAngle = calcCurrentAngle();
    }

    public void update() {
        currentAngle = calcCurrentAngle();
        player.getWeaponSprite().setRotation(currentAngle);
        handleReloading();
        updateReloadTime();
        handleShoot();
        updateBullets();
        player.getWeaponSprite().draw(Main.getBatch());
        drawBullets();
    }

    private void handleReloading() {
        if (KeyBind.Reload.isJustPressed())
            startReload();
        else if (player.isAutoReload() && player.getAmmo() == 0 && !isReloading)
            startReload();
    }

    private void startReload() {
        isReloading = true;
        int amount = player.getWeapon().getMaxAmmo() - player.getAmmo();
        reloadTime = player.getWeapon().getReloadTime() * amount / player.getWeapon().getMaxAmmo();
    }

    private void updateReloadTime() {
        if (!isReloading)
            return;
        reloadTime -= Gdx.graphics.getDeltaTime();
        if (reloadTime <= 0f) {
            player.setAmmo(player.getWeapon().getMaxAmmo());
            isReloading = false;
        }
    }

    private void handleShoot() {
        if (player.getAmmo() == 0 || isReloading)
            return;
        if (KeyBind.Shoot.isJustPressed()) {
            bullets.add(new Bullet(
                Gdx.graphics.getWidth() / 2f + player.getX(),
                Gdx.graphics.getHeight() / 2f + player.getY(),
                currentAngle,
                true)
            );
            player.addAmmo(-1);
        }
    }

    private void updateBullets() {
        ArrayList<Bullet> removableBullets = new ArrayList<>();
        for (Bullet bullet : bullets) {
            bullet.move();
            if (bullet.isOut())
                removableBullets.add(bullet);
        }
        bullets.removeAll(removableBullets);
    }

    private void drawBullets() {
        for (Bullet bullet : bullets) {
            bullet.getSprite().setPosition(
                bullet.getX() - player.getX(),
                bullet.getY() - player.getY()
            );
            bullet.getSprite().draw(Main.getBatch());
        }
    }

    private float calcCurrentAngle() {
        float cursorX = Gdx.input.getX();
        float cursorY = Gdx.input.getY();
        return (float) Math.atan2(
            -cursorY + Gdx.graphics.getHeight() / 2f,
            cursorX - Gdx.graphics.getWidth() / 2f
        ) * MathUtils.radiansToDegrees;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
