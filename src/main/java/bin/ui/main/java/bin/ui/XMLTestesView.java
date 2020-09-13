package main.java.bin.ui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.text.Document;

public class XMLTestesView {

    private XMLTestesPresenter presenter;
    private XMLTestesModel model;
    private CustomJFrame jFrame;
    private JFileChooser jfc1;

    private String find = "<money>";
    
    public XMLTestesView() {
    	
        presenter = new XMLTestesPresenter(this);
        setModel(getPresenter().getModel());

        jFrame = new CustomJFrame();
        jfc1 = new JFileChooser();
        
        jFrame.setVisible(true);
        jFrame.setTitle("Stardew Valley Money Mod Tool v1.2");
        jFrame.getInfoLabel().setText("Load a user XML file.");
        jFrame.getXmlArea().setWrapStyleWord(true);
        jFrame.getXmlArea().setText("Welcome to the SVMMT. \n\nLoad your user's XML file, \nthen set your new money amount. \n\nIt's that easy. \n\nThanks for using! \n\n-FrodoSackins");;
        jFrame.setLocationRelativeTo(null);
        jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("acc.png")));

        
        jFrame.getjMenuItem1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// new menu item
				jFrame.getLoadButton().setVisible(true);
				jFrame.getMoneyField().setVisible(false);
				jFrame.getSetButton().setVisible(false);
				jFrame.getXmlArea().setText("Load a new file.");
				jFrame.getInfoLabel().setText("Load a new file.");
				
			}
        	
        });
        
        jFrame.getjMenuItem2().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Open 
				loadFile();
			}
        	
        });
        
        jFrame.getjMenuItem3().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//exit menu item
				System.exit(1);
			}
        	
        });
        
        jFrame.getjMenuItem5().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//btc menu item bc1qp9nm8y654jsxr0v373ne6cn2lyzhag7wucqsth
				JFrame j =  new JFrame("Donate BTC");
				JTextArea ta = new JTextArea("bc1qp9nm8y654jsxr0v373ne6cn2lyzhag7wucqsth");
				ta.setBackground(Color.black);
				ta.setForeground(Color.green);
				j.setSize(350, 45);
				j.setLocationRelativeTo(jFrame);
				ta.setEditable(false);
				j.add(ta);
				j.setVisible(true);
				
			}
        	
        });
        
        jFrame.getjMenuItem6().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//contact menu item         https://www.patreon.com/enhance

				JFrame j =  new JFrame("Contact");
				JTextArea ta = new JTextArea("https://www.patreon.com/enhance");
				ta.setBackground(Color.black);
				ta.setForeground(Color.green);
				j.setSize(350, 45);
				j.setLocationRelativeTo(jFrame);
				ta.setEditable(false);
				j.add(ta);
				j.setVisible(true);
				
			}
        	
        });
        
        jFrame.getjMenuItem7().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//About menu
        		
				JFrame j =  new JFrame("About Stardew Valley Money Mod Tool");
				JTextArea ta = new JTextArea("Stardew Valley Money Mod Tool \nVersion 1.2 \nby FrodoSackins\n Windows\\Mac\\Linux compatible"
						+ "\n \nSpecial thanks to my patreon supporters: \nTyler Robins \nDustin Michael Dixon\nChau Minh \nShawn\nDivi Lalambay Habari");
				ta.setBackground(Color.black);
				ta.setForeground(Color.green);
				j.setSize(350,250);
				j.setLocationRelativeTo(jFrame);
				ta.setEditable(false);
				j.add(ta);
				j.setVisible(true);
				try {
					AudioHandler.playClip(new File("main/resources/yay.wav"));
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException	| InterruptedException e2) {
					e2.printStackTrace();
				}
				
			}
        	
        });
        
        jFrame.getjMenuItem8().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//ether menu item 0xE86242bf7eF0d0e81F45470e3C50B10D90D012F7
				JFrame j =  new JFrame("Donate ETH");
				JTextArea ta = new JTextArea("0xE86242bf7eF0d0e81F45470e3C50B10D90D012F7");
				ta.setBackground(Color.black);
				ta.setForeground(Color.green);
				j.setSize(350, 45);
				j.setLocationRelativeTo(jFrame);
				ta.setEditable(false);
				j.add(ta);
				j.setVisible(true);
			}
        	
        });
        
        jFrame.getSetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
        		try {
					AudioHandler.playClip(new File("main/resources/click.wav"));
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException	| InterruptedException e2) {
					e2.printStackTrace();
				}
        		
            	jFrame.getXmlArea().setText("");
                presenter.editFile(jFrame.getMoneyField().getText());
                presenter.reloadXMLfile();
                
                try {
					jFrame.getXmlArea().setText(presenter.readFile());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                scrollToWord(find);
                jFrame.getInfoLabel().setText("Success! User file updated to: $" + jFrame.getMoneyField().getText() +". Happy Farming :P ");
                jFrame.getSetButton().setEnabled(false);
                jFrame.getMoneyField().setEnabled(false);

            }
        });

        jFrame.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          
            	loadFile();

            }

        });

    };
    
	public void loadFile() {
		if (model.getPlatform().equals("Windows")) {
			jfc1.setCurrentDirectory(new File(model.getHomeDir()));

		} else {
			
			jfc1.setCurrentDirectory(new File (model.getHomeDir()));
		}

    	int i = jfc1.showOpenDialog(jFrame);
        if (i == JFileChooser.APPROVE_OPTION) {
            presenter.setFile(jfc1.getSelectedFile());
            presenter.setAbsolutePath(jfc1.getSelectedFile().getAbsolutePath());
            
            try {
				jFrame.getXmlArea().setText(presenter.readFile());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            jFrame.getXmlArea().setWrapStyleWord(true);
            jFrame.getXmlArea().setLineWrap(true);
            jFrame.getXmlArea().setEditable(false);
            jFrame.getLoadButton().setVisible(false);
            jFrame.getMoneyField().setVisible(true);
            jFrame.getSetButton().setVisible(true);
            jFrame.getMoneyField().setEnabled(true);
            jFrame.getSetButton().setEnabled(true);

            // Focus the text area, otherwise the highlighting won't show up
            scrollToWord(find);

            jFrame.getInfoLabel().setText("File Loaded. Enter new money amount and click Set.");

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
        jFrame.getXmlArea().requestFocusInWindow();
        // Make sure we have a valid search term
        if (find != null && find.length() > 0) {
            Document document = jFrame.getXmlArea().getDocument();
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
                    Rectangle viewRect = jFrame.getXmlArea().modelToView(pos);
                    // Scroll to make the rectangle visible
                    jFrame.getXmlArea().scrollRectToVisible(viewRect);
                    // Highlight the text
                    jFrame.getXmlArea().setCaretPosition(pos + findLength);
                    jFrame.getXmlArea().moveCaretPosition(pos);
                    // Move the search position beyond the current match
                    pos += findLength;
                }

            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
    }
}
