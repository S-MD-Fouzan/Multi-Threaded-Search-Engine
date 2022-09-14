package com.amadeus.searchEngineV2.driveFinder;

public class InvalidDriveFinderChoiceException extends Exception{

    public InvalidDriveFinderChoiceException(){

    }

    public InvalidDriveFinderChoiceException(String message){
        super(message);
    }

    public InvalidDriveFinderChoiceException(String message, Exception innerException){
        super(message, innerException);
    }
}
