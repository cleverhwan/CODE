package kr.co.code.common.util;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlGenerator {
	private Document doc;
	private Transformer transformer = null;
	private String filePath;
	
	public XmlGenerator() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		// root element
		doc = docBuilder.newDocument();
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	}

	public void setFilePath(String filePath) {
		File dir = new File(filePath);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		this.filePath = filePath;
	}

	public boolean addElement(String parentElementName, int index, String elementName, String elementValue) {
		NodeList nodeList = doc.getElementsByTagName(parentElementName);
		if (nodeList == null || nodeList.getLength() == 0)
			return false;

		Element element = doc.createElement(elementName);
		element.appendChild(doc.createTextNode(elementValue));
		nodeList.item(index).appendChild(element);

		return true;
	}

	public boolean addAttribute(String elementName, int index, String attributeName, String attributeValue) {
		NodeList nodeList = doc.getElementsByTagName(elementName);
		if (nodeList == null)
			return false;

		Element element = (Element) nodeList.item(index);
		element.setAttribute(attributeName, attributeValue);
		return true;
	}
	
	public void addRootElement(String elementName) {
		Element rootElement = doc.createElement(elementName);
		doc.appendChild(rootElement);
	}

	private void generator(StreamResult result) throws TransformerException {
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
	}

	public boolean generator(String fileName) {
		if(filePath != null){
			File file = new File(filePath + fileName);
			
			try {
				StreamResult result = new StreamResult(new FileOutputStream(file));
				generator(result);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} else{
			return false;
		}
	}

	public boolean print() {
		try {
			StreamResult result = new StreamResult(System.out);
			generator(result);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}