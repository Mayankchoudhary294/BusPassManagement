package com.amazon.buspassmanagement;

import java.io.File;

import com.amazon.buspassmanagement.db.DB;

public class App {
	
    public static void main( String[] args ){
       
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( "Welcome to Bus Pass Management Application" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	
    	Menu menu = new Menu();
        
    	if(args.length > 0) {
    	    String filePath = args[0];
    	    File file = new File(filePath);

    	    if (file.exists() && file.isFile()) {
    	        DB.FILEPATH = filePath;
    	    } else {
    	        System.out.println("Invalid file path: " + filePath);
    	        System.exit(1);
    	    }
    	}
        
        menu.showMainMenu();
		
    }
    
}
