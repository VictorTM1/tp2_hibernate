import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import net.sf.json.*;

/*
 * Classe permettant de parser le fichier JSON
 * Il y a de la place à l'amélioration
 * Pistes d'amélioration :
 * - Gérer plusieurs Terrain
 * - Optimisation du code
 * - ?
 *
 * ---------------------
 * @author robeen      |
 * ---------------------
 */
public class jsonParser {
    private static JSONObject json;
    
    // Parse le Json et retourne le premier Terrain
    public static Terrain parseJson(String fileName){
        String jsonString;
        try{
            jsonString = jsonParser.jsonToString(fileName);
        }
        catch(Exception e){
            return null;
        }
        json = JSONObject.fromObject(jsonString);
        Terrain terrain = creerTerrain();
        return terrain;
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
        ArrayList<Lot> lotissements = new ArrayList<>();
        JSONArray lotissement = json.getJSONArray("Lotissements");
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
        int nbService = jsonLotissement.getInt(" nombre_services");
        float superficie = getFloat(jsonLotissement.getString(" Superficie"));
        Date lotDate = getDate(jsonLotissement.getString("date_mesure"));
        return new Lot(nbService, nbDroitPassage, superficie, description, lotDate);
    }
    
    // Extirpe un Float d'un String
    public static float getFloat(String prix){
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
}
