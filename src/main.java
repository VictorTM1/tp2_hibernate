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

//import java.io.FileReader;
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
    public static void main(String[] args) throws IOException{
       
        // cas de test pour le jsonizer
        Terrain  test = new Terrain();
        Lot lot1 = new Lot();
        Lot lot2 = new Lot();
        Lot lot3 = new Lot();
        lot1.description = "YOLO";
        lot2.description = "CQFD";
        lot3.description = "Miel";
        test.liste_lots.add(lot1);
        test.liste_lots.add(lot2);
        test.liste_lots.add(lot3);
        Jsonizer.writeInJson(test);
    }
    
}
