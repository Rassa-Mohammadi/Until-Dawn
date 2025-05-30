package com.tilldawn.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.tilldawn.model.music.MusicPlayer;
import com.tilldawn.model.music.Track;

class SFXManager {
    private Sound buttonClick;

    {
        buttonClick = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/UI Click 36.wav"));
    }

    public Sound getButtonClick() {
        return buttonClick;
    }
}

public class GameAssetManager {
    public final static int fieldLength = 400;
    public final static int backButtonLength = 200;
    public final static int selectBoxLength = 600;
    public final static int backgroundWidth = 3768;
    public final static int backgroundHeight = 2680;
    private static GameAssetManager instance;
    private final Texture backgroundTexture;
    private final Texture cursorTexture;
    private final Texture bulletTexture;
    private final Texture ammoTexture;
    private final Texture aimTexture;
    private final Texture killTexture;
    private final Animation<Texture> xpDropAnimation;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private Array<String> avatarFiles;
    private MusicPlayer musicPlayer = new MusicPlayer();
    private SFXManager sfxManager = new SFXManager();

    {
        avatarFiles = new Array<>();
        FileHandle avatarDirectory = Gdx.files.internal("assets/avatars");
        for (FileHandle handle : avatarDirectory.list()) {
            avatarFiles.add(handle.path());
        }
    }

    private GameAssetManager() {
        backgroundTexture = new Texture("Images/Texture2D/background.png");
        cursorTexture = new Texture("Images/Texture2D/T_Cursor.png");
        bulletTexture = new Texture("Images/Texture2D/bullet.png");
        ammoTexture = new Texture("Images/Texture2D/T_AmmoIcon.png");
        aimTexture = new Texture("Images/Sprite/T_HitMarkerFX_2.png");
        killTexture = new Texture("Images/Sprite/T_HitMarkerFX_1.png");
        xpDropAnimation = new Animation<>(0.3f,
            new Texture("Images/Sprite/T_ChargeUp_0.png"),
            new Texture("Images/Sprite/T_ChargeUp_1.png")
        );
        musicPlayer.setCurrentTrack(Track.LuxAeterna);
    }

    public static GameAssetManager getInstance() {
        if (instance == null)
            instance = new GameAssetManager();
        return instance;
    }

    public Skin getSkin() {
        return skin;
    }

    public void addSymmetrical(Stage stage, Table table, Texture texture) {
        Image image = new Image(texture);
        float imageRatio = image.getWidth() / image.getHeight();
        float fitHeight = stage.getHeight();
        float fitWidth = fitHeight * imageRatio;
        // left side image
        image.setPosition(0, 0);
        image.setSize(fitWidth, fitHeight);
        table.addActor(image);

        // right side leaves
        TextureRegion region = new TextureRegion(texture);
        region.flip(true, false);
        Image imageCopy = new Image(region);
        imageCopy.setPosition(stage.getWidth() - fitWidth, 0);
        imageCopy.setSize(fitWidth, fitHeight);
        table.addActor(imageCopy);
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public String getRandomAvatar() {
        return avatarFiles.random();
    }

    public Array<String> getAvatarFiles() {
        return avatarFiles;
    }

    public Sound getButtonClick() {
        return sfxManager.getButtonClick();
    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public Texture getCursorTexture() {
        return cursorTexture;
    }

    public Texture getBulletTexture() {
        return bulletTexture;
    }

    public Texture getAmmoTexture() {
        return ammoTexture;
    }

    public Texture getAimTexture() {
        return aimTexture;
    }

    public Texture getKillTexture() {
        return killTexture;
    }

    public Animation<Texture> getXpDropAnimation() {
        return xpDropAnimation;
    }
}
