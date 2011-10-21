package dk.frv.eavdam.utils;

import dk.frv.eavdam.data.EAVDAMData;
import dk.frv.eavdam.data.EAVDAMUser;
import dk.frv.eavdam.data.FTP;
import dk.frv.eavdam.data.Options;
import dk.frv.eavdam.data.Simulation;
import dk.frv.eavdam.io.FTPHandler;
import dk.frv.eavdam.io.XMLExporter;
import dk.frv.eavdam.io.XMLImporter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import dk.frv.eavdam.io.derby.DerbyDBInterface;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class DBHandler {
        
    private static EAVDAMData data = new EAVDAMData();  // for testing before the database works        
    private static boolean initialized = false;    
    private static boolean updatedXML = false;
    
    public static EAVDAMData getData() {        

    	EAVDAMData dat = new EAVDAMData();
    	if(!initialized){
    		//Check if the database exists. It does the check in the constructor.
    		DerbyDBInterface d = new DerbyDBInterface();
			
			// loads other users' xmls
			try {
				Options options = getOptions();
				String ownFileName = XMLHandler.getLatestDataFileName();
				if (ownFileName != null && ownFileName.indexOf("/") != -1) {
					ownFileName = ownFileName.substring(ownFileName.lastIndexOf("/")+1);
				}			
				List<FTP> ftps = options.getFTPs();
				if (ftps != null && !ftps.isEmpty()) {
					for (FTP ftp : ftps) {
						try {
							FTPHandler.importDataFromFTP(ftp, XMLHandler.importDataFolder, ownFileName);                                       
						} catch (IOException ex) {
							System.out.println(ex.getMessage());
							ex.printStackTrace();
						}
					}
				}	
	    	} catch (Exception e) {
	    		e.printStackTrace();
	//    		System.out.println(e.getMessage());
	    	}	
			
    		d.closeConnection();
    		
    		dat = XMLHandler.importData();
    		initialized = true;
    	}else{
    	
 
	    	
	    	try {
	    		DerbyDBInterface d = new DerbyDBInterface();
	            EAVDAMUser user = d.retrieveDefaultUser();  
	            System.out.println("Retrieved default user: "+user.getOrganizationName());
	            dat = d.retrieveAllEAVDAMData(user);
	                    
	                    
	                    
	
	    	} catch (Exception e) {
	    		e.printStackTrace();
	//    		System.out.println(e.getMessage());
	    	}

    	}
            
        return dat;

    }

    public static void saveData(EAVDAMData data) {
        DBHandler.data = data;   // for testing before the database works
        DerbyDBInterface d = new DerbyDBInterface();
        //d.createDatabase(null);        
        
//        System.out.println("Saving data for user "+data.getUser().getOrganizationName()+" ("+data.getActiveStations().get(0).getStations().get(0).getStationName()+")");
        d.insertEAVDAMData(data);

        if(!updatedXML){
			System.out.println("Writing the xml to file...");
			try {

				XMLHandler.exportData();
	    		System.out.println("Writing finished!");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			updatedXML = true;
        }
    }
    
    public static void saveUserData(EAVDAMUser user){
    	DerbyDBInterface d = new DerbyDBInterface();
        //d.createDatabase(null);
    	try{
    		int id = d.insertEAVDAMUser(user, false);
//    		System.out.println("Added user under id "+id+".");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * Retrieves the options from the database..
     * 
     * @return
     */
    public static Options getOptions() {
		Options op = new Options();
    	
		DerbyDBInterface db = new DerbyDBInterface();
		
    	op = db.getOptions();
		
		
		return op;
	}
    		
    public static void saveOptions(Options options){
    	DerbyDBInterface db = new DerbyDBInterface();
    	db.insertOptions(options);
    }
    
    public static void deleteSimulation(String simulationName){
    	try{
    		DerbyDBInterface db = new DerbyDBInterface();
    		db.deleteSimulation(simulationName);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
}
        