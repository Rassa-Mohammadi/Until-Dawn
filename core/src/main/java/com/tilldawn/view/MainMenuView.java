package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.MainMenuController;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Output;

public class MainMenuView implements Screen {
    private MainMenuController controller;
    public Table table;
    private Stage stage;
    private Label menuTitle;
    private Texture appBackgroundTexture;
    private TextButton settingButton;
    private TextButton profileButton;
    private TextButton pregameButton;
    private TextButton scoreboardButton;
    private TextButton hintButton;
    private TextButton logoutButton;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.menuTitle = new Label(Output.MainMenu.getString(), skin);
        this.menuTitle.setFontScale(2.5f);
        appBackgroundTexture = new Texture(Gdx.files.internal("Images/Sprite/T_TitleLeaves.png"));
        this.settingButton = new TextButton(Output.Settings.getString(), skin);
        this.profileButton = new TextButton(Output.Profile.getString(), skin);
        this.pregameButton = new TextButton(Output.Pregame.getString(), skin);
        this.scoreboardButton = new TextButton(Output.Scoreboard.getString(), skin);
        this.hintButton = new TextButton(Output.Hint.getString(), skin);
        this.logoutButton = new TextButton(Output.Logout.getString(), skin);

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
        table.add(settingButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(profileButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(scoreboardButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(pregameButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(hintButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(logoutButton);

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

    public TextButton getSettingButton() {
        return settingButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getPregameButton() {
        return pregameButton;
    }

    public TextButton getScoreboardButton() {
        return scoreboardButton;
    }

    public TextButton getLogoutButton() {
        return logoutButton;
    }
}
