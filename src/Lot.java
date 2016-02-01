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
    float valeurParLot;
    
    public Lot(){
        this.description = "";
        this.nbDroitPassage = 0;
        this.nbService = 0;
        this.superficie = 0;
        this.dateMesure = new Date();
        this.valeurParLot = 0;
    }
    
    public Lot(int service, int passage, float superficie, String desc, Date date){
        this.description = desc;
        this.nbDroitPassage = passage;
        this.nbService = service + 2;
        this.superficie = superficie;
        this.dateMesure = date;
        valeurParLot = 0;
    }
    
    public float calculerValeurFonciere(int type, float prixMin, float prixMax){
        float valeurLot = calculerValeurSuperficie(type, prixMin, prixMax);
        float valeurDroitPassage = calculerValeurDroitPassage(valeurLot, type);
        float valeurServices = calculerValeurServices(type);
        return (valeurLot + valeurDroitPassage + valeurServices);
    }
    
    public float calculerValeurDroitPassage(float valeurLot, int type){
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
        return (val - (this.nbDroitPassage*valeurLot*pourcent));
    }
    
    public float calculerValeurSuperficie(int type, float prixMin, float prixMax){
        float prix = 0;
        switch(type){
            case 0 : 
                prix = prixMin;                
                break;
            case 1 : 
                prix = ((prixMin + prixMax)/2);
                break;
            case 2 : 
                prix = prixMax;
                break;
            default : 
                break;
        }
        return (this.superficie * prix);
    }
    
    public float calculerValeurServices(int type){
        float prix = 0;
        switch(type){
            case 0 : 
                prix = 0;         
                break;
            case 1 : 
                if(this.superficie <= 500){
                    prix = 0;
                }else if(this.superficie <= 10000){
                    prix = 500 * this.nbService;
                }else{
                    prix = 1000 * this.nbService;
                }
                break;
            case 2 : 
                if(this.superficie <= 500){
                    prix = 500;
                }else {
                    prix = 1500;
                }
                break;
            default : 
                break;
        }
        if(prix > 5000){
            return 5000;
        }else{
            return prix;
        }
    }
}
