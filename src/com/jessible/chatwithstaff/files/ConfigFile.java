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

package com.jessible.chatwithstaff.files;

import com.jessible.chatwithstaff.FileCreator;

/**
 * The handler for the config.yml file.
 * 
 * @since 1.0.0.0
 */
public class ConfigFile extends FileCreator implements YamlFile {
	
	/**
	 * Initializes ConfigFile class.
	 */
	public ConfigFile() {
		super("config");
	}
	
	@Override
	public void addDefaultValues() {
		// Format options
		get().addDefault("Format_For_Chat", "{prefix} {display_name}: &b{message}");
		get().addDefault("Format_For_Console", "{player_name}: {message}");
		get().addDefault("Format_For_File", "{date_and_time} {player_name}: {message}");
		
		// Log options
		get().addDefault("Log_To_Console", true);
		get().addDefault("Log_To_File", true);
		get().options().copyDefaults(true);
		save();
	}
	
	/**
	 * Gets the format for chat.
	 *
	 * @return the format for chat
	 */
	public String getFormatForChat() {
		return get().getString("Format_For_Chat");
	}
	
	/**
	 * Gets the format for console.
	 *
	 * @return the format for console
	 */
	public String getFormatForConsole() {
		return get().getString("Format_For_Console");
	}
	
	/**
	 * Gets the format for file.
	 *
	 * @return the format for file
	 */
	public String getFormatForFile() {
		return get().getString("Format_For_File");
	}

	/**
	 * Checks whether or not log to console is set to true.
	 * 
	 * @return true if true, false if false
	 */
	public boolean canLogToConsole() {
		return get().getBoolean("Log_To_Console");
	}

	
	/**
	 * Checks whether or not log to file is set to true.
	 * 
	 * @return true if true, false if false
	 */
	public boolean canLogToFile() {
		return get().getBoolean("Log_To_File");
	}
	
}
