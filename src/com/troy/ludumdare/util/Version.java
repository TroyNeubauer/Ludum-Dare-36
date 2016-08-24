package com.troy.ludumdare.util;

/** Represents the version of the game **/
public class Version {
	
	/** The version **/
	public static final int VERSION_MAJOR = 0, VERSION_MINOR = 1, VERSION_PATCH = 0;
	
	/** some info about the version such as Alpha Beta or release **/
	public static final String VERSION_INFO = "Alpha";
	
	/** @return The complete version as a String **/
	public static String getVersion() {
		return VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_PATCH + " " + VERSION_INFO;
	}
	
	/** @return The title used for the window **/
	public static String getWindowTitle(){
		return "Ludum Dare 36 Game " + Version.getVersion();
	}
}
