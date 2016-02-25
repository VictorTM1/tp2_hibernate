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
    private static final float BASIC_PRICE = 733.77f;    
    private static final float SCHOLAR_TAX = 0.012f; //1.2%
    private static final float MUNICIPAL_TAX = 0.025f; //1.2%
    
    int type;
    float priceMin, priceMax;
    ArrayList<Lot> list_lots;
    float totalLandValue;
    float schoolTax;
    float municipalTax;
    String errorMessage;
    
    public Terrain(){
        this.type = 0;
        this.priceMin = 0;
        this.priceMax = 0;
        this.list_lots = new ArrayList<Lot>();
        this.totalLandValue = 0;
        this.schoolTax = 0;
        this.municipalTax = 0;
        this.errorMessage = "";
    }
    
    public Terrain(int type, float min, float max, ArrayList<Lot> lots){
        this.type = type;
        this.priceMin = min;
        this.priceMax = max;
        this.list_lots=lots;
        this.errorMessage = "";
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
        float val = BASIC_PRICE;
        for(int i = 0 ; i < this.list_lots.size(); i++){
            val = val + this.list_lots.get(i).valuePerLot;
        }
        this.totalLandValue = round(val); 
    }
    
    public void calculateSchoolTax(){
        this.schoolTax = round(this.totalLandValue*SCHOLAR_TAX);
    }
    
    public void calculateMunicipalTax(){
        this.municipalTax = round(this.totalLandValue*MUNICIPAL_TAX);
    }
    
    private float round(float value){
        float val = Math.round(value*100);
        if((val%5) != 0) {
            val = val + 5 - (val % 5);
        }
        return val/100;
    }
    
    public boolean validateValues(){
        if ( this.type < 0 || this.type > 2 ){
            this.errorMessage = "La valeur de type_terrain doit être un nombre parmi 0, 1 ou 2";
        } else if ( this.priceMax < 0 || this.priceMin < 0 ) {
            this.errorMessage = "Une valeur d'argent ne doit pas être négative";
        } else if ( this.list_lots.isEmpty() || this.list_lots.size() > 10 ) {
            this.errorMessage = "Un terrain doit avoir au moins 1 lot et au plus 10 lots";
        }
        return this.errorMessage.equals("");
    }
}
