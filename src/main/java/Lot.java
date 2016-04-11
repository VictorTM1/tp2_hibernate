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
    int nbAccess, nbService;
    float surfaceArea;
    Date dateMeasured;
    float valuePerLot;
    String errorMessage;

    public Lot() {
        this.description = "";
        this.nbAccess = 0;
        this.nbService = 0;
        this.surfaceArea = 0;
        this.dateMeasured = new Date();
        this.valuePerLot = 0;
        this.errorMessage = "";
    }

    public Lot(int service, int access, float surfaceArea, String description,
            Date date) {
        this.description = description;
        this.nbAccess = access;
        this.nbService = service + 2;
        this.surfaceArea = surfaceArea;
        this.dateMeasured = date;
        this.errorMessage = "";
        valuePerLot = 0;
    }

    public void calculateLandValueLot(int type, float priceMin, float priceMax) {
        float valueLot = calculateValueSurfaceArea(type, priceMin, priceMax);
        float valueAccess = calculateValueAccess(valueLot, type);
        float valueServices = calculateValueServices(type);
        this.valuePerLot = (valueLot + valueAccess + valueServices);
    }

    public float calculateValueAccess(float valueLot, int type) {
        float val = 500;
        float percent = (float) (5 * (type + 1)) / 100;
        return (val - (this.nbAccess * valueLot * percent));
    }

    public float calculateValueSurfaceArea(int type, float priceMin,
            float priceMax) {
        float price = calculatePriceSurfaceByType(type, priceMin, priceMax);
        return (this.surfaceArea * price);
    }

    public float calculatePriceSurfaceByType(int type, float priceMin,
            float priceMax) {
        if (type == 0) {
            return priceMin;
        } else if (type == 1) {
            return ((priceMin + priceMax) / 2);
        } else {
            return priceMax;
        }
    }

    public float calculateValueServices(int type) {
        float prix = calculatePriceServiceByType(type);
        if (prix > 5000) {
            return 5000;
        } else {
            return prix;
        }
    }

    public float calculatePriceServiceByType(int type) {
        if (type == 0) {
            return 0;
        } else if (type == 1) {
            return calculateValueServicesType1();
        } else {
            return calculateValueServicesType2();
        }
    }

    public float calculateValueServicesType1() {
        if (this.surfaceArea <= 500) {
            return 0;
        } else if (this.surfaceArea <= 10000) {
            return 500 * this.nbService;
        } else {
            return 1000 * this.nbService;
        }
    }

    public float calculateValueServicesType2() {
        if (this.surfaceArea <= 500) {
            return 500 * this.nbService;
        } else {
            return 1500 * this.nbService;
        }
    }

    public boolean validateValues() {
        if (this.nbAccess < 0 || this.nbAccess > 10) {
            this.errorMessage = "le nombre d'accès doit être entre 0 et 10 inclusivement";
        } else if (this.nbService < 0 || this.nbService > 5) {
            this.errorMessage = "le nombre de services doit être entre 0 et 5 inclusivement";
        } else if (this.surfaceArea < 0 || this.surfaceArea > 50000) {
            this.errorMessage = "la superficie d'un lot ne peut être negative ni depasser 50000 m2";
        } else if (this.dateMeasured.toString().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            this.errorMessage = "les dates doivent respecter le format ISO8601";
        }

        return this.errorMessage.equals("");
    }
}
