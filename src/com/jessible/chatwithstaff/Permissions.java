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
 * Permissions for ChatWithStaff.
 */
public enum Permissions {
	
	CHATWITHSTAFF_HELP_CMD("chatwithstaff.help"),
	CHATWITHSTAFF_RELOAD_CMD("chatwithstaff.reload"),
	STAFFCHAT_CMD("chatwithstaff.staffchat"),
	STAFFCHATLIST_CMD("chatwithstaff.staffchatlist");
	
	private String permission;
	
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
