package dk.frv.eavdam.utils;

import dk.frv.eavdam.data.EAVDAMData;
import dk.frv.eavdam.data.EAVDAMUser;
import dk.frv.eavdam.io.derby.DerbyDBInterface;
import dk.frv.eavdam.io.XMLExporter;
import dk.frv.eavdam.io.XMLImporter;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBException;

public class XMLHandler {
    
    //public static EAVDAMData currentEAVDAMData;
	public static final String exportDataFolder = "generated";
	public static final String importDataFolder = "import";
    
    public static String getLatestDataFileName() {
        
        String datafile = "";
        
        File dir = new File(exportDataFolder);
        String[] children = dir.list();
        if (children != null) {
            for (int i=0; i<children.length; i++) {
                String filename = children[i];
                if (filename.endsWith("xml")) {
                    if (filename.compareTo(datafile) > 0) {
                        datafile = filename;
                    }
                }
            }
        }
        
        if (datafile.isEmpty()) {
            return null;
        } else {
            return exportDataFolder+"/" + datafile;
        }        
    }
    
    /**
     * Compares the two xml-files. 
     * 
     * @param compareXMLFile
     * @param compareToXMLFile
     * @return true if the first xml-file (compareXMLFile) is older (is before) than the second file (compareToXMLFile). If there are no timestamps, last modified values are compared.
     */
    public static boolean isOlderXML(File compareXMLFile, File compareToXMLFile){

		try{
    		
    		
    		Timestamp t1 = XMLImporter.getXMLTimestamp(compareXMLFile);
    		Timestamp t2 = XMLImporter.getXMLTimestamp(compareToXMLFile);
    		
    		return t1.before(t2);
    	}catch(Exception e){
    		e.printStackTrace();
    		
    	 
    	}
    	
		if(compareXMLFile.lastModified() < compareToXMLFile.lastModified()){
			System.err.println("There were no timestamps in xml-files. Last modified times of the files are compared instead!");
			return true;
		}
		
    	return false;
    }
    
    public static String getNewDataFileName(String organisationName) {
        
        if (organisationName == null || organisationName.isEmpty()) {
        
            organisationName = "undefined_organisation";
        
            try {
                String datafile = getLatestDataFileName();
                if (datafile != null) {        
                    EAVDAMData data = XMLImporter.readXML(new File(datafile));
                    if (data != null) {
                        EAVDAMUser user = data.getUser();
                        if (user != null) {
                            String temp = user.getOrganizationName();
                            if (temp != null && !temp.isEmpty()) {
                                organisationName = temp;
                            }
                        }
                    }
                }
            } catch (JAXBException e) {
            } catch (MalformedURLException e) {}
        
        }
        
        GregorianCalendar gc = new GregorianCalendar();
        String year = String.valueOf(gc.get(Calendar.YEAR));
        String month = String.valueOf(gc.get(Calendar.MONTH)+1);
        if (month.length() == 1) {
            month = "0" + month;
        }
        String day = String.valueOf(gc.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) {
            day = "0" + day;
        }
        String hours = String.valueOf(gc.get(Calendar.HOUR_OF_DAY));
        if (hours.length() == 1) {
            hours = "0" + hours;
        }
        String minutes = String.valueOf(gc.get(Calendar.MINUTE));
        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }
        String seconds = String.valueOf(gc.get(Calendar.SECOND));
        if (seconds.length() == 1) {
            seconds = "0" + seconds;
        }                
     
        final char[] illegalCharacters = { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':' };
        for (char c : illegalCharacters) {
            organisationName.replace(c, '_');
        }
        return exportDataFolder+ "/" + organisationName+".xml";// + "_" + year + month + day + hours + minutes + seconds + ".xml"; 
    }
    
    public static void deleteOldDataFiles() {
        
        String latestDataFile = getLatestDataFileName();
        
        if (latestDataFile != null) {
            File dir = new File("data");
            String[] children = dir.list();
            if (children != null) {
                for (int i=0; i<children.length; i++) {
                    String filename = children[i];
                    if (filename.endsWith("xml")) {
                        if (!new String("data/" + filename).equals(latestDataFile)) {
                            new File("data/" + filename).delete();
                        }
                    }
                }
            }
        }
    }
    
    
    /**
     * Imports the data from the importDataFolder (default: import/) and stores it to the database. After storing, all the data is retrieved from the database.
     * 
     * @return Data that holds all the relevant stations.
     */
    public static EAVDAMData importData() {
    	System.out.println("Importing data...");
		DerbyDBInterface db = DBHandler.derby;
		if(db == null) db = new DerbyDBInterface();
		
    	try {
        	File importFolder = new File(importDataFolder);
        	String[] files = importFolder.list();
        	if(files == null) return null;
        	
        	EAVDAMUser defaultUser = db.retrieveDefaultUser();
        	
        	String du = ""; 
        	if(defaultUser != null)
        		du = defaultUser.getOrganizationName();
        	
        	for(String file : files){
        		System.out.println("Importing file: "+file);
        		EAVDAMData d = XMLImporter.readXML(new File(importFolder+"/"+file));
				
        		System.out.println("USER: "+d.getUser().getOrganizationName()+" (default: "+du+")");
        		if(d.getUser().getOrganizationName().equals(du)){
        			System.out.println("\tUser is the default user. Skipping import on this file...");
        			continue;
        		}else{
        			db.deleteUser(d.getUser());
        			System.out.println("Delete complete...");
        			db.insertEAVDAMData(d);
        		}
        	}
        	
        	return db.retrieveAllEAVDAMData(db.retrieveDefaultUser());
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (JAXBException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e){
        	e.printStackTrace();
        }
        
        return null;
    
    }

    /** 
     * Saves data to XML file.
     *
     * @param data  Data to be saved
     */    
    public static void exportData() {
        try {
			DerbyDBInterface db = DBHandler.derby;
			if(db == null) db = new DerbyDBInterface();
			
    		EAVDAMData exportData = db.retrieveEAVDAMDataForXML();
        	
            File file = new File(exportDataFolder);
            if (!file.exists()) {
                file.mkdir();
            }
            
            String organisationName = null;
            if (exportData != null) {
                EAVDAMUser user = exportData.getUser();
                if (user != null) {
                    organisationName = user.getOrganizationName();
                }
            }
            //currentEAVDAMData = data;
            XMLExporter.writeXML(exportData, new File(getNewDataFileName(organisationName.replaceAll(" ", ""))));            
//            deleteOldDataFiles();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (JAXBException ex) {
            System.out.println("JAXBException: " + ex.getMessage());
            //ex.printStackTrace();
        }        
        
        
    }
    
}
        