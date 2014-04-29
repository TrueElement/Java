package gui;

import data.Data;
import data.Page;
import net.Parser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.io.IOException;

public class GUI {
    Data data;
    Parser parse;
    JTextField urlField;
    JTextField categoryField;
    JTextField categoryURL;
    JTextArea output;
    Listener evt;
    
    public GUI(Data data) {
        this.data = data;
        this.parse = new Parser();
    }

    public void createAndShowGUI() {
        evt = new Listener(this);
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();

        JFrame frame = new JFrame();
            frame.setTitle("CSC 365 - Assignment 1");
            frame.setSize(700,700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(grid);

        JLabel label = new JLabel("Enter URL:");
            gc.gridx = 0;
            gc.gridy = 0;
            gc.anchor = GridBagConstraints.WEST;
            gc.gridwidth = GridBagConstraints.RELATIVE;
            gc.weightx = 0;
            gc.weighty = 0;
            gc.fill = GridBagConstraints.BOTH;
        frame.add(label,gc);

        urlField = new JTextField();
            urlField.setColumns(30);
            gc.gridx = 1;
            gc.gridwidth = 4;
        frame.add(urlField, gc);

        JButton process = new JButton("Go!");
            process.setActionCommand("process");
            process.addActionListener(evt);
            gc.gridx = 2;
        frame.add(process, gc);

        output = new JTextArea();
            output.setEditable(false);
            output.setPreferredSize(new Dimension(700, 300));


        JScrollPane scroll = new JScrollPane(output);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setPreferredSize(new Dimension(700,300));
            gc.gridx = 0;
            gc.gridy = 2;
            gc.weightx = 1.0;
            gc.gridwidth = 1;
        frame.add(scroll, gc);

        gc = new GridBagConstraints();
        
        label = new JLabel("New Category: ");
            gc.gridx = 0;
            gc.gridy = 3;
            gc.weightx = 0;
            gc.gridwidth = 8;
        frame.add(label, gc);

        categoryField = new JTextField();
            categoryField.setColumns(15);
            gc.gridx = 1;
        frame.add(categoryField, gc);

        label = new JLabel("URL:");
            gc.gridx = 2;
        frame.add(label, gc);

        categoryURL = new JTextField();
            categoryURL.setColumns(30);
            gc.gridx = 3;
        frame.add(categoryURL, gc);

        JButton categoryButton = new JButton("Submit");
            categoryButton.setActionCommand("newCategory");
            categoryButton.addActionListener(evt);
            gc.gridx = 4;
        frame.add(categoryButton, gc);

        JButton viewButton = new JButton("Show Known URLs and Categories");
            viewButton.setActionCommand("view");
            viewButton.addActionListener(evt);
            gc.gridx = 0;
            gc.gridy = 4;
            
        frame.add(viewButton, gc);

        frame.pack();
        frame.setVisible(true);
    }

    public void append(String text) {
        output.append(text);
    }

    public void clear() {
        output.setText("");
    }

    public void set(String text) {
        output.setText(text);
    }

    public void process() {
        String url = urlField.getText();
            Page page = buildPage(url);
            String category = data.matchCategory(page);
            String similar = data.getSimilar(page);

        StringBuffer sb = new StringBuffer();
            sb.append("Closest Category: ");
            sb.append(category);
            sb.append(System.getProperty("line.separator"));
            sb.append("Similar Known URL's: ");
            sb.append(System.getProperty("line.separator"));
            sb.append(similar);
        set(sb.toString());
    }

    public void addCategory() {
        try {
        	append("Attempting to add category...");
            String url = categoryURL.getText();
            String category = categoryField.getText();
            String[] words = parse.parse(url);
            append("Got Category Name: " + category);
            append("Got URL: " + url);
            data.addCategory(category, url, words);
        } catch (IOException e) {
            append("----------------------------");
            append("ERROR - INVALID URL - ADD CATEGORY");
            append("----------------------------");
            e.printStackTrace();
        }
    }

    public void showCategories() {
        clear();
        this.append(data.getCategories());
    }

    public Page buildPage(String url) {
        Page temp = null;
        try {
            temp = new Page();
                temp.setURL(url);
                temp.process(parse.parse(url));
        } catch(IOException e) {
            append("----------------------------");
            append("ERROR - INVALID URL - BUILD PAGE");
            append("----------------------------");
            e.printStackTrace();
        }
        return temp;
    }

}

class Listener implements ActionListener {
    GUI gui;
    public Listener(GUI gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("process")) {
            gui.process();
        } else if(command.equals("newCategory")) {
            gui.addCategory();
        } else if(command.equals("view")) {
            gui.showCategories();
        } else {
            gui.append("DEBUG - Got action command: " + command);
        }
    }

}