package com.example.alici.remotecontroler.models;

public class Door {
    private boolean status;

    public Door(boolean status) {
        this.status = status;
    }

    public boolean isOpen() {
        return status;
    }

    public void setOpen() {
        this.status = true;
    }

    public void setClose() {
        this.status = false;
    }

}
