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
 * Handles ChatWithStaff's config.yml file.
 */
public class ConfigFile extends FileCreator implements YamlFile {
	
	public ConfigFile() {
		super("config");
	}
	
	@Override
	public void addDefaultValues() {
		get().addDefault("Format", "{prefix} {display_name}: &b{message}");
		get().options().copyDefaults(true);
		save();
	}
	
	/**
	 * Gets the format for staff chat.
	 *
	 * @return format
	 */
	public String getFormat() {
		return get().getString("Format");
	}

}
