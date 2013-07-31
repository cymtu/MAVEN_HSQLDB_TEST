package com.mycompany.maven_irkpk.Import;

import org.xml.sax.*;

public class ErrorHand implements ErrorHandler{

    @Override public void warning(SAXParseException exception){
        System.err.println("Warning: " + exception);
        System.err.println("line = " + exception.getLineNumber() + " col = " + exception.getColumnNumber());
    }

    @Override public void error(SAXParseException exception){
        System.err.println("Error: " + exception);
        System.err.println("line = " + exception.getLineNumber() + " col = " + exception.getColumnNumber());
    }

    @Override public void fatalError(SAXParseException exception){
        System.err.println("Fatal error: " + exception);
        System.err.println("line = " + exception.getLineNumber() + " col = " + exception.getColumnNumber());
    }
    
}
