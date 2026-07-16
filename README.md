# More-Map-Variations-launch-script
launching more map variations on steamdeck


this script helps launch the More Map Variations mod on the Valve steamdeck for nightreign.

first, disable operating system read only mode. this allows us to make the script run and install things to the operating system. 

#BE SURE TO RUN ALL COMANDS IN THE KONSOLE TERMINAL IN DESKTOP MODE

``sudo steamos-readonly disable``
you may need to create a root password with passwd DO NOT FORGET THIS PASSWORD 

install me3 with the basic linux instalation command you can find this on the release page here https://github.com/garyttierney/me3/releases/tag/v0.11.0
or, 

copy paste this command 

``sudo curl --proto '=https' --tlsv1.2 -sSfL https://github.com/garyttierney/me3/releases/download/v0.11.0/installer.sh | sh``

this script will automatically download and run the installer file from the official me3 install repository 

now, there's a chance it works just fine from there, but run 

``me3 --info`` to test, you should see a bunch of data. don't worry about it, if you don't you're going to either need to install it to your path if you want to (unnecessary for this) run it from desktop mode.

however the next steps are most important you can find wherever me3 is installed with this command 
``which me3`` make sure the output of this states /home/deck/.local/bin/me3 if it does you're golden and can download the mod and place it in your documents folder, that's where I put it and that's where this script expects it. if it's not there be sure to edit the script or the location of the mod. 

https://www.nexusmods.com/eldenringnightreign/mods/578

download and extract the folder to your /home/deck/Documents folder

after this download the script, you can place this ANYWHERE on your steamdeck, 

in terminal allow it execution permissions with ``chmod +x launch_nightreign.sh`` 

you can then just add launch_nightreign.sh to steam like any game 

re enable readonly mode with 
``sudo steamos-readonly enable`` 

then launch the script from game mode, if it doesn't work I won't be able to help you UNLESS you tell me exactly where the mod was downloaded and extracted to, 

AND show me the output of me3 --info if you can't get to me3 --info I can also help you but I need this information. 

#

MMV 2.1.7 and Weapons Merge Hotfix 1 Update Files
#

I have confirmed that this hotfix works without any special actions, just drag and drop the hotfix files
updating to the newest hotfit only requires that you copy the new modfolder over from the hotfix, just like you'd update it normally


