package com.example.mmvlauncher;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        if(!verifyPathStr(bashTerminalPath)) throw new IllegalArgumentException("Bash terminal path doesn't exist");
        this.bashTerminalPath = bashTerminalPath;
    }

    public void setInstallPath(String installPath) {
        if(!verifyPathStr(installPath)) throw new IllegalArgumentException("Install path doesn't exist");
        this.installPath = installPath;
    }

    //----------------VERIFICATION---------------------

    /**
     * Checks if the path String is a valid path and is not blank
     * @param pathStr The path String to be verified
     * @return Returns {@code true} if the string is a valid path, else it returns {@code false}
     */
    boolean verifyPathStr(String pathStr) {
        if (pathStr == null || pathStr.trim().isEmpty()) return false;
        //Use try catch with java paths to check if path exists
        try {
            Path path = Paths.get(pathStr);
            return true;
        } catch (InvalidPathException | NullPointerException e) {
            return false;
        }
    }

    void logSettings() {
        System.out.println("BashTerminalPath: " + getBashTerminalPath() + "\nInstallPath: " + getInstallPath());
    }
}
