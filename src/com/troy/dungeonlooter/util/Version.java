package com.troy.dungeonlooter.util;

public class Version {
	
	public static final int VERSION_MAJOR = 0, VERSION_MINOR = 1, VERSION_PATCH = 0;
	public static final String VERSION_INFO = "Alpha";

	public static String getVersion() {
		return VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_PATCH + " " + VERSION_INFO;
	}
	
	
	public static String getWindowTitle(){
		return "The Dungeon Looter " + Version.getVersion();
	}
}
