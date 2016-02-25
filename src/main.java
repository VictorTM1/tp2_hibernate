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
        if(args.length > 0){
            Terrain ter = jsonParser.parseJson(args[0]);
            if(ter != null){
                if ( ter.validateValues() ) {
                    for(int i=0;i<ter.list_lots.size();i++){
                        if( !ter.list_lots.get(i).validateValues() ) {
                            ter.errorMessage = ter.list_lots.get(i).errorMessage;
                            break;
                        }
                        ter.list_lots.get(i).calculateLandValueLot(ter.getType(), ter.priceMin, ter.priceMax);
                    }
                    if ( ter.errorMessage.equals("") ) {
                        ter.calculateLandValue();
                        ter.calculateSchoolTax();
                        ter.calculateMunicipalTax();
                    }
                }
                Jsonizer.Jsonize(ter, args[1]);
            }
            else{
                System.out.println("Erreur avec le fichier d'entrée !");
            }
        }else{
            System.out.println("Erreur! le nom du fichier n'a pas ete specifier dans la ligne de commande");
        }
        
    }
    
}
