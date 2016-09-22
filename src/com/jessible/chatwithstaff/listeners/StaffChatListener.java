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
import com.jessible.chatwithstaff.Permission;
import com.jessible.chatwithstaff.StaffChatMessage;
import com.jessible.chatwithstaff.StaffChatMode;
import com.jessible.chatwithstaff.files.ConfigFile;

/**
 * The handler for when a player talks while in staff chat mode.
 * 
 * @since 1.0.0.0
 */
public class StaffChatListener implements Listener {
	
	private String perm;
	private ChatWithStaff plugin;
	
	/**
	 * Initializes StaffChatListener class.
	 */
	public StaffChatListener() {
		this.perm = Permission.CMD_STAFFCHAT.getPermission();
		this.plugin = ChatWithStaff.getInstance();
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
		String lowerMsg = msg.toLowerCase();
		boolean instant = false;
		
		// If the player has permission to talk in staff chat.
		if (!player.hasPermission(perm)) {
			return;
		}
		
		// Loop through instant words.
		for (String word : plugin.getConfiguration().getInstantWords()) {
			// If the message contains an instant word.
			if (lowerMsg.contains(word)) {
				instant = true;
				break;
			}
		}
		
		// If the message doesn't contain an instant word.
		if (!instant) {
			StaffChatMode scm = new StaffChatMode();
			
			// If the player is not in staff chat mode.
			if (!scm.isInStaffChatMode(player)) {
				return;
			}
		}
		
		// All checks went through good. Getting ready to send message.
		e.setCancelled(true);
		
		StaffChatMessage staffMsg = new StaffChatMessage(msg, player);
		ConfigFile config = plugin.getConfiguration();
		Logger logger = plugin.getCWSLogger();
		
		// Send message to all staff members.
		staffMsg.sendToStaff();
		
		// If the message can be logged to console.
		if (config.canLogToConsole()) {
			// Log message to console.
			staffMsg.format(FormatType.CONSOLE);
			String msgToConsole = staffMsg.getFormattedMessage();
			logger.logToConsole(msgToConsole);
		}
		
		// If the message can be logged to the log file.
		if (config.canLogToFile()) {
			// Log message to staff chat log file.
			staffMsg.format(FormatType.FILE);
			String msgToFile = staffMsg.getFormattedMessage();
			logger.logToFile(msgToFile);
		}
	}

}
