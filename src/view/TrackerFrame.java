package view;

import javax.swing.*;

import model.MyXMLParser;
import model.MyXMLWriter;
import model.Nutriment;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

public class TrackerFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> mapItems;
    private JSplitPane mainPanel, workspacePanel;
    private JPanel writerPanel, nutriPanel, writerTable, nutriTable;
    public JTextArea textPane;
    private JToolBar nutriToolBar, writerToolBar, textToolBar;
    private JButton saveBtn = new JButton("Nahrungsmittel speichern");
    private JButton listButton = new JButton("Zutatenliste");
    private JButton calcButton = new JButton("mit Menge");
    private JButton stackButton = new JButton("Stack+");
    private JButton popButton = new JButton("Stack-");
    private File xmlDatei = new File("resources\\Nahrungstabelle.xml");
    private HashMap<String, Nutriment> nutrimentMap = new HashMap<>();
    private Set<String> mapKeys;
    private JLabel iName, iCarbs, iFat, iSaturated, iUnsaturated, iProtein, iFibres, iQuantity;
    private JTextField tName, tCarbs, tFat, tSaturated, tUnsaturated, tProtein, tFibres, tQuantity;
    private Stack<String> nutrimentStack = new Stack<>();
    private Stack<Double> quantityStack = new Stack<>();


    public TrackerFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.defaultScreenSize();
        this.createPanel();
        this.createWriterTools();
        this.createNutriTools();
        this.createTextTools();
        mainPanel.setDividerLocation(0.5);
        workspacePanel.setDividerLocation(0.5);
        MyXMLParser xmlParser = new MyXMLParser(xmlDatei);
        xmlParser.initParser();
        xmlParser.setHashMap(nutrimentMap);
        mapKeys = nutrimentMap.keySet();
        createCombobox();
    }

    
    private void defaultScreenSize() {
        double heightPerc = 0.6; // relative Höhe des Fensters bzgl. der der Bildschirmhöhe (1.0), hier also 60 %
        double aspectRatio = 16.0 / 10.0; // Seitenverhältnis des Fensters
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int h = (int) (screenSize.height * heightPerc);
        int w = (int) (h * aspectRatio);
        this.setBounds((screenSize.width - w) / 2, (screenSize.height - h) / 2, w, h);
    }
    
    private void createPanel() {
        textPane = new JTextArea();
        writerPanel = new JPanel(new BorderLayout());
        nutriPanel = new JPanel(new BorderLayout());
        nutriTable = new JPanel(new GridLayout(7, 2));
        nutriPanel.add(nutriTable);
        writerTable = new JPanel(new GridLayout(7, 2));
        writerPanel.add(writerTable);
        workspacePanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, nutriPanel, writerPanel);
        mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, workspacePanel, textPane);
        createTextFields();
        textPane.setEditable(false);
        textPane.setLineWrap(true);
        textPane.setFont(new Font("Courier New", Font.PLAIN, 12));
        JScrollPane scroller = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scroller);
        this.add(mainPanel);
        this.setVisible(true);

    }

    private void createTextTools() {
        //create texttools
        textToolBar = new JToolBar();

        // füge knöpfe hinzu und implementiere ihre actionlistener
        textToolBar.add(listButton);
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                List<String> outputTable = new ArrayList<>();
                String format1 = "|%1$-25s|%2$-35s|\n";
                outputTable.add(String.format(format1, "", "").replace(" ", "-"));
                outputTable.add(String.format(format1, "Nahrungsmittel", "carbs/protein/fat"));
                outputTable.add(String.format(format1, "", "", "").replace(" ", "-"));
                nutrimentMap.forEach((nutriment, nutriObject) -> {
                    outputTable.add(String.format(format1, nutriment, nutriObject.getCarbs() + "/" + nutriObject.getProtein() + "/" + nutriObject.getFat()));
                });
                textPane.append("\n");
                outputTable.add(String.format(format1, "", "", "").replace(" ", "-"));
                outputTable.forEach(x -> setText(x));
            }
        });

        //add texttools to textpane
        this.add(textToolBar, BorderLayout.SOUTH);
    }

    private void createNutriTools() {
        //create nutritools
        nutriToolBar = new JToolBar();

        //füge knöpfe und actionlistener hinzu
        nutriToolBar.add(calcButton);
        calcButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String comboItem = (String) mapItems.getSelectedItem();
                Double quantity = Double.parseDouble(tQuantity.getText().replace(",", "."));

                Nutriment nutriment = nutrimentMap.get(comboItem);
                textPane.append("KH: " + nutriment.getQCarbs(quantity).toString() + "\n");
                textPane.append("Fett: " + nutriment.getQFat(quantity).toString() + "\n");
                textPane.append("sat.: " + nutriment.getQSaturated(quantity).toString() + "\n");
                textPane.append("unsat: " + nutriment.getQUnsaturated(quantity).toString() + "\n");
                textPane.append("Protein: " + nutriment.getQProtein(quantity).toString() + "\n");
                textPane.append("Fibres: " + nutriment.getQFibres(quantity).toString() + "\n");
            }
        });

        nutriToolBar.add(stackButton);
        stackButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String comboItem = (String) mapItems.getSelectedItem();
                Double quantity = Double.parseDouble(tQuantity.getText().replace(",", "."));

                nutrimentStack.push(comboItem);
                quantityStack.push(quantity);

                setText(comboItem + " mit Quantität " + quantity + " zum Stack hinzugefügt!\n");
            }
        });

        nutriToolBar.add(popButton);
        popButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String format1 = "|%1$-25s|%2$-35s|\n";
                ArrayList<String> outputTable = new ArrayList<>();
                //tabellenkopf
                outputTable.add(String.format(format1, "", "").replace(" ", "-"));
                outputTable.add(String.format(format1, "Nahrungsmittel", "Quantität"));
                outputTable.add(String.format(format1, "", "").replace(" ", "-"));
                //tabelleninhalt erzeugen
                Double carbs = 0.0;
                Double fat = 0.0;
                Double saturated = 0.0;
                Double unsaturated = 0.0;
                Double protein = 0.0;
                Double fibres = 0.0;
                while(nutrimentStack.empty() == false && quantityStack.empty() == false){
                    Nutriment nutriment = nutrimentMap.get(nutrimentStack.pop());
                    Double quantity = quantityStack.pop();
                    carbs = carbs + nutriment.getQCarbs(quantity);
                    fat = fat + nutriment.getQFat(quantity);
                    saturated = saturated + nutriment.getQSaturated(quantity);
                    unsaturated = unsaturated + nutriment.getQUnsaturated(quantity);
                    protein = protein + nutriment.getQProtein(quantity);
                    fibres = fibres + nutriment.getQFibres(quantity);
                    outputTable.add(String.format(format1, nutriment.getName(), quantity));
                }
                outputTable.add(String.format(format1, "", "").replace(" ", "-"));
                outputTable.add(String.format(format1, "Macro", "Wert"));
                outputTable.add(String.format(format1, "", "").replace(" ", "-"));
                outputTable.add(String.format(format1, "Brennwert", calcKiloCalories(fat, protein, carbs)));
                outputTable.add(String.format(format1, "", "").replace(" ", "-"));
                outputTable.add(String.format(format1, "Kohlenhydrate", carbs));
                outputTable.add(String.format(format1, "Fett", fat));
                outputTable.add(String.format(format1, "gesättigt", saturated));
                outputTable.add(String.format(format1, "ungesättigt", unsaturated));
                outputTable.add(String.format(format1, "Protein", protein));
                outputTable.add(String.format(format1, "Ballaststoffe", fibres));
                //tabellenschluss und drucken
                textPane.append("\n");
                outputTable.add(String.format(format1, "", "", "").replace(" ", "-"));
                outputTable.forEach(x -> setText(x));
            }
        });

        //add nutritools to nutripanel
        nutriPanel.add(nutriToolBar, BorderLayout.SOUTH);
    }

    private void createWriterTools() {
        //create writertools
        writerToolBar = new JToolBar();

        //füge knöpfe und actionlistener hinzu
        writerToolBar.add(saveBtn);
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String name = tName.getText();
                Double carbs = Double.parseDouble(tCarbs.getText().replace(",", "."));
                Double fat = Double.parseDouble(tFat.getText().replace(",", "."));
                Double saturated = Double.parseDouble(tSaturated.getText().replace(",", "."));
                Double unsaturated = Double.parseDouble(tUnsaturated.getText().replace(",", "."));
                Double protein = Double.parseDouble(tProtein.getText().replace(",", "."));
                Double fibres = Double.parseDouble(tFibres.getText().replace(",", "."));
                boolean successfulWriting = false;
                try {
                    Nutriment nutriment = new Nutriment(name, carbs, fat, saturated, unsaturated, protein, fibres);
                    MyXMLWriter writer = new MyXMLWriter(xmlDatei);
                    writer.setNutriment(nutriment);
                    writer.initParser();
                    nutrimentMap.put(nutriment.getName(), nutriment);
                    mapItems.addItem(nutriment.getName());
                    successfulWriting = true;
                    if(successfulWriting){
                        setText("Nahrungsmittel " + nutriment.getName() +" erfolgreich geschrieben!");
                    }   
                } catch (Exception e) {
                    if(successfulWriting = false){
                        setText("Schreiben nicht erfolgreich.");
                    }
                }
            }
        });

        //add writertools to writerpanel   
        writerPanel.add(writerToolBar, BorderLayout.NORTH);
    }

    public void setText(String text) {
        textPane.append(text);
    }

    private void createCombobox(){
        Vector<String> dummyVector = new Vector<>(mapKeys);
        mapItems = new JComboBox<String>(dummyVector);
        nutriPanel.add(mapItems, BorderLayout.NORTH);
    }

    private void createTextFields(){
        // label erzeugen
        iName = new JLabel("Name");
        tName = new JTextField();
        iCarbs = new JLabel("Kohlenhydrate");
        tCarbs = new JTextField();
        iFat = new JLabel("Fett");
        tFat = new JTextField();
        iSaturated = new JLabel("gesättigt");
        tSaturated = new JTextField();
        iUnsaturated = new JLabel("ungesättigt");
        tUnsaturated = new JTextField();
        iProtein = new JLabel("Eiweiß");
        tProtein = new JTextField();
        iFibres = new JLabel("Ballaststoffe");
        tFibres = new JTextField();
        iQuantity = new JLabel("Menge");
        tQuantity = new JTextField();

        // label zu nutri hinzufügen
        nutriTable.add(iQuantity);
        nutriTable.add(tQuantity);

        // label zu writer hinzufügen
        writerTable.add(iName);
        writerTable.add(tName);
        writerTable.add(iCarbs);
        writerTable.add(tCarbs);
        writerTable.add(iFat);
        writerTable.add(tFat);
        writerTable.add(iSaturated);
        writerTable.add(tSaturated);
        writerTable.add(iUnsaturated);
        writerTable.add(tUnsaturated);
        writerTable.add(iProtein);
        writerTable.add(tProtein);
        writerTable.add(iFibres);
        writerTable.add(tFibres);
    }

    public Double calcKiloCalories(Double fat, Double protein, Double carbs) {
        Double cKal = fat * 9.1 + (carbs + protein) * 4.1;
        return cKal;
    }
}