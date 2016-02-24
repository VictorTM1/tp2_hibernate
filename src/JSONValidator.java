/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;

import net.sf.json.JSONObject;
/**
 * @author dvmedellin
 */
public class JSONValidator {
    List<String> cles_terrain;
    List<String> cles_lot;
   
    public JSONValidator(){
    
    } 
   
    public boolean validate(JSONObject object){
        for (String cle: cles_terrain) {
            if (!object.has(cle) ){
                return false;
            } 
        }
        
        return true;
   }
}
