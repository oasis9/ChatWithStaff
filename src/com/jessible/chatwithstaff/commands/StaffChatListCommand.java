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

import com.jessible.chatwithstaff.ChatWithStaff;
import com.jessible.chatwithstaff.Permissions;
import com.jessible.chatwithstaff.StaffChatMode;
import com.jessible.chatwithstaff.Utils;
import com.jessible.chatwithstaff.files.MessageFile;

/**
 * The handler for the /staffchatlist command.
 * 
 * @since 1.0.1.0
 */
public class StaffChatListCommand implements CommandExecutor {
	
	private ChatWithStaff cws;
	private String perm;
	
	/**
	 * Initializes StaffChatListCommand class.
	 *  
	 * @param cws Instance of ChatWithStaff class (main class)
	 */
	public StaffChatListCommand(ChatWithStaff cws) {
		this.cws = cws;
		this.perm = Permissions.STAFFCHATLIST_CMD.get();
	}

	/**
	 * The /staffchatlist command. 
	 * <p>
	 * <strong>/staffchatlist</strong>
	 * <ul>
	 * 		<li><strong>Example usage</strong>: /staffchatlist.</li>
	 * 		<li><strong>Description</strong>: Shows all staff members viewing
	 * 			staff chat.
	 * 		</li>
	 * </ul>
	 * 
	 * @param sender the command sender
	 * @param cmd the command
	 * @param s the shortcut/alias that is being used (such as "/scl;"
	 * 			see plugin.yml)
	 * @param args the command arguments (there are none for this command)
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
		
		// If there are arguments to "/staffchatlist."
		if (args.length != 0) {
			// Send invalid command message.
			String cmdName = "/" + cmd.getName();
			String cmdUsed = cmdName + " " + Utils.buildString(args);
			sender.sendMessage(msgs.getInvalidCommand(cmdUsed, cmdName));
			return true;
		}
		
		// "/staffchatlist" is executed.
		StaffChatMode scm = new StaffChatMode(cws);
		int staffAmount = scm.getAmount();
		String andOrIs = staffAmount == 1 ? msgs.getVerb1() : msgs.getVerb2();
		String sOrNoS = staffAmount == 1 ? "" : "s"; // s or no s
		String prefix = msgs.getPrefix();
		
		// Format list message.
		for (String listMsg : msgs.getList()) {
			listMsg = listMsg.replace("{verb}", andOrIs);
			listMsg = listMsg.replace("{conjunction|verb}", andOrIs); // support for v1.0.1.0
			listMsg = listMsg.replace("{amount}", String.valueOf(staffAmount));
			listMsg = listMsg.replace("{s}", sOrNoS);
			
			// Get all online staff names.
			String staff = "None";
			boolean onStaffLine = listMsg.contains("{staff}");
			if (onStaffLine && staffAmount > 0) {
				if (listMsg.contains("{staff}")) {
					String colorCodes = msgs.getColorCodeBefore("{s", listMsg);
					staff = Utils.buildString(scm.getNames(), "&f, " + colorCodes);
				}
			}
			listMsg = listMsg.replace("{staff}", staff);
			
			// Send formatted list message.
			sender.sendMessage(prefix + msgs.color(listMsg));
		}
		return true;
	}
	
}
