if grep -q '^ID=steamos$' /etc/os-release; then
    steamos-readonly disable
fi
prog_dir=$(find ~ -name "launch_nightreign.sh" 2>/dev/null)
echo "downloading and installing me3..."
curl --proto '=https' --tlsv1.2 -sSfL https://github.com/garyttierney/me3/releases/download/v0.11.0/installer.sh | sh
echo "install done."
cd "${1}" |exit 1
git clone https://github.com/Daybreak-Team/MMV-Launcher "${1}"
echo "mod installed"
cp "${prog_dir}" "${1}"
echo "launcer installed, enjoy your game"
sleep 5
if grep -q '^ID=steamos$' /etc/os-release; then
    steamos-readonly enable
fi
