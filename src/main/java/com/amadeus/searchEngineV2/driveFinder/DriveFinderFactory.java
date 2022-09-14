package com.amadeus.searchEngineV2.driveFinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amadeus.searchEngineV2.errorlog.ErrorLogService;

@Service
public class DriveFinderFactory {
	

    public static IDriveFinder create(String choice) throws InvalidDriveFinderChoiceException {
        IDriveFinder finder = null;

        if(choice.equals("All"))
            finder = new AllDriveFinder();
        else if(choice.equals("Active"))
            finder = new ActiveDriveFinder();
        else {
       
            throw new InvalidDriveFinderChoiceException("Invalid Choice");
        }

        return finder;
    }
}
