package com.example.mmvlauncher;

import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MMVLauncherController {

    //Home Menu
    @FXML
    private Label errorWarningLabel;

    @FXML
    private Label installedVersionText;

    @FXML
    private Label latestVersionText;

    @FXML
    private Button launchBtn;

    //Install Menu Button
    @FXML
    private SplitMenuButton installMenuBtn;

    @FXML
    private MenuItem installOpt1;

    @FXML
    private MenuItem installOpt2;

    //Change Log Menu
    @FXML
    private ScrollPane changeLogScroll;

    //Settings Menu
    @FXML
    private TextField bashPathInput;

    @FXML
    private TextField gamePathInput;

    @FXML
    private Button settingsConfirmBtn;

    private final String settingsPath = "src/main/resources/com/example/mmvlauncher/settings.json";

    private final String changeLogsPath = "";

    private String installedVersion;

    private SettingsModel launcherSettings;

    @FXML
    void launchNightReign(ActionEvent event) {
        runBash("launch_nightreign.sh", launcherSettings.getInstallPath());
    }

    @FXML
    void installMod(ActionEvent event) {
        //Get the selected data from the instal menu button
        MenuItem selected = (MenuItem) installMenuBtn.getUserData();
        String installOption = (String) selected.getUserData();

        //Run the Bash script with
        runBash("test_bash.sh", launcherSettings.getInstallPath(), installOption);
    }

    @FXML
    void installMenuChange(ActionEvent event) {
        //Get the selected menu item on change
        MenuItem selected = (MenuItem) event.getSource();

        //Change the menu buttons display text
        installMenuBtn.setText(selected.getText());

        //Change
        installMenuBtn.setUserData(selected);
    }

    void runBash(String scriptFile, String... posParams) {
        try {
            //Relative path to the bash script file
            String bashPathStr = "src/main/bash/" + scriptFile;

            //Using Path for error handling
            Path bashPath = Paths.get(bashPathStr);

            //Throws an error if the script file doesn't exist in Bash folder
            if (!bashPath.toFile().exists()) { throw new IllegalArgumentException("File not found"); }

            //Create command list for Process builder
            List<String> command = new ArrayList<>();
            command.add(launcherSettings.getBashTerminalPath());
            command.add(bashPathStr);

            //Adds all positional parameters
            command.addAll(Arrays.asList(posParams));

            //Initialize ProcessBuilder with the command list
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            //Start the process
            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println(scriptFile + " successfully exited");
            } else {
                System.out.println(scriptFile + " exited with code: " + exitCode);
            }

        } catch (Exception e) {
            System.err.println("MMVLauncherController runBash " + scriptFile + " Error: " + e.getMessage());
            errorWarningLabel.setText(e.getMessage());
        }
    }

    //Load Change Logs into it's tab
    void loadChangeLogs() {

    }

    //Save all settings from the settings menu to a config file
    @FXML
    void saveSettings(ActionEvent event) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //Applying the changes in the settings menu to launcherSettings
        launcherSettings.setBashTerminalPath(bashPathInput.getText());
        launcherSettings.setInstallPath(gamePathInput.getText());

        //Using file writer with GSON to JSON method to save settings to settings.json
        try (Writer writer = new FileWriter(settingsPath)){
            gson.toJson(launcherSettings, writer);
        } catch (IOException e) {
            System.err.println("MMVLauncherController saveSettings Error: " + e.getMessage());
            errorWarningLabel.setText(e.getMessage());
        }
    }

    //Load settings from a config file
    void loadSettings() {
        try (Reader reader = new FileReader(settingsPath);
             JsonReader jsonReader = new JsonReader(reader)) {
            Gson gson = new Gson();

            //Gets settings JSON from the file reader
            JsonObject settingsJson = gson.fromJson(jsonReader, JsonObject.class);

            //Gets the bash terminal location from the settings JSON and saves it as a local var
            SettingsModel settings = new SettingsModel(
                    settingsJson.get("bashTerminalPath").getAsString(),
                    settingsJson.get("launchPath").getAsString()
            );

            //Transferring local settings var to a global variable
            launcherSettings = settings;

            //Populates Settings menu with current settings
            bashPathInput.setText(settings.getBashTerminalPath());
            gamePathInput.setText(settings.getInstallPath());

        } catch (Exception e) {
            System.err.println("MMVLauncherController loadSettings Error: " + e.getMessage());
            errorWarningLabel.setText(e.getMessage());
        }
    }

    //Runs on launch
    @FXML
    void initialize() {

        loadSettings();
        //IMPORTANT: Add userData to installMenuBtn menu items manually in mmv-launcher-view.fxml
        //Have the 1st option selected by default
        installMenuBtn.setUserData(installOpt1);
        loadChangeLogs();
    }
}

