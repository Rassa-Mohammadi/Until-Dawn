package com.tilldawn.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Result {
    private Label message;
    private float displayTime;
    private boolean isError = true;

    public Result() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        message = new Label("", labelStyle);
        message.setVisible(false);
        message.setFontScale(1.5f);
    }

    public Result(String message, Color color) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        this.message = new Label(message, labelStyle);
        this.message.setVisible(true);
        this.message.setFontScale(1.5f);
        this.message.setColor(color);
        this.displayTime = 3f;
        this.isError = true;
    }

    public Result(String message, Color color, boolean isError) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        this.message = new Label(message, labelStyle);
        this.message.setVisible(true);
        this.message.setFontScale(1.5f);
        this.message.setColor(color);
        this.displayTime = 3f;
        this.isError = isError;
    }

    public void set(Result result) {
        this.message.setText(result.getMessage().getText());
        this.message.setVisible(true);
        this.displayTime = result.displayTime;
        this.message.setColor(result.getColor());
        this.isError = result.isError;
    }

    public void update(float deltaTime) {
        if (displayTime > 0f) {
            displayTime -= deltaTime;
            if (displayTime < 1f)
                message.setColor(1, 0, 0, displayTime);
            if (displayTime <= 0f)
                this.message.setVisible(false);
        }
    }

    public Label getMessage() {
        return message;
    }

    public Color getColor() {
        return message.getColor();
    }

    public boolean hasError() {
        return isError;
    }
}
