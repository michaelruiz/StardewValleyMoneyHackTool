package bin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class XMLTestesView extends JFrame implements ActionListener{

	private XMLTestesPresenter presenter;
	private XMLTestesModel model;
	
	JTextField moneyTextField;
	JButton setButton;
	JButton selectXMLButton;
	JLabel filepathLabel;
	JTextArea infoLabel;
	String newMoney;
	JFileChooser jfc1;
	String filepath;
	JTextArea xmlPreviewTextArea;
	CustomMenu menu;
	JPanel panel;
	
	public XMLTestesView(){
		presenter = new XMLTestesPresenter(this);
		setModel(getPresenter().getModel());

		buildUI();
						
	}

	public XMLTestesPresenter getPresenter(){
		return presenter;
	}
	public void setModel(XMLTestesModel model) {
		this.model = model;
	}
	public XMLTestesModel getModel(){
		return model;
	}

	public void buildUI(){
		
		menu = new CustomMenu();
		moneyTextField = new JTextField("50000");
		setButton = new JButton("Set!");
		selectXMLButton = new JButton("Load XML File...");
//		filepathLabel = new JLabel("<--Select an XML file");
		infoLabel = new JTextArea("Load an XML file.");
		jfc1 = new JFileChooser();
		filepath = "";
		xmlPreviewTextArea = new JTextArea("Load XML...");
		panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(xmlPreviewTextArea);
		scrollPane.setViewportView(xmlPreviewTextArea);
		scrollPane.setPreferredSize(new Dimension(500, 500));

//		JFrame.setDefaultLookAndFeelDecorated(true);

		add(panel);
		panel.setSize(800, 800);
//		panel.add(filepathLabel);
		panel.add(selectXMLButton, BorderLayout.NORTH);
		panel.add(infoLabel, BorderLayout.NORTH);
		panel.add(moneyTextField, BorderLayout.NORTH);
		panel.add(setButton, BorderLayout.NORTH);
		panel.add(menu);
		panel.add(scrollPane);		
		
		this.setTitle("Stardew Valley Money Hack");
		this.setJMenuBar(menu);
		this.setSize(810,660);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
				
		selectXMLButton.setBounds(50,50,150,20); 
//		moneyTextField.setBounds(100,100,200,20);  
//		infoLabel.setBounds(50,200,150,40);   
//		setButton.setBounds(10,50,20,50); 
		setButton.setVisible(false);
		moneyTextField.setVisible(false);
		infoLabel.setVisible(false);
//		moneyTextField.setSize(300, 300);
//		filepathLabel.setBounds(250,40,500,40); 
//		panel.setBounds(10,10,450,450); 
		xmlPreviewTextArea.setBounds(0, 0, 400, 400);
	
		setButton.addActionListener(this);
		selectXMLButton.addActionListener(this);
		
        jfc1.setCurrentDirectory(new File (this.presenter.getFilePath()));
        
        xmlPreviewTextArea.setWrapStyleWord(true);
        xmlPreviewTextArea.setLineWrap(true);
        xmlPreviewTextArea.setEditable(false);
        
        infoLabel.setWrapStyleWord(true);
        infoLabel.setLineWrap(true);
        infoLabel.setEditable(false);
        infoLabel.setOpaque(false);
        infoLabel.setBackground(Color.lightGray);
        infoLabel.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
        if(e.getSource()==setButton){  
        	
        	presenter.editFile(moneyTextField.getText(), presenter.getFilePath());
        	infoLabel.setText("XML File Updated! Money set to: " + moneyTextField.getText());

            xmlPreviewTextArea.setOpaque(true);
            xmlPreviewTextArea.setBackground(Color.MAGENTA);

            
        } else if (e.getSource()==selectXMLButton) {

        	int i = jfc1.showOpenDialog(this);
        	if(i==JFileChooser.APPROVE_OPTION){    
        	       File f=jfc1.getSelectedFile();    
        	       String filepath=f.getPath();    
        	       try{  
        	       BufferedReader br=new BufferedReader(new FileReader(filepath));    
        	       String s1="",s2="";                         
        	       while((s1=br.readLine())!=null){    
        	       s2+=s1+"\n";    
        	       }    
        	       xmlPreviewTextArea.setText(s2);
        	       filepathLabel.setText(presenter.getFilePath());
        	       br.close();    
        	       
        	     
        	       }catch (Exception ex) {ex.printStackTrace();  }                 
        	   } 
        	
        	panel.remove(selectXMLButton);

        	selectXMLButton.setVisible(false);
        	infoLabel.setVisible(true);
        	moneyTextField.setVisible(true);
    		setButton.setVisible(true);
    		infoLabel.setText("Enter new money amount and click Set");

        }
	
	}

}
