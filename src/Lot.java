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

import java.util.Date;

/**
 *
 * @author dvmedellin
 */       
public class Lot {
    String description;
    int nbDroitPassage, nbService;
    float superficie;
    Date dateMesure;
    
    public Lot(){
        this.description = "";
        this.nbDroitPassage = 0;
        this.nbService = 0;
        this.superficie = 0;
        this.dateMesure = new Date();
    }
    
    public Lot(int service, int passage, float superficie, String desc, Date date){
        this.description = desc;
        this.nbDroitPassage = passage;
        this.nbService = service + 2;
        this.superficie = superficie;
        this.dateMesure = date;
    }
    
    public float calculerValeurFonciere(float prix){
        return (this.superficie*prix);
    }
    
    public float calcluerValeurDroitPassage(float prix, int type){
        float val = 500;
        float pourcent = 0;
        switch(type){
            case 0 : 
                pourcent = 5/100;                
                break;
            case 1 : 
                pourcent = 10/100;
                break;
            case 2 : 
                pourcent = 15/100;
                break;
            default : 
                break;
        }
        return (val - (this.nbDroitPassage*prix*pourcent));
    }
}
