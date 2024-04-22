package ru.education.pastebox.util;

public enum Status {
    PUBLIC("public"), UNLISTED("unlisted");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
