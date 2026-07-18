server_redirector=$(/usr/bin/find ~ -name "cl_server_redirector.dll" 2>/dev/null)
prog_path=$(/usr/bin/find ~ -name "launch_nightreign.sh" 2>/dev/null)
prog_dir=$(/usr/bin/dirname "$prog_path")
mod_engine=$(/usr/bin/which me3)
$mod_engine launch \
  --native "${server_redirector" \
  --package "${prog_dir}mod" \
  -g nightreign \
  --savefile "NR0000.co2" \
  --online true
