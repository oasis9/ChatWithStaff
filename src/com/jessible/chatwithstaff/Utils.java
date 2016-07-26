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

/**
 * ChatWithStaff's frequently used methods.
 * 
 * @since 1.0.0.0
 */
public class Utils {

	/**
	 * Takes a string array and builds it into one string, puts a space
	 * in between elements.
	 * 
	 * @param args string array
	 * @return newly built string
	 */
	public static String buildString(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		String lastWord = args[args.length - 1];
		
		// Loop through args, adding each element to stringBuilder.
		for (String word : args) {
			// If word is the last word in args.
			if (word.equals(lastWord)) {
				// Add word to stringBuilder.
				stringBuilder.append(word);
			} 
			
			// Word is not the last word in args.
			else {
				// Add word to stringBuilder, add additional space.
				stringBuilder.append(word + " ");
			}
		}
		
		return stringBuilder.toString();
	}

	/**
	 * Takes a string array and builds it into one string, puts a chosen string
	 * in between elements.
	 * 
	 * @param args string array
	 * @param chosenString chosen string
	 * @return newly built string
	 */
	public static String buildString(String[] args, String chosenString) {
		StringBuilder stringBuilder = new StringBuilder();
		String lastWord = args[args.length - 1];
		
		// Loop through args, adding each element to stringBuilder.
		for (String word : args) {
			// If word is the last word in args.
			if (word.equals(lastWord)) { // optimize this in the future. = ? :
				// Add word to stringBuilder.
				stringBuilder.append(word);
			} 
			// Word is not the last word in args.
			else {
				// Add word to stringBuilder, add chosenString.
				stringBuilder.append(word + chosenString);
			}
		}
		
		return stringBuilder.toString();
	}
	
	/** 
	 * Checks if a string is a color code.
	 * 
	 * @param colorCode color code
	 * @return true if the string is a color code, otherwise false
	 */
	public static boolean isColorCode(String colorCode) {
		// not using switch/case on a string due to the possibility of some servers still using Java 6.
		if (colorCode.equalsIgnoreCase("&0")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&1")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&2")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&3")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&4")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&5")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&6")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&7")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&8")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&9")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&a")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&b")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&c")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&d")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&e")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&f")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&l")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&o")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&k")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&m")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&n")) {
			return true;
		} else if (colorCode.equalsIgnoreCase("&r")) {
			return true;
		}
		return false;
	}
}
