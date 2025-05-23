package com.tilldawn.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;

public class User {
    private String username;
    private String password;
    private int points = 0;
    private SecurityQuestion securityQuestion = null;
    private String avatarPath;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        avatarPath = GameAssetManager.getInstance().getRandomAvatar();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecurityQuestion(SecurityQuestion securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public Texture getAvatarTexture() {
        return new Texture(avatarPath);
    }

    public void setAvatar(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Table getInfo() {
        Table table = new Table();
        Image avatar = new Image(getAvatarTexture());
        avatar.setScaling(Scaling.fit);
        table.add(avatar).size(300).row();
        Label label = new Label("Username: " + username, GameAssetManager.getInstance().getSkin());
        label.setFontScale(1.2f);
        table.add(label).pad(10).row();
        label = new Label("Points: " + points, GameAssetManager.getInstance().getSkin());
        label.setFontScale(1.2f);
        table.add(label).pad(10).row();
        return table;
    }

    public static Result isPasswordWeak(String password) {
        if (password.length() < 8)
            return new Result(Output.PasswordLength.getString(), Color.RED);
        if (!password.matches(".*[@_()*&%$#].*"))
            return new Result(Output.PasswordSpecialCharacter.getString(), Color.RED);
        if (!password.matches(".*[0-9].*"))
            return new Result(Output.PasswordNumber.getString(), Color.RED);
        if (!password.matches(".*[A-Z].*"))
            return new Result(Output.PasswordCapitalLetter.getString(), Color.RED);
        return new Result("Strong password", Color.GREEN, false);
    }
}
