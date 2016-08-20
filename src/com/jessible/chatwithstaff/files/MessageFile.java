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

import com.jessible.chatwithstaff.FileCreator;
import com.jessible.chatwithstaff.Utils;

/**
 * The handler for the messages.yml file.
 * 
 * @since 1.0.0.0
 */
public class MessageFile extends FileCreator implements YamlFile {
	
	/**
	 * Initializes MessageFile class.
	 */
	public MessageFile() {
		super("messages");
	}

	@Override
	public void addDefaultValues() {
		// Prefix
		get().addDefault(
				"Prefix", 
				"&8[&aChatWithStaff&8]");
		
		// No_Permission
		get().addDefault(
				"No_Permission",
				"&4You &cdo not have the &4{permission} &cpermission.");
		
		// No_Console
		get().addDefault(
				"No_Console",
				"You cannot use {used_command} from the console. "
				+ "Try {suggested_command}.");
		
		// Invalid_Command
		get().addDefault(
				"Invalid_Command",
				"&4{used_command} &cis an invalid command. "
				+ "Did you mean &4{suggested_command}&c?");
		
		// Reload
		get().addDefault(
				"Reload",
				"&fFiles &bconfig.yml &fand &bmessages.yml &fhas been reloaded.");
		
		// Toggle_On
		get().addDefault(
				"Toggle_On",
				"&fStaff chat mode: &bON&f.");
		
		// Toggle_Off
		get().addDefault(
				"Toggle_Off",
				"&fStaff chat mode: &cOFF&f.");
		
		// Verb-1 (Change to Word_Is)
		get().addDefault(
				"Verb-1",
				"is");
		
		// Verb-1 (Change to Word_Are)
		get().addDefault(
				"Verb-2",
				"are");
		
		// List
		List<String> list = new ArrayList<String>();
		list.add("&fThere {verb} &b{amount} &fstaff member{s} viewing staff chat:");
		list.add("&b{staff}");
		get().addDefault("List", list);
		
		// Help
		List<String> help = new ArrayList<String>();
		help.add("&b/chatwithstaff &f- View plugin information.");
		help.add("&b/chatwithstaff help &f- View list of plugin's commands.");
		help.add("&b/chatwithstaff reload &f- Reloads plugin's config.yml and messages.yml files.");
		help.add("&b/staffchat &f- Toggles staff chat mode.");
		help.add("&b/staffchat <message> &f- Sends a message to all staff members.");
		help.add("&b/staffchatlist &f- Shows all staff members viewing staff chat.");
		get().addDefault("Help", help);
		
		// Save default values.
		get().options().copyDefaults(true);
		save();
	}
	
	/**
	 * Gets the Prefix message.
	 * 
	 * @return Prefix message, with a translated reset color code and space
	 */
	public String getPrefix() {
		String prefix = get().getString("Prefix");
		String ending = prefix.isEmpty() ? "" : "&r "; // to avoid a white space if prefix is empty
		return color(prefix + ending);
	}
	
	/**
	 * Gets the No_Permission message.
	 * 
	 * @param permission permission which the sender does not have permission to.
	 * @return No_Permission message
	 */
	public String getNoPermission(String permission) {
		return getMessage("No_Permission").replace("{permission}", permission);
	}
	
	/**
	 * Gets the No_Console message.
	 * 
	 * @param usedCommand command used
	 * @param suggestedCommand command suggested
	 * @return No_Console message
	 */
	public String getNoConsole(String usedCommand, String suggestedCommand) {
		return getMessage("No_Console").replace("{used_command}", usedCommand)
				.replace("{suggested_command}", suggestedCommand);
	}
	
	/**
	 * Gets the Invalid_Command message.
	 * 
	 * @param usedCommand command used
	 * @param suggestedCommand command suggested
	 * @return Invalid_Command message
	 */
	public String getInvalidCommand(String usedCommand, String suggestedCommand) {
		return getMessage("Invalid_Command").replace("{used_command}", usedCommand)
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
	 * Gets the Verb 1 message (is).
	 * 
	 * @return Verb 1 message (is), unformatted
	 */
	public String getVerb1() {
		return get().getString("Verb-1");
	}
	
	/**
	 * Gets the Verb 2 message (are)
	 * 
	 * @return Verb 2 message (are), unformatted
	 */
	public String getVerb2() {
		return get().getString("Verb-2");
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
	 * Gets the color code before a string. If you had a string like,
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
	 * Gets a message from the messages.yml file. The Prefix comes before
	 * all messages and is automatically inserted when this method is called.
	 * 
	 * @param pathToString path to the string of the message
	 * @return message
	 */
	private String getMessage(String pathToString) {
		return color(getPrefix() + get().getString(pathToString));
	}
	
}
