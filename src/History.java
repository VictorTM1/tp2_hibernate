/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rico
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import net.sf.json.*;

public class History {
    
    private static final String[] HISTORY_KEYS = {"numberOfTerrain", "lotsUnder1000", "lotsBetween1000And10000", "lotsHigher10000", "lotsTerrainType0", "lotsTerrainType1", "lotsTerrainType2", "highestSurfaceArea", "highestValueForLot"};
  
    public static void updateHistory(Terrain newTerrain) throws FileNotFoundException, IOException{
        JSONObject json = readHistory();
        recreateHistory(json, newTerrain);
    }
    
    public static JSONObject readHistory() throws IOException{
        File jsonfile = new File("History.json");
        if (!jsonfile.exists()) {
            jsonfile.createNewFile();
            resetHistory();
        }
        String jsonString = jsonParser.jsonToString("History.json");
        if(jsonString != null){
            return JSONObject.fromObject(jsonString);
        }else{
            return null;
        }
    }
    
    public static void recreateHistory(JSONObject oldJson, Terrain newTerrain) throws IOException{
        int temp;
        JSONObject newJson = new JSONObject();
        temp = oldJson.getInt("numberOfTerrain") + 1;
        newJson.accumulate("numberOfTerrain", temp);
        recreateLotsHistory(oldJson, newJson, newTerrain.getListeLots());
        recreateLotsByTypeHistory(oldJson, newJson, newTerrain);
        writeInJsonHistory(newJson);
    }
    
    public static void recreateLotsHistory(JSONObject oldJson, JSONObject newJson,  ArrayList<Lot> list_lots){
        float maxSurfaceArea = 0;
        float highestValue = 0;
        for(Lot e : list_lots){
            if(e.surfaceArea >= oldJson.getDouble("highestSurfaceArea") && e.surfaceArea > maxSurfaceArea){
                maxSurfaceArea = e.surfaceArea;
            }
            if(e.valuePerLot < 1000){
                newJson.accumulate("lotsUnder1000", oldJson.getInt("lotsUnder1000") + 1);
            }else if(e.valuePerLot < 10000){
                newJson.accumulate("lotsBetween1000And10000", oldJson.getInt("lotsBetween1000And10000") + 1);
            }else{
                newJson.accumulate("lotsHigher10000", oldJson.getInt("lotsHigher10000") + 1);
            }
            if(e.valuePerLot >= oldJson.getDouble("highestValueForLot") && e.valuePerLot > highestValue){
                highestValue = e.valuePerLot;
            }
        }
        newJson.accumulate("highestSurfaceArea", maxSurfaceArea);
        newJson.accumulate("highestValueForLot", highestValue);
    }
    
    public static void recreateLotsByTypeHistory(JSONObject oldJson, JSONObject newJson, Terrain newTerrain){
        if(newTerrain.type == 0){
           newJson.accumulate("lotsTerrainType0", oldJson.getInt("lotsTerrainType0") + 1);
           newJson.accumulate("lotsTerrainType1", oldJson.getInt("lotsTerrainType1") );
           newJson.accumulate("lotsTerrainType2", oldJson.getInt("lotsTerrainType2") );
        }else if(newTerrain.type == 1){
           newJson.accumulate("lotsTerrainType0", oldJson.getInt("lotsTerrainType0") );
           newJson.accumulate("lotsTerrainType1", oldJson.getInt("lotsTerrainType1") + 1);
           newJson.accumulate("lotsTerrainType2", oldJson.getInt("lotsTerrainType2") );
        }else if(newTerrain.type == 2){
           newJson.accumulate("lotsTerrainType0", oldJson.getInt("lotsTerrainType0") );
           newJson.accumulate("lotsTerrainType1", oldJson.getInt("lotsTerrainType1") );
           newJson.accumulate("lotsTerrainType2", oldJson.getInt("lotsTerrainType2") + 1);
        }
    }
    
    public static void writeInJsonHistory(JSONObject newJson) throws IOException{
        File jsonfile = new File("History.json");
        String stringJson = newJson.toString();
        FileWriter fileWriter = new FileWriter(jsonfile.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(stringJson);
        bufferedWriter.close();
    }
    
    public static void resetHistory() throws IOException{
        JSONObject json = new JSONObject();
        for(String e : HISTORY_KEYS){
            json.accumulate(e, 0);
        }
        writeInJsonHistory(json);
    }
    
    public static void printHistory(){
        String jsonString;
        JSONObject json;
        try {
            jsonString = new Scanner(new File("History.json")).useDelimiter("\\A").next();
            json = JSONObject.fromObject(jsonString);
            for(String e : HISTORY_KEYS){
                System.out.println(e + " : " + json.get(e));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
}
