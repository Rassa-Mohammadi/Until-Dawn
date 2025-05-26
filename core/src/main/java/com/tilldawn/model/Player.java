package com.tilldawn.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends User {
    private Sprite playerSprite;
    private float time = 0f;
    private float posX, posY;
    private float hp;

    public Player(User user) {
        super(user.username, user.password);
        this.playerSprite = new Sprite(hero.getIdleAnimation().getKeyFrame(0f));
        this.posX = 0;
        this.posY = 0;
        playerSprite.setSize(playerSprite.getWidth() * 2.5f, playerSprite.getHeight() * 2.5f);
        playerSprite.setCenter(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        this.hp = hero.getHp();
    }

    public void updateSprite() {
        time += Gdx.graphics.getDeltaTime();
        Animation<Texture> idleAnimation = hero.getIdleAnimation();
        if (idleAnimation.isAnimationFinished(time))
            time = 0f;
        playerSprite.setRegion(hero.getIdleAnimation().getKeyFrame(time));
        playerSprite.setSize(playerSprite.getWidth(), playerSprite.getHeight());
        playerSprite.setCenter(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
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
        posX += Gdx.graphics.getDeltaTime() * speed * 100;
        posX = Math.min(posX, 1884); // background.png width is 3776
        posX = Math.max(posX, -1884);
    }

    public void addY(int speed) {
        posY += Gdx.graphics.getDeltaTime() * speed * 100;
        posY = Math.min(posY, 1340); // background.png height is 2688
        posY = Math.max(posY, -1340);
    }
}
