package application;

import controller.MainController;
import controller.Restaurants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by nicholas on 6/26/17.
 */
public class MainClass extends Application {
    private static Restaurants restaurant = null;
    private static Scene scene;
    private static Stage stage;
    private static int width = 285;
    private static int height = 276;


    public static int getWindowX(){return (int)stage.getX();}
    public static int getWindowY(){return (int)stage.getY();}
    public static int getWidth(){return width;}
    public static int getHeight(){return height;}
    public static Restaurants getData(){
        return restaurant;
    }
    public static Scene getScene(){
        return scene;
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));
        primaryStage.setTitle("Hello World");
        scene = new Scene(root,width,height);
        stage.setScene(scene);
        stage.show();



        //Getting all the data takes a bit
        new Thread(new Runnable(){
            public void run(){
                try {
                    MainController.loading();
                    restaurant = new Restaurants();
                    MainController.loaded();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    public static void main(String[] args) {
        launch(args);
    }
}