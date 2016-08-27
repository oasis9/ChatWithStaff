package com.jessible.chatwithstaff;

import org.bukkit.plugin.PluginDescriptionFile;

/**
 * The details of the plugin.
 * 
 * @since 1.0.0.0
 */
public class PluginDetails {
	
	private PluginDescriptionFile pdf;
	private String name, version;
	private String prefixColor, highlightColor, textColor;
	private String highlightErrorColor, textErrorColor;
	
	/**
	 * Initializes PluginDetails class.
	 */
	public PluginDetails() {
		this.pdf = ChatWithStaff.getInstance().getDescription();
		this.name = pdf.getName();
		this.version = pdf.getVersion();
		this.prefixColor = "&a";
		this.highlightColor = "&b";
		this.textColor = "&f";
		this.highlightErrorColor = "&4";
		this.textErrorColor = "&c";
	}
	
	/**
	 * Gets the name of the plugin.
	 * 
	 * @return name of the plugin
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the version of the plugin.
	 * 
	 * @return version of the plugin
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * Gets the prefix color of the plugin.
	 * 
	 * @return prefix color of the plugin
	 */
	public String getPrefixColor() {
		return prefixColor;
	}
	
	/**
	 * Gets the highlight color of the plugin.
	 * 
	 * @return highlight color of the plugin
	 */
	public String getHighlightColor() {
		return highlightColor;
	}
	
	/**
	 * Gets the text color of the plugin.
	 * 
	 * @return text color of the plugin
	 */
	public String getTextColor() {
		return textColor;
	}
	
	/**
	 * Gets the highlight error color of the plugin.
	 * 
	 * @return highlight error color of the plugin
	 */
	public String getHighlightErrorColor() {
		return highlightErrorColor;
	}
	
	/**
	 * Gets the text error color of the plugin.
	 * 
	 * @return text error color of the plugin
	 */
	public String getTextErrorColor() {
		return textErrorColor;
	}

}
