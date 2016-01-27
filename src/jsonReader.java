/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author robeen
 */
import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class jsonReader {
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        File json = new File("../terrain.json");
        
        //Creates a FileReader Object
        FileReader fr = new FileReader(json); 
        char [] a = new char[50];
        fr.read(a); // reads the content to the array
        for(char c : a)
            System.out.print(c); //prints the characters one by one
        fr.close();
    }
    
}
