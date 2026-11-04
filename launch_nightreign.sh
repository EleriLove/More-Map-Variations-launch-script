${HOME}/.local/bin/me3 launch \
      --native "${1}mod/ServerRedirector/cl_server_redirector.dll" \
      --native "${1}mod/dll/custom_drop_fxrs.dll" \
      --package "${1}mod" \
      -g nightreign \
      --savefile "NR0000.co2" \
      --online true \
      --disable-arxan true
