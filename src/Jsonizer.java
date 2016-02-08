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

    private static final String[] KEY_WORD_TERRAIN = {"\"valeur_fonciere_total\":","\"taxe_scolaire\":"
            ,"\"taxe_municipale\":","\"Lotissements\":"};
    private static final String[] KEY_WORD_LOT = {"\"description\":","\"valeur_par_lot\":"};

    // Creation du json file.
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
    
    public static void WriteInJson (Terrain terrain, String sortieName) throws Exception{
    File jsonfile = createJsonFile(sortieName);
        FileWriter fileWriter = new FileWriter(jsonfile.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (String element : createJsonContent(terrain)){
            bufferedWriter.write(element);
        }
        bufferedWriter.close();    
    }
    
    // retourne une collection de String regroupant les information pour le generation de resultat.json
    public static ArrayList<String> createJsonContent(Terrain terrain) {
        ArrayList<String> arrayOfContent = new ArrayList<>();
        loadTerrainData(arrayOfContent, terrain);
        loadLotsData(arrayOfContent, terrain);
        return arrayOfContent;
    }
    
    public static void loadLotsData (ArrayList<String> array,Terrain terrain){
        for (int i = 0; i < terrain.liste_lots.size(); i++) {
            array.add("{\n");
            array.add(KEY_WORD_LOT[0] + "\"" + terrain.liste_lots.get(i).description + "\",\n");
            array.add(KEY_WORD_LOT[1] + "\"" + String.format("%.2f", terrain.liste_lots.get(i).valeurParLot) + "$\"\n}");
            if ((i+1) < terrain.liste_lots.size()){
            array.add(",\n");
            }
        }
        array.add("\n]\n}");
    }
    
    public static void loadTerrainData (ArrayList<String> array, Terrain terrain){
        array.add("{\n");
        array.add(KEY_WORD_TERRAIN[0] + "\"" + String.format("%.2f", terrain.valeurFonciereTotale) +"$\",\n");
        array.add(KEY_WORD_TERRAIN[1] + "\"" + String.format("%.2f", terrain.taxeScolaire) + "$\",\n");
        array.add(KEY_WORD_TERRAIN[2] + "\"" + String.format("%.2f", terrain.taxeMunicipale) + "$\",\n");
        array.add(KEY_WORD_TERRAIN[3] + "[\n");
    }
    
}