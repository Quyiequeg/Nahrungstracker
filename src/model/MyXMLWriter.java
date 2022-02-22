package model;

import java.io.File;
import java.io.IOException;
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
import org.xml.sax.SAXException;

public class MyXMLWriter {
    private Nutriment elementToAdd;
    private File xmlDatei;
    private Element root;

    public static void main(String[] args) {
        if (args.length > 0) {
			File xmlDatei = new File(args[0]);
			if (xmlDatei.exists()) {
				MyXMLWriter xmlWriter = new MyXMLWriter(xmlDatei);
                xmlWriter.initParser();
			} else {
				System.err.println("Die Datei " + xmlDatei.getAbsolutePath()
						+ " wurde nicht gefunden!");
			}
		} else {
			System.out.println("Bitte eine Datei als Parameter angeben!");
		}
	}
        
    

    public MyXMLWriter(final File xml){
        super();
        this.xmlDatei = xml;
    }

    public final void initParser(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(xmlDatei);
            root = doc.getDocumentElement();
            write(elementToAdd, doc);
            DOMSource source = new DOMSource(doc);
            // create resultstream
            StreamResult result = new StreamResult(xmlDatei);
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer transformer = tff.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e){
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }
    

    public void write(Nutriment nutriment, Document doc){
        // create nutrient
        Element newNutrient = doc.createElement("nutrient");

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(nutriment.getName()));
        newNutrient.appendChild(name);

        // create carbelement
        Element carbs = doc.createElement("carbs");
        carbs.appendChild(doc.createTextNode(Double.toString(nutriment.getCarbs())));
        newNutrient.appendChild(carbs);

        // create nutrientvalue
        Element fat = doc.createElement("fat");
        fat.appendChild(doc.createTextNode(Double.toString(nutriment.getFat())));
        newNutrient.appendChild(fat);

        // create nutrientvalue
        Element saturated = doc.createElement("saturated");
        saturated.appendChild(doc.createTextNode(Double.toString(nutriment.getSaturated())));
        newNutrient.appendChild(saturated);

        // create nutrientvalue
        Element nonSaturated = doc.createElement("unsaturated");
        nonSaturated.appendChild(doc.createTextNode(Double.toString(nutriment.getNonSaturated())));
        newNutrient.appendChild(nonSaturated);

        // create nutrientvalue
        Element protein = doc.createElement("protein");
        protein.appendChild(doc.createTextNode(Double.toString(nutriment.getProtein())));
        newNutrient.appendChild(protein);

        // create nutrientvalue
        Element fibres = doc.createElement("fibres");
        fibres.appendChild(doc.createTextNode(Double.toString(nutriment.getFibres())));
        newNutrient.appendChild(fibres);

        root.appendChild(newNutrient);
    }

    public void setNutriment(Nutriment nutriment){
        elementToAdd = nutriment;
    }
}
