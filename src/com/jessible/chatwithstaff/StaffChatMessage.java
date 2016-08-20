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

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jessible.chatwithstaff.files.ConfigFile;

/**
 * The handler for formatting staff chat messages.
 * 
 * @since 1.0.3.0
 */
public class StaffChatMessage {
	
	private String message, formattedMessage;
	private CommandSender sender;
	private ChatWithStaff cws;
	
	/**
	 * Creates a new staff chat message.
	 * 
	 * @param message Staff chat message
	 * @param sender Sender
	 */
	public StaffChatMessage(String message, CommandSender sender) {
		this.message = message;
		this.formattedMessage = null;
		this.sender = sender;
		this.cws = ChatWithStaff.getInstance();
	}

	/**
	 * Gets the staff chat message.
	 * 
	 * @return the staff chat message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the staff chat message.
	 * 
	 * @param message The staff chat message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Gets the formatted message. Call {@link StaffChatMessage#format(FormatType)}
	 * or {@link StaffChatMessage#setFormattedMessage(String)} before use.
	 * 
	 * @return formatted message if had been formatted, otherwise null
	 */
	public String getFormattedMessage() {
		return formattedMessage;
	}
	
	/**
	 * Sets the formatted message.
	 * 
	 * @param formattedMessage The new formatted message
	 */
	public void setFormattedMessage(String formattedMessage) {
		this.formattedMessage = formattedMessage;
	}
	
	/**
	 * Gets the sender.
	 * 
	 * @return sender The sender
	 */
	public CommandSender getSender() {
		return sender;
	}
	
	/**
	 * Sets the sender.
	 * 
	 * @param sender The new sender
	 */
	public void setSender(CommandSender sender) {
		this.sender = sender;
	}
	
	/**
	 * Formats a staff chat message.
	 * 
	 * @param type Format type
	 */
	public void format(FormatType type) {
		ConfigFile config = cws.getConfiguration();
		String format;
		
		// If the format is chat.
		if (type == FormatType.CHAT) {
			// Set format to chat.
			format = config.getFormatForChat();
		}
		
		// If the format is console.
		else if (type == FormatType.CONSOLE) {
			// Set format to console.
			format = config.getFormatForConsole();
		}
		
		// If the format is file - only option after this point.
		else {
			// Set format to file.
			format = config.getFormatForFile();
		}
		
		String prefix = cws.getMessages().getPrefix().trim();
		
		// Format message.
		format = format.replace("{prefix}", prefix);
		
		// If the sender is a player, thus can have a display name with formatting.
		if (sender instanceof Player) {
			Player player = (Player) sender;
			format = format.replace("{display_name}", player.getDisplayName());
		} 
		
		// Sender is console, thus cannot have a display name with formatting.
		else {
			format = format.replace("{display_name}", sender.getName());
		}
		
		// Continue to format message.
		format = format.replace("{player_name}", sender.getName());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy [HH:mm:ss]");
		Date date = new Date(System.currentTimeMillis());
		format = format.replace("{date_and_time}", dateFormat.format(date));
		
		format = config.color(format); // putting this here to avoid translating color codes used by the sender if inputed.
		format = format.replace("{message}", message);
		
		formattedMessage = format;
	}
	
	/**
	 * Sends the staff chat message in the chat format to all staff members.
	 */
	public void sendToStaff() {
		String perm = Permissions.CMD_STAFFCHAT.get();
		
		format(FormatType.CHAT);
		String msg = getFormattedMessage();
		
		for (Player staff : Bukkit.getOnlinePlayers()) {
			if (staff.hasPermission(perm)) {
				staff.sendMessage(msg);
			}
		}
	}
	
}
