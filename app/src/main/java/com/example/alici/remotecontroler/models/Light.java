package com.example.alici.remotecontroler.models;

public class Light {
    private boolean status;

    public Light(boolean status) {
        this.status = status;
    }

    public boolean isOn() {
        return status;
    }

    public void setOn() {
        this.status = true;
    }

    public void setOff() {
        this.status = false;
    }

}
