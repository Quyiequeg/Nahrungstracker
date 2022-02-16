package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

import javax.naming.spi.DirStateFactory.Result;
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
import org.w3c.dom.Text;

public class MyXMLWriter {

    public static void main(String[] args) {
        String path = "resources\\Nahrungstabelle.xml";
        File xmlDatei = new File(path);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument(); // create temp

            // create root node
            Element root = doc.createElement("food");

            // create nutrient
            Element nutrient = doc.createElement("nutrient");

            // create nutrientvalue
            Element name = doc.createElement("name");
            Text nameValue = doc.createTextNode("data");
            name.appendChild(nameValue);
            // create nutrientvalue
            Element carbs = doc.createElement("carbs");
            Text carbValue = doc.createTextNode("data");
            carbs.appendChild(carbValue);
            // create nutrientvalue
            Element fat = doc.createElement("fat");
            Text fatvalue = doc.createTextNode("data");
            fat.appendChild(fatvalue);
            // create nutrientvalue
            Element saturated = doc.createElement("saturated");
            Text satValue = doc.createTextNode("data");
            saturated.appendChild(satValue);
            // create nutrientvalue
            Element unsaturated = doc.createElement("unsaturated");
            Text unSatValue = doc.createTextNode("data");
            unsaturated.appendChild(unSatValue);
            // create nutrientvalue
            Element protein = doc.createElement("protein");
            Text protValue = doc.createTextNode("data");
            protein.appendChild(protValue);
            // create nutrientvalue
            Element fibres = doc.createElement("fibres");
            Text fibValue = doc.createTextNode("data");
            fibres.appendChild(fibValue);

            // add to nutrient node
            nutrient.appendChild(name);
            nutrient.appendChild(carbs);
            nutrient.appendChild(fat);
            nutrient.appendChild(saturated);
            nutrient.appendChild(unsaturated);
            nutrient.appendChild(protein);
            nutrient.appendChild(fibres);

            // add nutrient to root
            root.appendChild(nutrient);

            // add root to document
            doc.appendChild(root);

            // write temp from memory to file

            DOMSource source = new DOMSource(doc);
            // create resultstream
            StreamResult result = new StreamResult(xmlDatei);
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer transformer = tff.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,  "no");
            transformer.setOutputProperty(OutputKeys.INDENT,  "yes");
            transformer.transform(source, result);
            System.out.println("write data success to file: " + path);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e){
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
