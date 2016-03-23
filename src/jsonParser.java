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
 * Class that parses the input json file
 * ---------------------
 * @author robeen      |
 * ---------------------
 */
public class jsonParser {

    private static JSONObject json;
    private static final String[] terrainKeys = {"type_terrain", "prix_m2_min",
        "prix_m2_max", "lotissements"};
    private static final String[] lotKeys = {"description", "nombre_droits_passa"
        + "ge", "nombre_services", "superficie", "date_mesure"};

    // Parse the input Json file and return the first Terrain
    public static Terrain parseJson(String fileName) {
        String jsonString;
        jsonString = jsonParser.jsonToString(fileName);
        if (jsonString != null) {
            json = JSONObject.fromObject(jsonString);
            formatJSONKeys(json);
            if (validateJSONTerrain()) {
                return createTerrain();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    // Turn the json file into a String
    public static String jsonToString(String jsonFileName) {
        String jsonString;
        try {
            jsonString = new Scanner(new File(jsonFileName))
                    .useDelimiter("\\A").next();
        } catch (Exception e) {
            return null;
        }
        return jsonString;
    }

    // Create the Terrain
    public static Terrain createTerrain() {
        int type = json.getInt("type_terrain");
        float prixMin = getFloat(json.getString("prix_m2_min"));
        float prixMax = getFloat(json.getString("prix_m2_max"));
        ArrayList<Lot> lotissements = createLotissementTable();
        Terrain terrain = new Terrain(type, prixMin, prixMax, lotissements);
        return terrain;
    }

    // Create the table of lotissements
    public static ArrayList<Lot> createLotissementTable() {
        ArrayList<Lot> lotissements = new ArrayList<Lot>();
        JSONArray lotissement = json.getJSONArray("lotissements");
        for (int i = 0; i < lotissement.size(); i++) {
            lotissements.add(createLotissement(i, lotissement, lotissements));
        }
        return lotissements;
    }

    // Create a lotissement
    public static Lot createLotissement(int index, JSONArray jsonLotissements, ArrayList<Lot> lotissements) {
        JSONObject jsonLotissement = jsonLotissements.getJSONObject(index);
        String description = jsonLotissement.getString("description");
        int nbDroitsPassage = jsonLotissement.getInt("nombre_droits_passage");
        int nbServices = jsonLotissement.getInt("nombre_services");
        float superficie = getFloat(jsonLotissement.getString("superficie"));
        Date lotDate = getDate(jsonLotissement.getString("date_mesure"));
        if (!descriptionExist(description, lotissements)) {
            return new Lot(nbServices, nbDroitsPassage, superficie, description,
                    lotDate);
        }
        return null;
    }

    // Valide la description d'un lotissement
    public static boolean descriptionExist(String description, ArrayList<Lot> lotissements) {
        for (int i = 0; i < lotissements.size(); i++) {
            if (lotissements.get(i).description == description) {
                return true;
            }
        }
        return false;
    }

    // Get float from a string
    public static float getFloat(String price) {
        price = price.replace(',', '.');
        float thePrice = Float.valueOf(price.replaceAll("[^\\d.]+|\\.(?!\\d)",
                ""));
        return thePrice;
    }

    // Create a date object coming from a string formatted as such : "yyyy-MM-dd"
    public static Date getDate(String jsonDate) {
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date lotDate = new Date();
        try {
            lotDate = date.parse(jsonDate);
        } catch (Exception e) {
            return null;
        }
        return new Date();
    }

    private static void formatJSONKeys(JSONObject object) {
        JSONObject object2 = JSONObject.fromObject(object);
        Iterator jsonCles = object2.keys();
        while (jsonCles.hasNext()) {
            String cle = jsonCles.next().toString();
            Object subObject = object2.get(cle);
            if (subObject instanceof JSONArray) {
                formatJSONKeys((JSONArray) subObject);
            }
            if (subObject instanceof JSONObject) {
                formatJSONKeys((JSONObject) subObject);
            }
            object.discard(cle);
            object.put(cle.trim().toLowerCase(), subObject);
        }
    }

    private static void formatJSONKeys(JSONArray objects) {
        Iterator iterator = objects.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object instanceof JSONObject) {
                formatJSONKeys((JSONObject) object);
            }
        }
    }

    private static boolean validateJSONTerrain() {
        for (String key : terrainKeys) {
            if (!json.has(key)) {
                return false;
            }
        }
        return validateJSONLots();
    }

    private static boolean validateJSONLots() {
        JSONArray object = json.getJSONArray("lotissements");

        Iterator iteratorArray = object.iterator();
        while (iteratorArray.hasNext()) {
            JSONObject object2 = (JSONObject) iteratorArray.next();
            for (String key : lotKeys) {
                if (!object2.has(key)) {
                    return false;
                }
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
