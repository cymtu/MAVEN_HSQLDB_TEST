package com.mycompany.maven_irkpk;

import com.mycompany.maven_irkpk.Import.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Irkpk  extends JFrame{
    private JMenuBar menuBar;
    private JMenu fileMenu;    
    private JToolBar toolBar;
    private Action openAction, exitAction,deleteAction;
    private PersonsTable table;
    
    public Irkpk(){
        super("Test task");
        
        createAction();
        createMenu();
        createToolBar(); 
        createTable();
    }  
    
    private void createAction(){
        // Action Выход  
        Icon exitIcon = new ImageIcon(getClass().getResource("/images/exit.gif"));
      
        exitAction = new Action( "Exit", exitIcon,
            "Exit", new Integer('E')) {
         
        @Override public void actionPerformed(ActionEvent event){ 
            System.exit(0);
        }
      };
         
        // Action Импорт файла
        Icon openIcon = new ImageIcon(getClass().getResource("/images/open.gif"));
      
        openAction = new Action( "Open file", openIcon,
            "Import file", new Integer('O')) {
         
        @Override public void actionPerformed(ActionEvent event){ 
            openFile();
        }
      };  
        
        // Action Очистить таблицу
        Icon deleteIcon = new ImageIcon(getClass().getResource("/images/trash.gif"));
      
        deleteAction = new Action( "Delete", deleteIcon,
            "Delete", new Integer('D')) {
         
        @Override public void actionPerformed(ActionEvent event){ 
            Hibernate.getInstance().delete();
        }
      };         
    }
    
    private void createMenu(){
        menuBar = new JMenuBar(); 
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        
        fileMenu.add(openAction).setIcon(null);
        fileMenu.add(deleteAction).setIcon(null);
        fileMenu.addSeparator();
        fileMenu.add(exitAction).setIcon( null );
        
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
    
    private void createToolBar(){
        toolBar = new JToolBar();
        
        toolBar.add(openAction);  
        toolBar.add(deleteAction);
        toolBar.addSeparator();
        toolBar.add( exitAction );
        
        add(toolBar, BorderLayout.NORTH); 
    } 
    
    private void createTable(){ 
        table = new PersonsTable();      
        add(new JScrollPane(table));
    }
    
    private void openFile(){
        JFileChooser fileChooser;
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter( new IrkpkFileFilter("XML Files (*.xml)",".xml") );
        fileChooser.setFileFilter( new IrkpkFileFilter("CSV Files (*.csv)",".csv") );    
        fileChooser.setFileFilter( new IrkpkFileFilter("Property Files (*.properties)",".properties") );          
        
        int response = fileChooser.showOpenDialog( this );
      
        if ( response == fileChooser.APPROVE_OPTION ) {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath();
            String format = fileName.substring(fileName.indexOf(".") + 1);
            if(format.equals("xml")) new ImportContext(new ImportXML()).importFile(fileName);
            if(format.equals("csv")) new ImportContext(new ImportCSV()).importFile(fileName); 
            if(format.equals("properties")) new ImportContext(new ImportProperty()).importFile(fileName);                
        }
    }    

    public static void main(String[] args) {
        JFrame irkpk = new Irkpk();
        irkpk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        irkpk.setExtendedState(JFrame.MAXIMIZED_BOTH);
        irkpk.setVisible(true);
    }
}
