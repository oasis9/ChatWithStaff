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

package com.jessible.chatwithstaff.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jessible.chatwithstaff.ChatWithStaff;
import com.jessible.chatwithstaff.CommandHelper;
import com.jessible.chatwithstaff.FormatType;
import com.jessible.chatwithstaff.Logger;
import com.jessible.chatwithstaff.Permission;
import com.jessible.chatwithstaff.StaffChatMessage;
import com.jessible.chatwithstaff.StaffChatMode;
import com.jessible.chatwithstaff.Utils;
import com.jessible.chatwithstaff.files.ConfigFile;
import com.jessible.chatwithstaff.files.MessageFile;

/**
 * The handler for the /staffchat command.
 * 
 * @since 1.0.0.0
 */
public class StaffChatCommand extends CommandHelper implements CommandExecutor {
	
	private Permission perm;
	private ChatWithStaff cws;
	
	/**
	 * Initializes StaffChatCommand class.
	 */
	public StaffChatCommand() {
		super("staffchat", "[message]");
		this.perm = Permission.CMD_STAFFCHAT;
		this.cws = ChatWithStaff.getInstance();
	}
	
	/**
	 * The /staffchat command. 
	 * <p>
	 * <strong>/staffchat</strong>
	 * <ul>
	 * 		<li><strong>Example usage</strong>: /staffchat.</li>
	 * 		<li><strong>Description</strong>: Toggles sender's staff chat mode.
	 * 		</li>
	 * </ul>
	 * 
	 * <strong>/staffchat message</strong>
	 * <ul>
	 * 		<li><strong>Example usage</strong>: /staffchat Hello, I'm talking
	 * 			in staff chat.</li>
	 * 		<li><strong>Description</strong>: Sends a message to all staff
	 * 			members.
	 * 		</li>
	 * </ul>
	 * 
	 * @param sender Command sender
	 * @param baseCmd Base command (/staffchat)
	 * @param cmd Command that is being used ("/sc" - see plugin.yml)
	 * @param args Command arguments (such as "Hello there")
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command baseCmd, String cmd,
			String[] args) {
		MessageFile msgs = cws.getMessages();
		
		// If the sender doesn't have permission.
		if (!hasPermission(perm, sender)) {
			// hasPermission(Permission, CommandSender) sends the no
			// permission message.
			return true;
		}
		// The sender has permission.
		
		// If "/staffchat" is executed with no arguments.
		if (args.length == 0) {
			// The sender is toggling their staff chat mode.

			/* Doing a check to see if the sender is the console because:
			 * #1. Console operators cannot chat simply by typing whatever
			 * 	they please without the consult of a command before their message.
			 * 
			 * #2. StaffChatMode#saveToFile(), which gets called when the plugin
			 * disables itself, does not need to write the name "CONSOLE" in the
			 * staff_chat_mode.yml file. (see #1)
			 */
			if (!(sender instanceof Player)) {
				String cmdName = "/" + cmd;
				sender.sendMessage(msgs.getNoConsole(cmdName,
						cmdName + " <message>"));
				return true;
			}
			// The sender is a player.
			
			StaffChatMode scm = new StaffChatMode();
			
			// If sender is in staff chat mode.
			if (scm.isInStaffChatMode(sender)) {
				// Staff chat mode toggle off
				scm.remove(sender);
				sender.sendMessage(msgs.getToggleOff());
			} 
			
			// If the sender is not in staff chat mode
			else {
				// Staff chat mode toggle on
				scm.add(sender);
				sender.sendMessage(msgs.getToggleOn());
			}
			return true;
		}
		// "/staffchat <msg>" is executed. 
		
		String msg = Utils.buildString(args);
		StaffChatMessage staffMsg = new StaffChatMessage(msg, sender);
		ConfigFile config = cws.getConfiguration();
		Logger logger = cws.getCWSLogger();
		
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
		return true;
	}

}
