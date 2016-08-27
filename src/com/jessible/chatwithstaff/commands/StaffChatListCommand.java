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
import com.jessible.chatwithstaff.CommandHelper;
import com.jessible.chatwithstaff.Permission;
import com.jessible.chatwithstaff.StaffChatMode;
import com.jessible.chatwithstaff.Utils;
import com.jessible.chatwithstaff.files.MessageFile;

/**
 * The handler for the /staffchatlist command.
 * 
 * @since 1.0.1.0
 */
public class StaffChatListCommand extends CommandHelper implements CommandExecutor {
	
	private Permission perm;
	private ChatWithStaff cws;
	
	/**
	 * Initializes StaffChatListCommand class.
	 */
	public StaffChatListCommand() {
		super("staffchatlist", "");
		this.perm = Permission.CMD_STAFFCHATLIST;
		this.cws = ChatWithStaff.getInstance();
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
	 * @param sender Command sender
	 * @param baseCmd Base command (/staffchatlist)
	 * @param cmd Command that is being used ("/scl" - see plugin.yml)
	 * @param args Command arguments (none)
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
		
		// If "/staffchatlist" is executed with arguments.
		if (args.length != 0) {
			// Send invalid command message.
			invalidCommand(cmd, args, sender);
			return true;
		}
		// "/staffchatlist" is executed without arguments.
		
		StaffChatMode scm = new StaffChatMode();
		int staffAmount = scm.getAmount();
		String isOrAnd = staffAmount == 1 ? msgs.getWordIs() : msgs.getWordAre();
		String sOrNoS = staffAmount == 1 ? "" : "s"; // s or no s
		String prefix = msgs.getPrefix();
		
		// Format list message.
		for (String listMsg : msgs.getList()) {
			listMsg = listMsg.replace("{word}", isOrAnd)
					.replace("{verb}", isOrAnd) // support for v1.0.1.1
					.replace("{conjunction|verb}", isOrAnd) // support for v1.0.1.0
					.replace("{amount}", String.valueOf(staffAmount))
					.replace("{s}", sOrNoS);
			
			// Get all online staff names. (as of v1.0.3.0, this needs to be reworked)
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
