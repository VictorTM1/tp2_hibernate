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

import java.util.ArrayList;

/**
 * @author dvmedellin
 */
public class Terrain {
    private static final float PRIX_DE_BASE = 733.77f;    
    private static final float TAXE_SCOLAIRE = 0.012f; //1.2%
    private static final float TAXE_MUNICIPALE = 0.025f; //1.2%
    
    int type;
    float prixMin, prixMax;
    ArrayList<Lot> liste_lots;
    float valeurFonciereTotale;
    float taxeScolaire;
    float taxeMunicipale;
    
    public Terrain(){
        this.type = 0;
        this.prixMin = 0;
        this.prixMax = 0;
        this.liste_lots = new ArrayList<Lot>();
        this.valeurFonciereTotale = 0;
        this.taxeScolaire = 0;
        this.taxeMunicipale = 0;
    }
    
    public Terrain(int type, float min, float max, ArrayList<Lot> lots){
        this.type = type;
        this.prixMin = min;
        this.prixMax = max;
        this.liste_lots=lots;
    }
    
    public ArrayList<Lot> getListeLots(){
        return this.liste_lots;
    }
    
    public int getType(){
        return this.type;
    }
    
    public float getPrixMin(){
        return this.prixMin;
    }
    
    public float getPrixMax(){
        return this.prixMax;
    }
    
    public float getPrixMoyen(){
        return (getPrixMin()+getPrixMax())/2;
    }
    
    public void calculerValeurFonciere(){
        float val = PRIX_DE_BASE;
        for(int i=0;i<this.liste_lots.size();i++){
            val = val + this.liste_lots.get(i).valeurParLot;
        }
        this.valeurFonciereTotale = arrondir(val); 
    }
    
    public void calculerTaxeScolaire(){
        this.taxeScolaire = arrondir(this.valeurFonciereTotale*TAXE_SCOLAIRE);
    }
    
    public void calculerTaxeMunicipale(){
        this.taxeMunicipale = arrondir(this.valeurFonciereTotale*TAXE_MUNICIPALE);
    }
    
    private float arrondir(float valeur){
        float val = Math.round(valeur*100);
        if((val%5)!=0) {
            val = val + 5 -(val%5);
        }
        return val/100;
    }
}
