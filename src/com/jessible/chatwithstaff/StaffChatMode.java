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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jessible.chatwithstaff.files.StaffChatModeFile;

/**
 * The handler for adding, removing, saving, and loading players to and from staff
 * chat mode. Staff chat mode is defined as a player who has toggled
 * their staff chat mode by using /staffchat without any arguments.
 * 
 * @since 1.0.0.0
 */
public class StaffChatMode {
	
	private static List<String> playersInStaffChat = new ArrayList<String>();
	private StaffChatModeFile scmFile;
	private ChatWithStaff cws;
	private String staffChatPerm;
	
	/**
	 * Initializes StaffChatMode class.
	 *  
	 * @param cws Instance of ChatWithStaff class (main class)
	 */
	public StaffChatMode(ChatWithStaff cws) {
		this.scmFile = cws.getStaffChatMode();
		this.cws = cws;
		this.staffChatPerm = Permissions.STAFFCHAT_CMD.get();
	}
	
	/**
	 * Adds a player to staff chat mode.
	 * 
	 * @param sender player's name
	 */
	public void add(CommandSender sender) {
		playersInStaffChat.add(sender.getName());
	}
	
	/**
	 * Adds a player to staff chat mode.
	 * 
	 * @param player player's name
	 */
	public void add(Player player) {
		playersInStaffChat.add(player.getName());
	}
	
	/**
	 * Adds a player to staff chat mode.
	 * 
	 * @param sender player's name
	 */
	public void add(String name) {
		playersInStaffChat.add(name);
	}
	
	/**
	 * Removes a player from staff chat mode.
	 * 
	 * @param sender player's name
	 */
	public void remove(CommandSender sender) {
		playersInStaffChat.remove(sender.getName());
	}
	
	/**
	 * Removes a player from staff chat mode.
	 * 
	 * @param player player's name
	 */
	public void remove(Player player) {
		playersInStaffChat.remove(player.getName());
	}
	
	/**
	 * Removes a player from staff chat mode.
	 * 
	 * @param sender player's name
	 */
	public void remove(String name) {
		playersInStaffChat.remove(name);
	}
	
	/**
	 * Checks if the player is in staff chat mode.
	 * 
	 * @param sender the player
	 * @return true if the player is in staff chat mode, otherwise false
	 */
	public boolean isInStaffChatMode(CommandSender sender) {
		return playersInStaffChat.contains(sender.getName());
	}

	/**
	 * Checks if the player is in staff chat mode.
	 * 
	 * @param player the player
	 * @return true if the player is in staff chat mode, otherwise false
	 */
	public boolean isInStaffChatMode(Player player) {
		return playersInStaffChat.contains(player.getName());
	}
	
	/**
	 * Checks if the player is in staff chat mode.
	 * 
	 * @param name the player
	 * @return true if the player is in staff chat mode, otherwise false
	 */
	public boolean isInStaffChatMode(String name) {
		return playersInStaffChat.contains(name);
	}
	
	/**
	 * Gets the names of the players viewing staff chat. Includes players in
	 * staff chat mode and players not in staff chat mode; as long as the
	 * player has permission to access staff chat, they're "viewing staff chat."
	 * 
	 * @return names of the players viewing staff chat.
	 */
	public String[] getNames() {
		List<String> staffNames = new ArrayList<String>();
		
		// Add names of staff to staffNames.
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.hasPermission(staffChatPerm)) {
				staffNames.add(player.getName());
			}
		}
		
		// Turn staffNames into a string array.
		String[] names = new String[staffNames.size()];
		int x = 0;
		for (String name : staffNames) {
			names[x] = name;
			x++;
		}
		
		return names;
	}
	
	/**
	 * Gets the amount of players viewing staff chat. Includes players in
	 * staff chat mode and players not in staff chat mode; as long as the
	 * player has permission to access staff chat, they're "viewing staff chat."
	 * 
	 * @return amount of players viewing staff chat.
	 */
	public int getAmount() {
		List<String> staffNames = new ArrayList<String>();
		
		// Add names of staff to staffNames.
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.hasPermission(staffChatPerm)) {
				staffNames.add(player.getName());
			}
		}
		
		return staffNames.size();
	}
	
	/**
	 * Saves all of the names of the players in staff chat 
	 * to the staff_chat_mode.yml file.
	 */
	public void saveToFile() {
		scmFile.get().set("Names", playersInStaffChat);
		scmFile.save();
	}

	/**
	 * Loads all of the names of the players in staff chat 
	 * from the staff_chat_mode.yml file, then deletes the file.
	 */
	public void loadFromFile() {
		for (String name : scmFile.get().getStringList("Names")) {
			playersInStaffChat.add(name);
		}
		scmFile.delete();
	}
	
	/**
	 * Formats a message the way its defined in ChatWithStaff's config.yml file.
	 * 
	 * @param message the message
	 * @param sender the one in staff chat
	 * @return message formatted
	 */
	public String formatMessage(String message, CommandSender sender) {
		String format = cws.getConfiguration().getFormat();
		String prefix = cws.getMessages().getPrefix().trim();
		
		// Format message.
		format = format.replace("{prefix}", prefix);
		
		// If the sender is a player, thus can have a display name with formatting.
		if (sender instanceof Player) {
			format = format.replace("{display_name}", ((Player) sender).getDisplayName());
		} 
		
		// Sender is console, thus cannot have a display name with formatting.
		else {
			format = format.replace("{display_name}", sender.getName());
		}
		
		// Continue to format message.
		format = format.replace("{player_name}", sender.getName());
		format = scmFile.color(format); // putting this here to avoid translating color codes used by the sender if inputed.
		format = format.replace("{message}", message);
		
		return format;
	}
	
}
