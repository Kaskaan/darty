package com.kaskaan.darts;

//extends Application
public class Main  {

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Darty");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }

    public static void main(String[] args) {
//        launch(args);
    Game game = new Game.Builder("Konrad").newPlayer("Malwina").newPlayer("Hubuś").newPlayer("Bożena").build().pl;
        game.play();
    }

}


















