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
 * The command helper for ChatWithStaff's commands.
 * 
 * @since 1.0.3.0
 */
public class CommandHelper {
	
	private String argUsage;
	private ChatWithStaff cws;
	
	/**
	 * Initializes CommandHelper class with the command's argument usage.
	 * 
	 * @param argUsage Command's argument usage
	 */
	public CommandHelper(String argUsage) {
		this.argUsage = argUsage;
		this.cws = ChatWithStaff.getInstance();
	}
	
	/**
	 * Checks if a sender has permission. This will send the no
	 * permission message from ChatWithStaff's messages.yml file if the sender
	 * doesn't have permission.
	 * 
	 * @param permission The permission
	 * @param sender The sender
	 * @return true if the sender has permission, otherwise false
	 */
	public boolean hasPermission(Permissions permission, CommandSender sender) {
		MessageFile msgs = cws.getMessages();
		String perm = permission.get();
		
		if (!sender.hasPermission(perm)) {
			sender.sendMessage(msgs.getNoPermission(perm));
			return false;
		}
		
		return true;
	}
	
	/**
	 * Sends a sender the invalid command message.
	 * 
	 * @param cmd The command
	 * @param argsUsed The command's arguments used
	 * @param sender The sender
	 */
	public void invalidCommand(String cmd, String[] argsUsed,
			CommandSender sender) {
		MessageFile msgs = cws.getMessages();
		
		String cmdName = "/" + cmd;
		String cmdArgs = argsUsed.length >= 1 ? Utils.buildString(argsUsed) : "";
		String cmdUsed = cmdName + " " + cmdArgs;
		String cmdSuggest = cmdName + " " + argUsage;
		
		sender.sendMessage(msgs.getInvalidCommand(cmdUsed.trim(), cmdSuggest.trim()));
	}

	/**
	 * Gets the command's usage.
	 * 
	 * @param cmd The command
	 * @return command usage
	 */
	public String getCommandUsage(String cmd) {
		String cmdUsage = "/" + cmd + " " + argUsage;
		
		return cmdUsage.trim();
	}

}
