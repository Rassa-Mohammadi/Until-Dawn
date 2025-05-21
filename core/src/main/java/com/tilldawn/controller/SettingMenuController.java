package com.tilldawn.controller;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tilldawn.Main;
import com.tilldawn.model.GameAssetManager;
import com.tilldawn.model.music.Track;
import com.tilldawn.view.MainMenuView;
import com.tilldawn.view.SettingMenuView;

public class SettingMenuController {
    private SettingMenuView view;

    public void setView(SettingMenuView view) {
        this.view = view;
        setListeners();
    }

    public void handleSettingMenuButtons() {
        if (view == null)
            return;
        if (view.getBackButton().isPressed()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    private void setListeners() {
        Slider volumeSlider = view.getVolumeSlider();
        volumeSlider.addListener(event -> {
            GameAssetManager.getGameAssetManager().getMusicPlayer().setVolume(volumeSlider.getValue());
            return false;
        });

        SelectBox<String> selector = view.getMusicSelectBox();
        selector.addListener(event -> {
            if (event instanceof ChangeListener.ChangeEvent) {
                GameAssetManager.getGameAssetManager().getMusicPlayer().setCurrentTrack(
                    Track.getByName(selector.getSelected())
                );
            }
            return false;
        });
    }
}
