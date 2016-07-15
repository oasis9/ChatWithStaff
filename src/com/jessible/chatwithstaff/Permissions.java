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

/**
 * The permissions for ChatWithStaff.
 * 
 * @since 1.0.0.0
 */
public enum Permissions {
	
	/**
	 * Permission for "/chatwithstaff help."
	 */
	CHATWITHSTAFF_HELP_CMD("chatwithstaff.help"),
	
	/**
	 * Permission for "/chatwithstaff reload."
	 */
	CHATWITHSTAFF_RELOAD_CMD("chatwithstaff.reload"),
	
	/**
	 * Permission for "/staffchat" and "/staffchat [msg]."
	 */
	STAFFCHAT_CMD("chatwithstaff.staffchat"),
	
	/**
	 * Permission for "/staffchatlist."
	 */
	STAFFCHATLIST_CMD("chatwithstaff.staffchatlist");
	
	private String permission;
	
	/**
	 * Initializes Permissions enum.
	 * 
	 * @param permission the permission
	 */
	private Permissions(String permission) {
		this.permission = permission;
	}
	
	/**
	 * Gets the permission.
	 * 
	 * @return permission
	 */
	public String get() {
		return permission;
	}

}
