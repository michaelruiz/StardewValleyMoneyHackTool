package ui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.Document;

import com.sun.javafx.PlatformUtil;

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
        n.setTitle("Stardew Valley Money Mod v1.1");
        n.getInfoLabel().setText("Load a user XML file.");
        n.getXmlArea().setWrapStyleWord(true);
        n.getXmlArea().setText("Welcome to the tool. \n\nLoad your user's XML file, \nthen set your new money amount. \n\nIt's that easy. \n\nThanks for using! \n\n-FrodoSackins");;
        n.getjMenuItem1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// new menu item
				n.getLoadButton().setVisible(true);
				n.getMoneyField().setVisible(false);
				n.getSetButton().setVisible(false);
				n.getXmlArea().setText("Load a new file.");
				n.getInfoLabel().setText("Load a new file.");
				
			}
        	
        });
        n.getjMenuItem2().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Open 
				loadFile();
			}
        	
        });
        n.getjMenuItem3().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//exit menu item
				System.exit(1);
			}
        	
        });
        n.getjMenuItem5().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//btc menu item bc1qp9nm8y654jsxr0v373ne6cn2lyzhag7wucqsth
				JFrame j =  new JFrame("Donate BTC");
				JTextArea ta = new JTextArea("bc1qp9nm8y654jsxr0v373ne6cn2lyzhag7wucqsth");
				ta.setBackground(Color.black);
				ta.setForeground(Color.green);
				j.setSize(350, 45);
				j.setLocationRelativeTo(n);
				ta.setEditable(false);
				j.add(ta);
				j.setVisible(true);
				
			}
        	
        });
        n.getjMenuItem6().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//contact menu item         https://www.patreon.com/enhance

				JFrame j =  new JFrame("Contact");
				JTextArea ta = new JTextArea("https://www.patreon.com/enhance");
				ta.setBackground(Color.black);
				ta.setForeground(Color.green);
				j.setSize(350, 45);
				j.setLocationRelativeTo(n);
				ta.setEditable(false);
				j.add(ta);
				j.setVisible(true);
				
			}
        	
        });
        n.getjMenuItem7().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//btc menu item bc1qp9nm8y654jsxr0v373ne6cn2lyzhag7wucqsth
				JFrame j =  new JFrame("About Stardew Valley Money Mod Tool");
				JTextArea ta = new JTextArea("Stardew Valley Money Mod Tool \nVersion 1.1 \nby FrodoSackins\n Windows\\Mac\\Linux compatible");
				ta.setBackground(Color.black);
				ta.setForeground(Color.green);
				j.setSize(350, 100);
				j.setLocationRelativeTo(n);
				ta.setEditable(false);
				j.add(ta);
				j.setVisible(true);
				
			}
        	
        });
        n.getjMenuItem8().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//ether menu item 0xE86242bf7eF0d0e81F45470e3C50B10D90D012F7
				JFrame j =  new JFrame("Donate ETH");
				JTextArea ta = new JTextArea("0xE86242bf7eF0d0e81F45470e3C50B10D90D012F7");
				ta.setBackground(Color.black);
				ta.setForeground(Color.green);
				j.setSize(350, 45);
				j.setLocationRelativeTo(n);
				ta.setEditable(false);
				j.add(ta);
				j.setVisible(true);
			}
        	
        });
        
        
        	
        
        n.getSetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	n.getXmlArea().setText("");
                presenter.editFile(n.getMoneyField().getText());
                presenter.reloadXMLfile();
                
                try {
					n.getXmlArea().setText(presenter.readFile());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                scrollToWord(find);
                n.getInfoLabel().setText("Success! User file updated to: $" + n.getMoneyField().getText() +". Happy Farming :P ");
                n.getSetButton().setEnabled(false);
                n.getMoneyField().setEnabled(false);

            }
        });

        n.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          
            	loadFile();

            }

        });

    }

    ;
	public void loadFile() {
		jfc1.setCurrentDirectory(new File (System.getProperty("user.home") + System.getProperty("file.separator") + presenter.getHomeDir()));

    	int i = jfc1.showOpenDialog(n);
        if (i == JFileChooser.APPROVE_OPTION) {
            presenter.setFile(jfc1.getSelectedFile());
            presenter.setAbsolutePath(jfc1.getSelectedFile().getAbsolutePath());
            
            try {
				n.getXmlArea().setText(presenter.readFile());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

            n.getInfoLabel().setText("File Loaded. Enter new money amount and click Set.");

        } if (i==1) {
        	
        }
	}
    public XMLTestesPresenter getPresenter() {
        return presenter;
    }

    public void setModel(XMLTestesModel model) {
        this.model = model;
    }

    public XMLTestesModel getModel() {
        return model;
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
                    // Extract the text from the document
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
