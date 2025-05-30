package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Main;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Player;

public class StatusController { // handles ammo, hearts, timer and ...
    private Player player;
    private Sprite shadowSprite;
    private Texture heartTexture;
    private float heartTimer = 0f;
    private Animation<Texture> heart;

    public StatusController(Player player) {
        this.player = player;
        this.shadowSprite = new Sprite(new Texture("Images/Sprite/Hale.png"));
        shadowSprite.setCenter(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        shadowSprite.setColor(shadowSprite.getColor().r, shadowSprite.getColor().g, shadowSprite.getColor().b, 0.25f);
        this.heart = new Animation<> (
            0.5f,
            new Texture("Images/Sprite/HeartAnimation_0.png"),
            new Texture("Images/Sprite/HeartAnimation_1.png"),
            new Texture("Images/Sprite/HeartAnimation_2.png")
        );
        this.heart.setPlayMode(Animation.PlayMode.LOOP);
        this.heartTexture = heart.getKeyFrame(0f);
    }

    public void update() {
        updateHeartTimer();
        drawShadow();
        drawAmmo();
        drawHearts();
    }

    private void drawShadow() {
        shadowSprite.draw(Main.getBatch());
    }

    private void drawAmmo() {
        for (int i = 0; i < player.getAmmo(); i++) {
            Sprite ammoSprite = new Sprite(GameAssetManager.getInstance().getAmmoTexture());
            ammoSprite.setPosition(10 + (ammoSprite.getWidth() - 10) * i, Gdx.graphics.getHeight() - ammoSprite.getHeight() - 90);
            ammoSprite.draw(Main.getBatch());
        }
    }

    private void drawHearts() {
        for (int i = 0; i < player.getHp(); i++) {
            Sprite heartSprite = new Sprite(heartTexture);
            heartSprite.setScale(2f);
            heartSprite.setPosition(20 + heartSprite.getWidth() * i, Gdx.graphics.getHeight() - heartSprite.getHeight() - 30);
            heartSprite.draw(Main.getBatch());
        }
        for (int i = player.getHp(); i < player.getHero().getHp(); i++) {
            Sprite heartSprite = new Sprite(new Texture("Images/Sprite/HeartAnimation_3.png"));
            heartSprite.setScale(2f);
            heartSprite.setPosition(20 + heartSprite.getWidth() * i, Gdx.graphics.getHeight() - heartSprite.getHeight() - 30);
            heartSprite.draw(Main.getBatch());
        }
    }

    private void updateHeartTimer() {
        heartTimer += Gdx.graphics.getDeltaTime();
        if (heart.isAnimationFinished(heartTimer))
            heartTimer = 0f;
        heartTexture = heart.getKeyFrame(heartTimer);
    }
}
