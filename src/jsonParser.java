/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import net.sf.json.*;

/*
 * Classe permettant de parser le fichier JSON *
 * ---------------------
 * @author robeen      |
 * ---------------------
 */
public class jsonParser {
    private static JSONObject json;
    private static final String[] clesTerrain = {"type_terrain","prix_m2_min","prix_m2_max","lotissements"};
    private static final String[] clesLot = {"description","nombre_droits_passage","nombre_services","superficie","date_mesure"};
    
    // Parse le Json et retourne le premier Terrain
    public static Terrain parseJson(String fileName){
        String jsonString;
        jsonString = jsonParser.jsonToString(fileName);
        if(jsonString != null){
            json = JSONObject.fromObject(jsonString);
            formatJSONKeys(json);
            if (validateJSONTerrain()) 
                return creerTerrain();
            else
                return null;
        }
        else{
            return null;
        }
    }
    
    // Transforme le .json en String
    public static String jsonToString(String jsonFileName){
        String jsonString;
        try{
            jsonString = new Scanner(new File(jsonFileName))
            .useDelimiter("\\A").next();
        }
        catch(Exception e){
            return null;
        }
        return jsonString;
    }
    
    // Crée un terrain
    public static Terrain creerTerrain(){
        int type = json.getInt("type_terrain");
        float prixMin = getFloat(json.getString("prix_m2_min"));
        float prixMax = getFloat(json.getString("prix_m2_max"));
        ArrayList<Lot> lotissements = getLotissements();
        Terrain leTerrain = new Terrain(type, prixMin, prixMax, lotissements);
        return leTerrain;
    }
    
    // Crée le tableau de lotissements
    public static ArrayList<Lot> getLotissements(){
        ArrayList<Lot> lotissements = new ArrayList<Lot>();
        JSONArray lotissement = json.getJSONArray("lotissements");
        for(int i = 0; i < lotissement.size(); i++){
            lotissements.add(creerLotissement(i, lotissement));
        }
        return lotissements;
    }
    
    // Crée un lotissement
    public static Lot creerLotissement(int index, JSONArray jsonLotissements){
        JSONObject jsonLotissement = jsonLotissements.getJSONObject(index);
        String description = jsonLotissement.getString("description");
        int nbDroitPassage = jsonLotissement.getInt("nombre_droits_passage");
        int nbService = jsonLotissement.getInt("nombre_services");
        float superficie = getFloat(jsonLotissement.getString("superficie"));
        Date lotDate = getDate(jsonLotissement.getString("date_mesure"));
        return new Lot(nbService, nbDroitPassage, superficie, description, lotDate);
    }
    
    // Extirpe un Float d'un String
    public static float getFloat(String prix){
        prix = prix.replace(',', '.');
        float lePrix = Float.valueOf(prix.replaceAll("[^\\d.]+|\\.(?!\\d)", ""));
        return lePrix;
    }
    
    // Crée un Object Date partant d'un String au format "yyyy-MM-dd"
    public static Date getDate(String jsonDate){
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date lotDate = new Date();
        try{
            lotDate = date.parse(jsonDate);
        }catch(Exception e){
            return null;
        }
        return new Date();
    }
    
    private static void formatJSONKeys(JSONObject object){
        JSONObject object2 = JSONObject.fromObject(object);
        Iterator jsonCles = object2.keys();
        while (jsonCles.hasNext()){
            String cle=jsonCles.next().toString();
            Object subObject = object2.get(cle);
            if ( subObject instanceof JSONArray ) formatJSONKeys((JSONArray) subObject);
            if ( subObject instanceof JSONObject ) formatJSONKeys((JSONObject) subObject);
            object.discard(cle);
            object.put(cle.trim().toLowerCase(), subObject);            
        }
    }
    
    private static void formatJSONKeys(JSONArray objects){
        Iterator iterator = objects.iterator();
        while (iterator.hasNext()){
            Object object = iterator.next();
            if ( object instanceof JSONObject ) formatJSONKeys((JSONObject) object);
        }
    }
    
    private static boolean validateJSONTerrain(){
        for (String cle : clesTerrain){
            if (!json.has(cle)) return false;
        }       
        return validateJSONLots();
    }
    
    private static boolean validateJSONLots(){
        JSONArray object = json.getJSONArray("lotissements");
        
        Iterator iteratorArray = object.iterator();
        while (iteratorArray.hasNext()){
            JSONObject object2 = (JSONObject) iteratorArray.next();
            for (String cle : clesLot){
                if ( !object2.has(cle) ) return false;
            }
        }        
        return true;
    }

    private static class Arraylist extends ArrayList<String> {

        public Arraylist(int initialCapacity) {
            super(initialCapacity);
        }
    }
}

