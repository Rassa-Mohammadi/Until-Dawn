package com.tilldawn.model.enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public enum MonsterType {
    Tree(new Animation<>(
        1f,
        new Texture("Images/Sprite/T_TreeMonster_0.png"),
        new Texture("Images/Sprite/T_TreeMonster_1.png"),
        new Texture("Images/Sprite/T_TreeMonster_2.png")
    ), 1),
    TentacleMonster(new Animation<>(
        0.12f,
        new Texture("Images/Sprite/BrainMonster_0.png"),
        new Texture("Images/Sprite/BrainMonster_1.png"),
        new Texture("Images/Sprite/BrainMonster_2.png"),
        new Texture("Images/Sprite/BrainMonster_3.png")
    ), 25),
    EyeBat(new Animation<>(
        0.12f,
        new Texture("Images/Sprite/T_EyeBat_0.png"),
        new Texture("Images/Sprite/T_EyeBat_1.png"),
        new Texture("Images/Sprite/T_EyeBat_2.png")
    ), 50);

    public static final Animation<Texture> deathAnimation = new Animation<>(
        0.12f,
        new Texture("Images/Sprite/DeathFX_0.png"),
        new Texture("Images/Sprite/DeathFX_1.png"),
        new Texture("Images/Sprite/DeathFX_2.png"),
        new Texture("Images/Sprite/DeathFX_3.png")
    );
    private final Animation<Texture> animation;
    private final float hp;

    MonsterType(Animation<Texture> animation, float hp) {
        this.animation = animation;
        this.hp = hp;
        this.animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }


    public float getHp() {
        return hp;
    }
}
