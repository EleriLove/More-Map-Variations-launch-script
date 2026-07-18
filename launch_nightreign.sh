server_redirector=$(find ~ -name "cl_server_redirector.dll")
prog_path=$(find ~ -name "launch_nightreign.sh" 2>/dev/null)
prog_dir=$(dirname "$prog_path")
mod_engine=$(/usr/bin/which me3)
$mod_engine launch \
  --native "${server_redirector" \
  --package "${prog_dir}mod" \
  -g nightreign \
  --savefile "NR0000.co2" \
  --online true
