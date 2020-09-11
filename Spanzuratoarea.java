import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Spanzuratoarea extends Application {

    ArrayList<String> words = new ArrayList<>();
    int numberOfWrongChars = 1;
    int getNumberOfCorrectChars =0;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        words.add("Java");
        words.add("Presedinte");
        words.add("Nevastuica");
        words.add("Lampa");
        words.add("Masina");

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(40, 40, 40, 40));
        Group group = new Group();

        Line line0 = new Line();
        line0.setStartX(80);
        line0.setStartY(200);
        line0.setEndX(120);
        line0.setEndY(200);

        Line line1 = new Line();
        line1.setStartX(100);
        line1.setStartY(50);
        line1.setEndX(100);
        line1.setEndY(200);

        Line line2 = new Line();
        line2.setStartX(100);
        line2.setStartY(50);
        line2.setEndX(150);
        line2.setEndY(50);

        Line line3 = new Line();
        line3.setStartX(150);
        line3.setStartY(50);
        line3.setEndX(150);
        line3.setEndY(70);

        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setCenterX(150);
        circle.setCenterY(90);

        Line line4 = new Line();
        line4.setStartX(150);
        line4.setStartY(110);
        line4.setEndX(150);
        line4.setEndY(160);

        Line line5 = new Line();
        line5.setStartX(150);
        line5.setStartY(160);
        line5.setEndX(180);
        line5.setEndY(190);

        Line line6 = new Line();
        line6.setStartX(150);
        line6.setStartY(160);
        line6.setEndX(120);
        line6.setEndY(190);

        Line line7 = new Line();
        line7.setStartX(150);
        line7.setStartY(130);
        line7.setEndX(180);
        line7.setEndY(160);

        Line line8 = new Line();
        line8.setStartX(150);
        line8.setStartY(130);
        line8.setEndX(120);
        line8.setEndY(160);

        group.getChildren().addAll(line0, line1,  line2, line3, circle, line4, line5, line6, line7, line8);

        TextField textField = new TextField();

        HBox hBoxWord = new HBox();
        hBoxWord.setSpacing(20);
        hBoxWord.setAlignment(Pos.BASELINE_CENTER);

        //Generam un numar cuprins intre 0 si words.size()
        int wordIndex = (int) Math.random() * words.size();
        String wordToGuess = words.get(wordIndex);

        for(int i = 0; i< wordToGuess.length(); i++) {
            Label label = new Label("_");
            hBoxWord.getChildren().addAll(label);
        }

        textField.setOnKeyReleased(e -> {
            if(textField.getText().length() > 1) {
                textField.setText(textField.getText().substring(0,1));
            }

            if(e.getCode() == KeyCode.ENTER) {
                String userInput = textField.getText();
                boolean found = false;
                for(int i = 0; i< wordToGuess.length(); i++) {
                    String currentChar = wordToGuess.charAt(i) +"";
                    if(currentChar.equalsIgnoreCase(userInput)) {
                        Label labelCurrentChar = (Label) hBoxWord.getChildren().get(i);
                        labelCurrentChar.setText(currentChar);
                        getNumberOfCorrectChars ++;
                        found = true;
                    }
                }
                if(!found) {
                    group.getChildren().remove(group.getChildren().size() - 1);
                    if( numberOfWrongChars == 6) {
                        Alert lostAlert = new Alert(Alert.AlertType.ERROR);
                        lostAlert.setHeaderText("Ai pierdut!");
                        lostAlert.setContentText("Mai incearca o data cu alt cuvant...poate reusesti :)");
                        lostAlert.show();
                    }
                    numberOfWrongChars ++;
                }
                textField.setText("");

                if(getNumberOfCorrectChars == wordToGuess.length())
                {
                    Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
                    winAlert.setHeaderText("Ai castigat!");
                    winAlert.setContentText("Esti cel mai tare din parcare :)");
                    winAlert.show();
                }
            }
        });
        vBox.getChildren().addAll( group, textField, hBoxWord);
        Scene scene = new Scene(vBox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
