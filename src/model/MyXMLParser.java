package model;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Diese Klasse implementiert die Grundlage für einen einfachen XML Parser, der
 * Nährwerte für einen Nährwertrechner parst.
 */
public class MyXMLParser {
	private static File xmlDatei = new File("resources\\Nahrungstabelle.xml");

	public static void main(final String[] args) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlDatei);
			document.getDocumentElement().normalize();
			NodeList nutrientList = document.getElementsByTagName("nutrient");
			for (int i = 0; i < nutrientList.getLength(); i++) {
				Node nutrient = nutrientList.item(i);
				if (nutrient.getNodeType() == Node.ELEMENT_NODE) {
					Element nutriElement = (Element) nutrient;
					NodeList nutriDetails = nutriElement.getChildNodes();
					for (int j = 0; j < nutriDetails.getLength(); j++) {
						Node details = nutriDetails.item(j);
						parseDetails(details);
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void parseDetails(Node details) {
		if (details.getNodeName().equals("name")) {
			System.out.println(details.getTextContent());
		} else if (details.getNodeName().equals("carbs")) {
			double dbl = Double.parseDouble(details.getTextContent());
			System.out.println(dbl);

		} else if (details.getNodeName().equals("fat")) {
			double dbl = Double.parseDouble(details.getTextContent());
			System.out.println(dbl);

		} else if (details.getNodeName().equals("saturated")) {
			double dbl = Double.parseDouble(details.getTextContent());
			System.out.println(dbl);

		} else if (details.getNodeName().equals("unsaturated")) {
			double dbl = Double.parseDouble(details.getTextContent());
			System.out.println(dbl);

		} else if (details.getNodeName().equals("protein")) {
			double dbl = Double.parseDouble(details.getTextContent());
			System.out.println(dbl);

		} else if (details.getNodeName().equals("fibres")) {
			double dbl = Double.parseDouble(details.getTextContent());
			System.out.println(dbl);

		}
	}
}
