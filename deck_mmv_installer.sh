echo "disabling os readonly."
steamos-readonly disable
echo "downloading and installing me3..."
curl --proto '=https' --tlsv1.2 -sSfL https://github.com/garyttierney/me3/releases/download/v0.11.0/installer.sh | sh
echo "install done."
Engine_DIR=$(which me3)
git clone https://github.com/Daybreak-Team/MMV-Launcher/tree/main ${HOME}
echo "mod installed"
steamos-readonly enable
echo "steam readonly reenabled"
git clone https://github.com/EleriLove/More-Map-Variations-launch-script ${HOME}/MMV-Launcher/
chmod +x ${HOME}/MMV-Launcher/launch_nightreign.sh
ECHO "launcer installed"