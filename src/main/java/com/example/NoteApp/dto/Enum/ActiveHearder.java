package com.example.NoteApp.dto.Enum;

public enum ActiveHearder {
    Coment("comment"),

    Notice("notice")
    ;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ActiveHearder(String name) {
        this.name = name;
    }
}
