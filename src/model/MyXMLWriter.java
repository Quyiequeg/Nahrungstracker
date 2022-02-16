package model;

import java.io.File;
import java.util.logging.Logger;

// import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;

public class MyXMLWriter {
    public static void main(String[] args) {
        File xmlFile = new File("resources\\Nahrungstabelle.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();

            NodeList root = doc.getElementsByTagName("food");
        } catch (ParserConfigurationException e) {
            e.getStackTrace();
        }
    }
}
