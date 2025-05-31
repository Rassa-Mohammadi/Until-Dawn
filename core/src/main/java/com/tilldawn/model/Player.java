package com.tilldawn.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends User {
    private Sprite playerSprite;
    private Sprite weaponSprite;
    private float spriteTime = 0f;
    private float invincibleTime = 0f;
    private float posX, posY;
    private int hp;
    private boolean isRunning = false;
    private boolean isAutoAim = false;
    private int ammo;
    private int kills;
    private int xps;
    private int level;
    private float survivedTime;

    public Player(User user) {
        super(user.username, user.password, user.isGuest);
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
        this.ammo = weapon.getMaxAmmo();
        this.kills = 0;
        this.xps = 0;
        this.level = 1;
        this.survivedTime = 0f;
    }

    public void updateSprite() {
        spriteTime += Gdx.graphics.getDeltaTime();
        Animation<Texture> animation = isRunning? hero.getRunAnimation() : hero.getIdleAnimation();
        if (animation.isAnimationFinished(spriteTime))
            spriteTime = 0f;
        playerSprite.setRegion(animation.getKeyFrame(spriteTime));
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
        posX = Math.min(posX, GameAssetManager.backgroundWidth / 2f); // background.png width is 3776
        posX = Math.max(posX, -GameAssetManager.backgroundWidth / 2f);
    }

    public void addY(int speed) {
        posY += Gdx.graphics.getDeltaTime() * speed * App.playerMovementCoefficient;
        posY = Math.min(posY, GameAssetManager.backgroundHeight / 2f); // background.png height is 2688
        posY = Math.max(posY, -GameAssetManager.backgroundHeight / 2f);
    }

    public void setSpriteTime(float spriteTime) {
        this.spriteTime = spriteTime;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public int getAmmo() {
        return ammo;
    }

    public void addAmmo(int amount) {
        ammo += amount;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getHp() {
        return hp;
    }

    public CollisionRect getCollisionRect() {
        return new CollisionRect(
            Gdx.graphics.getWidth() / 2f - playerSprite.getWidth() / 2f + posX,
            Gdx.graphics.getHeight() / 2f - playerSprite.getHeight() / 2f + posY,
            playerSprite.getWidth(), playerSprite.getHeight()
        );
    }

    private void copyUser(User user) {
        this.isGuest = user.isGuest;
        this.weapon = user.weapon;
        this.hero = user.hero;
        this.gameDuration = user.gameDuration;
        this.autoReload = user.autoReload;
    }

    public void reduceHp(int amount) {
        if (invincibleTime > 0f)
            return;
        this.hp -= amount;
    }

    public void setInvincible() {
        if (invincibleTime > 0f)
            return;
        invincibleTime = 1f;
    }

    public void updateInvincibleTime() {
        invincibleTime -= Gdx.graphics.getDeltaTime();
        if (invincibleTime <= 0f)
            invincibleTime = 0f;
    }

    public int getProjectile() {
        return weapon.getProjectile();
    }

    public boolean isAutoAim() {
        return isAutoAim;
    }

    public void setAutoAim(boolean autoAim) {
        isAutoAim = autoAim;
    }

    public void addKill(int amount) {
        kills += amount;
    }

    public int getKills() {
        return kills;
    }

    public void addXp(int amount) {
        xps += amount;
    }

    public int getXps() {
        return xps;
    }

    public boolean hasLevelUp() {
        if (xps >= 20 * level) {
            xps -= 20 * level;
            ++level;
            return true;
        }
        return false;
    }

    public int getLevel() {
        return level;
    }

    public float getSurvivedTime() {
        return survivedTime;
    }

    public void addSurvivedTime(float amount) {
        survivedTime += amount;
    }

    public String getFormatedTime() {
        int seconds = (int) (survivedTime / 60);
        int minutes = (int) (survivedTime % 60);
        String sec = seconds < 10? "0" + seconds: "" + seconds;
        String min = minutes < 10? "0" + minutes: "" + minutes;
        return sec + ":" + min;
    }
}
