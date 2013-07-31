package com.mycompany.maven_irkpk;

import java.awt.event.*;
import javax.swing.*;

public abstract class Action extends AbstractAction {
   
   public Action( String name, Icon icon, 
      String description, Integer mnemonic){
      setName( name );
      setSmallIcon( icon );
      setShortDescription( description );
      setMnemonic( mnemonic );
   }
   
   public void setName( String name ){
      putValue( Action.NAME, name );
   }

   public void setSmallIcon( Icon icon ){
      putValue( Action.SMALL_ICON, icon );
   }
   
   public void setShortDescription( String description ){
      putValue( Action.SHORT_DESCRIPTION, description );
   }
   
   public void setMnemonic( Integer mnemonic ) {
      putValue( Action.MNEMONIC_KEY, mnemonic );
   }
   
    @Override public abstract void actionPerformed( ActionEvent event );
}
