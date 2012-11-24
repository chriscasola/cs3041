package ccasola.man2oh.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ManParser {
	
	protected Document xmlDom;

	public ManParser(File xmlFile) {
		try {
			this.xmlDom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
			this.xmlDom.normalize(); // remove empty text nodes
		}
		catch (Exception e) {
			System.err.println("An error occurred parsing the XML file");
			e.printStackTrace();
		}
	}
	
	public String getManEntry(String entryTitle) {
		String retVal = "";
		NodeList entryNodes = xmlDom.getElementsByTagName("entry");
		
		for (int i = 0; i < entryNodes.getLength(); i++) {
			if (!(entryNodes.item(0).getNodeName().equals("entry"))) {
				continue;
			}
			NodeList entryItems = entryNodes.item(i).getChildNodes();
			String title = "", content = "";
			for (int j = 0; j < entryItems.getLength(); j++) {
				if (entryItems.item(j).getNodeName().equals("title")) {
					title = entryItems.item(j).getTextContent();
				}
				else if (entryItems.item(j).getNodeName().equals("body")) {
					content = entryItems.item(j).getTextContent();
				}
			}
			if (title.length() == 0 || content.length() == 0) {
				throw new RuntimeException("Invalid XML file, all entry nodes must have title and summary child nodes!");
			}
			if (title.equals(entryTitle)) {
				retVal = content;
			}
		}
		
		return retVal;
	}
	
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
	
	public List<String> getEntryTitles() {
		ArrayList<String> retVal = new ArrayList<String>();
		NodeList entryTitleNodes = xmlDom.getElementsByTagName("title");
		
		for (int i = 0; i < entryTitleNodes.getLength(); i++) {
			retVal.add(entryTitleNodes.item(i).getTextContent());
		}
		
		return retVal;
	}
}
