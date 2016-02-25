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

/**
 *
 * @author ventilooo
 */

import java.io.*;
import java.util.ArrayList;

public class Jsonizer {

    private static final String[] KEY_WORD_TERRAIN = {"\"valeur_fonciere_tot"
            + "al\":","\"taxe_scolaire\":"
            ,"\"taxe_municipale\":","\"lotissements\":"};
    private static final String[] KEY_WORD_LOT = {"\"description\":","\"valeur_"
            + "par_lot\":"};
    
    private static final String[] ERROR_MESSAGES = {"Le nombre de droits de pas"
            + "sage est supérieur à 10.","Le nombre de droits de passage est in"
            + "férieur à 0.","Le nombre de service est supérieur à 5.","Le nomb"
            + "re de service est inférieur à 0.","Le type de valeur du terrain "
            + "n'est pas entre 0 et 2","Le nombres de Lots est supérieur à 10.",
            "Le nombres de Lots est inférieurs à 1.", "Le montant d'argent est "
            + "négatif.","La superficie est négative.","La superficie est supér"
            + "ieur à 50000 m2."};

    // Json file creation.
    public static File createJsonFile(String sortieName){
        File jsonfile = new File(sortieName);
        try {
            if (!jsonfile.exists()) {
                jsonfile.createNewFile();
            }   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonfile;
    }
    
    public static void Jsonize(Terrain terrain, String sortieName) {
        try {
        WriteInJson(terrain,sortieName);   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
    
    public static void WriteInJson (Terrain terrain, String sortieName) throws 
            Exception{
    File jsonfile = createJsonFile(sortieName);
        FileWriter fileWriter = new FileWriter(jsonfile.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (String element : createJsonContent(terrain)){
            bufferedWriter.write(element);
        }
        bufferedWriter.close();    
    }
    
    // return a string collection with all information needed to generate 
    // resulat.json
    public static ArrayList<String> createJsonContent(Terrain terrain) {
        ArrayList<String> arrayOfContent = new ArrayList<>();
        loadTerrainData(arrayOfContent, terrain);
        loadLotsData(arrayOfContent, terrain);
        return arrayOfContent;
    }
    
    public static void loadLotsData (ArrayList<String> array,Terrain terrain){
        for (int i = 0; i < terrain.list_lots.size(); i++) {
            array.add("{\n");
            array.add(KEY_WORD_LOT[0] + "\"" + 
                    terrain.list_lots.get(i).description + "\",\n");
            array.add(KEY_WORD_LOT[1] + "\"" + String.format("%.2f", 
                    terrain.list_lots.get(i).valuePerLot) + "$\"\n}");
            if ((i+1) < terrain.list_lots.size()){
            array.add(",\n");
            }
        }
        array.add("\n]\n}");
    }
    
    public static void loadTerrainData (ArrayList<String> array, Terrain terrain){
        array.add("{\n");
        array.add(KEY_WORD_TERRAIN[0] + "\"" + String.format("%.2f", 
                terrain.totalLandValue) +"$\",\n");
        array.add(KEY_WORD_TERRAIN[1] + "\"" + String.format("%.2f", 
                terrain.schoolTax) + "$\",\n");
        array.add(KEY_WORD_TERRAIN[2] + "\"" + String.format("%.2f", 
                terrain.municipalTax) + "$\",\n");
        array.add(KEY_WORD_TERRAIN[3] + "[\n");
    }
    
}