package com.tilldawn.model.enums;

public enum KeyBind {
    Up(51), // key code of W
    Down(47), // key code of S
    Left(29), // key code of A
    Right(32), // key code of D
    Reload(46); // key code of R

    private int keyCode;

    KeyBind(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }
}
