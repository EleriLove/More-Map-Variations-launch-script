mod_engine=$(/usr/bin/which me3)
$mod_engine launch \
  --native "${HOME}/MMV-Launcher/mod/Server Redirector/cl_server_redirector.dll" \
  --package "${HOME}/MMV-Launcher/mod" \
  -g nightreign \
  --savefile "NR0000.co2" \
  --online true
