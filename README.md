# ScreenOff
Important : Requires ROOT 

This is a simple App that emulates the click of Power Button.

Handy when u want to reduce wear and tear of the physical button or if the button is not working.

Currently the following method of Trigger are available:

- Using the Swipe Up Assist action which usually opens the Google Now App
- Application icon (In Launcher)
- Shortcut
- Intent Support - Use 'in.pathri.screenoff.SCREEN_OFF' as Intent action to trigger screen off from ANY App, including TASKER

Following are planned to be added:

- Widget (Is it Needed?? )
- Tasker Extension

Advantage over other similar apps in Market:

The usual 'Screen Off' 'Screen Lock' Apps that are available in market, makes the 'Smart Lock' feature introduced in Lollipop not work. Also this makes the timeout period set in the 'Automatically Lock' security settings to not work.

Since my App uses shell commands (and hence the need for Root) to emulate Power Button press, both Smart Lock and 'Automatically Lock' settings work.  

Disclaimer: 
AFAIK should not cause any problems in any device since nothing dangerous is being used. But still the usual disclaimer : ' I am not responsible for any damage done to your device, hand, home etc... LOL '

Have tested so far in the following Mobiles. Would be glad if others test it out in various mobiles and report here.
Nexus 5
HTC One X

Change Log:
v0.1 - 5th Jan:
- Initial Version with only Google Now Swipe gesture support

v0.2 - 8th Jan:
- Added the following:
Application launcher
Shortcut
Intent Support : 'in.pathri.screenoff.SCREEN_OFF'

v1.0 - 18th Jan:
Modified code to support more Devices. Probably must work in all Android devices irrespective of Android Version

v1.1 - 20th Jan
Shouldn't be showing in recents anymore

v1.2 - 20th March
Added icon for the swipe up gesture
Fixed a bug that was causing adding shortcut to crash in certain launchers(AFAIK GEL based)

v2.0b - 30th March
Adding a new material icon - thanks to @slider112
ScreenOff
