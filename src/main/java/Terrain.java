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

    private static final int ROUNDER = 5;
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

    public Terrain() {
        this.type = 0;
        this.priceMin = 0;
        this.priceMax = 0;
        this.list_lots = new ArrayList<Lot>();
        this.totalLandValue = 0;
        this.schoolTax = 0;
        this.municipalTax = 0;
        this.errorMessage = "";
    }

    public Terrain(int type, float min, float max, ArrayList<Lot> lots) {
        this.type = type;
        this.priceMin = min;
        this.priceMax = max;
        this.list_lots = lots;
        this.errorMessage = "";
    }

    public ArrayList<Lot> getListeLots() {
        return this.list_lots;
    }

    public int getType() {
        return this.type;
    }

    public float getPriceMin() {
        return this.priceMin;
    }

    public float getPriceMax() {
        return this.priceMax;
    }

    public float getAveragePrice() {
        return (getPriceMin() + getPriceMax()) / 2;
    }

    public void calculateLandValue() {
        float val = BASIC_PRICE;
        for (int i = 0; i < this.list_lots.size(); i++) {
            val = val + this.list_lots.get(i).valuePerLot;
        }
        this.totalLandValue = round(val, ROUNDER);
    }

    public void calculateSchoolTax() {
        this.schoolTax = round(this.totalLandValue * SCHOLAR_TAX, ROUNDER);
    }

    public void calculateMunicipalTax() {
        this.municipalTax = round(this.totalLandValue * MUNICIPAL_TAX, ROUNDER);
    }

    private float round(float value, int rounder) {
        float val = Math.round(value * 100);
        int cents = (int) val % rounder;

        if (cents < rounder / 2) val = val - cents;
        else val = val + 5 - cents;

        return val / 100;
    }
    
    public boolean descriptionsOk(){
        for(int i = 0; i <= this.list_lots.size() - 1; i++){
            String description = list_lots.get(i).description;
            for(int j = i + 1; j < this.list_lots.size(); j++){
                if(this.list_lots.get(j).description.equals(description))
                    return false;                
            }
        }
        return true;
    }
    
    public boolean validateValues() {
        if (this.type < 0 || this.type > 2) {
            this.errorMessage = "La valeur de type_terrain doit être un nombre parmi 0, 1 ou 2";
        } else if (this.priceMax < 0 || this.priceMin < 0) {
            this.errorMessage = "Une valeur d'argent ne doit pas être négative";
        } else if (this.list_lots.isEmpty() || this.list_lots.size() > 10) {
            this.errorMessage = "Un terrain doit avoir au moins 1 lot et au plus 10 lots";
        } else if (!descriptionsOk()){
            this.errorMessage = "Une ou plusieurs description(s) de terrain(s) sont similaire(s) et/ou vide(s).";
        }
        return this.errorMessage.equals("");
    }
}
