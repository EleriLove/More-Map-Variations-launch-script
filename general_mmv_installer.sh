if grep -q '^ID=steamos$' /etc/os-release  && sudo -n true 2>/dev/null; then
    echo "Sudo requires a password. Please set one with: passwd"
    exit 1
fi
if grep -q '^ID=steamos$' /etc/os-release; then
    sudo steamos-readonly disable
fi
if ! command -v git-lfs >/dev/null 2>&1; then
    echo "Git LFS is required but is not installed."
    if command -v apt-get >/dev/null 2>&1; then
        sudo apt-get update
        sudo apt-get install -y git-lfs
	git lfs install
    elif command -v pacman >/dev/null 2>&1; then
        sudo pacman -Sy --noconfirm git-lfs
	git lfs install
    else
        echo "Could not find a supported package manager to install Git LFS."
	echo "please install it yourself"
        exit 1
    fi
fi
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
git lfs pull
echo "mod installed"
cp "${prog_dir}" "${1}"
echo "launcer installed, enjoy your game"
if grep -q '^ID=steamos$' /etc/os-release; then
    sudo -n steamos-readonly enable
fi
rm -rf  "${prog_dir}"
exit 1
