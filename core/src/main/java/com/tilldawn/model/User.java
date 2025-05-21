package com.tilldawn.model;

import com.badlogic.gdx.graphics.Color;

public class User {
    private String username;
    private String password;
    private SecurityQuestion securityQuestion = null;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
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
