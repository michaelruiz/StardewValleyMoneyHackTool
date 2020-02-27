package ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CustomMenu extends JMenuBar{

	JMenu fileMenu;
	JMenu submenu;
	JMenu helpMenu;
	JMenuItem i1, i2, i3, i4, i5, about;
	
	public CustomMenu(){
		
		fileMenu=new JMenu("File");
		submenu=new JMenu("Contact");
		helpMenu=new JMenu("Help");
		
		i1=new JMenuItem("New");
		i2=new JMenuItem("Open XML File...");
		i3=new JMenuItem("Exit");
		i4=new JMenuItem("Email");
		i5=new JMenuItem("Patreon");
		
		about = new JMenuItem("About");
		
		fileMenu.add(i1); 
		fileMenu.add(i2); 
		fileMenu.add(i3);
		
		submenu.add(i4); 
		submenu.add(i5);		
		
		helpMenu.add(submenu);
		helpMenu.add(about);
		
		this.add(fileMenu);
		this.add(helpMenu);
	}

	
}
