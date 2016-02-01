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
    public static File createJsonFile(){
        File jsonfile = new File("resultat.json");
        try {
            if (!jsonfile.exists()) {
                jsonfile.createNewFile();
                return jsonfile; 
            }   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonfile;
    }
    // Ecriture du json.
    public static void writeInJson(Terrain terrain) {
        try {
        File jsonfile = createJsonFile();
        FileWriter fileWriter = new FileWriter(jsonfile.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (String element : createArrayOfStringContent(terrain)){
            bufferedWriter.write(element);
        }
        bufferedWriter.close();    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    // retourne une collection de String regroupant les information pour le generation de resultat.json
    public static ArrayList<String> createArrayOfStringContent(Terrain terrain) {

        ArrayList<String> arrayOfcontent = new ArrayList<String>(); 
        
        arrayOfcontent.add("{\n");
        
        arrayOfcontent.add(KEY_WORD_TERRAIN[0] + "\"" + terrain.valeurFonciereTotale +"\",\n");
        arrayOfcontent.add(KEY_WORD_TERRAIN[1] + "\"" + terrain.taxeScolaire + "\",\n");
        arrayOfcontent.add(KEY_WORD_TERRAIN[2] + "\"" + terrain.taxeMunicipale + "\",\n");
        
        arrayOfcontent.add(KEY_WORD_TERRAIN[3] + "[\n");
        
        for (int i = 0; i < terrain.liste_lots.size(); i++) {
            arrayOfcontent.add("{\n");
            arrayOfcontent.add(KEY_WORD_LOT[0] + "\"" + terrain.liste_lots.get(i).description + "\",\n");
            arrayOfcontent.add(KEY_WORD_LOT[1] + "\"" + terrain.liste_lots.get(i).valeurParLot + "$\"\n}");
            
            if ((i+1) < terrain.liste_lots.size()){
            arrayOfcontent.add(",\n");
            }
        }
        
        arrayOfcontent.add("\n]\n}");
        
        return arrayOfcontent;
    }
    
}