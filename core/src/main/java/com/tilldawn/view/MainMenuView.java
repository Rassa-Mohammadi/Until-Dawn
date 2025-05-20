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
    private TextButton backButton;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.menuTitle = new Label(Output.MainMenu.getString(), skin);
        this.menuTitle.setFontScale(2.5f);
        appBackgroundTexture = new Texture(Gdx.files.internal("Images/Sprite/T_TitleLeaves.png"));
        this.settingButton = new TextButton(Output.Settings.getString(), skin);
        this.profileButton = new TextButton(Output.Profile.getString(), skin);
        this.scoreboardButton = new TextButton(Output.Scoreboard.getString(), skin);
        this.backButton = new TextButton(Output.Back.getString(), skin);

        this.controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        GameAssetManager.getGameAssetManager().addSymmetrical(stage, table, appBackgroundTexture);

        table.row().pad(0, 0, 80, 0);
        table.add(menuTitle);
        table.row().pad(10, 0, 10, 0);
        table.add(settingButton).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(profileButton).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(scoreboardButton).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(backButton).width(400);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
//        controller.handleMainMenuButtons();
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
