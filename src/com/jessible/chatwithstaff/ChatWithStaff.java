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

import org.bukkit.plugin.java.JavaPlugin;

import com.jessible.chatwithstaff.commands.ChatWithStaffCommand;
import com.jessible.chatwithstaff.commands.StaffChatCommand;
import com.jessible.chatwithstaff.commands.StaffChatListCommand;
import com.jessible.chatwithstaff.files.ConfigFile;
import com.jessible.chatwithstaff.files.MessageFile;
import com.jessible.chatwithstaff.files.StaffChatModeFile;
import com.jessible.chatwithstaff.listeners.StaffChatListener;

/**
 * The main class for the plugin.
 * 
 * @since 1.0.0.0
 */
public class ChatWithStaff extends JavaPlugin {
	
	private static ChatWithStaff plugin;
	private PluginDetails details;
	private ConfigFile config;
	private MessageFile msgs;
	private StaffChatModeFile scm;
	private Logger log;
	
	/**
	 * Actions to be performed upon enabling the plugin.
	 */
	@Override
	public void onEnable() {
		// Setup plugin instance.
		plugin = this;
		
		// Setup plugin details.
		details = new PluginDetails();
		
		// Setup config.yml file.
		config = new ConfigFile();
		config.addDefaultValues();
		config.cacheInstantWords();
		
		// Setup messages.yml file.
		msgs = new MessageFile();
		msgs.addDefaultValues();
		
		// Load data from staff_chat_mode.yml.
		scm = new StaffChatModeFile(); // does not have any default values, nor needs to be created upon enabling
		new StaffChatMode().loadFromFile();
		
		// Setup logger.
		log = new Logger();
		log.getStaffChatLog().reload();
		
		// Register commands.
		
		// Command > /chatwithstaff
		ChatWithStaffCommand cwsCmd = new ChatWithStaffCommand();
		getCommand(cwsCmd.getName()).setExecutor(cwsCmd);
		
		// Command > /staffchat
		StaffChatCommand scCmd = new StaffChatCommand();
		getCommand(scCmd.getName()).setExecutor(scCmd);
		
		// Command > /staffchatlist
		StaffChatListCommand sclCmd = new StaffChatListCommand();
		getCommand(sclCmd.getName()).setExecutor(sclCmd);
		
		// Register listeners.
		getServer().getPluginManager().registerEvents(new StaffChatListener(), this);
	}
	
	/**
	 * Actions to be performed upon disabling the plugin.
	 */
	@Override
	public void onDisable() {
		// Save staff names in staff chat mode to staff_chat_mode.yml.
		new StaffChatMode().saveToFile();
		
		// Nullify plugin instance.
		plugin = null;
		
		// Nullify plugin details.
		details = null;
		
		// Nullify file instances.
		config = null;
		msgs = null;
		scm = null;
		log = null;
	}
	
	/**
	 * Gets the instance of the plugin.
	 * 
	 * @return instance of the plugin
	 */
	public static ChatWithStaff getInstance() {
		return plugin;
	}
	
	/**
	 * Gets the instance of PluginDetails.
	 * 
	 * @return instance of PluginDetails
	 */
	public PluginDetails getDetails() {
		return details;
	}
	
	/**
	 * Gets the instance of ConfigFile.
	 * 
	 * @return instance of ConfigFile
	 */
	public ConfigFile getConfiguration() {
		return config;
	}
	
	/**
	 * Gets the instance of MessageFile.
	 * 
	 * @return instance of MessageFile
	 */
	public MessageFile getMessages() {
		return msgs;
	}
	
	/**
	 * Gets the instance of StaffChatModeFile.
	 * 
	 * @return instance of StaffChatModeFile
	 */
	public StaffChatModeFile getStaffChatMode() {
		return scm;
	}
	
	/**
	 * Gets the instance of Logger.
	 * 
	 * @return instance of Logger
	 */
	public Logger getCWSLogger() {
		return log;
	}
	
}
