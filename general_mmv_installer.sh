if grep -q '^ID=steamos$' /etc/os-release  && sudo -n true 2>/dev/null; then
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
    export PATH="$PATH:/home/deck/.local/bin"
fi
echo "install done."
mkdir -p "${1}" || {
   echo "mkdir failed"
   exit 1
}
git clone "https://github.com/Daybreak-Team/MMV-Launcher" "${1}"
cd "${1}"
cat  "${1}/"*.zip* > "${1}/MMV.zip"
unzip "${1}/MMV.zip" -d "${1}"
rm "${1}/"*.zip.*
mv "${1}/More Map Variations and Weapons Mod Merge 578 2.1.8.3 2026-08-26T22-23Z KXPnhGD30" "${1}/MMV-Files"
cp "${1}/MMV-Files/"* "${1}"
rm -rf "${1}/MMV-Files"
mv "${1}/More Map Variations 2.1.8-hotfix3 & Weapons Mod" "MMV-Files"
echo "mod installed"
cp "${prog_dir}" "${1}"
echo "launcer installed, enjoy your game"
if grep -q '^ID=steamos$' /etc/os-release; then
    sudo -n steamos-readonly enable
fi
rm -rf  "${prog_dir}"
exit 1
