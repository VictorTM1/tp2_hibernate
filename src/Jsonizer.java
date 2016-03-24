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
        + "al\":", "\"taxe_scolaire\":", "\"taxe_municipale\":", "\"lotissements\":"};
    private static final String[] KEY_WORD_LOT = {"\"description\":", "\"valeur_"
        + "par_lot\":"};

    // Json file creation.
    public static File createJsonFile(String outputName) {
        File jsonfile = new File(outputName);
        try {
            if (!jsonfile.exists()) {
                jsonfile.createNewFile();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonfile;
    }

    public static void Jsonize(Terrain terrain, String outputName) {
        try {
            WriteInJson(terrain, outputName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void WriteInJson(Terrain terrain, String sortieName) throws
            Exception {
        File jsonfile = createJsonFile(sortieName);
        FileWriter fileWriter = new FileWriter(jsonfile.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (String element : createJsonContent(terrain)) {
            bufferedWriter.write(element);
        }
        bufferedWriter.close();
    }

    // return a string collection with all information needed to generate 
    // resulat.json
    public static ArrayList<String> createJsonContent(Terrain terrain) {
        ArrayList<String> arrayOfContent = new ArrayList<>();
        if (terrain.errorMessage.equals("")) {
            loadTerrainData(arrayOfContent, terrain);
            loadLotsData(arrayOfContent, terrain);
            loadObservations(arrayOfContent, terrain);
        } else {
            arrayOfContent.add("{\n");
            arrayOfContent.add("\"message\": \"" + terrain.errorMessage + "\"\n");
            arrayOfContent.add("}");
        }
        return arrayOfContent;
    }

    public static void loadLotsData(ArrayList<String> array, Terrain terrain) {
        for (int i = 0; i < terrain.list_lots.size(); i++) {
            array.add("{\n");
            array.add(KEY_WORD_LOT[0] + "\""
                    + terrain.list_lots.get(i).description + "\",\n");
            array.add(KEY_WORD_LOT[1] + "\"" + String.format("%.2f",
                    terrain.list_lots.get(i).valuePerLot) + "$\"\n}");
            if ((i + 1) < terrain.list_lots.size()) {
                array.add(",\n");
            }
        }
        array.add("\n]\n");
    }

    public static void loadTerrainData(ArrayList<String> array, Terrain terrain) {
        array.add("{\n");
        array.add(KEY_WORD_TERRAIN[0] + "\"" + String.format("%.2f",
                terrain.totalLandValue) + "$\",\n");
        array.add(KEY_WORD_TERRAIN[1] + "\"" + String.format("%.2f",
                terrain.schoolTax) + "$\",\n");
        array.add(KEY_WORD_TERRAIN[2] + "\"" + String.format("%.2f",
                terrain.municipalTax) + "$\",\n");
        array.add(KEY_WORD_TERRAIN[3] + "[\n");
    }

    public static void loadObservations(ArrayList<String> array, Terrain terrain) {
        array.add("\n[,\n}");
        if (TestObservation.testFonciereValue(terrain)) {
            array.add("\"La valeur foncière totale ne doit pas dépasser 300000.00 $.\"");
        }
        if (TestObservation.testSchoolTaxe(terrain)) {
            array.add("\"La taxe scolaire payable par le propriétaire ne doit pas dépasser 500.00 $. Vous devez effectuer deux versements sont nécessaires pour le payement\"");
        }
        if (TestObservation.testSquareMeterPrice(terrain)) {
            array.add("\"Le prix maximum du m2 ne peut pas dépasser deux fois le prix minimum du m2.\"");
        }
        if (TestObservation.testTaxeMunicipal(terrain)) {
            array.add("\"La taxe municipale payable par le propriétaire ne doit pas dépasser 1000.00 $. Vous devez effectuer deux versements sont nécessaires pour le payement\"");
        }
        if (TestObservation.getTheWrongLotSurfaceMessage(terrain) != null) {
            array.add(TestObservation.getTheWrongLotSurfaceMessage(terrain));
        }
        if (TestObservation.getTheWrongLotValueMessage(terrain) != null) {
            array.add(TestObservation.getTheWrongLotValueMessage(terrain));
        }
        //if (TestObservation.testDateMesured(terrain)) {
          //  array.add("\"L’écart maximal entre les dates de mesure des lots d’un même terrain devrait être de moins de 6 mois\"\n");
        //}
        array.add("\n],\n}");
    }
}
