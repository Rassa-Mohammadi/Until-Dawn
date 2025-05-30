package com.tilldawn.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.model.enums.MonsterType;

public class Monster {
    private MonsterType type;
    private float spriteTime;
    private Sprite sprite;
    private float posX, posY;
    private float hp;
    private boolean isExploding = false;

    public Monster(MonsterType type, float posX, float posY) {
        this.type = type;
        this.hp = type.getHp();
        this.spriteTime = 0f;
        this.sprite = new Sprite(type.getAnimation().getKeyFrame(0f));
        this.posX = posX;
        this.posY = posY;
    }

    public void updateSprite() {
        if (isDead())
            return;
        Animation<Texture> animation = isExploding? MonsterType.deathAnimation : type.getAnimation();
        spriteTime += Gdx.graphics.getDeltaTime();
        if (animation.isAnimationFinished(spriteTime)) {
            if (isExploding) {
                isExploding = false;
                return;
            }
            spriteTime = 0f;
        }
        sprite.setRegion(animation.getKeyFrame(spriteTime));
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

    public CollisionRect getCollisionRect() {
        return new CollisionRect(posX, posY, sprite.getWidth(), sprite.getHeight());
    }

    public void reduceHp(float amount) {
        this.hp -= amount;
        if (hp <= 0f && !isExploding) {
            isExploding = true;
            spriteTime = 0f;
        }
    }

    public float getHp() {
        return hp;
    }

    public boolean isExploding() {
        return isExploding;
    }

    public void setExploding(boolean exploding) {
        isExploding = exploding;
    }

    public boolean isDead() {
        return hp <= 0f && !isExploding;
    }
}
