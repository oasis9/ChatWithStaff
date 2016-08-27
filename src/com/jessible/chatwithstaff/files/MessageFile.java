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

package com.jessible.chatwithstaff.files;

import java.util.ArrayList;
import java.util.List;

import com.jessible.chatwithstaff.ChatWithStaff;
import com.jessible.chatwithstaff.FileCreator;
import com.jessible.chatwithstaff.Permission;
import com.jessible.chatwithstaff.PluginDetails;
import com.jessible.chatwithstaff.Utils;

/**
 * The handler for the messages.yml file.
 * 
 * @since 1.0.0.0
 */
public class MessageFile extends FileCreator implements YamlFile {
	
	private PluginDetails details;
	
	/**
	 * Initializes MessageFile class.
	 */
	public MessageFile() {
		super("messages");
		this.details = ChatWithStaff.getInstance().getDetails();
	}
	
	@Override
	public void addDefaultValues() {
		String pluginName = details.getName();
		
		String prefixColor = details.getPrefixColor();
		String highlightColor = details.getHighlightColor();
		String textColor = details.getTextColor();
		
		String highlightErrorColor = details.getHighlightErrorColor();
		String textErrorColor = details.getTextErrorColor();
		
		// Prefix
		get().addDefault("Prefix",
				"&8[" + prefixColor + pluginName + "&8]");
		
		// No_Permission
		get().addDefault("No_Permission",
				highlightErrorColor + "You "
						+ textErrorColor + "do not have the "
						+ highlightErrorColor + "{permission} " 
						+ textErrorColor + "permission.");
		
		// No_Console (Console doesn't get formatting; besides the prefix)
		get().addDefault("No_Console",
				"You cannot use {used_command} from the console. "
						+ "Try {suggested_command}.");
		
		// Invalid_Command
		get().addDefault("Invalid_Command",
				highlightErrorColor + "{used_command} "
						+ textErrorColor + "is an invalid command. "
						+ "Did you mean "
						+ highlightErrorColor + "{suggested_command}?");
		
		// Reload
		get().addDefault("Reload",
				textColor + "Files "
						+ highlightColor + "config.yml "
						+ textColor + "and "
						+ highlightColor + "messages.yml "
						+ textColor + "has been reloaded.");
		
		// Toggle_On
		get().addDefault("Toggle_On",
				textColor + "Staff chat mode: "
				+ highlightColor + "ON.");
		
		// Toggle_Off
		get().addDefault("Toggle_Off",
				textColor + "Staff chat mode: "
				+ textErrorColor + "OFF.");
		
		// Word_Is
		get().addDefault("Word_Is",
				"is");
		
		// Word_Are
		get().addDefault("Word_Are",
				"are");
		
		// List
		List<String> list = new ArrayList<String>();
		
		// List > Amount
		list.add(textColor + "There {word} "
				+ highlightColor + "{amount} "
				+ textColor + "staff member{s} viewing staff chat:");
		
		// List > Staff
		list.add(highlightColor + "{staff}");
		
		// List > Default
		get().addDefault("List", list);
		
		// Help
		String pluginNameCmd = "/" + pluginName.toLowerCase() + " ";
		List<String> help = new ArrayList<String>();
		
		// Help > /pluginname
		help.add(highlightColor + pluginNameCmd
				+ textColor + "- View plugin information.");
		
		// Help > /pluginname help
		help.add(highlightColor + pluginNameCmd + "help "
				+ textColor + "- View list of plugin's commands.");
		
		// Help > /pluginname reload
		help.add(highlightColor + pluginNameCmd + "reload "
				+ textColor + "- Reload plugin's config.yml and messages.yml files.");
		
		// Help > /staffchat
		help.add(highlightColor + "/staffchat "
				+ textColor + "- Toggle staff chat mode.");
		
		// Help > /staffchat <message>
		help.add(highlightColor + "/staffchat <message> "
				+ textColor + "- Send a message to all staff members.");
		
		// Help > /staffchatlist
		help.add(highlightColor + "/staffchatlist "
				+ textColor + "- Show all staff members viewing staff chat.");
		
		// Help > Default
		get().addDefault("Help", help);
		
		// Save default values.
		get().options().copyDefaults(true);
		save();
	}
	
	/**
	 * Gets the Prefix message.
	 * 
	 * @return Prefix message.
	 */
	public String getPrefix() {
		String prefix = get().getString("Prefix");
		String ending = prefix.isEmpty() ? "" : "&r "; // avoid a white space.
		return color(prefix + ending);
	}
	
	/**
	 * Gets the No_Permission message.
	 * 
	 * @param permission Permission
	 * @return No_Permission message
	 */
	public String getNoPermission(Permission permission) {
		return getMessage("No_Permission")
				.replace("{permission}", permission.getPermission());
	}
	
	/**
	 * Gets the No_Console message.
	 * 
	 * @param usedCommand Used command
	 * @param suggestedCommand Suggested command
	 * @return No_Console message
	 */
	public String getNoConsole(String usedCommand, String suggestedCommand) {
		return getMessage("No_Console")
				.replace("{used_command}", usedCommand)
				.replace("{suggested_command}", suggestedCommand);
	}
	
	/**
	 * Gets the Invalid_Command message.
	 * 
	 * @param usedCommand Used command
	 * @param suggestedCommand Suggested command
	 * @return Invalid_Command message
	 */
	public String getInvalidCommand(String usedCommand, String suggestedCommand) {
		return getMessage("Invalid_Command")
				.replace("{used_command}", usedCommand)
				.replace("{suggested_command}", suggestedCommand);
	}
	
	/**
	 * Gets the Reload message.
	 * 
	 * @return Reload message
	 */
	public String getReload() {
		return getMessage("Reload");
	}
	
	/**
	 * Gets the Toggle_On message.
	 *
	 * @return Toggle_On message
	 */
	public String getToggleOn() {
		return getMessage("Toggle_On");
	}
	
	/**
	 * Gets the Toggle_Off message.
	 *
	 * @return Toggle_Off message
	 */
	public String getToggleOff() {
		return getMessage("Toggle_Off");
	}
	
	/**
	 * Gets the Word_Is message.
	 * 
	 * @return Word_Is message, unformatted
	 */
	public String getWordIs() {
		return get().getString("Word_Is");
	}
	
	/**
	 * Gets the Word_Are message.
	 * 
	 * @return Word_Are message, unformatted
	 */
	public String getWordAre() {
		return get().getString("Word_Are");
	}
	
	/**
	 * Gets the Help messages.
	 * 
	 * @return Help messages, unformatted
	 */
	public List<String> getHelp() {
		return get().getStringList("Help");
	}
	
	/**
	 * Gets the List messages.
	 * 
	 * @return List messages, unformatted
	 */
	public List<String> getList() {
		return get().getStringList("List");
	}
	
	/**
	 * Gets the color code before a string. If you had a string such as,
	 * "&4Hello! &cWhat's going &7on?," and "before" was set to "going," then
	 * "&4&c" would be returned. <strong>Not suggested to use for anyone else,
	 * besides Jessible, as this is a quick fix and not a long term solution.</strong>
	 * 
	 * @param before where to stop searching for color codes.
	 * 			"before" must have a length of 2 or the unpredictable could happen
	 * @param search string to be searched for color codes
	 * @return all color codes behind "before" string. Empty string if no color codes are found
	 */
	public String getColorCodeBefore(String before, String search) {
		String colorCodes = "";
		int length = search.length();
		
		for (int x = 0; x + 2 < length; x += 2) {
			String possibleCode = search.substring(x, x + 2);
			if (before.equalsIgnoreCase(possibleCode)) {
				break;
			}
			if (Utils.isColorCode(possibleCode)) {
				colorCodes += possibleCode;
			}
		}
		return colorCodes;
	}
	
	/**
	 * Gets a message from the plugin's messages.yml file. The Prefix comes
	 * before all messages and is automatically inserted when this method is
	 * called.
	 * 
	 * @param pathToMessage Path to message
	 * @return message
	 */
	private String getMessage(String pathToMessage) {
		return color(getPrefix() + get().getString(pathToMessage));
	}
	
}
