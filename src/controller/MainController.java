package controller;

import application.BinaryTree;
import application.MainClass;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Created by nicholas on 6/26/17.
 */
public class MainController {
    private static Restaurants restaurants;
    private static BinaryTree<Float> location = new BinaryTree<>();
    private static ObservableList list;
    public static String latitudeValue;
    public static String longitudeValue;
    private static boolean loaded;


    public static ListView listView;
    public ProgressIndicator progress;
    public TextField txtLatitude;
    public TextField txtLongitude;

    public void start(MouseEvent mouseEvent) {
        final long startTime = System.nanoTime();
        for(int i = 1; i < restaurants.size();i++){
            System.out.println(restaurants.getName(i));
        }
        final long duration = System.nanoTime() - startTime;
        //System.out.println(duration);
    }

    public static void loading(){
        listView = (ListView) MainClass.getScene().lookup("#restaurantList");
        list = FXCollections.observableArrayList();
        list.add("Fetching Data");
        listView.setItems(list);
        javafx.scene.control.Button btnStart = (javafx.scene.control.Button) MainClass.getScene().lookup("#btnStart");
        //Platform.runLater(() -> btnStart.setText("Please Wait"));
    }

    public static void loaded(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        listView = (ListView) MainClass.getScene().lookup("#restaurantList");
        javafx.scene.control.Button btnStart = (javafx.scene.control.Button) MainClass.getScene().lookup("#btnStart");
        list = FXCollections.observableArrayList();
        //Platform.runLater(() -> btnStart.setText("Ready!"));
        restaurants = MainClass.getData();
        ProgressIndicator progressIndicator = (ProgressIndicator) MainClass.getScene().lookup("#progress");

        double count = 0;
        int max = restaurants.size();

        for(int i = 1; i < restaurants.size()+1; i++){
            int finalI = i;
            Platform.runLater(() -> location.add((restaurants.getLatitude(finalI) + restaurants.getLongitude(finalI)),finalI));
            progressIndicator.setProgress(count/max);
            list.add(restaurants.getName(finalI));
            count++;
        }

        //tree.display();


        progressIndicator.setProgress(count/max);
        Platform.runLater(() ->listView.setItems(list));
        loaded = true;
    }


    public void keyPressed(KeyEvent keyEvent) {

        String latitudeValue = txtLatitude.getText();
        String longitudeValue = txtLongitude.getText();




        if(keyEvent.getCode().toString().equals("ENTER")){
            if(latitudeValue.length() > 1 && longitudeValue.length() > 1){
                if(location.contains(Float.parseFloat(latitudeValue) + Float.parseFloat(longitudeValue))){
                    int id = location.get(Float.parseFloat(latitudeValue) + Float.parseFloat(longitudeValue)).getRID();
                    //System.out.println(restaurants.getName(id));
                    //String name, String address, String phoneNumber, int latitude, int longitude, File image
                    //Create new restaurant and pass in the ID
                    //new Restaurant().build(restaurants.getName(id),restaurants.getAddress(id),restaurants.getPhoneNumber(id),restaurants.getLatitude(id),restaurants.getLongitude(id),restaurants.getImage(id));
                    list = FXCollections.observableArrayList();
                    list.add(restaurants.getName(id));
                    listView.setItems(list);
                }
            }
        }else if(!keyEvent.getCode().toString().equals("ENTER")){
            //System.out.println(latitudeValue);
            if(latitudeValue.length() < 3 || longitudeValue.length() < 3){
                //Reset the list
                list = FXCollections.observableArrayList();
                for(int i = 1; i < restaurants.size()+1; i++){
                    location.add((restaurants.getLatitude(i) + restaurants.getLongitude(i)),i);
                    list.add(restaurants.getName(i));
                }
            }
            listView.setItems(list);
        }
    }


    public void listViewClicked(MouseEvent mouseEvent) {
        if(loaded) {
            int id;
            String latitudeValue = txtLatitude.getText();
            String longitudeValue = txtLongitude.getText();


            if (listView.getItems().size() == 1)
                id = location.get(Float.parseFloat(latitudeValue) + Float.parseFloat(longitudeValue)).getRID();
            else
                id = listView.getFocusModel().getFocusedIndex() + 1;

            new Restaurant().build(
                    restaurants.getName(id),
                    restaurants.getAddress(id),
                    restaurants.getPhoneNumber(id),
                    restaurants.getLatitude(id),
                    restaurants.getLongitude(id),
                    restaurants.getImage(id),
                    restaurants.getState(id),
                    restaurants.getCity(id),
                    restaurants.getZipCode(id));
        }
    }
}
