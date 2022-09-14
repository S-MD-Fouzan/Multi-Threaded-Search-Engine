package com.amadeus.searchEngineV2.driveFinder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AllDriveFinder implements IDriveFinder{

    public List<String> getDrives(){

        List<String> drives = new ArrayList<String>();

        for(File drive : File.listRoots()){
            drives.add(drive.getAbsolutePath());
        }

        return drives;
    }
}
