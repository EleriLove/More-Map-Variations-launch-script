package com.example.mmvlauncher;

public class SettingsModel {
    //Initializing variables for the needed settings model
    private String bashTerminalPath;
    private String installPath;

    //SettingsModel Constructors
    public SettingsModel(String bashTerminalPath, String installPath) {
        setBashTerminalPath(bashTerminalPath);
        setInstallPath(installPath);
    }


    //----------------GETTERS---------------------
    public String getBashTerminalPath() {
        return bashTerminalPath;
    }

    public String getInstallPath() {
        return installPath;
    }

    //----------------SETTERS---------------------
    public void setBashTerminalPath(String bashTerminalPath) {
        this.bashTerminalPath = bashTerminalPath;
    }

    public void setInstallPath(String installPath) {
        this.installPath = installPath;
    }
}
