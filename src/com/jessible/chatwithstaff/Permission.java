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

/**
 * The permissions for the plugin's commands and other command-related actions.
 * 
 * @since 1.0.0.0
 */
public enum Permission {
	
	/**
	 * Permission for "/chatwithstaff help."
	 */
	CMD_CHATWITHSTAFF_HELP("help"),
	
	/**
	 * Permission for "/chatwithstaff reload."
	 */
	CMD_CHATWITHSTAFF_RELOAD("reload"),
	
	/**
	 * Permission for "/staffchat" and "/staffchat [msg]."
	 */
	CMD_STAFFCHAT("staffchat"),
	
	/**
	 * Permission for "/staffchatlist."
	 */
	CMD_STAFFCHATLIST("staffchatlist");
	
	private String permission, pluginName;
	
	/**
	 * Initializes Permission enum.
	 * 
	 * @param permission Permission.
	 */
	private Permission(String permission) {
		this.pluginName = ChatWithStaff.getInstance().getDescription().getName();
		this.permission = pluginName.toLowerCase() + "." + permission;
	}
	
	/**
	 * Gets the permission.
	 * 
	 * @return permission
	 */
	public String getPermission() {
		return permission;
	}

}
