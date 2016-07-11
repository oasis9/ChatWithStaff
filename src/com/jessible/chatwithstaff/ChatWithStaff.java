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

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.jessible.chatwithstaff.commands.ChatWithStaffCommand;
import com.jessible.chatwithstaff.commands.StaffChatCommand;
import com.jessible.chatwithstaff.commands.StaffChatListCommand;
import com.jessible.chatwithstaff.files.ConfigFile;
import com.jessible.chatwithstaff.files.MessageFile;
import com.jessible.chatwithstaff.files.StaffChatModeFile;
import com.jessible.chatwithstaff.listeners.StaffChatListener;

/**
 * Enables and disables ChatWithStaff.
 */
public class ChatWithStaff extends JavaPlugin {
	
	private ConfigFile configFile;
	private MessageFile msgFile;
	private StaffChatModeFile scmFile;
	
	/**
	 * Actions to be performed upon enabling ChatWithStaff.
	 */
	@Override
	public void onEnable() {
		configFile = new ConfigFile();
		configFile.addDefaultValues();
		
		msgFile = new MessageFile();
		msgFile.addDefaultValues();
		
		scmFile = new StaffChatModeFile(); // does not have any default values, nor needs to be created upon enabling
		new StaffChatMode(this).loadFromFile();
		
		getCommand("chatwithstaff").setExecutor(new ChatWithStaffCommand(this));
		getCommand("staffchat").setExecutor(new StaffChatCommand(this));
		getCommand("staffchatlist").setExecutor(new StaffChatListCommand(this));
		
		getServer().getPluginManager().registerEvents(new StaffChatListener(this), this);
	}
	
	/**
	 * Actions to be performed upon disabling ChatWithStaff.
	 */
	@Override
	public void onDisable() {
		new StaffChatMode(this).saveToFile();
		configFile = null;
		msgFile = null;
		scmFile = null;
	}
	
	/**
	 * Gets the ConfigFile class.
	 * 
	 * @return ConfigFile class
	 */
	public ConfigFile getConfiguration() {
		return configFile;
	}
	
	/**
	 * Gets the MessageFile class.
	 * 
	 * @return MessageFile class
	 */
	public MessageFile getMessages() {
		return msgFile;
	}
	
	/**
	 * Gets the StaffChatModeFile class.
	 * 
	 * @return StaffChatModeFile class
	 */
	public StaffChatModeFile getStaffChatMode() {
		return scmFile;
	}
	
	/**
	 * Colors the string by translating all color codes starting with "&".
	 * 
	 * @param stringToColor string to color
	 * @return the string colored
	 */
	public String color(String stringToColor) {
		return ChatColor.translateAlternateColorCodes('&', stringToColor);
	}
	
}
