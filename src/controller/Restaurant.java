package controller;

import application.MainClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nicholas on 6/26/17.
 */
public class Restaurant {
    private static String restaurantName,
            restaurantAddress,
            restaurantPhoneNumber,
            restaurantState,
            restaurantCity,
            restaurantZip;
    private static float latitude,longitude;
    private static URL image;
    private static Stage stage;
    private static Scene scene;
    private static Restaurants restaurants;
    private static boolean open = false;
    private Image phoneIcon = new Image("file:src/images/icons/phone-icon.png");
    private Image locationIcon = new Image("file:src/images/icons/location-icon.png");

    public Label lblName;
    public ImageView imgImage;
    public Label lblAddress;
    public Label lblCity;
    public Label lblState;
    public Label lblZip;
    public Label lblPhoneNumber;
    public ImageView imgLocation;
    public ImageView imgPhoneNumber;


    public void build(String name, String address,String phoneNumber, float latitude, float longitude, URL image, String state, String city, String zip){

        restaurantState = state;
        restaurantName = name;
        restaurantAddress = address;
        restaurantPhoneNumber = phoneNumber;
        restaurantCity = city;
        restaurantZip = zip;
        latitude = latitude;
        longitude = longitude;
        image = image;

        if(!open) {
            try {
                stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/restaurant.fxml"));
                stage.setTitle("Hello World");
                scene = new Scene(root, 285, 276);
                stage.setScene(scene);
                stage.setX(MainClass.getWindowX() + MainClass.getWidth() + 15);
                stage.setY(MainClass.getWindowY() + 20);
                stage.show();
                stage.setOnCloseRequest(e ->{
                    open = false;
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            open = true;
        }

        lblName = (Label)scene.lookup("#lblName");
        lblAddress = (Label)scene.lookup("#lblAddress");
        lblCity = (Label)scene.lookup("#lblCity");
        lblState = (Label)scene.lookup("#lblState");
        lblPhoneNumber = (Label)scene.lookup("#lblPhoneNumber");
        lblZip = (Label)scene.lookup("#lblZip");
        imgImage = (ImageView)scene.lookup("#imgImage");
        imgLocation = (ImageView)scene.lookup("#imgLocation");
        imgPhoneNumber = (ImageView)scene.lookup("#imgPhoneNumber");


        Image loading = new Image("file:src/images/loading.png");
        imgPhoneNumber.setImage(phoneIcon);
        imgLocation.setImage(locationIcon);
        lblName.setText(restaurantName);
        lblAddress.setText(restaurantAddress);
        lblCity.setText(restaurantCity);
        lblState.setText(restaurantState);
        lblPhoneNumber.setText("(" + restaurantPhoneNumber.substring(0,3) + ") " +
                restaurantPhoneNumber.substring(3,6) + "-" +
                restaurantPhoneNumber.substring(6));

        lblZip.setText(restaurantZip);
        Image unavailable = new Image("file:src/images/unavailable.png");




        if(!image.toString().endsWith(".jpg") && !image.toString().endsWith(".png")){
            imgImage.setImage(unavailable);
        }else{
            imgImage.setImage(loading);
            URL finalImage = image;
            new Thread(() -> {
                try {
                    URL url = new URL(finalImage.toString());
                    imgImage.setImage(new Image(url.toString()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }).start();
        }




    }
}
