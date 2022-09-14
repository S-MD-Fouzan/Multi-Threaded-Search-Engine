package com.amadeus.searchEngineV2.analytics;

public interface IAnalyticsManager {

	public long getNumberOfSearchesMadeByUser();
	public long getTimesSearched(String filename);
	public String getMostSearched();
}
