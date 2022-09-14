package com.amadeus.searchEngineV2.driveFinder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ActiveDriveFinder implements IDriveFinder{

    public List<String> getDrives(){

        List<String> drives = new ArrayList<String>();

        for(File drive : File.listRoots()){
            if(drive.canRead()){
            	
                drives.add(drive.getAbsolutePath());
            }
        }

        return drives;
    }
}
