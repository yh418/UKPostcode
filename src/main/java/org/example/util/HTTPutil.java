package org.example.util;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.model.ResponseObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//singleton
public class HTTPutil {
    private static HTTPutil instance;
    private OkHttpClient client;

    private HTTPutil() {
        client = new OkHttpClient();

    }

    public static HTTPutil getInstance() {
        if (instance == null) {
            instance = new HTTPutil();
        }
        return instance;
    }

    /**
     * validate postcode
     * @param postCode
     * @return
     */
    public Boolean validate(String postCode) {
        Request request = new Request.Builder()
                .url("http://api.postcodes.io/postcodes/" + postCode +"/validate")
                .build();
        Response response;

        try{
            response = client.newCall(request).execute();
            String body = response.body().string();

            Gson gson = new Gson();
            ResponseObject json = gson.fromJson(body, ResponseObject.class);
            Double status = json.getStatus();

            if (Double.compare(200d, status)==0) {

                String result = StringUtil.nullToEmpty(json.getResult());
                return Boolean.parseBoolean(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String getCountryAndRegion(String postCode){

        Request request = new Request.Builder()
                .url("http://api.postcodes.io/postcodes/" + postCode)
                .build();
        Response response = null;
        try{
            response = client.newCall(request).execute();
            String body = response.body().string();

            Gson gson = new Gson();
            ResponseObject json = gson.fromJson(body, ResponseObject.class);
            if (Double.compare(200d, json.getStatus()) == 0){
                Map<String,String> result = (Map<String,String> )json.getResult();
                String country = result.get("country");
                String region = result.get("region");
                return "Country: " + country + ", Region: " + region;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Request failed.";

    }

    public List<Map<String, String>> getNearestPostcode(String postCode){

        List<Map<String, String>> desiredOutput = new ArrayList<Map<String, String>>();

        Request request = new Request.Builder()
                .url("http://api.postcodes.io/postcodes/" + postCode + "/nearest")
                .build();
        Response response = null;
        try{
            response = client.newCall(request).execute();
            String body = response.body().string();

            Gson gson = new Gson();
            ResponseObject json = gson.fromJson(body, ResponseObject.class);
            if (Double.compare(200d, json.getStatus()) == 0){
                List<Map<String, String>> result = (List<Map<String, String>>) json.getResult();
                for (Map<String,String> item : result) {
                    Map<String, String> record = new HashMap<String, String>();
                    record.put("postcode", item.get("postcode"));
                    record.put("country", item.get("country"));
                    record.put("region", item.get("region"));

                    desiredOutput.add(record);

                }
                return desiredOutput;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
