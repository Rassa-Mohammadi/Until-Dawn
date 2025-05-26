package com.tilldawn.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends User {
    private Sprite playerSprite;
    private Sprite weaponSprite;
    private float time = 0f;
    private float posX, posY;
    private float hp;
    private boolean isRunning = false;

    public Player(User user) {
        super(user.username, user.password);
        copyUser(user);
        this.playerSprite = new Sprite(hero.getIdleAnimation().getKeyFrame(0f));
        this.weaponSprite = new Sprite(weapon.getSprite());
        this.posX = 0;
        this.posY = 0;
        playerSprite.setSize(playerSprite.getWidth() * 3.5f, playerSprite.getHeight() * 3.5f);
        playerSprite.setCenter(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        weaponSprite.setCenter(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        weaponSprite.setScale(3f);
        this.hp = hero.getHp();
    }

    public void updateSprite() {
        time += Gdx.graphics.getDeltaTime();
        Animation<Texture> animation = isRunning? hero.getRunAnimation() : hero.getIdleAnimation();
        if (animation.isAnimationFinished(time))
            time = 0f;
        playerSprite.setRegion(animation.getKeyFrame(time));
        playerSprite.setSize(playerSprite.getWidth(), playerSprite.getHeight());
        playerSprite.setCenter(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public Sprite getWeaponSprite() {
        return weaponSprite;
    }

    public int getSpeed() {
        return hero.getSpeed();
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

    public void addX(int speed) {
        posX += Gdx.graphics.getDeltaTime() * speed * App.playerMovementCoefficient;
        posX = Math.min(posX, 1884); // background.png width is 3776
        posX = Math.max(posX, -1884);
    }

    public void addY(int speed) {
        posY += Gdx.graphics.getDeltaTime() * speed * App.playerMovementCoefficient;
        posY = Math.min(posY, 1340); // background.png height is 2688
        posY = Math.max(posY, -1340);
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    private void copyUser(User user) {
        this.isGuest = user.isGuest;
        this.weapon = user.weapon;
        this.hero = user.hero;
        this.gameDuration = user.gameDuration;
        this.autoReload = user.autoReload;
    }
}
