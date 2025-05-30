package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.model.App;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Monster;
import com.tilldawn.model.Player;
import com.tilldawn.model.enums.MonsterType;
import com.tilldawn.view.XpDrop;

import java.util.ArrayList;
import java.util.Random;

public class MonsterController {
    private Player player;
    private ArrayList<Monster> monsters;
    private ArrayList<XpDrop> xpDrops;
    private float tentacleMonsterSpawnTimer = 3f;


    public MonsterController(Player player) {
        this.player = player;
        this.monsters = new ArrayList<>();
        this.xpDrops = new ArrayList<>();
        generateTrees();
    }

    public void update(float timePassed) {
        updateTimers();
        generateTentacleMonsters(timePassed);
        generateEyeBat(timePassed);
        cleanMonsters();
        updateSprites();
        moveMonsters();
        draw();
        setTimers();
    }

    public Monster getClosestMonster() {
        Monster closestMonster = null;
        float bestDistance = Float.MAX_VALUE;
        for (Monster monster : monsters) {
            if (monster.getType() == MonsterType.Tree)
                continue;
            float deltaX = player.getX() + Gdx.graphics.getWidth() / 2f - monster.getX();
            float deltaY = player.getY() + Gdx.graphics.getHeight() / 2f - monster.getY();
            float distance = deltaX * deltaX + deltaY * deltaY;
            if (distance < bestDistance) {
                bestDistance = distance;
                closestMonster = monster;
            }
        }
        return closestMonster;
    }

    private void updateTimers() {
        for (Monster monster : monsters) {
            monster.updateKnockback();
        }
        tentacleMonsterSpawnTimer -= Gdx.graphics.getDeltaTime();
    }

    private void setTimers() {
        if (tentacleMonsterSpawnTimer <= 0f)
            tentacleMonsterSpawnTimer = 3f;
    }

    private void cleanMonsters() {
        ArrayList<Monster> removableMonsters = new ArrayList<>();
        for (Monster monster : monsters) {
            if (monster.isDead()) {
                removableMonsters.add(monster);
                player.addKill(1);
                xpDrops.add(new XpDrop(monster.getX(), monster.getY()));
            }
        }
        monsters.removeAll(removableMonsters);
    }

    private void updateSprites() {
        for (Monster monster : monsters) {
            monster.updateSprite();
        }

        for (XpDrop xpDrop : xpDrops) {
            xpDrop.updateSprite();
        }
    }

    private void moveMonsters() {
        for (Monster monster : monsters) {
            Vector2 direction = getMovementDirection(monster);
            monster.move(direction);
        }
    }

    private void draw() {
        // drawing monsters
        for (Monster monster : monsters) {
            monster.getSprite().setPosition(
                monster.getX() - player.getX(),
                monster.getY() - player.getY()
            );
            monster.getSprite().draw(Main.getBatch());
        }
        // drawing xp drops
        for (XpDrop xpDrop : xpDrops) {
            xpDrop.getSprite().setPosition(
                xpDrop.getX() - player.getX(),
                xpDrop.getY() - player.getY()
            );
            xpDrop.getSprite().draw(Main.getBatch());
        }
    }

    private void generateTentacleMonsters(float timePassed) {
        if (tentacleMonsterSpawnTimer > 0f)
            return;
        for (int i = 0; i < timePassed / 30f; i++) {
            Monster tentacle = new Monster(MonsterType.TentacleMonster, 0, 0);
            setPosition(tentacle);
            monsters.add(tentacle);
        }
    }

    private void generateEyeBat(float timePassed) {

    }

    private void generateTrees() {
        float backgroundWidth = GameAssetManager.getInstance().getBackgroundTexture().getWidth();
        float backgroundHeight = GameAssetManager.getInstance().getBackgroundTexture().getHeight();
        float treeWidth = MonsterType.Tree.getAnimation().getKeyFrame(0f).getWidth() * 2;
        float treeHeight = MonsterType.Tree.getAnimation().getKeyFrame(0f).getHeight() * 2;
        Random random = new Random();
        int treeCount = 10 + random.nextInt(10);
        for (int i = 0; i < treeCount; i++) {
            float minX = Gdx.graphics.getWidth() / 2f - backgroundWidth / 2f + treeWidth;
            float maxX = Gdx.graphics.getWidth() / 2f + backgroundWidth / 2f - treeWidth;
            float x = minX + random.nextFloat() * (maxX - minX);

            float minY = Gdx.graphics.getHeight() / 2f - backgroundHeight / 2f + treeHeight;
            float maxY = Gdx.graphics.getHeight() / 2f + backgroundHeight / 2f - treeHeight;
            float y = minY + random.nextFloat() * (maxY - minY);

            Monster tree = new Monster(MonsterType.Tree, x, y);
            monsters.add(tree);
        }
    }

    private Vector2 getMovementDirection(Monster monster) {
        float deltaX = player.getX() + Gdx.graphics.getWidth() / 2f - monster.getX();
        float deltaY = player.getY() + Gdx.graphics.getHeight() / 2f - monster.getY();
        Vector2 direction = new Vector2(deltaX, deltaY);
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        direction.x /= distance;
        direction.y /= distance;
        direction.x *= monster.getType().getSpeed();
        direction.y *= monster.getType().getSpeed();
        direction.x *= Gdx.graphics.getDeltaTime();
        direction.y *= Gdx.graphics.getDeltaTime();
        direction.x *= App.monsterMovementCoefficient;
        direction.y *= App.monsterMovementCoefficient;
        return direction;
    }

    private void setPosition(Monster monster) {
        float backgroundWidth = GameAssetManager.getInstance().getBackgroundTexture().getWidth();
        float backgroundHeight = GameAssetManager.getInstance().getBackgroundTexture().getHeight();
        float monsterWidth = monster.getSprite().getWidth();
        float monsterHeight = monster.getSprite().getHeight();
        Random random = new Random();
        int side = random.nextInt(4); // 0: up, 1: down, 2: right, 3: left
        float x, y;
        if (side == 0) {
            y = Gdx.graphics.getHeight() / 2f + backgroundHeight / 2f - monsterHeight;
            float minX = Gdx.graphics.getWidth() / 2f - backgroundWidth / 2f + monsterWidth;
            float maxX = Gdx.graphics.getWidth() / 2f + backgroundWidth / 2f - monsterWidth;
            x = minX + random.nextFloat() * (maxX - minX);
        }
        else if (side == 1) {
           y = Gdx.graphics.getHeight() / 2f - backgroundHeight / 2f + monsterHeight;
            float minX = Gdx.graphics.getWidth() / 2f - backgroundWidth / 2f + monsterWidth;
            float maxX = Gdx.graphics.getWidth() / 2f + backgroundWidth / 2f - monsterWidth;
            x = minX + random.nextFloat() * (maxX - minX);
        }
        else if (side == 2) {
            x = Gdx.graphics.getWidth() / 2f + backgroundWidth / 2f - monsterWidth;
            float minY = Gdx.graphics.getHeight() / 2f - backgroundHeight / 2f + monsterHeight;
            float maxY = Gdx.graphics.getHeight() / 2f + backgroundHeight / 2f - monsterHeight;
            y = minY + random.nextFloat() * (maxY - minY);
        }
        else {
            x = Gdx.graphics.getWidth() / 2f - backgroundWidth / 2f + monsterWidth;
            float minY = Gdx.graphics.getHeight() / 2f - backgroundHeight / 2f + monsterHeight;
            float maxY = Gdx.graphics.getHeight() / 2f + backgroundHeight / 2f - monsterHeight;
            y = minY + random.nextFloat() * (maxY - minY);
        }
        monster.setPosX(x);
        monster.setPosY(y);
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public ArrayList<XpDrop> getXpDrops() {
        return xpDrops;
    }
}
