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

package com.jessible.chatwithstaff.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jessible.chatwithstaff.ChatWithStaff;
import com.jessible.chatwithstaff.Permissions;
import com.jessible.chatwithstaff.StaffChatMode;
import com.jessible.chatwithstaff.Utils;
import com.jessible.chatwithstaff.files.MessageFile;

/**
 * The handler for the /staffchat command.
 * 
 * @since 1.0.0.0
 */
public class StaffChatCommand implements CommandExecutor {
	
	private ChatWithStaff cws;
	private String perm;
	
	/**
	 * Initializes StaffChatCommand class.
	 *  
	 * @param cws Instance of ChatWithStaff class (main class)
	 */
	public StaffChatCommand(ChatWithStaff cws) {
		this.cws = cws;
		this.perm = Permissions.STAFFCHAT_CMD.get();
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
	 * <strong>/staffchat [msg]</strong>
	 * <ul>
	 * 		<li><strong>Example usage</strong>: /staffchat Hello, I'm talking
	 * 			in staff chat.</li>
	 * 		<li><strong>Description</strong>: Sends a message to all staff
	 * 			members.
	 * 		</li>
	 * </ul>
	 * 
	 * @param sender the command sender
	 * @param cmd the command
	 * @param s the shortcut/alias that is being used (such as "/sc;"
	 * 			see plugin.yml)
	 * @param args the command arguments (such as "Hello there")
	 * 
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s,
			String[] args) {
		MessageFile msgs = cws.getMessages();
		
		// If the sender doesn't have permission.
		if (!sender.hasPermission(perm)) {
			// Send no permission message.
			sender.sendMessage(msgs.getNoPermission(perm));
			return true;
		}
		
		// If "/staffchat" is executed.
		if (args.length == 0) {

			/* Doing a check to see if the sender is the console because:
			 * #1. Console operators cannot chat simply by typing whatever
			 * 	they please without the consult of a command before their message.
			 * 
			 * #2. StaffChatMode#saveToFile(), which gets called when the plugin
			 * disables itself, does not need to write the name "CONSOLE" in the
			 * staff_chat_mode.yml file. (see #1)
			 */
			if (!(sender instanceof Player)) {
				String cmdName = "/" + cmd.getName();
				String cmdUsed = cmdName + " " + Utils.buildString(args);
				sender.sendMessage(msgs.getNoConsole(cmdUsed, cmdName + " [message]"));
				return true;
			}
			
			StaffChatMode scm = new StaffChatMode(cws);
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
		
		// "/staffchat <msg>" is executed. Send <msg> to all staff members.
		StaffChatMode scm = new StaffChatMode(cws);
		String message = scm.formatMessage(Utils.buildString(args), sender);
		for (Player staff : Bukkit.getOnlinePlayers()) {
			if (staff.hasPermission(perm)) {
				staff.sendMessage(message);
			}
		}
		return true;
	}

}
