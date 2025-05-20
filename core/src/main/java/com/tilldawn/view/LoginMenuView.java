package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.LoginMenuController;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Result;

public class LoginMenuView implements Screen {
    private LoginMenuController controller;
    public Table table;
    private Stage stage;
    private Label menuTitle;
    private Result loginResult;
    private TextField usernameField;
    private TextField passwordField;
    private TextButton submitButton;
    private TextButton backButton;

    public LoginMenuView(LoginMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.menuTitle = new Label("Login Menu", skin);
        menuTitle.setFontScale(2.5f);
        loginResult = new Result();
        usernameField = new TextField("Enter username", skin);
        passwordField = new TextField("Enter password", skin);
        submitButton = new TextButton("Submit", skin);
        this.backButton = new TextButton("Back", skin);

        this.controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.row().pad(0, 0, 140, 0);
        table.add(menuTitle);
        table.row().pad(10, 0, 10, 0);
        table.add(loginResult.getMessage());
        table.row().pad(10, 0, 10, 0);
        table.add(usernameField).width(GameAssetManager.fieldLength);
        table.row().pad(10, 0, 10, 0);
        table.add(passwordField).width(GameAssetManager.fieldLength);
        table.row().pad(10, 0, 10, 0);
        table.add(submitButton).width(GameAssetManager.fieldLength);
        table.row().pad(10, 0, 10, 0);
        table.add(backButton).width(GameAssetManager.backButtonLength);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        loginResult.update(delta);
        stage.act(delta);
        stage.draw();
        controller.handleLoginMenuButtons();
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
}
