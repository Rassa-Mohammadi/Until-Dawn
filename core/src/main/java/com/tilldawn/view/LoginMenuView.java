package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.LoginMenuController;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Output;
import com.tilldawn.model.Result;

public class LoginMenuView implements Screen {
    private LoginMenuController controller;
    private Texture appBackgroundTexture;
    public Table table;
    private Stage stage;
    private Label menuTitle;
    private Result loginResult;
    private TextField usernameField;
    private TextField passwordField;
    private TextButton submitButton;
    private TextButton forgetPasswordButton;
    private TextButton backButton;

    public LoginMenuView(LoginMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.menuTitle = new Label(Output.LoginMenu.getString(), skin);
        menuTitle.setFontScale(2.5f);
        appBackgroundTexture = new Texture(Gdx.files.internal("Images/Sprite/T_TitleLeaves.png"));
        loginResult = new Result();
        usernameField = new TextField("", skin);
        usernameField.setMessageText(Output.EnterUsername.getString());
        passwordField = new TextField("", skin);
        passwordField.setMessageText(Output.EnterPassword.getString());
        submitButton = new TextButton(Output.Submit.getString(), skin);
        forgetPasswordButton = new TextButton(Output.ForgotPassword.getString(), skin);
        this.backButton = new TextButton(Output.Back.getString(), skin);

        this.controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        GameAssetManager.getInstance().addSymmetrical(stage, table, appBackgroundTexture);

        table.top().add(menuTitle).padTop(20);
        table.row().pad(10, 0, 10, 0);
        table.add(loginResult.getMessage());
        table.row().pad(10, 0, 10, 0);
        table.add(usernameField).width(GameAssetManager.fieldLength);
        table.row().pad(10, 0, 10, 0);
        table.add(passwordField).width(GameAssetManager.fieldLength);
        table.row().pad(10, 0, 10, 0);
        table.add(forgetPasswordButton);
        table.row().pad(10, 0, 10, 0);
        table.add(submitButton);
        table.row().pad(10, 0, 10, 0);
        table.add(backButton);

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

    public Result getLoginResult() {
        return loginResult;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextButton getSubmitButton() {
        return submitButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public void setResult(Result result) {
        loginResult.set(result);
    }

    public TextButton getForgetPasswordButton() {
        return forgetPasswordButton;
    }
}
