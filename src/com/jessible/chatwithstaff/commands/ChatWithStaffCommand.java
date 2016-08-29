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
import com.jessible.chatwithstaff.PluginDetails;
import com.jessible.chatwithstaff.files.MessageFile;

/**
 * The handler for the /chatwithstaff command.
 * 
 * @since 1.0.0.0
 */
public class ChatWithStaffCommand extends CommandHelper implements CommandExecutor {
	
	private Permission permHelp, permReload;
	private ChatWithStaff plugin;
	private PluginDetails details;
	private String textColor, highlightColor;
	private String[] info;
	
	/**
	 * Initializes ChatWithStaffCommand class.
	 */
	public ChatWithStaffCommand() {
		super("chatwithstaff", "[help | reload]");
		this.permHelp = Permission.CMD_CHATWITHSTAFF_HELP;
		this.permReload = Permission.CMD_CHATWITHSTAFF_RELOAD;
		this.plugin = ChatWithStaff.getInstance();
		this.details = plugin.getDetails();
		this.textColor = details.getTextColor();
		this.highlightColor = details.getHighlightColor();
		this.info = new String[] {
				// Info > Name
				textColor + details.getStaticName() + " "
						+ highlightColor + details.getVersion(),
						
				// Info > Developed
				textColor + "Developed by "
						+ highlightColor + "Jessible",
						
				// Info > Website
				highlightColor + details.getWebsite()};
	}

	/**
	 * The /chatwithstaff command. 
	 * <p>
	 * <strong>/chatwithstaff</strong>
	 * <ul>
	 * 		<li><strong>Example usage</strong>: /chatwithstaff.</li>
	 * 		<li><strong>Description</strong>: Shows the plugin's information.
	 * 		</li>
	 * </ul>
	 * 
	 * <strong>/chatwithstaff help</strong>
	 * <ul>
	 * 		<li><strong>Example usage</strong>: /chatwithstaff help.</li>
	 * 		<li><strong>Description</strong>: Shows the plugin's help
	 * 			information found in the plugin's messages.yml file.
	 * 		</li>
	 * </ul>
	 * 	
	 * <strong>/chatwithstaff reload</strong>
	 * <ul>
	 * 		<li><strong>Example usage</strong>: /chatwithstaff reload.</li>
	 * 		<li><strong>Description</strong>: Reloads the plugin's config.yml
	 * 			and messages.yml files.
	 * 		</li>
	 * </ul>
	 * 
	 * @param sender Command sender
	 * @param baseCmd Base command (/chatwithstaff)
	 * @param cmd Command that is being used ("/cws" - see plugin.yml)
	 * @param args Command arguments (such as "help" or "reload")
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command baseCmd, String cmd,
			String[] args) {
		MessageFile msgs = plugin.getMessages();
		
		// If "/chatwithstaff" is executed without any arguments.
		if (args.length == 0) {
			// Send the plugin's information.
			String prefix = msgs.getPrefix();
			
			for (String msg : info) {
				sender.sendMessage(prefix + msgs.color(msg));
			}
			return true;
		}
		// "/chatwithstaff" is executed with arguments.
		
		// If "/chatwithstaff" is executed with one argument.
		if (args.length == 1) {
			
			// If "/chatwithstaff help" is executed.
			if (args[0].equalsIgnoreCase("help")) {
				// If the sender doesn't have permission.
				if (!hasPermission(permHelp, sender)) {
					// hasPermission(Permission, CommandSender) sends the no
					// permission message.
					return true;
				}
				// The sender has permission.
	
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
				if (!hasPermission(permReload, sender)) {
					// hasPermission(Permission, CommandSender) sends the no
					// permission message.
					return true;
				}
				// The sender has permission.
				
				// Reload the plugin's config.yml and messages.yml files.
				plugin.getConfiguration().reload();
				msgs.reload();
				sender.sendMessage(msgs.getReload());
				return true;
			} 
			
			// The one argument isn't "help" or "reload."
			else {
				// Send invalid command message.
				invalidCommand(cmd, args, sender);
				return true;
			}
			
			// If "/chatwithstaff" is executed with more than one argument.
		} else {
			// Send invalid command message.
			invalidCommand(cmd, args, sender);
			return true;
		}
	}

}
