package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
    This class allows me to directly index any row of the restaurant list
    So I can do:
    new Restaurants().getName(24);
    which would return Fuji Sushi
 */


public class Restaurants {
    private JSONArray data;
    private int length = 0;
    double count = 0;

    public Restaurants() throws Exception{

        //String site = "https://script.google.com/macros/s/AKfycbygukdW3tt8sCPcFDlkMnMuNu9bH5fpt7bKV50p2bM/exec?id=1xAnPy-WfQDg9KIuwIaQ7LIfkaVl2anXUXnfNOa1vDlo&sheet=Sheet1";
        String site = "https://script.google.com/macros/s/AKfycbz5h3Sdh_M6jjLLpR_NwFoJNCxzb_d3vQozYkqnX-CptWK17kpF/exec?id=1xAnPy-WfQDg9KIuwIaQ7LIfkaVl2anXUXnfNOa1vDlo&sheet=Sheet1";
        StringBuilder result = new StringBuilder();
        URL url = new URL(site);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;

        while((line = reader.readLine()) != null){
            result.append(line);
        }
        reader.close();

        JSONObject jsonObject = new JSONObject(result.toString());
        data = jsonObject.getJSONArray("Sheet1");


        int i = 0;
        //Get the size
        while(true){

            //System.out.println(data.getJSONObject(i).get("RESTAURANT_NAME").toString());
            try{
                if(!data.getJSONObject(i).get("RESTAURANT_NAME").toString().equals("")) {
                    length++;
                    i++;
                }
            }catch(Exception e){
                break;
            }


        }


    }

    public String getName(int ID){
        try {
            return data.getJSONObject(ID-1).get("RESTAURANT_NAME").toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public int getID(int ID){
        try {
            return Integer.parseInt(data.getJSONObject(ID-1).get("ID").toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public String getPhoneNumber(int ID){
        try {
            return data.getJSONObject(ID-1).get("_Phone_Number").toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getZipCode(int ID){
        try {
            return data.getJSONObject(ID-1).get("ZIP_CODE").toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getAddress(int ID){
        try {
            return data.getJSONObject(ID-1).get("RESTAURANT_ADDRESS").toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getState(int ID){
        try {
            return data.getJSONObject(ID-1).get("RESTAURANT_STATE").toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getCity(int ID){
        try {
            return data.getJSONObject(ID-1).get("RESTAURANT_CITY").toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public float getLatitude(int ID){
        try {
            return Float.parseFloat(data.getJSONObject(ID-1).get("LATITUDE").toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    public float getLongitude(int ID){
        try {
            return Float.parseFloat(data.getJSONObject(ID-1).get("LONGITUDE").toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    public URL getImage(int ID){
        try {
            return new URL(data.getJSONObject(ID-1).get("URL_IMAGE").toString());
        } catch (JSONException | MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public int size(){
        return length;
    }
}