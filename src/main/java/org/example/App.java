package org.example;

import com.google.gson.Gson;
import org.example.util.HTTPutil;

import java.util.List;
import java.util.Map;

/**
 * Hello postcode!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        String postCode = "CB3 0FA";
        Boolean outcome = HTTPutil.getInstance().validate(postCode);
        if (outcome){
            System.out.println("Get Country and Region of " + postCode);
            System.out.println(HTTPutil.getInstance().getCountryAndRegion(postCode));

            System.out.println("\n=========================================");
            System.out.println("Get the nearest postcode, country and region of " + postCode);
            List<Map<String, String>> nearest = HTTPutil.getInstance().getNearestPostcode(postCode);
            Gson gson = new Gson();
            System.out.println(gson.toJson(nearest));

        } else {
            System.out.println("Invalid Postcode.");
        }
    }
}
