package com.mycompany.bin;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.text.Document;

public class XMLTestesView {

    private XMLTestesPresenter presenter;
    private XMLTestesModel model;
    private CustomJFrame n;
    private JFileChooser jfc1;

    private String find = "<money>";

    public XMLTestesView() {
        presenter = new XMLTestesPresenter(this);
        setModel(getPresenter().getModel());

        n = new CustomJFrame();
        jfc1 = new JFileChooser();

        n.setVisible(true);

        n.getSetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.editFile(n.getMoneyField().getText(), presenter.getFilePath());
//                    reloadXml();
//                    scrollToWord(find);
                n.getInfoLabel().setText("Success! User file updated. Happy Farming :P ");
                n.getSetButton().setEnabled(false);
                n.getMoneyField().setEnabled(false);
                throw new UnsupportedOperationException(e.toString()); //To change body of generated methods, choose Tools | Templates.
            }
        });

        n.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = jfc1.showOpenDialog(n);

                if (i == JFileChooser.APPROVE_OPTION) {
                    presenter.setFile(jfc1.getSelectedFile());

                    String filepath = presenter.getFile().getPath();
                    presenter.setFilePath(filepath);
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(presenter.getFilePath()));
                        String s1 = "", s2 = "";
                        while ((s1 = br.readLine()) != null) {
                            s2 += s1 + "\n";
                        }

                        br.close();
                        n.getXmlArea().setText(s2);
                        n.getXmlArea().setWrapStyleWord(true);
                        n.getXmlArea().setLineWrap(true);
                        n.getXmlArea().setEditable(false);
                        n.getLoadButton().setVisible(false);
                        n.getMoneyField().setVisible(true);
                        n.getSetButton().setVisible(true);
                        n.getMoneyField().setEnabled(true);
                        n.getSetButton().setEnabled(true);

                        // Focus the text area, otherwise the highlighting won't show up
                        scrollToWord(find);

                        n.getInfoLabel().setText("File Loaded.");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                throw new UnsupportedOperationException(e.toString()); //To change body of generated methods, choose Tools | Templates.

            }
        });

    }

    ;
    
    public XMLTestesPresenter getPresenter() {
        return presenter;
    }

    public void setModel(XMLTestesModel model) {
        this.model = model;
    }

    public XMLTestesModel getModel() {
        return model;
    }

    public void reloadXml() {
        n.getXmlArea().setText("");
        try {
            BufferedReader br = new BufferedReader(new FileReader(presenter.getFilePath()));
            String s1 = "", s2 = "";
            while ((s1 = br.readLine()) != null) {
                s2 += s1 + "\n";
            }
            br.close();
            n.getXmlArea().setText(s2);

            // Focus the text area, otherwise the highlighting won't show up
            scrollToWord(find);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void scrollToWord(String find) {
        int pos = 0;
        n.getXmlArea().requestFocusInWindow();
        // Make sure we have a valid search term
        if (find != null && find.length() > 0) {
            Document document = n.getXmlArea().getDocument();
            int findLength = find.length();
            try {
                boolean found = false;
                // Rest the search position if we're at the end of the document
                if (pos + findLength > document.getLength()) {
                    pos = 0;
                }
                // While we haven't reached the end...
                // "<=" Correction
                while (pos + findLength <= document.getLength()) {
                    // Extract the text from teh docuemnt
                    String match = document.getText(pos, findLength).toLowerCase();
                    // Check to see if it matches or request
                    if (match.equals(find)) {
                        found = true;
                        break;
                    }
                    pos++;
                }

                // Did we find something...
                if (found) {
                    // Get the rectangle of the where the text would be visible...
                    Rectangle viewRect = n.getXmlArea().modelToView(pos);
                    // Scroll to make the rectangle visible
                    n.getXmlArea().scrollRectToVisible(viewRect);
                    // Highlight the text
                    n.getXmlArea().setCaretPosition(pos + findLength);
                    n.getXmlArea().moveCaretPosition(pos);
                    // Move the search position beyond the current match
                    pos += findLength;
                }

            } catch (Exception exp) {
                exp.printStackTrace();
            }

        }
    }

}
