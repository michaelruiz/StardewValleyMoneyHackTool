package ui;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLTestesModel {
	String homeDir = System.getProperty("user.home");
    String stardew = homeDir + "/.config/StardewValley/Saves/";
	String filepath = stardew;
	private XMLTestesPresenter presenter;
	
	public XMLTestesModel(XMLTestesPresenter presenter){
		
		this.presenter = presenter;
		
	}
	private String money;	
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getMoney() {
		return money;
	}


	private DocumentBuilderFactory docFactory;
	private Document doc;
	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	private DocumentBuilder docBuilder;
	
	public Boolean editFile(String money, String path) {
		docFactory = DocumentBuilderFactory.newInstance();
		doc = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(new InputSource(path));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get the root element
		 setMoney(doc.getDocumentElement(), money);
		 
		 Transformer xformer = null;
		try {
			xformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerFactoryConfigurationError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   try {
				xformer.transform(new DOMSource(doc), new StreamResult(new File(filepath)));
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
	}
	public void setMoney(Node node, String money) {
	   // do something with the current node instead of System.out
//	   System.out.println(node.getNodeName());
		
		if (node.getNodeName() =="money") {
    		node.setTextContent(money);	
    		
    		System.out.println("Money set to: "+ money);
    	}

	   NodeList nodeList = node.getChildNodes();
	   for (int i = 0; i < nodeList.getLength(); i++) {
	       Node currentNode = nodeList.item(i);
	       if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
	           //calls this method for all the children which is Element
	       	Element eElement = (Element) node;
	       	if ("money".equals(eElement.getNodeName())) {
	       		eElement.setTextContent("123444");
	       	}
	           setMoney(currentNode, money);
	       }
	   }
	}


}