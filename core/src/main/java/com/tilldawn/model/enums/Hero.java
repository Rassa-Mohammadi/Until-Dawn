package com.tilldawn.model.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.w3c.dom.Text;

public enum Hero {
    Shana(4, 4, new Texture(Gdx.files.internal("Images/Texture2D/T_Shana_Portrait.png"))),
    Diamond(7, 1, new Texture(Gdx.files.internal("Images/Texture2D/T_Diamond_Portrait.png"))),
    Scarlet(3, 5, new Texture(Gdx.files.internal("Images/Texture2D/T_Scarlett_Portrait.png"))),
    Lilith(5, 3, new Texture(Gdx.files.internal("Images/Texture2D/T_Lilith_Portrait.png"))),
    Dasher(2, 10, new Texture(Gdx.files.internal("Images/Texture2D/T_Dasher_Portrait.png")));

    private final int hp;
    private final int speed;
    private final Texture texture;

    Hero(int hp, int speed, Texture texture) {
        this.hp = hp;
        this.speed = speed;
        this.texture = texture;
    }

    public String getName() {
        return this.name();
    }

    public Texture getTexture() {
        return texture;
    }

    public static Texture getTexture(String heroName) {
        for (Hero hero : values()) {
            if (hero.getName().equals(heroName))
                return hero.texture;
        }
        return null;
    }

    public static Hero getHero(String heroName) {
        for (Hero hero : values()) {
            if (hero.getName().equals(heroName))
                return hero;
        }
        return null;
    }
}
