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

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * The file creation system for all of the plugin's files.
 * 
 * @since 1.0.0.0
 */
public class FileCreator {
	
	private File ntsDir, sndDir, file;
	private YamlConfiguration yamlFile;
	private String pluginName;
	
	/**
	 * Initializes FileCreator class.
	 */
	private FileCreator() {
		this.pluginName = ChatWithStaff.getInstance().getDetails().getName();
	}
	
	/**
	 * Creates a new .yml file in the plugin's folder.
	 * 
	 * @param fileName name of file, excluding the ".yml" extension
	 */
	public FileCreator(String fileName) {
		this();
		this.ntsDir = new File("plugins" + File.separator + pluginName);
		this.file = new File(ntsDir, fileName + ".yml");
	}
	
	/**
	 * Creates a new .yml file in a second directory of the plugin's folder.
	 * 
	 * @param fileName name of file, excluding the ".yml" extension
	 * @param dirName name of directory
	 */
	public FileCreator(String fileName, String dirName) {
		this();
		this.ntsDir = new File("plugins" + File.separator + pluginName);
		this.sndDir = new File(ntsDir, dirName);
		this.file = new File(sndDir, fileName + ".yml");
	}

	/**
	 * Gets the file and returns it as a YamlConfiguration object.
	 * 
	 * @return file as a YamlConfiguration object
	 */
	public YamlConfiguration get() {
		// If the yamlFile isn't yet loaded.
		if (yamlFile == null) {
			// Load yamlFile.
			reload();
		}
		
		return yamlFile;
	}
	
	/**
	 * Saves the file.
	 */
	public void save() {
		// If the yamlFile isn't yet loaded.
		if (yamlFile == null) {
			// Load yamlFile.
			reload();
		}
		
		// Try to save yamlFile.
		try {
			yamlFile.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reloads the file. If the plugin's folder is not found, it will create
	 * a new one. If the file itself is not found, it will create a new one
	 * as well. Afterwards, it will reload the file.
	 */
	public void reload() {
		// If the plugin's main directory isn't found.
		if (!ntsDir.exists()) {
			// Create the plugin's main directory.
			ntsDir.mkdir();
		}
		
		// If the constructor allowing a second directory is used and does not
		// exist.
		if (sndDir != null && !sndDir.exists()) {
			// Create second directory.
			sndDir.mkdir();
		}
		
		// If file isn't found.
		if (!file.exists()) {
			// Try to create file.
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Load yamlFile.
		yamlFile = YamlConfiguration.loadConfiguration(file);
 	}
	
	/**
	 * Deletes the file.
	 */
	public void delete() {
		// If the file is found.
		if (file.exists()) {
			// Delete file.
			file.delete();
		}
		
		// If yamlFile is loaded.
		if (yamlFile != null) {
			// Unload yamlFile.
			yamlFile = null;
		}
	}
	
	/**
	 * Colors the string by translating all color codes starting with "&".
	 * 
	 * @param stringToColor string to color
	 * @return string colored
	 */
	public String color(String stringToColor) {
		return ChatColor.translateAlternateColorCodes('&', stringToColor);
	}

}
