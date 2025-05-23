package com.tilldawn.view.menus.hintmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.controller.menus.hintmenu.CheatCodeMenuController;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.enums.Output;

public class CheatCodeMenuView implements Screen {
    private CheatCodeMenuController controller;
    private Stage stage;
    private Table table;
    private Texture appBackgroundTexture;
    private Label menuTitle;

    public CheatCodeMenuView(CheatCodeMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.appBackgroundTexture = new Texture(Gdx.files.internal("Images/Sprite/T_TitleLeaves.png"));
        this.menuTitle = new Label(Output.CheatCodesMenu.getString(), skin);
        menuTitle.setFontScale(2.5f);
        setListeners();
        this.controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        GameAssetManager.getInstance().addSymmetrical(stage, table, appBackgroundTexture);

        table.setFillParent(true);
        table.center();
        table.add(menuTitle).center().pad(20).row();
        stage.addActor(table);
    }

    @Override
    public void render(float v) {

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

    private void setListeners() {

    }
}
