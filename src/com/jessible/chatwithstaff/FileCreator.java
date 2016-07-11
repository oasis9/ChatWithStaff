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

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * The file creation system for all of ChatWithStaff's files.
 */
public class FileCreator {
	
	private String sep; // File separator.
	private File dir, file; // Directory folder and the newly created file.
	private YamlConfiguration yamlFile; // Yaml type file for the newly created file.
	
	/**
	 * Creates a new .yml file in the ChatWithStaff folder.
	 * 
	 * @param fileName name of file, excluding the ".yml" extension
	 */
	public FileCreator(String fileName) {
		this.sep = File.separator;
		this.dir = new File("plugins" + sep + "ChatWithStaff");
		this.file = new File(dir, fileName + ".yml");
	}
	
	/**
	 * Gets the file and returns it as a YamlConfiguration object.
	 * 
	 * @return the file as a YamlConfiguration object
	 */
	public YamlConfiguration get() {
		if (yamlFile == null) {
			reload();
		}
		return yamlFile;
	}
	
	/**
	 * Saves the file.
	 */
	public void save() {
		if (yamlFile == null) {
			reload();
		}
		try {
			yamlFile.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reloads the file. If the ChatWithStaff folder is not found, it will create
	 * a new one. If the file itself is not found, it will create a new one
	 * as well. Afterwards, it will reload the file.
	 */
	public void reload() {
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		yamlFile = YamlConfiguration.loadConfiguration(file);
 	}
	
	/**
	 * Deletes the file.
	 */
	public void delete() {
		if (file.exists()) {
			file.delete();
		}
		if (yamlFile != null) {
			yamlFile = null;
		}
	}
	
	/**
	 * Colors the string by translating all color codes starting with "&".
	 * 
	 * @param stringToColor string to color
	 * @return the string colored
	 */
	public String color(String stringToColor) {
		return ChatColor.translateAlternateColorCodes('&', stringToColor);
	}

}
