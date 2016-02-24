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
    float priceMin, priceMax;
    ArrayList<Lot> list_lots;
    float totalLandValue;
    float schoolTax;
    float municipalTax;
    
    public Terrain(){
        this.type = 0;
        this.priceMin = 0;
        this.priceMax = 0;
        this.list_lots = new ArrayList<Lot>();
        this.totalLandValue = 0;
        this.schoolTax = 0;
        this.municipalTax = 0;
    }
    
    public Terrain(int type, float min, float max, ArrayList<Lot> lots){
        this.type = type;
        this.priceMin = min;
        this.priceMax = max;
        this.list_lots=lots;
    }
    
    public ArrayList<Lot> getListeLots(){
        return this.list_lots;
    }
    
    public int getType(){
        return this.type;
    }
    
    public float getPriceMin(){
        return this.priceMin;
    }
    
    public float getPriceMax(){
        return this.priceMax;
    }
    
    public float getAveragePrice(){
        return (getPriceMin()+getPriceMax())/2;
    }
    
    public void calculateLandValue(){
        float val = PRIX_DE_BASE;
        for(int i=0;i<this.list_lots.size();i++){
            val = val + this.list_lots.get(i).valuePerLot;
        }
        this.totalLandValue = round(val); 
    }
    
    public void calculateSchoolTax(){
        this.schoolTax = round(this.totalLandValue*TAXE_SCOLAIRE);
    }
    
    public void calculateMunicipalTax(){
        this.municipalTax = round(this.totalLandValue*TAXE_MUNICIPALE);
    }
    
    private float round(float valeur){
        float val = Math.round(valeur*100);
        if((val%5)!=0) {
            val = val + 5 -(val%5);
        }
        return val/100;
    }
}
