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
	public static void main(final String[] args) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlDatei);
			document.getDocumentElement().normalize();
			NodeList nutrientList = document.getElementsByTagName("nutrient");
			for(int i = 0; i < nutrientList.getLength(); i++){
				Node nutrient = nutrientList.item(i);
				if(nutrient.getNodeType() == Node.ELEMENT_NODE){
					Element nutriElement = (Element) nutrient;
					System.out.println("Nahrungsmittel: " + nutriElement.getAttribute("name"));
					NodeList nutriDetails = nutrient.getChildNodes();
					for(int j = 0; j < nutriDetails.getLength(); j ++){
						Node details = nutriDetails.item(j);
						if(details.getNodeType() == Node.ELEMENT_NODE){
							Element detailElement = (Element) details;
							System.out.println(detailElement.getTagName() + " " + detailElement.getAttribute("value"));
						}
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		// if (args.length > 0) {
		// File xmlDatei = new File(args[0]);
		// if (xmlDatei.exists()) {
		// MyXMLParser myXmlParser = new MyXMLParser(xmlDatei);
		// myXmlParser.initParser();
		// myXmlParser.parse();
		// } else {
		// System.err.println("Die Datei " + xmlDatei.getAbsolutePath()
		// + " wurde nicht gefunden!");
		// }
		// } else {
		// System.out.println("Bitte eine Datei als Parameter angeben!");
		// }
	}

	private static File xmlDatei = new File("resources\\Nahrungstabelle.xml");
	// private XMLEventReader xmlParser = null;
	// private String lastId = null;

	// public MyXMLParser(final File xml) {
	// super();

	// this.xmlDatei = xml;
	// }

	// public final void initParser() {
	// try {
	// InputStream dateiEingabeStrom = new FileInputStream(xmlDatei);
	// XMLInputFactory factory = XMLInputFactory.newInstance();
	// try {
	// xmlParser = factory.createXMLEventReader(dateiEingabeStrom);

	// } catch (XMLStreamException e) {
	// System.err
	// .println("XML Verarbeitungsfehler: " + e.getMessage());
	// e.printStackTrace();
	// }
	// } catch (FileNotFoundException e) {
	// System.err.println("Die Datei wurde nicht gefunden! "
	// + e.getMessage());
	// }
	// }

	// public final void parse() {
	// while (xmlParser.hasNext()) {
	// try {
	// XMLEvent event = xmlParser.nextEvent();
	// switch (event.getEventType()) {
	// case XMLStreamConstants.START_ELEMENT:
	// handleStartEvent(event);
	// break;
	// case XMLStreamConstants.END_ELEMENT:
	// String name = event.asEndElement().getName().toString()
	// .toLowerCase();
	// if (name.equals("initialmarking")) {
	// isInitialMarking = false;
	// // ----------------
	// } else if (name.equals("name")) {
	// isName = false;

	// } else if (name.equals("text")) {
	// isText = false;
	// // ----------------
	// }
	// break;
	// case XMLStreamConstants.CHARACTERS:
	// if (isText && lastId != null) {
	// Characters ch = event.asCharacters();
	// if (!ch.isWhiteSpace()) {
	// handleText(ch.getData());
	// }
	// }
	// break;
	// case XMLStreamConstants.END_DOCUMENT:
	// // schließe den Parser
	// xmlParser.close();
	// break;
	// default:
	// }
	// } catch (XMLStreamException e) {
	// System.err.println("Fehler beim Parsen des PNML Dokuments. "
	// + e.getMessage());
	// e.printStackTrace();
	// }
	// }
	// }
}
