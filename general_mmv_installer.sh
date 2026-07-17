echo "downloading and installing me3..."
curl --proto '=https' --tlsv1.2 -sSfL https://github.com/garyttierney/me3/releases/download/v0.11.0/installer.sh | sh
echo "install done."
cd ${HOME}
git clone https://github.com/Daybreak-Team/MMV-Launcher
echo "mod installed"
cd "${HOME}/MMV-Launcher/"
git clone https://github.com/EleriLove/More-Map-Variations-launch-script/
cp "${HOME}/MMV-Launcher/More-Map-Variations-launch-script/launch_nightreign.sh" "${HOME}/MMV-Launcher/"
rm -rf "${HOME}/MMV-Launcher/More-Map-Variations-launch-script"
chmod +x "${HOME}/MMV-Launcher/launch_nightreign.sh"
echo "launcer installed"