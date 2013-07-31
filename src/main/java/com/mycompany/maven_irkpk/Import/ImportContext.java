package com.mycompany.maven_irkpk.Import;

import com.mycompany.maven_irkpk.*;
import java.util.Vector;

public class ImportContext {
    private ImportFile strategy;
    private Hibernate hibernate;
    
    public ImportContext(ImportFile strategy) {
        hibernate = Hibernate.getInstance();
        this.strategy = strategy;
    }
 
    public void importFile(String file){
        Vector v = strategy.importFile(file);
        if(v==null) return;
        if(v.isEmpty()) return;
        hibernate.save(v);
    }    
}
