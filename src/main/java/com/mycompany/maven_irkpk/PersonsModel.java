package com.mycompany.maven_irkpk;

import java.util.*;
import javax.swing.table.DefaultTableModel;

public class PersonsModel extends DefaultTableModel{
    
    protected String[] colName;
    List tableData; 
    
    public PersonsModel(List data){
        super(data.size(),Person.class.getDeclaredFields().length);
        
        colName = new String[Person.class.getDeclaredFields().length];
        int i=0;
        String s;
        for(Object o: Person.class.getDeclaredFields()){
            s = o.toString().trim();
            s = s.substring(s.lastIndexOf('.')+1);
            colName[i]=s.toUpperCase();
            i++;
        }
        tableData=data;
    }
    
    @Override public String getColumnName(int col){
        return colName[col];
    }
    
    @Override public Object getValueAt(int row, int col){
        if(row>=tableData.size()) return null;
        Object o = tableData.get(row);
        switch(col){
            case 0: return ((Person)o).getId();
            case 1: return ((Person)o).getName();
            case 2: return ((Person)o).getAddress();
            case 3: return ((Person)o).getNotation();    
            default : return null;
        }
    }   
}
