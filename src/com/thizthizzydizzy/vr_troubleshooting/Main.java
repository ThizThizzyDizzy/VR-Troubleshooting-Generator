package com.thizthizzydizzy.vr_troubleshooting;
import java.io.File;
import java.io.IOException;
public class Main{
    public static File root = new File(new File("src").getAbsoluteFile().getParentFile().getParentFile(), "ThizThizzyDizzy.github.io"+File.separatorChar+"vr-troubleshooting");
    public static void main(String[] args) throws IOException{
        new Page("index", "Troubleshooting Home")
            .paragraph("Welcome to my VR Troubleshooting page! Please pick the type of device you are having issues with:")
            .paragraph("WARNING: Some of these guides assume you have already exhausted traditional product support resources. (i.e. Warranty)<br>Performing hardware modifications may void your warranty. This is your only warning.", true)
            .subpage("Software", "(Specific game or software, not directly related to any hardware)", new Page("software", "Software")
                .paragraph("What kind of software are you having issues with?")
                .subpage("SteamVR Overlay", "(Including OVRToolkit, XSOverlay, OVR Advanced Settings, and others)", new Page("steamvr-overlay", "SteamVR Overlay")
                    .paragraph("What overlay are you having trouble with?")
                    .subpage("OVR Toolkit", "(Not to be confused with OVR Advanced Settings. This is the one that lets you see and interact with your desktop)", new Page("ovrtoolkit", "OVR Toolkit")
                        .problem("OBS chat windows keep resizing", "(Also applies to other programs with sub-windows)", new Page("sub-window-resizing", "OVR Toolkit - Sub-Window Resizing")
                            .action("Dock to parent window & Crop", "The window capture API does not differentiate well between a parent window and sub-windows, especially after the program is restarted. If possible, dock sub-windows into the main window, capture the main window instead, and crop as needed.")
                        )
                        .problem("OVRToolkit won't appear in-game", "(And I'm using an oculus/meta headset)", new Page("oculus", "OVRToolkit - Oculus Issues")
                            .paragraph("The game must be running through the SteamVR runtime.")
                            .action("OpenXR", "If you are using OpenXR, ensure SteamVR is set as the default OpenXR Runtime. (SteamVR Settings > OpenXR tab > Set as default runtime)")
                            .action("Launch options", "Many games will accept launch arguments that tell it to run through SteamVR. Try <strong>-vrmode openvr</strong> or <strong>-hmd=SteamVR</strong> (Right click the game > Properties > Launch options)")
                            .seeAlso("the OVRToolkit wiki (Oculus Fixes)", "https://wiki.ovrtoolkit.co.uk/#/Oculus")
                        )
                        .seeAlso("the OVR Toolkit wiki", "https://wiki.ovrtoolkit.co.uk/#/Troubleshooting")
                    )
                    .subpage("OVR Advanced Settings", "(Not to be confused with OVR Toolkit. This is the one that has space drag, often called \"playspace mover\")", new Page("ovras", "OVR Advanced Settings"))
                )
            )
            .subpage("VR Headset or Controllers", "(Including built-in extras such as eye tracking or face tracking)", new Page("headset", "VR Headset or Controllers")
                .paragraph("What brand of VR Headset/Controllers are you having trouble with?")
                .subpage("Pimax", null, new Page("pimax", "Pimax Headset/Controllers")
                    .paragraph("Which device are you having trouble with?")
                    .subpage("I have an error code", "(from pimax play)", new Page("error-codes", "Pimax Error Codes")
                        .pimaxError("10500", "Connected the headset to the computer, but the USB is not recognized.", "Try reconnect the USB first. If the issue persists, consider uninstalling the USB drivers and reinstalling them by downloading the latest version from the motherboard manufacturer's website")
                        .pimaxError("10517", "Failed to retrieve device information via USB", "Restart the headset, and reboot the computer")
                        .pimaxError("10518", "Headset positioning information is abnormal", "Contact after-sales")
                        .pimaxError("10600", "The graphics card's DisplayPort is not being detected.", "Reconnect the DP cable and reinstall the graphics card driver (for AMD cards, it is recommended to uninstall and reinstall).<br>"
                            +"Try using another DP port on the dedicated graphics card, then reboot the operating system.<br>"
                            +"The issue might be with the DP cable; consider replacing it.<br>"
                            +"The Crystal Light firmware update may have failed; attempt to update the firmware again")
                        .pimaxError("10632-10636", "Graphics card or DP cable problem", "Reconnect the display cable<br>Install the graphics card driver<br>Restart the operating system")
                        .pimaxError("10900", "The headset is connected to the integrated graphics card interface. The graphics card is too old to support direct mode. Also could be graphics card driver is also outdated, lacking support for direct mode.", "Connect the headset to the dedicated graphics card<br>"
                            +"Update or downgrade the graphics card driver to different version<br>"
                            +"Check the graphics card model and ensure it meets the minimum requirements")
                        .pimaxError("10932", "Graphics card or DP cable problem", "Reconnect the display cable<br>Install the graphics card driver<br>Restart the operating system")
                        .pimaxError("10933", "The device is currently in use", "Reconnect the DP cable<br>Switch to another available DP port on the dedicated graphics card, or restart the computer")
                        .pimaxError("10936", "Unable to initialize the headset screen", "Reconnect the DP cable or restart the computer<br>Replace the data cable or switch to another available DP port on the dedicated graphics card")
                        .pimaxError("10938", "Exceeding the graphics card's bandwidth", "Remove the excess display devices.<br>Check if there is a high-refresh-rate monitor using a DP cable, and switch to an HDMI connection.")
                        .pimaxError("20000", "Connecting the headset to the computer, the USB is not recognized", "Reconnect the DP cable<br>May attempt to resolve the USB driver issue by uninstalling t he current driver and downloading the appropriate driver from the motherboard's official website")
                        .pimaxError("20100", "PimaxPlay file installation error", "Reinstall the Pimax Play client")
                        .pimaxError("20400", "Failed to acquire the headset device", "Restart the Pimax Play client, reinstall the Pimax Play client")
                        .pimaxError("20800", "The connection to the VR service failed", "Restart the Pimax service")
                        .pimaxError("29001", "There is an abnormality in the internal data communication of Pimax Play", "Restart the Pimax service")
                        .pimaxError("30000", "The Pimax Play service is experiencing an anomaly", "Restart the Pimax Play client, reinstall the PimaxPlay client")
                        .pimaxError("30202-30204", "Pimax Play and headset communication is malfunctioning", "Please wait. If the waiting time is too long, please restart the headset and the Pimax service.")
                        .pimaxError("30205", "Pimax Play has timed out while communicating with the headset", "Restart the computer or reinstall the Pimax Play client")
                        .source("Pimax", "https://discord.com/channels/1152070918462525461/1155345298957283329/1278523099293290517")
                    )
                    .subpage("Pimax Crystal", null, new Page("crystal", "Pimax Crystal")
                        .problem("Connection problems", null, new Page("connection", "Pimax Crystal - Connection Problems")
                            .paragraph("What are the USB and HDMI/DP states? (As reported by VR Manager or Pimax Play)")
                            .subpage("Both not connected", "(usb: no-connect, hdmi: no-connect)", new Page("no-connect")
                                .paragraph("What color is the main headset light? (The one by the power button, not by the battery)")
                                .subpage("Blue", null, new Page("blue")
                                    .action("Manually restart the headset", "Hold the power button until both the main headset light and the battery light turn off, and wait up to 2 minutes for the headset to restart and reconnect. Make sure the battery is fully charged.")
                                    .action("Reconnect DisplayPort cable", "If the problem persists, disconnect and reconnect the DisplayPort cable. Wait up to 10 seconds for the headset to connect.")
                                )
                                .subpage("Off", null, new Page("off")
                                    .action("Wait up to 2 minutes", "the headset can sometimes take a while to start up.")
                                    .action("Replace the headset battery", "Remove the battery, wait 10 seconds, and replace the battery. Wait up to 2 minutes for the headset to start up and reconnect.")
                                )
                            )
                            .subpage("Both connected", "(usb: success, hdmi: success)", new Page("success")
                                .action("Restart the Pimax Service", "Press \"Restart Service\" in pimax play. (VR Manager will do this for you automatically)")
                                .action("Manually restart the headset", "Hold the power button until both the main headset light and the battery light turn off, and wait up to 2 minutes for the headset to restart and reconnect.")
                            )
                        )
                    )
                    .subpage("Pimax Crystal Light", null, new Page("crystal-light", "Pimax Crystal Light"))
                    .subpage("Pimax Crystal Controllers", "(the inside-out tracked controllers for the Crystal and Crystal Light)", new Page("crystal-controllers", "Pimax Crystal Controllers"))
                )
                .subpage("Valve", null, new Page("valve", "Valve Headset/Controllers")
                    .paragraph("Which device are you having trouble with?")
                    .subpage("Valve Index Headset", null, new Page("index", "Valve Index"))
                    .subpage("Valve Index Controllers", "(Knuckles)", new Page("knuckles", "Valve Index Controllers"))
                )
            )
            .subpage("Full-body Tracking", "(SteamVR Trackers, IMU tracking, or other types of FBT)", new Page("fbt", "Full-body Tracking")
                .paragraph("What type of Full-body Tracking are you using?")
                .subpage("SteamVR Trackers", "(Usually vive or Tundra trackers)", new Page("steamvr", "SteamVR Trackers")
                    .paragraph("What device are you having trouble with?")
                    .subpage("Vive Tracker", null, new Page("vive", "Vive Trackers"))
                    .subpage("Tundra Tracker", null, new Page("tundra", "Tundra Trackers"))
                )
                .subpage("IMU Trackers", "SlimeVR, HaritoraX, mocopi, etc.", new Page("imu", "IMU Trackers")
                    .paragraph("What device are you having trouble with?")
                    .subpage("SlimeVR Tracker", "(DIY IMU Trackers, or other devices like phones or joycons repurposed as trackers)", new Page("slime", "SlimeVR Trackers"))
                )
            )
            .subpage("Haptics", "(vibration, electrostimulation, or other types of haptic feedback)", new Page("haptics", "VR Haptics")
                .paragraph("What brand of haptics are you using?")
                .subpage("bHaptics", null, new Page("bHaptics", "bHaptics")
                    .paragraph("What device are you having trouble with?")
                    .subpage("Tactal", null, new Page("tactal", "bHaptics Tactal"))
                    .subpage("TactVisor", null, new Page("tactvisor", "bHaptics TactVisor"))
                    .subpage("TactSuit X40", null, new Page("x40", "bHaptics TactSuit X40"))
                    .subpage("TactSuit X16", null, new Page("x16", "bHaptics TactSuit X16"))
                    .subpage("Tactosy for Arms", null, new Page("arms", "bHaptics Tactosy for Arms"))
                    .subpage("Tactosy for Feet", null, new Page("feet", "bHaptics Tactosy for Feet"))
                )
                .subpage("OWO Game", null, new Page("owo", "OWO Haptics")
                    .paragraph("What device are you having trouble with?")
                    .subpage("owoskin", null, new Page("owoskin", "owoskin"))
                )
                .subpage("GiggleTech", null, new Page("giggletech", "GiggleTech Haptics")
                    .paragraph("What device are you having trouble with?")
                    .subpage("Giggle Puck", null, new Page("puck", "Giggle Puck"))
                    .subpage("Giggle Spark", null, new Page("spark", "Giggle Spark"))
                    .subpage("Giggle Bank", null, new Page("bank", "Giggle Bank"))
                )
            )
            .subpage("Other Tracking Accessories", "(Face tracking, eye tracking, hand tracking, etc, DIY or otherwise NOT built into the headset.", new Page("tracking-accessories", "VR Tracking Accessories")
                .paragraph("What type of tracking device are you having trouble with?")
                .subpage("Face Tracking", "(Mouth tracking)", new Page("face", "Face Tracking")
                    .paragraph("What device are you having trouble with?")
                    .subpage("Vive Facial Tracker", "(NOT the Vive Focus 3 facial tracker)", new Page("vive", "Vive Facial Tracker"))
                )
                .subpage("Eye Tracking", "(Including upper face/eybrows)", new Page("eye", "Eye Tracking")
                    .paragraph("What device are you having trouble with?")
                    .subpage("EyeTrackVR", "(DIY Eye trackers)", new Page("etvr", "EyeTrackVR"))
                )
                .subpage("Hand Tracking", null, new Page("hand", "Hand Tracking")
                    .paragraph("What device are you having trouble with?")
                    .subpage("Leap Motion Controller 2", null, new Page("leap-motion-2", "Leap Motion Controller 2"))
                )
            )
            .generate(root);
    }
}
