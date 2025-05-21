package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.SettingMenuController;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Output;
import com.tilldawn.model.music.Track;

public class SettingMenuView implements Screen {
    private SettingMenuController controller;
    private Texture appBackgroundTexture;
    private Stage stage;
    private Table table;
    private Label menuTitle;
    private Slider volumeSlider;
    private SelectBox<String> musicSelectBox;
    private TextButton backButton;

    {
        musicSelectBox = new SelectBox<>(GameAssetManager.getGameAssetManager().getSkin());
        Array<String> array = new Array<>();
        for (Track track : Track.values()) {
            array.add(track.getName());
        }
        musicSelectBox.setItems(array);
        musicSelectBox.setSelected(GameAssetManager.getGameAssetManager().getMusicPlayer().getCurrentTrack().getName());
    }

    public SettingMenuView(SettingMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.appBackgroundTexture = new Texture(Gdx.files.internal("Images/Sprite/T_TitleLeaves.png"));
        this.menuTitle = new Label(Output.SettingMenu.getString(), skin);
        menuTitle.setFontScale(2.5f);
        this.volumeSlider = new Slider(0, 1, 0.01f, false, skin);
        volumeSlider.setValue(GameAssetManager.getGameAssetManager().getMusicPlayer().getVolume());
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

        table.top().add(menuTitle).padTop(20);
        table.row().pad(10, 0, 10, 0);
        table.add(volumeSlider).width(500).row();
        table.row().pad(10, 0, 10, 0);
        table.add(musicSelectBox).width(500).row();
        table.row().pad(10, 0, 10, 0);
        table.add(backButton);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(delta);
        stage.draw();
        controller.handleSettingMenuButtons();
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

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public SelectBox<String> getMusicSelectBox() {
        return musicSelectBox;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
