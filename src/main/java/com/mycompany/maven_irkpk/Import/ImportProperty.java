package com.mycompany.maven_irkpk.Import;

import com.mycompany.maven_irkpk.*;
import java.io.*;
import java.util.Vector;
import java.util.logging.*;

public class ImportProperty implements ImportFile{

    @Override public Vector importFile(String file) {
        String s;
        String[] tmp;
        Person person = new Person();
        BufferedReader in;
        Vector v = new Vector();
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            try {
                while ((s = in.readLine()) != null  && s.length() != 0){
                  s=s.trim();
                  if(s.charAt(0)=='!' || s.charAt(0)=='#') continue;
                  tmp = s.split("=");
                  if(tmp[0].trim().equals("name")) person.setName(tmp[1].trim());
                  if(tmp[0].trim().equals("address")) person.setAddress(tmp[1].trim());
                  if(tmp[0].trim().equals("notation")) person.setNotation(tmp[1].trim());                 
                }
                v.add(person);
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
