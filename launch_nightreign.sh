if !grep -q '^ID=steamos$' /etc/os-release; then
    mod_engine=$(/usr/bin/which me3)
    $mod_engine launch \
      --native "${1}MMV-Files/mod/ServerRedirector/cl_server_redirector.dll" \
      --native "${1}MMV-Files/mod/dll/custom_drop_fxrs.dll" \
      --package "${1}MMV-Files/mod" \
      -g nightreign \
      --savefile "NR0000.co2" \
      --online true \
      --disable-arxan true
else
    ${HOME}/.local/bin/me3 launch \
      --native "${1}MMV-Files/mod/ServerRedirector/cl_server_redirector.dll" \
      --native "${1}MMV-Files/mod/dll/custom_drop_fxrs.dll" \
      --package "${1}MMV-Files/mod" \
      -g nightreign \
      --savefile "NR0000.co2" \
      --online true \
      --disable-arxan true
fi
