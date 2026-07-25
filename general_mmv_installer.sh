if grep -q '^ID=steamos$' /etc/os-release && ! sudo -n true 2>/dev/null; then
    echo "Sudo requires a password. Please set one with: passwd"
    exit 1
fi
if grep -q '^ID=steamos$' /etc/os-release; then
    sudo steamos-readonly disable
fi
prog_dir=$(find ~ -name "launch_nightreign.sh" 2>/dev/null)
echo "downloading and installing me3..."
curl --proto '=https' --tlsv1.2 -sSfL https://github.com/garyttierney/me3/releases/download/v0.12.1/installer.sh | sh
if ! echo $PATH |grep "${HOME}/.local/bin";  then
    printf 'export PATH="$PATH:/home/deck/.local/bin"\n' >> ${HOME}/.bash_profile
fi
echo "install done."
cd "${1}" || exit 1
git clone https://github.com/Daybreak-Team/MMV-Launcher "${1}"
echo "mod installed"
cp "${prog_dir}" "${1}"
echo "launcer installed, enjoy your game"
if grep -q '^ID=steamos$' /etc/os-release; then
    sudo -n steamos-readonly enable
fi
exit 1
