package com.amadeus.searchEngineV2.model;

public class PathFactory {
	public static Path getInstance(String path) {
		return new Path(path);
	}

}
