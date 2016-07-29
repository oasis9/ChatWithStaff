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

package com.jessible.chatwithstaff.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.jessible.chatwithstaff.ChatWithStaff;
import com.jessible.chatwithstaff.FormatType;
import com.jessible.chatwithstaff.Logger;
import com.jessible.chatwithstaff.StaffChatMessage;
import com.jessible.chatwithstaff.StaffChatMode;
import com.jessible.chatwithstaff.files.ConfigFile;

/**
 * The handler for when a player talks while their staff chat mode is on.
 * 
 * @since 1.0.0.0
 */
public class StaffChatListener implements Listener {
	
	private ChatWithStaff cws;
	
	/**
	 * Initializes StaffChatListener class.
	 *  
	 * @param cws Instance of ChatWithStaff class (main class)
	 */
	public StaffChatListener(ChatWithStaff cws) {
		this.cws = cws;
	}

	/**
	 * Handles players talking in staff chat mode.
	 * 
	 * @param e AsyncPlayerChatEvent
	 */
	@EventHandler
	public void onPlayerChatInStaffChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		String msg = e.getMessage();
		StaffChatMode scm = new StaffChatMode(cws);
		
		// If the player is not in staff chat mode.
		if (!scm.isInStaffChatMode(player)) {
			return;
		}
		e.setCancelled(true);
		
		StaffChatMessage staffMsg = new StaffChatMessage(msg, player, cws);
		
		// Send message to all staff members.
		staffMsg.sendToStaff();
		
		ConfigFile config = cws.getConfiguration();
		Logger logger = cws.getCWSLogger();
		
		if (config.canLogToConsole()) {
			// Logs <msg> to console.
			staffMsg.format(FormatType.CONSOLE);
			String msgToConsole = staffMsg.getFormattedMessage();
			logger.logToConsole(msgToConsole);
		}
		
		if (config.canLogToFile()) {
			// Logs <msg> to staff chat log file.
			staffMsg.format(FormatType.FILE);
			String msgToFile = staffMsg.getFormattedMessage();
			logger.logToFile(msgToFile);
		}
	}

}
