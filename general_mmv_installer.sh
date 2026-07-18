if grep -q '^ID=steamos$' /etc/os-release; then
    steamos-readonly disable
fi
echo "downloading and installing me3..."
curl --proto '=https' --tlsv1.2 -sSfL https://github.com/garyttierney/me3/releases/download/v0.11.0/installer.sh | sh
echo "install done."
cd "${1}" | exit 1
git clone https://github.com/Daybreak-Team/MMV-Launcher
echo "mod installed"
cd "${1}/MMV-Launcher/" | exit 1
git clone https://github.com/EleriLove/More-Map-Variations-launch-script/
cp "${1}/MMV-Launcher/More-Map-Variations-launch-script/launch_nightreign.sh" "${1}/MMV-Launcher/"
rm -rf "${1}/MMV-Launcher/More-Map-Variations-launch-script"
chmod +x "${1}/MMV-Launcher/launch_nightreign.sh"
echo "launcer installed, enjoy your game"
sleep 20
if grep -q '^ID=steamos$' /etc/os-release; then
    steamos-readonly enable
fi
