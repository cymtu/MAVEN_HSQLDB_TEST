package com.mycompany.maven_irkpk;

import java.io.File;
import javax.swing.filechooser.*;

public class IrkpkFileFilter  extends FileFilter {
    private String DESCRIPTION = "XML Files (*.xml)";
    private String EXTENSION = ".xml";
    
    public IrkpkFileFilter(String DES, String EXT){
        DESCRIPTION = DES;
        EXTENSION = EXT;
    }
    
    @Override public boolean accept(File file) {
        return ( file.getName().toLowerCase().endsWith( EXTENSION ) );
    }

    @Override public String getDescription() {
        return DESCRIPTION;
    }
}
