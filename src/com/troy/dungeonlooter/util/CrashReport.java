package com.troy.dungeonlooter.util;

import java.io.*;
import java.text.*;
import java.util.*;

public class CrashReport {

	/** Description of the crash report. */
	private final String description;

	/** Some information about the system. */
	private String info;

	/** The Throwable that is the "cause" for this crash and Crash Report. */
	private final Throwable cause;

	public CrashReport(String decs, Throwable throwable) {
		this.description = decs;
		this.cause = throwable;
		this.populateInfo();
	}

	private void populateInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("Java version " + System.getProperty("java.version") + ", " + System.getProperty("java.vendor") + "\n");

		sb.append("Operating system " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version "
			+ System.getProperty("os.version") + "\n");

		sb.append("Java VM Version " + System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), "
			+ System.getProperty("java.vm.vendor") + "\n");

		Runtime runtime = Runtime.getRuntime();
		long maxMemory = runtime.maxMemory();
		long totalMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		long maxMemoryMB = maxMemory / 1024L / 1024L;
		long totalMemoryMB = totalMemory / 1024L / 1024L;
		long freeMemoryMB = freeMemory / 1024L / 1024L;

		sb.append("Memory " + freeMemory + " bytes (" + freeMemoryMB + " MB) / " + totalMemory + " bytes (" + totalMemoryMB + " MB) up to "
			+ maxMemory + " bytes (" + maxMemoryMB + " MB)");

		this.info = sb.toString();
	}

	public String getCompleteReport() {
		StringBuilder sb = new StringBuilder();
		sb.append("---- The Dungeon Looter Crash Report ----\n\n");
		sb.append("Time: ");
		sb.append((new SimpleDateFormat()).format(new Date()) + "\n\n");
		sb.append("System Info:\n");
		sb.append(info + "\n\n");
		sb.append("Description: ");
		sb.append(this.description);
		sb.append("\n");
		sb.append(this.getCauseStackTraceOrString());
		return sb.toString();
	}

	public String getCauseStackTraceOrString() {
		StringWriter stringwriter = null;
		PrintWriter printwriter = null;
		Throwable throwable = this.cause;

		if (throwable.getMessage() == null) {
			if (throwable instanceof NullPointerException) {
				throwable = new NullPointerException(this.description);
			} else if (throwable instanceof StackOverflowError) {
				throwable = new StackOverflowError(this.description);
			} else if (throwable instanceof OutOfMemoryError) {
				throwable = new OutOfMemoryError(this.description);
			}

			throwable.setStackTrace(this.cause.getStackTrace());
		}

		String s = throwable.toString();

		try {
			stringwriter = new StringWriter();
			printwriter = new PrintWriter(stringwriter);
			throwable.printStackTrace(printwriter);
			s = stringwriter.toString();
		} finally {
			try {
				stringwriter.close();
				printwriter.close();
			} catch (IOException ignore) {
			}
		}

		return s;
	}

	public Throwable getCrashCause() {
		return this.cause;
	}

	public CrashReport print() {
		System.err.println(this.getCompleteReport());
		return this;
	}

}
