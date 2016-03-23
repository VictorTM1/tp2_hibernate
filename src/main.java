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
import java.io.FileNotFoundException;
import java.io.IOException;
import net.sf.json.*;

/**
 * @author ventilooo
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            Terrain terrains = jsonParser.parseJson(args[0]);
            if (terrains != null) {
                for (int i = 0; i < terrains.list_lots.size(); i++) {
                    terrains.list_lots.get(i).calculateLandValueLot(
                            terrains.getType(), terrains.priceMin,
                            terrains.priceMax);
                }
                terrains.calculateLandValue();
                terrains.calculateSchoolTax();
                terrains.calculateMunicipalTax();
                Jsonizer.Jsonize(terrains, args[1]);
            } else {
                System.out.println("Error with the input file !");
            }
        } else {
            System.out.println("Error ! The input file was not specified in the"
                    + " command line when the program was run.");
        }

    }

}
