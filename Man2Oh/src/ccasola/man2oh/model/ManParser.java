/*
 * References:
 * 	- http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
 */

package ccasola.man2oh.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Parses XML files designed to hold man page data
 */
public class ManParser {
	
	/** Enum used to represent the level of help */
	public enum HELP_LEVEL { 
		TOPIC, SUMMARY, DETAIL
	};
	
	/** The XML document */
	protected Document xmlDom;
	
	/** The singleton instance of ManParser */
	private static ManParser instance = null;
	
	/**
	 * Returns the singleton instance of ManParser
	 * @return the singleton instance of ManParser
	 */
	public static ManParser getInstance() {
		if (instance != null) {
			return instance;
		}
		else {
			throw new RuntimeException("The man parser must be given a file!");
		}
	}
	
	/**
	 * Returns the singleton instance of ManParser after initializing it with the given XML file
	 * @param xmlFile the XML file to parse
	 * @return the singleton instance of ManParser
	 */
	public static ManParser getInstance(File xmlFile) {
		if (instance == null) {
			instance = new ManParser(xmlFile);
		}
		return instance;
	}

	/**
	 * Constructs a new ManParser for the given XML file
	 * @param xmlFile the XML file
	 */
	private ManParser(File xmlFile) {
		try {
			this.xmlDom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
			this.xmlDom.normalize(); // remove empty text nodes
		}
		catch (Exception e) {
			System.err.println("An error occurred parsing the XML file");
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the manual entry corresponding to the given entry title
	 * @param entryTitle the title of the entry to retrieve
	 * @param helpLevel the level of help to retrieve
	 * @return the manual entry corresponding to the given entry title
	 */
	public String getEntry(String entryTitle, HELP_LEVEL helpLevel) throws UnknownEntryException {
		String retVal = "";
		NodeList entryNodes = xmlDom.getElementsByTagName("entry");
		
		// Iterate over entry nodes
		for (int i = 0; i < entryNodes.getLength(); i++) {
			// Ignore any garbage nodes (e.g. not entry nodes)
			if (!(entryNodes.item(0).getNodeName().equals("entry"))) {
				continue;
			}
			NodeList entryItems = entryNodes.item(i).getChildNodes();
			String title = "", topic = "", summary = "", detail = "";
			
			//Iterate over children of entry nodes
			for (int j = 0; j < entryItems.getLength(); j++) {
				String nodeName = entryItems.item(j).getNodeName();
				if (nodeName.equals("title")) {
					title = entryItems.item(j).getTextContent();
				}
				else if (nodeName.equals("topic")) {
					topic = entryItems.item(j).getTextContent();
				}
				else if (nodeName.equals("summary")) {
					summary = entryItems.item(j).getTextContent();
				}
				else if (nodeName.equals("detail")) {
					detail = entryItems.item(j).getTextContent();
				}
			}
			
			// Make sure that all entry nodes have topic, summary, and detail elements
			if (title.length() == 0 || topic.length() == 0 || summary.length() == 0 || detail.length() == 0) {
				throw new RuntimeException("Invalid XML file, all entry nodes must have title, topic, summary, and detail child nodes!");
			}
			
			// Return the content of the current node if it matches the given title
			if (title.equals(entryTitle)) {
				switch (helpLevel) {
				case DETAIL:
					retVal = detail;
					break;
				case SUMMARY:
					retVal = summary;
					break;
				case TOPIC:
					retVal = topic;
					break;
				default:
					break;
				}
				return retVal;
			}
		}
		
		// The manual entry was not found
		throw new UnknownEntryException();
	}
	
	/**
	 * Returns a map with entry titles as the keys and entry summaries as the values
	 * @return a map with entry titles as the keys and entry summaries as the values
	 */
	public Map<String, String> getEntryHeaders() {
		HashMap<String, String> retVal = new HashMap<String, String>();
		NodeList entryNodes = xmlDom.getElementsByTagName("entry");
		
		for (int i = 0; i < entryNodes.getLength(); i++) {
			if (!(entryNodes.item(0).getNodeName().equals("entry"))) {
				continue;
			}
			String title = "", summary = "";
			NodeList entryItems = entryNodes.item(i).getChildNodes();
			for (int j = 0; j < entryItems.getLength(); j++) {
				if (entryItems.item(j).getNodeName().equals("title")) {
					title = entryItems.item(j).getTextContent();
				}
				else if (entryItems.item(j).getNodeName().equals("summary")) {
					summary = entryItems.item(j).getTextContent();
				}
			}
			if (title.length() == 0 || summary.length() == 0) {
				throw new RuntimeException("Invalid XML file, all entry nodes must have title and summary child nodes!");
			}
			retVal.put(title, summary);
		}
		return retVal;
	}
	
	/**
	 * Returns a list containing all of the entry titles
	 * @return a list containing all of the entry titles
	 */
	public List<String> getEntryTitles() {
		ArrayList<String> retVal = new ArrayList<String>();
		NodeList entryTitleNodes = xmlDom.getElementsByTagName("title");
		
		for (int i = 0; i < entryTitleNodes.getLength(); i++) {
			retVal.add(entryTitleNodes.item(i).getTextContent());
		}
		
		return retVal;
	}
}
