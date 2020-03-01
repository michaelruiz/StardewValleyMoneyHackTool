package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

import com.sun.javafx.PlatformUtil;

public class XMLTestesModel {

    private String homeDir;
    private String absolutePath;


	private String filepath = "";
    private XMLTestesPresenter presenter;
    private File file;
    private String money;
    private String platform;
    private String systemPropHomeDir;

    public XMLTestesModel(XMLTestesPresenter presenter) {

        this.presenter = presenter;
        
    	
	  if (PlatformUtil.isMac()){
		  platform = "Mac";
		  systemPropHomeDir = System.getProperty("user.home");
		  homeDir = ("/.config/StardewValley/Saves/");
		  filepath = homeDir;
	  } else if (PlatformUtil.isWindows()){
		  platform = "Windows";
		  systemPropHomeDir = System.getProperty("user.home");
	      homeDir = ("\\.config\\StardewValley\\Saves\\");
		  filepath = homeDir;

	  }

    }
    
    public String getAbsolutePath() {
		return absolutePath;
	}


	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public String getHomeDir() {
		return homeDir;
	}

	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
	}
	
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

    public Boolean editFile(String money) {
        docFactory = DocumentBuilderFactory.newInstance();
        doc = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(new InputSource(this.getAbsolutePath()));
        } catch (ParserConfigurationException | SAXException | IOException e) {
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
            xformer.transform(new DOMSource(doc), new StreamResult(new File(this.getAbsolutePath())));
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    public String readFile() throws IOException {
    	
    	BufferedReader br = new BufferedReader(new FileReader(getFile()));
        String s1 = "", s2 = "";
        while ((s1 = br.readLine()) != null) {
            s2 += s1 + "\n";
        }

        br.close();
    	
		return s2;
    	
    }
    
    public void setMoney(Node node, String money) {

        if (node.getNodeName() == "money") {
            node.setTextContent(money);

            System.out.println("Money set to: " + money);
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

    public void reloadXMLfile() {
    	
        docFactory = DocumentBuilderFactory.newInstance();
        doc = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(new InputSource(this.getAbsolutePath()));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
