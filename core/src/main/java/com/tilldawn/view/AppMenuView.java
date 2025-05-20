package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.AppMenuController;
import com.tilldawn.model.GameAssetManager;

public class AppMenuView implements Screen {
    public Table table;
    private Stage stage;
    private final Texture gameTitleTexture;
    private final Image gameTitleImage;
    private final Texture appBackgroundTexture;
    private final Image appBackgroundImage;
    private final TextButton registerButton;
    private final TextButton loginButton;
    private final TextButton exitButton;
    private final AppMenuController controller;

    public AppMenuView(AppMenuController controller, Skin skin) {
        this.controller = controller;
        this.gameTitleTexture = new Texture(Gdx.files.internal("Images/Sprite/T_20Logo.png"));
        this.gameTitleImage = new Image(gameTitleTexture);
        this.appBackgroundTexture = new Texture(Gdx.files.internal("Images/Sprite/T_TitleLeaves.png"));
        this.appBackgroundImage = new Image(appBackgroundTexture);
        this.registerButton = new TextButton("Register", skin);
        this.loginButton = new TextButton("Login", skin);
        this.exitButton = new TextButton("Exit", skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        GameAssetManager.getGameAssetManager().addSymmetrical(stage, table, appBackgroundTexture);

        gameTitleImage.setScaling(Scaling.fit);
        table.add(gameTitleImage).width(400).height(200).pad(10);

        table.row().pad(10, 0, 10, 0);
        table.add(registerButton).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(loginButton).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(exitButton).width(400);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleMainMenuButtons();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    private void addLeaves() {
        float imageRatio = appBackgroundImage.getWidth() / appBackgroundImage.getHeight();
        float fitHeight = stage.getHeight();
        float fitWidth = fitHeight * imageRatio;
        // left side leaves
        appBackgroundImage.setPosition(0, 0);
        appBackgroundImage.setSize(fitWidth, fitHeight);
        table.addActor(appBackgroundImage);

        // right side leaves
        TextureRegion region = new TextureRegion(appBackgroundTexture);
        region.flip(true, false);
        Image image = new Image(region);
        image.setPosition(stage.getWidth() - fitWidth, 0);
        image.setSize(fitWidth, fitHeight);
        table.addActor(image);
    }
}
