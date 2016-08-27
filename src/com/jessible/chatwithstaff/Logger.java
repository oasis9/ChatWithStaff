/*
 * ChatWithStaff - Chat with your staff in private.
 * Copyright (C) 2016 Jessible
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jessible.chatwithstaff;

import java.util.logging.Level;

import com.jessible.chatwithstaff.files.ConfigFile;
import com.jessible.chatwithstaff.files.StaffChatLogFile;

/**
 * Log system for the plugin.
 * 
 * @since 1.0.2.0
 */
public class Logger {
	
	private StaffChatLogFile staffChatLogFile;
	private ChatWithStaff plugin;
	
	/**
	 * Initializes StaffChatLogFile class.
	 */
	public Logger() {
		this.staffChatLogFile = new StaffChatLogFile();
		this.plugin = ChatWithStaff.getInstance();
	}
	
	/**
	 * Logs an info message to console.
	 * 
	 * @param message Message
	 */
	public void logToConsole(String message) {
		plugin.getLogger().log(Level.INFO, message);
	}
	
	/**
	 * Logs a message to console. (default level: info)
	 * 
	 * @param message Message
	 * @param level Message level (default level: info)
	 */
	public void logToConsole(String message, Level level) {
		if (level == null) {
			logToConsole(message);
		}
		
		plugin.getLogger().log(level, message);
	}
	
	/**
	 * Logs a message to the staff chat log file.
	 * 
	 * @param message Message
	 */
	public void logToFile(String message) {
		staffChatLogFile.log(message);
	}
	
	/**
	 * Logs an info message to console and the staff chat log file.
	 * <p>
	 * In order for a message to be logged to the console,
	 * the option to do so must be enabled in the plugin's config file;
	 * same goes for logging a message to the staff chat log file.
	 */
	public void log(String message) {
		ConfigFile config = plugin.getConfiguration();
		
		if (config.canLogToConsole()) {
			logToConsole(message);
		}
		
		if (config.canLogToFile()) {
			logToFile(message);
		}
	}
	
	/**
	 * Gets the instance of StaffChatLogFile.
	 * 
	 * @return instance of StaffChatLogFile
	 */
	public StaffChatLogFile getStaffChatLog() {
		return staffChatLogFile;
	}
	
}
