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
	private String staffChatPerm;
	
	/**
	 * Initializes StaffChatMode class.
	 */
	public StaffChatMode() {
		this.scmFile = ChatWithStaff.getInstance().getStaffChatMode();
		this.staffChatPerm = Permission.CMD_STAFFCHAT.getPermission();
	}
	
	/**
	 * Adds a sender to staff chat mode.
	 * 
	 * @param sender Sender
	 */
	public void add(CommandSender sender) {
		playersInStaffChat.add(sender.getName());
	}
	
	/**
	 * Adds a player to staff chat mode.
	 * 
	 * @param player Player
	 */
	public void add(Player player) {
		playersInStaffChat.add(player.getName());
	}
	
	/**
	 * Adds a name to staff chat mode.
	 * 
	 * @param name Name
	 */
	public void add(String name) {
		playersInStaffChat.add(name);
	}
	
	/**
	 * Removes a sender from staff chat mode.
	 * 
	 * @param sender Sender
	 */
	public void remove(CommandSender sender) {
		playersInStaffChat.remove(sender.getName());
	}
	
	/**
	 * Removes a player from staff chat mode.
	 * 
	 * @param player Player
	 */
	public void remove(Player player) {
		playersInStaffChat.remove(player.getName());
	}
	
	/**
	 * Removes a name from staff chat mode.
	 * 
	 * @param name Name
	 */
	public void remove(String name) {
		playersInStaffChat.remove(name);
	}
	
	/**
	 * Checks if a sender is in staff chat mode.
	 * 
	 * @param sender Sender
	 * @return true if the sender is in staff chat mode, otherwise false
	 */
	public boolean isInStaffChatMode(CommandSender sender) {
		return playersInStaffChat.contains(sender.getName());
	}

	/**
	 * Checks if a player is in staff chat mode.
	 * 
	 * @param player Player
	 * @return true if the player is in staff chat mode, otherwise false
	 */
	public boolean isInStaffChatMode(Player player) {
		return playersInStaffChat.contains(player.getName());
	}
	
	/**
	 * Checks if a name is in staff chat mode.
	 * 
	 * @param name Name
	 * @return true if the name is in staff chat mode, otherwise false
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
	
}
