package com.felzr.movies.api.enums;

public enum Winner {
    YES("yes", true),

    NO("", false);

    private String text;
    private boolean win;

    Winner(String text, boolean win) {
        this.text = text;
        this.win = win;
    }

    public String getText() {
        return text;
    }

    public boolean getWin() {
        return win;
    }
}
