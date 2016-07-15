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
 * The handler for the /chatwithstaff command.
 * 
 * @since 1.0.0.0
 */
public class ChatWithStaffCommand implements CommandExecutor {
	
	private ChatWithStaff cws;
	private String permHelp, permReload, version;
	private String[] info;
	
	/**
	 * Initializes ChatWithStaffCommand class.
	 *  
	 * @param cws Instance of ChatWithStaff class (main class)
	 */
	public ChatWithStaffCommand(ChatWithStaff cws) {
		this.cws = cws;
		this.permHelp = Permissions.CHATWITHSTAFF_HELP_CMD.get();
		this.permReload = Permissions.CHATWITHSTAFF_RELOAD_CMD.get();
		this.version = cws.getDescription().getVersion();
		this.info = new String[] {
				"ChatWithStaff " + cws.getMessages().color("&bv" + version),
				"Developed by " + cws.getMessages().color("&b" + "Jessible"), 
				"https://www.spigotmc.org/resources/25182/"};
	}

	/**
	 * The /chatwithstaff command. 
	 * <p>
	 * <strong>/chatwithstaff</strong>
	 * <ul>
	 * 		<li><strong>Example usage</strong>: /chatwithstaff.</li>
	 * 		<li><strong>Description</strong>: Shows ChatWithStaff's version
	 * 			number and download link.
	 * 		</li>
	 * </ul>
	 * 
	 * <strong>/chatwithstaff help</strong>
	 * <ul>
	 * 		<li><strong>Example usage</strong>: /chatwithstaff help.</li>
	 * 		<li><strong>Description</strong>: Shows ChatWithStaff's help
	 * 			information found in ChatWithStaff's messages.yml file.
	 * 		</li>
	 * </ul>
	 * 	
	 * <strong>/chatwithstaff reload</strong>
	 * <ul>
	 * 		<li><strong>Example usage</strong>: /chatwithstaff reload.</li>
	 * 		<li><strong>Description</strong>: Reloads ChatWithStaff's
	 * 			config.yml and messages.yml files.
	 * 		</li>
	 * </ul>
	 * 
	 * @param sender the command sender
	 * @param cmd the command
	 * @param s the shortcut/alias that is being used (such as "/cws;"
	 * 			see plugin.yml)
	 * @param args the command arguments (such as "help" or "reload")
	 * 
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s,
			String[] args) {
		MessageFile msgs = cws.getMessages();
		
		// If "/chatwithstaff" is executed.
		if (args.length == 0) {
			// Send ChatWithStaff's version number and download link.
			String prefix = msgs.getPrefix();
			for (String msg : info) {
				sender.sendMessage(prefix + msg);
			}
			return true;
		}
		
		// If there is one argument for "/chatwithstaff."
		if (args.length == 1) {
			
			// If "/chatwithstaff help" is executed.
			if (args[0].equalsIgnoreCase("help")) {
				
				// If the sender doesn't have permission.
				if (!sender.hasPermission(permHelp)) {
					// Send no permission message.
					sender.sendMessage(msgs.getNoPermission(permHelp));
					return true;
				}
	
				// Send help information.
				String prefix = msgs.getPrefix();
				for (String helpMsg : msgs.getHelp()) {
					sender.sendMessage(prefix + msgs.color(helpMsg));
				}
				return true;
			} 
			
			// If "/chatwithstaff reload" is executed.
			else if (args[0].equalsIgnoreCase("reload")) {
				
				// If the sender doesn't have permission.
				if (!sender.hasPermission(permReload)) {
					// Send no permission message.
					sender.sendMessage(msgs.getNoPermission(permReload));
					return true;
				}
				
				// Reload ChatWithStaff's config.yml and messages.yml files.
				cws.getConfiguration().reload();
				msgs.reload();
				sender.sendMessage(msgs.getReload());
				return true;
			} 
			
			// The one argument isn't "help" or "reload."
			else {
				// Send invalid command message.
				String cmdName = "/" + cmd.getName();
				String cmdUsed = cmdName + " " + Utils.buildString(args);
				sender.sendMessage(msgs.getInvalidCommand(cmdUsed, cmdName + " [help | reload]"));
				return true;
			}
			
		// There is more than one argument for "/chatwithstaff."
		} else {
			// Send invalid command message.
			String cmdName = "/" + cmd.getName();
			String cmdUsed = cmdName + " " + Utils.buildString(args);
			sender.sendMessage(msgs.getInvalidCommand(cmdUsed, cmdName + " [help | reload]"));
			return true;
		}
	}

}
