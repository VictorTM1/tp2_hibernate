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
    
    private static final String File_Name = "History.json";
    private static final String[] HISTORY_KEYS = {"nombre_terrain", "nombre_lots_moins_de_1000$", "nombre_lots_entre_1000$_et_10000$", "nombre_lots_plus_de_10000$", "nombre_lots_terrain_type0", "nombre_lots_terrain_type1", "nombre_lots_terrain_type2", "superficie_maximale", "valeur_maximale_pour_un_lot"};
  
    public static void updateHistory(Terrain newTerrain) throws FileNotFoundException, IOException{
        JSONObject json = readHistory();
        recreateHistory(json, newTerrain);
    }
    
    public static JSONObject readHistory() throws IOException{
        File jsonfile = new File(File_Name);
        if (!jsonfile.exists()) {
            jsonfile.createNewFile();
            resetHistory();
        }
        String jsonString = jsonParser.jsonToString(File_Name);
        if(jsonString != null){
            return JSONObject.fromObject(jsonString);
        }else{
            return null;
        }
    }
    
    public static void recreateHistory(JSONObject oldJson, Terrain newTerrain) throws IOException{
        int temp;
        JSONObject newJson = new JSONObject();
        temp = oldJson.getInt(HISTORY_KEYS[0]) + 1;
        newJson.accumulate(HISTORY_KEYS[0], temp);
        recreateLotsHistory(oldJson, newJson, newTerrain.getListeLots());
        recreateLotsByTypeHistory(oldJson, newJson, newTerrain);
        writeInJsonHistory(newJson);
    }
    
    public static void recreateLotsHistory(JSONObject oldJson, JSONObject newJson,  ArrayList<Lot> list_lots){
        float maxSurfaceArea = 0;
        float highestValue = 0;
        for(Lot e : list_lots){
            if(e.surfaceArea >= oldJson.getDouble(HISTORY_KEYS[7]) && e.surfaceArea > maxSurfaceArea){
                maxSurfaceArea = e.surfaceArea;
            }
            if(e.valuePerLot < 1000){
                newJson.accumulate(HISTORY_KEYS[1], oldJson.getInt(HISTORY_KEYS[1]) + 1);
            }else if(e.valuePerLot < 10000){
                newJson.accumulate(HISTORY_KEYS[2], oldJson.getInt(HISTORY_KEYS[2]) + 1);
            }else{
                newJson.accumulate(HISTORY_KEYS[3], oldJson.getInt(HISTORY_KEYS[3]) + 1);
            }
            if(e.valuePerLot >= oldJson.getDouble(HISTORY_KEYS[8]) && e.valuePerLot > highestValue){
                highestValue = e.valuePerLot;
            }
        }
        newJson.accumulate(HISTORY_KEYS[7], maxSurfaceArea);
        newJson.accumulate(HISTORY_KEYS[8], highestValue);
    }
    
    public static void recreateLotsByTypeHistory(JSONObject oldJson, JSONObject newJson, Terrain newTerrain){
        if(newTerrain.type == 0){
           newJson.accumulate(HISTORY_KEYS[4], oldJson.getInt(HISTORY_KEYS[4]) + 1);
           newJson.accumulate(HISTORY_KEYS[5], oldJson.getInt(HISTORY_KEYS[5]) );
           newJson.accumulate(HISTORY_KEYS[6], oldJson.getInt(HISTORY_KEYS[6]) );
        }else if(newTerrain.type == 1){
           newJson.accumulate(HISTORY_KEYS[4], oldJson.getInt(HISTORY_KEYS[4]) );
           newJson.accumulate(HISTORY_KEYS[5], oldJson.getInt(HISTORY_KEYS[5]) + 1);
           newJson.accumulate(HISTORY_KEYS[6], oldJson.getInt(HISTORY_KEYS[6]) );
        }else if(newTerrain.type == 2){
           newJson.accumulate(HISTORY_KEYS[4], oldJson.getInt(HISTORY_KEYS[4]) );
           newJson.accumulate(HISTORY_KEYS[5], oldJson.getInt(HISTORY_KEYS[5]) );
           newJson.accumulate(HISTORY_KEYS[6], oldJson.getInt(HISTORY_KEYS[7]) + 1);
        }
    }
    
    public static void writeInJsonHistory(JSONObject newJson) throws IOException{
        File jsonfile = new File(File_Name);
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
            jsonString = new Scanner(new File(File_Name)).useDelimiter("\\A").next();
            json = JSONObject.fromObject(jsonString);
            for(String e : HISTORY_KEYS){
                System.out.println(e + " : " + json.get(e));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
}
