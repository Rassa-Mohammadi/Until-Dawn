package com.tilldawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Main;
import com.tilldawn.controller.ProfileMenuController;
import com.tilldawn.model.App;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.Output;

public class ProfileMenuView implements Screen {
    private ProfileMenuController controller;
    private Stage stage;
    private Table table;
    private Texture appBackgroundTexture;
    private Label menuTitle;
    private TextButton changeUsername;
    private TextButton changePassword;
    private TextButton deleteAccountButton;
    private TextButton avatarButton;
    private TextButton backButton;

    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.appBackgroundTexture = new Texture(Gdx.files.internal("Images/Sprite/T_TitleLeaves.png"));
        this.menuTitle = new Label(Output.ProfileMenu.getString(), skin);
        menuTitle.setFontScale(2.5f);
        this.changeUsername = new TextButton(Output.ChangeUsername.getString(), skin);
        this.changePassword = new TextButton(Output.ChangePassword.getString(), skin);
        this.avatarButton = new TextButton(Output.Avatar.getString(), skin);
        this.deleteAccountButton = new TextButton(Output.DeleteAcount.getString(), skin);
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

        Table buttonTable = new Table();
        buttonTable.add(changeUsername).pad(10).fillX().row();
        buttonTable.add(changePassword).pad(10).fillX().row();
        buttonTable.add(deleteAccountButton).pad(10).fillX().row();
        buttonTable.add(avatarButton).pad(10).fillX().row();
        buttonTable.add(backButton).pad(10).row();
        Table contentTable = new Table();
        App.getLoggedInUser().getInfo(contentTable);

        table.add(menuTitle).colspan(2).center().pad(30).row();
        table.add(buttonTable).left().pad(20);
        table.add(contentTable).right().pad(20);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(delta);
        stage.draw();
        controller.handleProfileMenuButtons();
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

    public TextButton getChangeUsername() {
        return changeUsername;
    }

    public TextButton getChangePassword() {
        return changePassword;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextButton getAvatarButton() {
        return avatarButton;
    }

    public TextButton getDeleteAccountButton() {
        return deleteAccountButton;
    }
}
