package com.moises.sportiumParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class EventParser 
{
	
	public EventParser() {
		super();
	}

	public JSONObject toJSON(String event, String textToParse) {
		JSONObject info = new JSONObject();
		String[] teams = textToParse.split("-");
		String teamA = teams[0];
		String teamB = teams[1];
		
		if (event.equals("football")){
			teamA = teamA.substring(1);
			String[] partsTeamA = teamA.split(" ");
			if (partsTeamA.length > 2){
				String teamAName = partsTeamA[0];
				for(int i = 1; i < partsTeamA.length-1; i++){
					teamAName += " " + partsTeamA[i];
				}
				info.put("teamAName", teamAName);
			}
			else{
				info.put("teamAName", partsTeamA[0]);
			}
			info.put("teamAScore", partsTeamA[partsTeamA.length-1]);
			
			teamB = teamB.substring(0, teamB.length()-1);
			String[] partsTeamB = teamB.split(" ");
			if (partsTeamB.length > 2){
				String teamBName = partsTeamB[1];
				for(int i = 2; i < partsTeamB.length; i++){
					teamBName += " " + partsTeamB[i];
				}
				info.put("teamBName", teamBName);
			}
			else{
				info.put("teamBName", partsTeamB[1]);
			}
			
			info.put("teamBScore", partsTeamB[0]);
		}
		
		else if(event.equals("tennis")){
			String[] partsTeamA = teamA.split(" ");
			if (partsTeamA.length > 4){
				String teamAName = partsTeamA[0];
				for(int i = 1; i < partsTeamA.length-3; i++){
					teamAName += " " + partsTeamA[i];
				}
				info.put("teamAName", teamAName);
			}
			else{
				info.put("teamAName", partsTeamA[0]);
			}
			
			info.put("teamAScore", partsTeamA[partsTeamA.length-1]);
			info.put("teamAGames", partsTeamA[partsTeamA.length-2]);
			
			
			String[] partsTeamB = teamB.split(" ");
			boolean isBServing = partsTeamB[3].substring(0, 1).equals("*");
			info.put("teamBServing", isBServing);
			if (partsTeamB.length > 4){
				String teamBName = (isBServing) ? partsTeamB[3].substring(1) : partsTeamB[3];
				for(int i = 4; i < partsTeamB.length; i++){
					teamBName += " " + partsTeamB[i];
				}
				info.put("teamBName", teamBName);
			}
			else{
				info.put("teamBName", (isBServing) ? partsTeamB[3].substring(1) : partsTeamB[3]);
			}
			
			info.put("teamBScore", partsTeamB[0]);
			info.put("teamBGames", partsTeamB[1]);
			
			Map <String, List<Map<String,String>>> scoreboard = new HashMap<String, List<Map<String,String>>>();
			Map <String, String> element = new HashMap<String,String>();
			element.put("title", "Sets");
			element.put("teamAScore", "(" + partsTeamA[partsTeamA.length-3] + ")");
			element.put("teamBScore", "(" + partsTeamB[2] + ")");
			List<Map<String,String>> elements = new ArrayList<Map<String, String>>();
			elements.add(element);
			scoreboard.put("elements", elements);
			info.put("scoreboard", scoreboard);
		}
		else if(event.equals("americanfootball")){
			String[] partsTeamA = teamA.split(" ");
			if (partsTeamA.length > 2){
				String teamAName = partsTeamA[0];
				for(int i = 1; i < partsTeamA.length-1; i++){
					teamAName += " " + partsTeamA[i];
				}
				info.put("teamAName", teamAName);
			}
			else{
				info.put("teamAName", partsTeamA[0]);
			}
			info.put("teamAScore", partsTeamA[partsTeamA.length-1]);
			
			String[] partsTeamB = teamB.split(" ");
			if (partsTeamB.length > 4){
				String teamBName = partsTeamB[1];
				for(int i = 2; i < partsTeamB.length-2; i++){
					teamBName += " " + partsTeamB[i];
				}
				info.put("teamBName", teamBName);
			}
			else{
				info.put("teamBName", partsTeamB[1]);
			}
			
			info.put("teamBScore", partsTeamB[0]);
			info.put("currentPeriod", partsTeamB[partsTeamB.length-2] + " " + partsTeamB[partsTeamB.length-1]);
		}
		else{
			info.put("Error", "Event not known");
		}
		return info;
	}
    
}
