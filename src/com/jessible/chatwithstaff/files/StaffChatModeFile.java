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
 * Handles ChatForStaff's staff_chat_mode.yml file.
 */
public class StaffChatModeFile extends FileCreator implements YamlFile {
	
	public StaffChatModeFile() {
		super("staff_chat_mode");
	}
	
	@Override
	public void addDefaultValues() {
		save();
	}

}
