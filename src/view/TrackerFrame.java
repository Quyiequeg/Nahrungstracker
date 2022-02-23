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
import java.util.Vector;

public class TrackerFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> mapItems;
    private JSplitPane mainPanel, workspacePanel;
    private JPanel writerPanel, nutriPanel;
    public JTextArea textPane;
    private JToolBar nutriToolBar, writerToolBar, textToolBar;
    private JButton saveBtn = new JButton("Nahrungsmittel speichern");
    private JButton listButton = new JButton("Zutatenliste");
    private File xmlDatei = new File("resources\\Nahrungstabelle.xml");
    private HashMap<String, Nutriment> nutrimentMap = new HashMap<>();
    private Set<String> mapKeys;
    
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
        workspacePanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, nutriPanel, writerPanel);
        mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, workspacePanel, textPane);
        textPane.setEditable(false);
        textPane.setLineWrap(true);
        textPane.setFont(new Font("Courier New", Font.PLAIN, 10));
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

        //add nutritools to nutripanel
        nutriPanel.add(nutriToolBar, BorderLayout.NORTH);
    }

    private void createWriterTools() {
        //create writertools
        writerToolBar = new JToolBar();

        //füge knöpfe und actionlistener hinzu
        writerToolBar.add(saveBtn);
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean successfulWriting = false;
                try {
                    Nutriment nutriment = new Nutriment("Erdbeere", 1, 2, 3, 4, 5, 6);
                    MyXMLWriter writer = new MyXMLWriter(xmlDatei);
                    writer.setNutriment(nutriment);
                    writer.initParser();
                    nutrimentMap.put(nutriment.getName(), nutriment);
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
}