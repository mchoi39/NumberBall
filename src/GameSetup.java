
import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javafx.scene.input.MouseEvent;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class GameSetup extends Application {
    final String FILE_PROTOCOL = "file:";
    final String IMAGES_PATH = "./images/";
    final String BLACK_ANT_IMAGE_URL = FILE_PROTOCOL + IMAGES_PATH + "BlackAnt.png";
    final String MAP_IMAGE_URL = FILE_PROTOCOL + IMAGES_PATH + "usa_map.jpg";
    //final ArrayList<City> cities = new ArrayList<City>();
    //Image mapImage = loadImage(MAP_IMAGE_URL);


    ArrayList<User> players = new ArrayList<>();
    ListView<String> listView1;
    ObservableList<String> items1 = FXCollections.observableArrayList();



    ListView<String> listView2;
    ObservableList<String> items2 = FXCollections.observableArrayList();



    Scanner reader = new Scanner(System.in);

    BorderPane appPane = new BorderPane();  // THIS ARRANGES ALL THE CONTROLS INSIDE THE SCENE
    Pane centerPane = new Pane();           // THIS IS FOR THE TWO STACKED IMAGES IN THE CENTER
    ImageView bottomImageView = new ImageView();    // THIS IS FOR THE BOTTOM IMAGE IN THE MIDDLE
    ImageView topImageView = new ImageView();       // THIS IS FOR THE TOP IMAGE IN THE MIDDLE
    VBox rightPane = new VBox();                   // THIS IS FOR THE TOOLBAR ON THE BOTTOM WITH THE BUTTONS
    VBox leftPane = new VBox();
    HBox player1info = new HBox();
    HBox player2info = new HBox();

    TextField txtP1 = new TextField();
    TextField txtP2 = new TextField();



    CheckBox imageSelectionCheckBox = new CheckBox("Replace Top Image:");   // THIS TOGGLE WILL LET US CHANGE EITHER THE TOP OR BOTTOM IMAGE
    Scene windowScene = new Scene(appPane);         // THIS WILL CONTAIN THE FULL UI INSIDE THE WINDOW
    GridPane grid = new GridPane();



    HBox bot = new HBox();

    HBox stopPane = new HBox();
    HBox filePane = new HBox();


    Label label1 = new Label("Player 1: ");
    Label label2 = new Label("Player 2: ");


    //trip global things

    //ArrayList<City> tripList = new ArrayList<>();


    //Label displayDist = new Label("Total Distance: " + totalDist);


    Button newGame = new Button("New");

    Button guessP1 = new Button("Guess");
    Button guessP2 = new Button("Guess");


    //drawing stuff
    StackPane stack = new StackPane();
    Canvas canvas = new Canvas(512,269.6);
    GraphicsContext gc = canvas.getGraphicsContext2D();



    FileChooser fileChooser = new FileChooser();

    MouseEvent mouseEvent;

    Integer player1;
    Integer player2;



    // WE'LL GET THIS FROM THE start METHOD
    Stage window;



    @Override
    public void start(Stage initWindow) {
        listView1 = new ListView<>();
        listView2 = new ListView<>();

        newGame.setOnAction(event -> {
            Stage alertWindow = new Stage();
            Button okies = new Button("Ok");
            Button cancel = new Button("Cancel");

            Label msg1 = new Label("Enter non repeating 4 digit number: ");
            Label msg2 = new Label("Enter non repeating 4 digit number: ");
            TextField nameGetter1 = new TextField();
            TextField nameGetter2 = new TextField();

            okies.setOnAction(eventz -> {
                try{
                    player1 = Integer.parseInt(nameGetter1.getText());
                    player2 = Integer.parseInt(nameGetter2.getText());
                    int lengthP1 = String.valueOf(player1).length();
                    int lengthP2 = String.valueOf(player2).length();

                    if (lengthP1 != 4 || lengthP2 != 4){
                        throw new IllegalArgumentException();
                    } 
                    initUsers(player1, player2);

                    alertWindow.close();
                } catch (IllegalArgumentException g){
                    System.out.println("Invalid Number try again");
                }
//                player1 = Integer.parseInt(nameGetter1.getText());
//                player2 = Integer.parseInt(nameGetter2.getText());
//                int lengthP1 = String.valueOf(player1).length();
//                int lengthP2 = String.valueOf(player2).length();
//
//
//                initUsers(player1, player2);
//
//                alertWindow.close();

            });

            cancel.setOnAction(event1 -> {
                alertWindow.close();
            });
            HBox okAndCancel = new HBox();
            okAndCancel.getChildren().addAll(okies, cancel);
            VBox layout = new VBox();
            layout.getChildren().addAll(msg1,nameGetter1, msg2, nameGetter2, okAndCancel);

            Scene scene = new Scene(layout);
            alertWindow.setScene(scene);
            alertWindow.showAndWait();
//            items1.add(Integer.toString(players.get(0).getNumber()));
//            items2.add(Integer.toString(players.get(1).getNumber()));



        });
//        items1.add(Integer.toString(players.get(0).getNumber()));
//        items2.add(Integer.toString(players.get(1).getNumber()));
//        listView1.setItems(items1);
//        listView2.setItems(items2);
        guessP1.setOnAction(event -> {
            try {
                int length = String.valueOf(txtP1).length();
                if (length != 4) {
                    throw new IllegalArgumentException();
                }
                char[] master = String.valueOf(players.get(1).getNumber()).toCharArray();
                Guess player1guess = new Guess(Integer.parseInt(txtP1.getText()));
                String num = String.valueOf(player1guess.getTheGuess());
                char[] digits = num.toCharArray();
                int strkCounter = 0;
                for (int i = 0; i < master.length; i++) {
                    if (digits[i] == master[i]) {
                        //strikeIndex[strikCounter] = i;
                        strkCounter++;
                        player1guess.incrStrike();
                        continue;
                    }

                }
                items1.add(txtP1.getText());
                listView1.setItems(items1);

                int ballCounter = 0;
                for (int g = 0; g < master.length; g++) {
                    for (int h = 0; h < master.length; h++) {
                        if (digits[g] == master[h]) {
                            ballCounter++;
                        }
                    }
                }
                ballCounter -= strkCounter;
                System.out.println("ball is " + ballCounter);
            } catch (IllegalArgumentException e){
                System.out.println("Invalid input");
            }
        });

        guessP2.setOnAction(event -> {
            try {
                int length = String.valueOf(txtP2).length();
                if (length != 4) {
                    throw new IllegalArgumentException();
                }
                char[] master = String.valueOf(players.get(0).getNumber()).toCharArray();
                Guess player1guess = new Guess(Integer.parseInt(txtP2.getText()));
                String num = String.valueOf(player1guess.getTheGuess());
                char[] digits = num.toCharArray();
                int strkCounter = 0;
                for (int i = 0; i < master.length; i++) {
                    if (digits[i] == master[i]) {
                        //strikeIndex[strikCounter] = i;
                        strkCounter++;
                        player1guess.incrStrike();
                        continue;
                    }

                }
                items1.add(txtP1.getText());
                listView1.setItems(items1);

                int ballCounter = 0;
                for (int g = 0; g < master.length; g++) {
                    for (int h = 0; h < master.length; h++) {
                        if (digits[g] == master[h]) {
                            ballCounter++;
                        }
                    }
                }
                ballCounter -= strkCounter;
                System.out.println("ball is " + ballCounter);
            } catch (IllegalArgumentException e){
                System.out.println("Invalid input");
            }
        });


        //topImageView.setImage(mapImage);
        topImageView.setFitHeight(269.6);
        topImageView.setFitWidth(512);
        topImageView.setPreserveRatio(true);


        stack.setAlignment(Pos.TOP_LEFT);

        // THEN PUT EVERYTHING INSIDE THE appPane, WHICH IS ALREADY IN THE SCENE
        player1info.getChildren().addAll(label1);
        leftPane.getChildren().addAll(newGame, player1info, txtP1, guessP1);



        player2info.getChildren().addAll(label2);
        rightPane.getChildren().addAll(player2info, txtP2, guessP2);


        leftPane.getChildren().add(listView1);
        rightPane.getChildren().add(listView2);





        appPane.setCenter(stack);

        appPane.setRight(rightPane);
        appPane.setLeft(leftPane);
        appPane.setTop(filePane);

        appPane.setBottom(bot);
        window = initWindow;
        window.setWidth(1024);
        window.setHeight(768);
        window.setScene(windowScene);
        window.show();

    }

    public void initUsers(int a, int b){
        User player1 = new User();
        User player2 = new User();

        player1.setNumber(a);
        player2.setNumber(b);

        players.add(player1);
        players.add(player2);
    }




    public static void main(String[] args){
        launch(args);
    }
}