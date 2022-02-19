package view;

import javax.swing.*;

import model.MyXMLWriter;
import model.Nutriment;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class TrackerFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JSplitPane mainPanel;
    private JPanel calculusPanel;
    public JTextArea textPane;
    private JToolBar toolBar;
    private JButton retrieveBtn = new JButton("Setze Parameter");
    private File xmlDatei = new File("resources\\Nahrungstabelle.xml");

    public TrackerFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.defaultScreenSize();
        this.createPanel();
        this.createTools();
        mainPanel.setDividerLocation(0.5);
        setText("Bitte Datensätze eingeben:\n");

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
        calculusPanel = new JPanel(new BorderLayout());
        mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, calculusPanel, textPane);
        textPane.setEditable(true);
        textPane.setLineWrap(true);
        textPane.setFont(new Font("Courier New", Font.PLAIN, 12));
        this.add(mainPanel);
        this.setVisible(true);

    }

    private void createTools() {
        toolBar = new JToolBar();
        toolBar.add(retrieveBtn);
        retrieveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Nutriment nutriment = new Nutriment("Erdbeere", 1, 2, 3, 4, 5, 6);
                MyXMLWriter writer = new MyXMLWriter(xmlDatei);
                writer.setNutriment(nutriment);
                writer.initParser();
                
            }
        });
        this.add(toolBar, BorderLayout.NORTH);

        // retrieveBtn.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent ae) {

        // }
        // });

    }

    public void setText(String text) {
        textPane.append(text);
    }
}