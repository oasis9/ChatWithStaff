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

package com.jessible.chatwithstaff.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.jessible.chatwithstaff.ChatWithStaff;
import com.jessible.chatwithstaff.Permissions;
import com.jessible.chatwithstaff.StaffChatMode;

/**
 * Listens for players in staff chat mode.
 */
public class StaffChatListener implements Listener {
	
	private ChatWithStaff cws;
	private String perm;
	
	public StaffChatListener(ChatWithStaff cws) {
		this.cws = cws;
		this.perm = Permissions.STAFFCHAT_CMD.get();
	}

	/**
	 * Handles players talking in staff chat mode.
	 * 
	 * @param e AsyncPlayerChatEvent
	 */
	@EventHandler
	public void onPlayerChatInStaffChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		String message = e.getMessage();
		StaffChatMode scm = new StaffChatMode(cws);
		
		if (!scm.isInStaffChatMode(player)) {
			return;
		}
		e.setCancelled(true);
		
		message = scm.formatMessage(message, player);
		for (Player staff : Bukkit.getOnlinePlayers()) {
			if (staff.hasPermission(perm)) {
				staff.sendMessage(message);
			}
		}
		
	}

}
