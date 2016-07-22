/* ChatWithStaff - Chat with your staff in private.
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

import org.bukkit.ChatColor;

import com.jessible.chatwithstaff.files.ConfigFile;
import com.jessible.chatwithstaff.files.StaffChatLogFile;

/**
 * ChatWithStaff's log system.
 * 
 * @since 1.0.2.0
 */
public class Logger {
	
	private ChatWithStaff cws;
	private StaffChatLogFile staffChatLogFile;
	
	/**
	 * Initializes StaffChatLogFile class.
	 * 
	 * @param cws Instance of ChatWithStaff class (main class)
	 */
	public Logger(ChatWithStaff cws) {
		this.cws = cws;
		this.staffChatLogFile = new StaffChatLogFile();
	}
	
	/**
	 * Logs an info message to console.
	 * 
	 * @param message the message
	 */
	public void logToConsole(String message) {
		cws.getLogger().log(Level.INFO, strip(message));
	}
	
	/**
	 * Logs a message to console. (default level: info)
	 * 
	 * @param message the message
	 * @param level the message level (default level: info)
	 */
	public void logToConsole(String message, Level level) {
		if (level == null) {
			logToConsole(message);
		}
		
		cws.getLogger().log(level, strip(message));
	}
	
	/**
	 * Logs a message to the staff chat log file.
	 * 
	 * @param message the message
	 */
	public void logToFile(String message) {
		staffChatLogFile.log(strip(message));
	}
	
	/**
	 * Logs an info message to console and the staff chat log file.
	 * <p>
	 * In order for a message to be logged to the console,
	 * the option to do so must be enabled in the config file;
	 * same goes for logging a message to the staff chat log file.
	 */
	public void log(String message) {
		ConfigFile config = cws.getConfiguration();
		
		if (config.canLogToConsole()) {
			logToConsole(message);
		}
		
		if (config.canLogToFile()) {
			logToFile(message);
		}
	}
	
	/**
	 * Gets the StaffChatLogFile class.
	 * 
	 * @return StaffChatLogFile class
	 */
	public StaffChatLogFile getLog() {
		return staffChatLogFile;
	}
	
	/**
	 * Strips all color codes from a string.
	 * 
	 * @param stringToStrip the string to strip
	 * @return string without color codes
	 */
	private String strip(String stringToStrip) {
		return ChatColor.stripColor(stringToStrip);
	}
	
}
