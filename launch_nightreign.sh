mod_engine=$(which me3)
$mod_engine launch \
  --native "${home}/MMV-Launcher/mod/Server Redirector/cl_server_redirector.dll" \
  --package "${home}/MMV-Launcher/mod" \
  -g nightreign \
  --savefile "NR0000.co2" \
  --online true
