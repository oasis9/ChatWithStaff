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

import org.bukkit.command.CommandSender;

import com.jessible.chatwithstaff.files.MessageFile;

/**
 * The command helper for the plugin's commands.
 * 
 * @since 1.0.3.0
 */
public class CommandHelper {
	
	private String name, argUsage;
	private ChatWithStaff plugin;
	
	/**
	 * Initializes CommandHelper class with the command's name and argument
	 * usage.
	 * 
	 * @param name Command's name
	 * @param argUsage Command's argument usage
	 */
	public CommandHelper(String name, String argUsage) {
		this.name = name;
		this.argUsage = argUsage;
		this.plugin = ChatWithStaff.getInstance();
	}
	
	/**
	 * Gets the command's name.
	 * 
	 * @return command's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the command's argument usage.
	 * 
	 * @return command's argument usage
	 */
	public String getArgumentUsage() {
		return argUsage;
	}
	
	/**
	 * Checks if a sender has permission. This will send the no
	 * permission message from the plugin's messages.yml file if the sender
	 * doesn't have permission.
	 * 
	 * @param permission Permission
	 * @param sender Sender
	 * @return true if sender has permission, otherwise false
	 */
	public boolean hasPermission(Permission permission, CommandSender sender) {
		MessageFile msgs = plugin.getMessages();
		
		// If the sender doesn't have permission.
		if (!sender.hasPermission(permission.getPermission())) {
			// Send no permission message.
			sender.sendMessage(msgs.getNoPermission(permission));
			return false;
		}
		
		return true;
	}
	
	/**
	 * Sends a sender the invalid command message.
	 * 
	 * @param cmd Command
	 * @param argsUsed Command's arguments used
	 * @param sender Sender
	 */
	public void invalidCommand(String cmd, String[] argsUsed,
			CommandSender sender) {
		MessageFile msgs = plugin.getMessages();
		
		String cmdName = "/" + cmd;
		String cmdArgs = argsUsed.length >= 1 ? Utils.buildString(argsUsed) : "";
		String cmdUsed = cmdName + " " + cmdArgs;
		String cmdSuggest = cmdName + " " + argUsage;
		
		sender.sendMessage(msgs.getInvalidCommand(cmdUsed.trim(), cmdSuggest.trim()));
	}

}
