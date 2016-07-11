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

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.jessible.chatwithstaff.ChatWithStaff;
import com.jessible.chatwithstaff.Permissions;
import com.jessible.chatwithstaff.Utils;
import com.jessible.chatwithstaff.files.MessageFile;

/**
 * Handles the /chatforstaff command.
 */
public class ChatWithStaffCommand implements CommandExecutor {
	
	private ChatWithStaff cws;
	private String permHelp, permReload, version;
	private String[] info;
	
	public ChatWithStaffCommand(ChatWithStaff cws) {
		this.cws = cws;
		this.permHelp = Permissions.CHATWITHSTAFF_HELP_CMD.get();
		this.permReload = Permissions.CHATWITHSTAFF_RELOAD_CMD.get();
		this.version = cws.getDescription().getVersion();
		this.info = new String[] {
				"ChatWithStaff " + cws.color("&bv" + version),
				"Developed by " + cws.color("&b" + "Jessible"), 
				"https://www.spigotmc.org/resources/25182/"};
	}

	/**
	 * Shows detailed information about ChatWithStaff.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s,
			String[] args) {
		MessageFile msgs = cws.getMessages();
		
		if (args.length == 0) {
			String prefix = msgs.getPrefix();
			for (String msg : info) {
				sender.sendMessage(prefix + msg);
			}
			return true;
		}
		
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("help")) {
				if (!sender.hasPermission(permHelp)) {
					sender.sendMessage(msgs.getNoPermission(permHelp));
					return true;
				}
				String prefix = msgs.getPrefix();
				for (String helpMsg : msgs.getHelp()) {
					sender.sendMessage(prefix + msgs.color(helpMsg));
				}
				return true;
			} 
			
			else if (args[0].equalsIgnoreCase("reload")) {
				if (!sender.hasPermission(permReload)) {
					sender.sendMessage(msgs.getNoPermission(permReload));
					return true;
				}
				cws.getConfiguration().reload();
				msgs.reload();
				sender.sendMessage(msgs.getReload());
				return true;
			} 
			
			else {
				String cmdName = "/" + cmd.getName();
				String cmdUsed = cmdName + " " + Utils.buildString(args);
				sender.sendMessage(msgs.getInvalidCommand(cmdUsed, cmdName + " [help | reload]"));
				return true;
			}
			
		} else {
			String cmdName = "/" + cmd.getName();
			String cmdUsed = cmdName + " " + Utils.buildString(args);
			sender.sendMessage(msgs.getInvalidCommand(cmdUsed, cmdName + " [help | reload]"));
			return true;
		}
	}

}
