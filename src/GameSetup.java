
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



    ListView<String> listView;
    ObservableList<String> items = FXCollections.observableArrayList();

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
    //ArrayList<City> trip = new ArrayList<>();
    //ArrayList<City> tripList = new ArrayList<>();
    ObservableList<String> trip1 = FXCollections.observableArrayList();
    ListView<String> tripView;
    String tripName;
    HBox tripPlusMinusHBox = new HBox();
    double totalDist;

    //Label displayDist = new Label("Total Distance: " + totalDist);




    Button guessP1 = new Button("Guess");
    Button guessP2 = new Button("Guess");


    //drawing stuff
    StackPane stack = new StackPane();
    Canvas canvas = new Canvas(512,269.6);
    GraphicsContext gc = canvas.getGraphicsContext2D();



    FileChooser fileChooser = new FileChooser();

    MouseEvent mouseEvent;


    // WE'LL GET THIS FROM THE start METHOD
    Stage window;
    @Override
    public void start(Stage initWindow) {



        //topImageView.setImage(mapImage);
        topImageView.setFitHeight(269.6);
        topImageView.setFitWidth(512);
        topImageView.setPreserveRatio(true);

        stack.setAlignment(Pos.TOP_LEFT);

        // THEN PUT EVERYTHING INSIDE THE appPane, WHICH IS ALREADY IN THE SCENE
        player1info.getChildren().addAll(label1);
        leftPane.getChildren().addAll(player1info, txtP1, guessP1);


        player2info.getChildren().addAll(label2);
        rightPane.getChildren().addAll(player2info, txtP2, guessP2);








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

//        topImageView.setOnMouseClicked(e -> {
//            System.out.println("["+e.getX()+", "+e.getY()+"]");
//
//
//            // PUT THE SCDENE IN THE WINDOW AND OPEN THE WINDOW UP
//            window = initWindow;
//            window.setWidth(1024);
//            window.setHeight(768);
//            window.setScene(windowScene);
//            window.show();
//
//        }

        initUsers();
    }

    public void initUsers(){
        User player1 = new User();
        System.out.print("Enter non-repeating 4 digit number: ");
        int num1 = Integer.parseInt(reader.nextLine());
        User player2 = new User();
        System.out.print("Enter non-repeating 4 digit number: ");
        int num2 = Integer.parseInt(reader.nextLine());

        player1.setNumber(num1);
        player2.setNumber(num2);
    }




    public static void main(String[] args){
        launch(args);
    }
}






//
//
//
//public class ImageSwitcher extends Application {
//    final String FILE_PROTOCOL = "file:";
//    final String IMAGES_PATH = "./images/";
//    final String BLACK_ANT_IMAGE_URL = FILE_PROTOCOL + IMAGES_PATH + "BlackAnt.png";
//    final String MAP_IMAGE_URL = FILE_PROTOCOL + IMAGES_PATH + "usa_map.jpg";
//    final ArrayList<City> cities = new ArrayList<City>();
//    Image mapImage = loadImage(MAP_IMAGE_URL);
//    // NOTE THAT THE DECLARATION AND CONSTRUCTION OF
//    // THESE COMPONENTS WILL HAPPEN AS A RESULT OF
//    // launch BEFORE THE start METHOD IS EXECTUTED
//
//
//    ListView<String> listView;
//    ObservableList<String> items = FXCollections.observableArrayList();
//
//
//    BorderPane appPane = new BorderPane();  // THIS ARRANGES ALL THE CONTROLS INSIDE THE SCENE
//    Pane centerPane = new Pane();           // THIS IS FOR THE TWO STACKED IMAGES IN THE CENTER
//    ImageView bottomImageView = new ImageView();    // THIS IS FOR THE BOTTOM IMAGE IN THE MIDDLE
//    ImageView topImageView = new ImageView();       // THIS IS FOR THE TOP IMAGE IN THE MIDDLE
//    VBox rightPane = new VBox();                   // THIS IS FOR THE TOOLBAR ON THE BOTTOM WITH THE BUTTONS
//    CheckBox imageSelectionCheckBox = new CheckBox("Replace Top Image:");   // THIS TOGGLE WILL LET US CHANGE EITHER THE TOP OR BOTTOM IMAGE
//    Scene windowScene = new Scene(appPane);         // THIS WILL CONTAIN THE FULL UI INSIDE THE WINDOW
//    GridPane grid = new GridPane();
//
//
//    HBox bot = new HBox();
//
//    HBox stopPane = new HBox();
//    HBox filePane = new HBox();
//
//
//    Label label1 = new Label("Name:");
//    Label label2 = new Label("State:");
//    Label label3 = new Label("Latitude Degrees:");
//    Label label4 = new Label("Latitude Minutes:");
//    Label label5 = new Label("Longitude Degrees:");
//    Label label6 = new Label("Longitude Minutes:");
//
//
//    //trip global things
//    //ArrayList<City> trip = new ArrayList<>();
//    ArrayList<City> tripList = new ArrayList<>();
//    ObservableList<String> trip1 = FXCollections.observableArrayList();
//    ListView<String> tripView;
//    String tripName;
//    HBox tripPlusMinusHBox = new HBox();
//    double totalDist;
//
//    //Label displayDist = new Label("Total Distance: " + totalDist);
//
//
//
//    Button addStop = new Button("+");
//    Button removeStop = new Button("-");
//    Button update = new Button("Update");
//
//
//    //drawing stuff
//    StackPane stack = new StackPane();
//    Canvas canvas = new Canvas(512,269.6);
//    GraphicsContext gc = canvas.getGraphicsContext2D();
//
//
//
//    FileChooser fileChooser = new FileChooser();
//
//    MouseEvent mouseEvent;
//
//
//    // WE'LL GET THIS FROM THE start METHOD
//    Stage window;
//
//    public void start(Stage initWindow) {
//        File temp = new File("Cities.dat");
//        if (!temp.exists()){
//            initCitiesArrayList();
//            writeCitiesArrayListToFile("Cities.dat", cities);
//
//        }
//        loadCitiesArrayListFromFile("Cities.dat");
//
//        // LAYOUT ALL THE USER INTERFACE CONTROLS
//
//        listView = new ListView<>();
//        bringData();
//        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//
//        tripView = new ListView<>();
//        tripView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//
//
//
//        centerPane.getChildren().add(bottomImageView);
//        centerPane.getChildren().add(topImageView);
//        bottomImageView.setX(50);
//        bottomImageView.setY(50);
//
//
//
//
//        stack.getChildren().add(topImageView);
//        stack.getChildren().add(canvas);
//
//
//
//        TextField textField1 = new TextField ();
//        TextField textField2 = new TextField ();
//        TextField textField3 = new TextField ();
//        TextField textField4 = new TextField ();
//        TextField textField5 = new TextField ();
//        TextField textField6 = new TextField ();
//
//
//
//
//
//
//        grid.add(addStop, 1,0);
//        grid.add(removeStop, 3,0);
//        grid.add(update, 1,7);
//        grid.add(label1, 1,1);
//        grid.add(textField1, 2,1);
//        grid.add(label2, 1,2);
//        grid.add(textField2, 2,2);
//        grid.add(label3, 1,3);
//        grid.add(textField3, 2,3);
//        grid.add(label4, 1,4);
//        grid.add(textField4, 2,4);
//        grid.add(label5, 1,5);
//        grid.add(textField5, 2,5);
//        grid.add(label6, 1,6);
//        grid.add(textField6, 2,6);
//        grid.setAlignment(Pos.CENTER);
//
//        bot.getChildren().addAll(listView);
//        bot.getChildren().addAll(grid);
//        bot.setAlignment(Pos.TOP_LEFT);
//
//
//
//        Button addStopToTrip = new Button("+");
//        Button removeStopToTrip = new Button("-");
//        Label tripStopLabel = new Label("Trip Stops ");
//        Label displayDist = new Label("Total Mileage: " + totalDist);
//
//        tripPlusMinusHBox.getChildren().addAll(tripStopLabel, addStopToTrip, removeStopToTrip);
//        rightPane.getChildren().addAll(tripPlusMinusHBox, tripView, displayDist);
//
//
//
//
//
//        tripView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent c) {
//
//                if (c.getClickCount() == 2) {
//                    tripView.getSelectionModel().clearSelection();
//                    //use this to do whatever you want to. Open Link etc.
//                }
//            }
//        });
//
//        //bottomPane.setMaxHeight(20);
//        //textField1.setText();
//
//
//        Button newFile = new Button("New");
//        Button saveFile = new Button("Save");
//        Button loadFile = new Button("Load");
//        filePane.getChildren().add(newFile);
//        filePane.getChildren().add(saveFile);
//        filePane.getChildren().add(loadFile);
//
//
//
//
//        topImageView.setImage(mapImage);
//        topImageView.setFitHeight(269.6);
//        topImageView.setFitWidth(512);
//        topImageView.setPreserveRatio(true);
//
//        stack.setAlignment(Pos.TOP_LEFT);
//
//        // THEN PUT EVERYTHING INSIDE THE appPane, WHICH IS ALREADY IN THE SCENE
//
//        appPane.setCenter(stack);
//
//        appPane.setRight(rightPane);
//        appPane.setTop(filePane);
//
//        appPane.setBottom(bot);
//
//
//
//        topImageView.setOnMouseClicked(e -> {
//            System.out.println("["+e.getX()+", "+e.getY()+"]");
//        });
//
//
//        // PUT THE SCDENE IN THE WINDOW AND OPEN THE WINDOW UP
//        window = initWindow;
//        window.setWidth(1024);
//        window.setHeight(768);
//        window.setScene(windowScene);
//        window.show();
//    }
//
//    public void writeCitiesArrayListToFile(String fileName, ArrayList arr) {
//        DataOutputStream dos;
//        try {
//
//
//            File f = new File(fileName);
//            FileOutputStream fos = new FileOutputStream(f);
//            dos = new DataOutputStream(fos);
//
//
//            for (int i = 0; i < arr.size(); i++){
//                if (!cities.get(i).getCityName().equals(arr.get(i))){
//                    dos.writeUTF(cities.get(i).getCityName()+"-"+cities.get(i).getState()+"-"+cities.get(i).getLatDeg()+"-"+cities.get(i).getLatMin()
//                            +"-"+cities.get(i).getLongDeg()+"-"+cities.get(i).getLongMin()+"\n");
//                }
//            }
//
//
//            dos.close();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }
//    private void initCitiesArrayList() {
//        cities.add(new City("Austin","Texas",30,59,98,22));
//        cities.add(new City("Boston","Massachusetts",42,51,71,51));
//        cities.add(new City("Dallas","Texas",32,51,96,51));
//        cities.add(new City("Denver","Colorado",39,25,104,1));
//        cities.add(new City("El Paso","Texas",31,25,106,1));
//        cities.add(new City("Houston","Texas",29,59,95,22));
//        cities.add(new City("Indianapolis","Indiana",39,59,86,22));
//        cities.add(new City("Jacksonville","Florida",30,59,81,22));
//        cities.add(new City("Los Angeles","California",33,56,95,22));
//        cities.add(new City("New Orleans","Louisiana",29,56,90,22));
//
//    }
//
//    private void loadCitiesArrayListFromFile(String fileName){
//        try {
//            File f = new File(fileName);
//            FileInputStream fis = new FileInputStream(f);
//            DataInputStream dis = new DataInputStream(fis);
//
//            while(dis.available() > 0){
//                String before = dis.readUTF();
//                String[] arr = before.split("-");
//                int last = arr[5].indexOf("\n");
//
//                String gg = arr[5].substring(0,last);
////                System.out.println(gg);
////                for (String a : arr){
////                    System.out.println(a);
////                }
//
//                City a = new City(arr[0],arr[1],Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),Integer.parseInt(gg));
//                boolean exists = false;
//                for (City b : cities){
//                    if (b.getCityName().equals(a.getCityName())){
//                        exists = true;
//                    }
//                }
//                if (!exists){
//                    cities.add(a);
//                    Collections.sort(cities, new Comparator<City>() {
//                        @Override
//                        public int compare(City o1, City o2) {
//                            return o1.getCityName().compareTo(o2.getCityName());
//                        }
//                    });
//                }
//                //cities.add(a);
//            }
//
//
//
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } catch (NumberFormatException e){
//            System.out.println("idk");
//        }
//    }
//
//    private void bringData(){
//        for (City a : cities){
//            items.addAll(a.getCityName() + ", " + a.getState());
//        }
//        listView.setItems(items);
//    }
//
//    private Image loadImage(String imageFileURL) {
//        Image image = new Image(imageFileURL);
//        if (!image.isError()) {
//            return image;
//        }
//        else
//            return null;
//    }
//
//    private void cityNameAlert(){
//        Stage alertWindow = new Stage();
//        Label msg = new Label("Enter name of new trip: ");
//        TextField nameGetter = new TextField();
//        Button okButton = new Button("Ok");
//        okButton.setOnAction(event -> {
//            tripName = nameGetter.getText();
//            alertWindow.close();
//            makeNewTrip();
//        });
//        VBox layout = new VBox();
//        layout.getChildren().addAll(msg,nameGetter, okButton);
//
//        Scene scene = new Scene(layout);
//        alertWindow.setScene(scene);
//        alertWindow.showAndWait();
//    }
//
//    public void makeNewTrip(){
//
//        try {
//            gc.clearRect(0,0,512,269.6);
//            tripList.clear();
//            trip1.clear();
//            tripView.setItems(trip1);
//            //checkcheckcheck!!!!
//
//            window.setTitle(tripName);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void fileSaver(){
//        fileChooser.setInitialFileName(tripName);
//        File file = fileChooser.showSaveDialog(window);
//
//        tripName = file.getName();
//        if (file != null){
//
//            try {
//
//                FileOutputStream f = new FileOutputStream(tripName + ".txt", false);
//                DataOutputStream foo = new DataOutputStream(f);
//                if (tripList.isEmpty()){
//                    return;
//                }
//
//                for (City b : tripList){
//                    foo.writeUTF(b.getCityName()+"-"+ b.getState()+"-"+b.getLatDeg()+"-"+b.getLatMin()
//                            +"-"+b.getLongDeg()+"-"+b.getLongMin()+"\n");
//
//                }
//                foo.close();
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void loadFile() {
//        try {
//            gc.clearRect(0,0,512,269.6);
//            tripList.clear();
//            trip1.clear();
//
//
//            File f = fileChooser.showOpenDialog(window);
//            tripName = f.getName();
//            window.setTitle(tripName);
//            FileInputStream fis = new FileInputStream(f);
//            DataInputStream dis = new DataInputStream(fis);
//
//
//            while (dis.available() > 0) {
//                String before = dis.readUTF();
//                String[] arr = before.split("-");
//                int last = arr[5].indexOf("\n");
//
//                String gg = arr[5].substring(0, last);
//
//
//                City a = new City(arr[0], arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(gg));
//                tripList.add(a);
//
//            }
//            for (City g : tripList){
//                trip1.add(g.getCityName() + ", " + g.getState());
//            }
//            tripView.setItems(trip1);
//
//
//
//            for (City b: tripList) {
//                double lat = b.getLatDeg() + (b.getLatMin() / 60.0);
//                double longi = b.getLongDeg() + (b.getLongMin() / 60.0);
//
//                double pixPerLat = (269.6 / (50-25));
//                double pixPerLong = (512.0 / (125-65));
//                double lastLat = (269.6- ((lat - 25)*pixPerLat));
//                double lastLong = (512.0- ((longi - 65)*pixPerLong));
//                gc.strokeOval(lastLong, lastLat, 5, 5);
//
//
//            }
//            for (int i = 1; i < tripList.size(); i++){
//
//                double pixPerLat = (269.6 / (50-25));
//                double pixPerLong = (512.0 / (125-65));
//
//
//
//                double lat1 = tripList.get(i).getLatDeg() + (tripList.get(i).getLatMin() / 60.0);
//                double longi1 = tripList.get(i).getLongDeg() + (tripList.get(i).getLongMin() / 60.0);
//
//                double lat2 = tripList.get(i-1).getLatDeg() + (tripList.get(i-1).getLatMin() / 60.0);
//                double longi2 = tripList.get(i-1).getLongDeg() + (tripList.get(i-1).getLongMin() / 60.0);
//
//
//                double lastLat1 = (269.6- ((lat1 - 25)*pixPerLat));
//                double lastLong1 = (512.0- ((longi1 - 65)*pixPerLong));
//
//                double lastLat2 = (269.6- ((lat2 - 25)*pixPerLat));
//                double lastLong2 = (512.0- ((longi2 - 65)*pixPerLong));
//
//                gc.strokeLine(lastLong1, lastLat1, lastLong2, lastLat2);
//
//            }
//
//
//
//
//        } catch (NullPointerException e) {
//            System.out.print("Null pointer exeption " + e);
//        } catch (FileNotFoundException gg) {
//            System.out.println("File does not exist!");
//        } catch (StringIndexOutOfBoundsException nope) {
//            System.out.println("The file is empty! no Stops in the trip. Total Distance = 0");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//    private double calculateDistance(){
//        double totalDist = 0;
//        double RADIAN_FACTOR = 180/Math.PI;
//        double EARTH_RADIUS = 6371;
//
//        for (int i = 1; i < tripList.size(); i++){
//
//            double lat1 = tripList.get(i-1).getLatDeg() + (tripList.get(i-1).getLatMin()/60.0);
//            double lat2 = tripList.get(i).getLatDeg() + (tripList.get(i).getLatMin()/60.0);
//            double long1 = tripList.get(i-1).getLongDeg() + (tripList.get(i-1).getLongMin()/60.0);
//            double long2 = tripList.get(i).getLongDeg() + (tripList.get(i).getLongMin()/60.0);
//
//            double x = (Math.sin(lat1/RADIAN_FACTOR) * Math.sin(lat2/RADIAN_FACTOR))
//                    + (Math.cos(lat1/RADIAN_FACTOR)
//                    * Math.cos(lat2/RADIAN_FACTOR)
//                    * Math.cos((long2/RADIAN_FACTOR) - (long1/RADIAN_FACTOR)));
//            double distance = EARTH_RADIUS * Math.atan((Math.sqrt(1 - Math.pow(x, 2))/x));
//            totalDist += distance;
//        }
//
//
//        return totalDist * 0.62137;
//    }
//
//    private void drawAndDist(){
//        totalDist = calculateDistance();
//        for (City b: tripList) {
//            double lat = b.getLatDeg() + (b.getLatMin() / 60.0);
//            double longi = b.getLongDeg() + (b.getLongMin() / 60.0);
//            //pixels per lat: (669 / (50-25)) =  26.76
//            //Calculate pixel: (669 - ((lat - 25) * 26.76))
//            //512,269.6
//
//
//            //pixels per lon: (1280 / (125-65)) = 21.33
//            //Calculate pixel: (1280 - ((lon - 65) * 21.33))
//
////                System.out.println(mapImage.getWidth() + ", " + mapImage.getHeight());
//            double pixPerLat = (269.6 / (50-25));
//            double pixPerLong = (512.0 / (125-65));
//            double lastLat = (269.6- ((lat - 25)*pixPerLat));
//            double lastLong = (512.0- ((longi - 65)*pixPerLong));
////                System.out.println("lat, long: " + lastLat + ", " + lastLong);
//            gc.strokeOval(lastLong, lastLat, 5, 5);
//            //gc.fillOval(50,100,70,70);
//
//        }
//        for (int i = 1; i < tripList.size(); i++){
//
//            double pixPerLat = (269.6 / (50-25));
//            double pixPerLong = (512.0 / (125-65));
//
//
//
//            double lat1 = tripList.get(i).getLatDeg() + (tripList.get(i).getLatMin() / 60.0);
//            double longi1 = tripList.get(i).getLongDeg() + (tripList.get(i).getLongMin() / 60.0);
//
//            double lat2 = tripList.get(i-1).getLatDeg() + (tripList.get(i-1).getLatMin() / 60.0);
//            double longi2 = tripList.get(i-1).getLongDeg() + (tripList.get(i-1).getLongMin() / 60.0);
//
//
//            double lastLat1 = (269.6- ((lat1 - 25)*pixPerLat));
//            double lastLong1 = (512.0- ((longi1 - 65)*pixPerLong));
//
//            double lastLat2 = (269.6- ((lat2 - 25)*pixPerLat));
//            double lastLong2 = (512.0- ((longi2 - 65)*pixPerLong));
//
//            gc.strokeLine(lastLong1, lastLat1, lastLong2, lastLat2);
//
//        }
//
//    }
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
