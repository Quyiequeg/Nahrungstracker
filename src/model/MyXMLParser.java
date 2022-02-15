package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Diese Klasse implementiert die Grundlage für einen einfachen XML Parser, der
 * Nährwerte für einen Nährwertrechner parst.
 */
public class MyXMLParser {
	public static void main(final String[] args) {
		if (args.length > 0) {
			File xmlDatei = new File(args[0]);
			if (xmlDatei.exists()) {
				MyXMLParser myXmlParser = new MyXMLParser(xmlDatei);
				myXmlParser.initParser();
				myXmlParser.parse();
			} else {
				System.err.println("Die Datei " + xmlDatei.getAbsolutePath()
						+ " wurde nicht gefunden!");
			}
		} else {
			System.out.println("Bitte eine Datei als Parameter angeben!");
		}
	}

	private File xmlDatei;
	private XMLEventReader xmlParser = null;
	private String lastId = null;

	public MyXMLParser(final File xml) {
		super();

		this.xmlDatei = xml;
	}

	public final void initParser() {
		try {
			InputStream dateiEingabeStrom = new FileInputStream(xmlDatei);
			XMLInputFactory factory = XMLInputFactory.newInstance();
			try {
				xmlParser = factory.createXMLEventReader(dateiEingabeStrom);

			} catch (XMLStreamException e) {
				System.err
						.println("XML Verarbeitungsfehler: " + e.getMessage());
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.err.println("Die Datei wurde nicht gefunden! "
					+ e.getMessage());
		}
	}

	public final void parse() {
		while (xmlParser.hasNext()) {
			try {
				XMLEvent event = xmlParser.nextEvent();
				switch (event.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
					handleStartEvent(event);
					break;
				case XMLStreamConstants.END_ELEMENT:
					String name = event.asEndElement().getName().toString()
							.toLowerCase();
					if (name.equals("initialmarking")) {
						isInitialMarking = false;
						// ----------------
					} else if (name.equals("name")) {
						isName = false;

					} else if (name.equals("text")) {
						isText = false;
						// ----------------
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					if (isText && lastId != null) {
						Characters ch = event.asCharacters();
						if (!ch.isWhiteSpace()) {
							handleText(ch.getData());
						}
					}
					break;
				case XMLStreamConstants.END_DOCUMENT:
					// schließe den Parser
					xmlParser.close();
					break;
				default:
				}
			} catch (XMLStreamException e) {
				System.err.println("Fehler beim Parsen des PNML Dokuments. "
						+ e.getMessage());
				e.printStackTrace();
			}
		}	
	}
}
