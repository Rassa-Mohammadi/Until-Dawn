package com.tilldawn.controller;

import com.tilldawn.view.LoginMenuView;

public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public void handleLoginMenuButtons() {
        if (view == null)
            return;

    }
}
