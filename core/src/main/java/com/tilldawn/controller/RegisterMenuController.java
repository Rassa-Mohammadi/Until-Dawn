package com.tilldawn.controller;

import com.badlogic.gdx.graphics.Color;
import com.tilldawn.Main;
import com.tilldawn.model.*;
import com.tilldawn.view.AppMenuView;
import com.tilldawn.view.RegisterMenuView;

public class RegisterMenuController {
    private RegisterMenuView view;

    public void setView(RegisterMenuView view) {
        this.view = view;
    }

    public void handleRegisterMenuButtons() {
        if (view == null)
            return;
        if (view.getBackButton().isChecked()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new AppMenuView(new AppMenuController(), GameAssetManager.getInstance().getSkin()));
        }
        else if (view.getSubmitButton().isPressed()) {
            String username = view.getUsername().getText();
            String password = view.getPassword().getText();
            String confirmedPassword = view.getConfirmedPassword().getText();
            String question = view.getQuestions().getSelected();
            String answer = view.getAnswer().getText();
            Result result = register(username, password, confirmedPassword, question, answer);
            if (result.hasError())
                view.setResult(result);
            else {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new AppMenuView(new AppMenuController(), GameAssetManager.getInstance().getSkin()));
            }
        }
    }

    private Result register(String username,
                            String password,
                            String confirmedPassword,
                            String questionString,
                            String answer) {
        if (App.getUser(username) != null)
            return new Result(Output.UsernameExists.getString(), Color.RED);
        if (password.isEmpty() || confirmedPassword.isEmpty())
            return new Result(Output.PasswordEmpty.getString(), Color.RED);
        if (answer.isEmpty())
            return new Result(Output.AnswerEmpty.getString(), Color.RED);
        if (!password.equals(confirmedPassword))
            return new Result(Output.ReenterPasswordError.getString(), Color.RED);
        Result passwordResult = User.isPasswordWeak(password);
        if (passwordResult.hasError())
            return passwordResult;
        Output question = Output.getPhrase(questionString);
        assert question != null;
        User user = new User(username, password);
        user.setSecurityQuestion(new SecurityQuestion(question, answer));
        App.addUser(user);
        return new Result("Successful registration", Color.GREEN, false);
    }
}
