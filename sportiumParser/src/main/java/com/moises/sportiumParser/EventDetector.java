package com.moises.sportiumParser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventDetector {
	
	private static final Map <String,String> patternMap;
	static
	{
		patternMap = new HashMap<String,String>();
		patternMap.put("football", "([\\\"a-zA-Z/./\\s]+\\d+-\\d+[a-zA-Z/./\\s\\\"]+$)");
		patternMap.put("tennis", "([\\w+\\s]+[(]\\d[)]\\s\\d\\s[\\d|/Adv/]+-[\\d|/Adv/]+\\s\\d\\s[(]\\d[)][\\s*\\w+]+)");
		patternMap.put("americanfootball", "([\\w+\\s]+\\d-\\d\\s[\\w+\\s]+[/1st|/2nd/|/3rd/|/4th/]\\s\\w+)");
		//Add new pattern to extend the functionality
	}
	
	public EventDetector() {
		super();
	}
	
	public String getEventTypeFromString(String textToParse) {
		String event= "Event not known";
		for(Map.Entry<String, String> entry : patternMap.entrySet()){
			Pattern r = Pattern.compile(entry.getValue());
			Matcher m = r.matcher(textToParse);
			if (m.matches()){
				event = entry.getKey();
			}
		}
		return event;
	}



	

}
