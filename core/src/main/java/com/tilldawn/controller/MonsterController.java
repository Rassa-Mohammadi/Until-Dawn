package com.tilldawn.controller;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Main;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Monster;
import com.tilldawn.model.Player;
import com.tilldawn.model.enums.MonsterType;

import java.util.ArrayList;
import java.util.Random;

public class MonsterController {
    private Player player;
    private ArrayList<Monster> monsters;
    private float tentacleMonsterSpawnTimer = 3f;


    public MonsterController(Player player) {
        this.player = player;
        this.monsters = new ArrayList<>();
        generateTrees();
    }

    public void update(float timePassed) {
        updateTimers();
        generateTentacleMonsters();
        cleanMonsters();
        updateSprites();
        drawMonsters();
        setTimers();
    }

    private void updateTimers() {
        tentacleMonsterSpawnTimer -= Gdx.graphics.getDeltaTime();
    }

    private void setTimers() {
        if (tentacleMonsterSpawnTimer <= 0f)
            tentacleMonsterSpawnTimer = 3f;
    }

    private void cleanMonsters() {
        ArrayList<Monster> removableMonsters = new ArrayList<>();
        for (Monster monster : monsters) {
            if (monster.isDead())
                removableMonsters.add(monster);
        }
        monsters.removeAll(removableMonsters);
    }

    private void updateSprites() {
        for (Monster monster : monsters) {
            monster.updateSprite();
        }
    }

    private void drawMonsters() {
        for (Monster monster : monsters) {
            monster.getSprite().setPosition(
                monster.getX() - player.getX(),
                monster.getY() - player.getY()
            );
            monster.getSprite().draw(Main.getBatch());
        }
    }

    private void generateTentacleMonsters() {
        if (tentacleMonsterSpawnTimer > 0f)
            return;

    }

    private void generateTrees() {
        float backgroundWidth = GameAssetManager.getInstance().getBackgroundTexture().getWidth();
        float backgroundHeight = GameAssetManager.getInstance().getBackgroundTexture().getHeight();
        float treeWidth = MonsterType.Tree.getAnimation().getKeyFrame(0f).getWidth();
        float treeHeight = MonsterType.Tree.getAnimation().getKeyFrame(0f).getHeight();
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

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
}
