package ui;

public class XMLTestesPresenter {
	
	private XMLTestesModel model;
	private XMLTestesView view;
	
	public XMLTestesView getView() {
		return this.view;
	}

	public XMLTestesPresenter(XMLTestesView view){
		this.view = view;
		model = new XMLTestesModel(this);
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
	
	public String getFilePath() {		
		return this.model.getFilepath();
	}
	
	public String getMoney() {			
		return this.model.getMoney();
	}
	
	public Boolean editFile(String money, String path) {	
		return (this.model.editFile(money, path));
	}	
}