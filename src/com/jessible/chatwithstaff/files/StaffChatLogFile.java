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

package com.jessible.chatwithstaff.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.jessible.chatwithstaff.ChatWithStaff;

/**
 * The handler for the staff_chat_log.txt file.
 * 
 * @since 1.0.2.0
 */
public class StaffChatLogFile {
	
	private String pluginName;
	private File dir, log;
	
	/**
	 * Initializes StaffChatLogFile class.
	 */
	public StaffChatLogFile() {
		this.pluginName = ChatWithStaff.getInstance().getDetails().getName();
		this.dir = new File("plugins" + File.separator + pluginName);
		this.log = new File(dir, "staff_chat_log.txt");
	}

	/**
	 * Gets the log file.
	 * 
	 * @return log file
	 */
	public File get() {
		if (!log.exists()) {
			reload();
		}
		
		return log;
	}
	
	/**
	 * Reloads the staff chat log file. If the the plugin's folder is not found, it will create
	 * a new one. If the staff chat log file itself is not found, it will create a new one
	 * as well.
	 */
	public void reload() {
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		if (!log.exists()) {
			try {
				log.createNewFile();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Logs a message to the staff chat log file.
	 * 
	 * @param message Message
	 */
	public void log(String message) {
		try {
			// Logs message to the staff chat log file.
			FileWriter fWriter = new FileWriter(log, true);
			BufferedWriter writer = new BufferedWriter(fWriter);
			writer.write(message + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
