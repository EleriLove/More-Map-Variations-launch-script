if !grep -q '^ID=steamos$' /etc/os-release; then
    prog_path=$(/usr/bin/find / -name "launch_nightreign.sh" 2>/dev/null)
    prog_dir=$(/usr/bin/dirname "$prog_path")
    mod_engine=$(/usr/bin/which me3)
    $mod_engine launch \
      --native "${prog_dir}/mod/Server Redirector/cl_server_redirector.dll" \
      --native "${prog_dir}/mod/dll/custom_drop_fxrs.dll" \
      --package "${prog_dir}/mod" \
      -g nightreign \
      --savefile "NR0000.co2" \
      --online true \
      --disable_arxan true
else
    prog_dir="${1}"
    ${HOME}/.local/bin/me3 launch \
      --native "${prog_dir}/mod/Server Redirector/cl_server_redirector.dll" \
      --native "${prog_dir}/mod/dll/custom_drop_fxrs.dll" \
      --package "${prog_dir}/mod" \
      -g nightreign \
      --savefile "NR0000.co2" \
      --online true \
      --disable_arxan true
fi
