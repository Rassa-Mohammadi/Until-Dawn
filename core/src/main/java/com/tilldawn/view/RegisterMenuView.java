package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.RegisterMenuController;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Result;

public class RegisterMenuView implements Screen {
    public Table table;
    private RegisterMenuController controller;
    private Stage stage;
    private Label menuTitle;
    private Result registerResult;
    private TextField username;
    private TextField password;
    private TextField confirmedPassword;
    private TextButton submitButton;
    private TextButton backButton;

    public RegisterMenuView(RegisterMenuController controller, Skin skin) {
        this.controller = controller;
        this.menuTitle = new Label("Register Menu", skin);
        menuTitle.setFontScale(2.5f);
        this.registerResult = new Result();
        this.username = new TextField("Enter username", skin);
        this.password = new TextField("Enter password", skin);
        this.confirmedPassword = new TextField("Enter password again", skin);
        this.submitButton = new TextButton("Submit", skin);
        this.backButton = new TextButton("Back", skin);
        this.table = new Table();
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
        table.add(registerResult.getMessage());
        table.row().pad(10, 0, 10, 0);
        table.add(username).width(GameAssetManager.fieldLength);
        table.row().pad(10, 0, 10, 0);
        table.add(password).width(GameAssetManager.fieldLength);
        table.row().pad(10, 0, 10, 0);
        table.add(confirmedPassword).width(GameAssetManager.fieldLength);
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
        registerResult.update(delta);
        stage.act(delta);
        stage.draw();
        controller.handleRegisterMenuButtons();
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

    public TextField getPassword() {
        return password;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getConfirmedPassword() {
        return confirmedPassword;
    }

    public TextButton getSubmitButton() {
        return submitButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public void setResult(Result result) {
        registerResult.set(result);
    }
}
