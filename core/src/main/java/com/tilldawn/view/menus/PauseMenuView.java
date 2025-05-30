package com.tilldawn.view.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.PauseMenuController;
import com.tilldawn.model.App;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.enums.Output;
import com.tilldawn.view.GameView;

public class PauseMenuView implements Screen {
    private PauseMenuController controller;
    private Texture appBackgroundTexture;
    private Stage stage;
    private Table table;
    private Label menuTitle;
    private TextButton resumeButton;
    private TextButton giveUpButton;

    public PauseMenuView(PauseMenuController pauseMenuController, GameView pausedGame, Skin skin) {
        this.controller = pauseMenuController;
        this.table = new Table();
        this.appBackgroundTexture = new Texture(Gdx.files.internal("Images/Sprite/T_TitleLeaves.png"));
        this.menuTitle = new Label(Output.PauseMenu.getString(), skin);
        menuTitle.setFontScale(2.5f);
        this.resumeButton = new TextButton(Output.Resume.getString(), skin);
        this.giveUpButton = new TextButton(Output.GiveUp.getString(), skin);
        setListeners();
        this.controller.setView(this, pausedGame);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        GameAssetManager.getInstance().addSymmetrical(stage, table, appBackgroundTexture);

        table.add(menuTitle).pad(20).row();
        table.add(resumeButton).pad(20).row();
        table.add(giveUpButton).pad(20).row();

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(delta);
        stage.getBatch().setShader(Main.getBatch().getShader());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    private void setListeners() {
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.isSfxEnabled())
                    GameAssetManager.getInstance().getButtonClick().play(1.0f);
                controller.resume();
            }
        });
        giveUpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.isSfxEnabled())
                    GameAssetManager.getInstance().getButtonClick().play(1.0f);
                controller.giveUp();
            }
        });
    }
}
