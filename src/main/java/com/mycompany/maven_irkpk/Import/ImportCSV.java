package com.mycompany.maven_irkpk.Import;

import com.mycompany.maven_irkpk.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class ImportCSV implements ImportFile {

    @Override public Vector importFile(String file) {
        String s;
        String[] tmp;
        Person person;
        BufferedReader in;
        Vector v = new Vector();
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            try {
                while ((s = in.readLine()) != null  && s.length() != 0){
                  s=s.trim();
                  if(s.charAt(0)=='#') continue;
                  tmp = s.split(";");
                  person = new Person(tmp[0].trim(),tmp[1].trim(),tmp[2].trim());
                  v.add(person);
                }
                return v;
            } catch (IOException ex) {
                Logger.getLogger(ImportProperty.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImportProperty.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    } 
}
