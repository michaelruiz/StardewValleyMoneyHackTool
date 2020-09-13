package main.java.bin.ui;

import java.io.File;
import java.io.IOException;


public class XMLTestesPresenter {

    private XMLTestesModel model;
    private XMLTestesView view;

    public XMLTestesView getView() {
        return this.view;
    }

    public XMLTestesPresenter(XMLTestesView view) {
        this.view = view;
        model = new XMLTestesModel(this);
        

    	
    }
    
    public void setAbsolutePath(String path) {
        this.model.setAbsolutePath(path);
    }

    public String getAbsolutePath() {
        return this.model.getAbsolutePath();
    }

    public void setModel(XMLTestesModel model) {
        this.model = model;
    }

    public XMLTestesModel getModel() {
        return this.model;
    }

    public void setView(XMLTestesView view) {
        this.view = view;
    }

    public void setFilePath(String filepath) {
        this.model.setFilepath(filepath);
    }

    public String getFilePath() {
        return this.model.getFilepath();
    }

    public String getMoney() {
        return this.model.getMoney();
    }
    
    public String getHomeDir() {
        return this.model.getHomeDir();
    }
    
    public void setHomeDir(String homeDir) {
    	this.model.setHomeDir(homeDir);
    }
    
    public Boolean editFile(String money) {
        return (this.model.editFile(money));
    }

    public void setFile(File file) {
        this.model.setFile(file);
    }

    public File getFile() {
        return this.model.getFile();
    }

	public String readFile() throws IOException {
		
		return this.model.readFile();
	}

	public void reloadXMLfile() {
		// TODO Auto-generated method stub
		this.model.reloadXMLfile();
	}
	
}
