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
public class XMLParser {
	public static void main(final String[] args) {
		if (args.length > 0) {
			File xmlDatei = new File(args[0]);
			if (xmlDatei.exists()) {
				XMLParser xmlParser = new XMLParser(xmlDatei);
				xmlParser.initParser();
				xmlParser.parse();
			} else {
				System.err.println("Die Datei " + xmlDatei.getAbsolutePath()
						+ " wurde nicht gefunden!");
			}
		} else {
			System.out.println("Bitte eine Datei als Parameter angeben!");
		}
	}

	private File xmlDatei;

	public XMLParser(final File xml) {
		super();

		this.xmlDatei = xml;
	}

	public final void initParser() {

	}

	public final void parse() {

	}
}
