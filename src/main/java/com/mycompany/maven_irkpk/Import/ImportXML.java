package com.mycompany.maven_irkpk.Import;

import com.mycompany.maven_irkpk.*;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class ImportXML implements ImportFile{
    
    private Person person;
    private Vector pers = new Vector();
    
    @Override public Vector importFile(String file) {
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        //fact.setNamespaceAware(true);
        fact.setValidating(true);
        
        try {
            DocumentBuilder builder = fact.newDocumentBuilder();
            builder.setErrorHandler(new ErrorHand());
            try {
                Document doc = builder.parse(file);
                NodeList list = doc.getElementsByTagName("person");
                
                int n = list.getLength();
                if(n==0) return null;
                
                Node thisNode = null;
                NodeList tmp = null;
                
                for(int k = 0; k < n; k++){
                    thisNode = list.item(k);
                    person = new Person();
                    tmp = thisNode.getChildNodes();
                    Node node;
                    for(int r=0; r <tmp.getLength(); r++){
                        node = tmp.item(r);
                        if(node.getNodeName().equals("name")) person.setName(node.getTextContent());
                        if(node.getNodeName().equals("address")) person.setAddress(node.getTextContent());
                        if(node.getNodeName().equals("notation")) person.setNotation(node.getTextContent());                       
                    }
                    pers.add(person);
                }
                
                return pers;
            } catch (SAXException ex) {
                Logger.getLogger(ImportXML.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (IOException ex) {
                Logger.getLogger(ImportXML.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ImportXML.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    } 
    
    
    
    
}
