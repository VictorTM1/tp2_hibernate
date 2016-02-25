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

public class DataVerification {
    
    public static boolean isValueAccessValid (int terrain){
    return terrain >= 0 || terrain <= 10 ;
    }
    
    public static boolean isServiceValid (int value){
        return value >= 0 || value <= 5 ; 
    }
    
    public static boolean isTypeTerrainValid (int value){
        return value >= 0 || value <= 2 ;
    }
    
    public static boolean isLotsQuantityValid (int value){
        return value <= 10 || value >= 1 ;
    }
    
    public static boolean isNegative (int value){
        return value >= 0 ;
    }
    
    public static boolean isSuperficieValid (int value){
        return value >= 0 || value <= 50000 ;
    }
    
}
