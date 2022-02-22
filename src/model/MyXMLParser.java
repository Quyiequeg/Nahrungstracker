package model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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
	private File xmlDatei;
	private HashMap<String, Nutriment> nutrimentMap = new HashMap<>();

	public static void main(final String[] args) {
		if (args.length > 0) {
			File xmlDatei = new File(args[0]);
			if (xmlDatei.exists()) {
				MyXMLParser xmlParser = new MyXMLParser(xmlDatei);
				xmlParser.initParser();
			} else {
				System.err.println("Die Datei " + xmlDatei.getAbsolutePath()
						+ " wurde nicht gefunden!");
			}
		} else {
			System.out.println("Bitte eine Datei als Parameter angeben!");
		}
	}

	public MyXMLParser(final File xml) {
		super();
		this.xmlDatei = xml;
	}

	public final void initParser() {
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
					parseDetails(nutriDetails);
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

	private void parseDetails(NodeList nutriDetails) {
		Nutriment nutriment = new Nutriment("dummy", 0, 0, 0, 0, 0, 0);
		for (int j = 0; j < nutriDetails.getLength(); j++) {
			Node details = nutriDetails.item(j);
			if (details.getNodeName().equals("name")) {
				nutriment.setName(details.getTextContent());
				System.out.println(nutriment.getName());

			} else if (details.getNodeName().equals("carbs")) {
				double dbl = Double.parseDouble(details.getTextContent());
				nutriment.setCarbs(dbl);
				System.out.println(dbl);

			} else if (details.getNodeName().equals("fat")) {
				double dbl = Double.parseDouble(details.getTextContent());
				nutriment.setFat(dbl);

			} else if (details.getNodeName().equals("saturated")) {
				double dbl = Double.parseDouble(details.getTextContent());
				nutriment.setSaturated(dbl);

			} else if (details.getNodeName().equals("unsaturated")) {
				double dbl = Double.parseDouble(details.getTextContent());
				nutriment.setNonSaturated(dbl);

			} else if (details.getNodeName().equals("protein")) {
				double dbl = Double.parseDouble(details.getTextContent());
				nutriment.setProtein(dbl);

			} else if (details.getNodeName().equals("fibres")) {
				double dbl = Double.parseDouble(details.getTextContent());
				nutriment.setFibres(dbl);
			}
			setHashMap(nutrimentMap, nutriment);
		}

	}

	private void setHashMap(HashMap<String, Nutriment> map, Nutriment nutriment) {
		// Nutriment nutriment dummy
		map.put(nutriment.getName(), nutriment);
	}

	public void setHashMap(HashMap<String, Nutriment> map) {
		nutrimentMap.forEach((nutriment, nutriObject) -> {
			map.put(nutriment, nutriObject);
		});
	}
}
