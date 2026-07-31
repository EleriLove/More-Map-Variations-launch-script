package com.example.mmvlauncher;

import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    private final Path settingsPath = Paths.get(
            System.getProperty("user.home"),
            ".mmv-launcher",
            "settings.json"
    );

    private final String changeLogsPath = "";

    private String installedVersion;

    private SettingsModel launcherSettings;

    @FXML
    void launchNightReign(ActionEvent event) {
        runBash("launch_nightreign.sh", launcherSettings.getInstallPath());
    }

    @FXML
    void installMod(ActionEvent event) {
        if (launcherSettings == null) {
            errorWarningLabel.setText("Settings not loaded.");
            return;
        }
        //Get the selected data from the instal menu button
        MenuItem selected = (MenuItem) installMenuBtn.getUserData();
        String installOption = (String) selected.getUserData();

        //Run the installer Bash for MMV mod
        runBash("general_mmv_installer.sh", launcherSettings.getInstallPath(), installOption);
    }

    @FXML
    void installMenuChange(ActionEvent event) {
        //Get the selected menu item on change
        MenuItem selected = (MenuItem) event.getSource();

        //Change the menu buttons display text and hidden user data
        installMenuBtn.setText(selected.getText());
        installMenuBtn.setUserData(selected);
    }

    /**
     * Runs a Bash script file from the src/main/bash directory
     * @param scriptFile The file name of the Bash script as a String e.g. "test_bash.sh"
     * @param posParams Add as many Strings as positional parameters to be used in the Bash script
     */
    void runBash(String scriptFile, String... posParams) {
        try {
            //Relative path to the bash script file
            String bashPathStr = "/com/example/mmvlauncher/bash/" + scriptFile;

            InputStream bashStream = getClass().getResourceAsStream(bashPathStr);

            //Throws an error if the script file doesn't exist in Bash folder
            if (bashStream == null) {
                throw new FileNotFoundException("Script not found in resources!");
            }
            File tempScript = File.createTempFile("mmv-launcher-script", ".sh");
            tempScript.deleteOnExit();

            Files.copy(bashStream, tempScript.toPath(), StandardCopyOption.REPLACE_EXISTING);
            bashStream.close();

            tempScript.setExecutable(true);

            //Create command list for Process builder
            List<String> command = new ArrayList<>();
            command.add(launcherSettings.getBashTerminalPath());
            command.add(tempScript.getAbsolutePath());

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

    /** Save all settings from the settings menu to a config file */
    @FXML
    void saveSettingsMenu(ActionEvent event) {
        //Applying the changes in the settings menu to launcherSettings
        launcherSettings.setBashTerminalPath(bashPathInput.getText());
        launcherSettings.setInstallPath(gamePathInput.getText());

        saveSettingsToFile();
    }

    void saveSettingsToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try{
            Files.createDirectories(settingsPath.getParent());
            //Using file writer with GSON to JSON method to save settings to settings.json
            try (Writer writer = Files.newBufferedWriter(settingsPath)) {
                gson.toJson(launcherSettings, writer);
            }
        } catch (IOException e) {
            System.err.println("MMVLauncherController saveSettingsToFile Error: " + e.getMessage());
            errorWarningLabel.setText(e.getMessage());
        }
    }

    /** Load settings from a config file */
    void loadSettings() {
        try {
            //Checks if the settings.json exists and if not it creates and initializes a new one
            if (!Files.exists(settingsPath)) {
                launcherSettings = new SettingsModel(
                        "C:\\Program Files\\Git\\git-bash.exe",
                        "\\path\\"
                );
                launcherSettings.logSettings();
                //Saves the new settings to settings.json
                saveSettingsToFile();
            } else {
                try (Reader reader = Files.newBufferedReader(settingsPath);
                     JsonReader jsonReader = new JsonReader(reader)) {
                    Gson gson = new Gson();

                    //Gets settings JSON from the file reader
                    JsonObject settingsJson = gson.fromJson(jsonReader, JsonObject.class);

                    //Gets the bash terminal location from the settings JSON and saves it to the global launcherSettings var
                    launcherSettings = new SettingsModel(
                            settingsJson.get("bashTerminalPath").getAsString(),
                            settingsJson.get("installPath").getAsString()
                    );
                }
            }
            //Populates Settings menu with current settings
            bashPathInput.setText(launcherSettings.getBashTerminalPath());
            gamePathInput.setText(launcherSettings.getInstallPath());
        } catch (Exception e) {
            System.err.println("MMVLauncherController loadSettings Error: " + e.getMessage());
            errorWarningLabel.setText(e.getMessage());
        }
    }

    /** Runs on launch */
    @FXML
    void initialize() {

        loadSettings();
        //IMPORTANT: Add userData to installMenuBtn menu items manually in mmv-launcher-view.fxml
        //Have the 1st option selected by default
        installMenuBtn.setUserData(installOpt1);
        loadChangeLogs();
    }
}

