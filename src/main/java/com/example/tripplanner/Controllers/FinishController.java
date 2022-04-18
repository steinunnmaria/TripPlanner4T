package com.example.tripplanner.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FinishController implements Initializable {

    @FXML
    private VBox fxPassengerInfoCont;
    @FXML
    private Pane PaymentCont;

    public void loadFisnishCards() throws IOException {
        //Hér köllum við á getDayTrips og fáum arraylista

        ArrayList<FinishCardController> listi = new ArrayList<FinishCardController>();
        FinishCardController fch = new FinishCardController("Fyrsti");
        FinishCardController fch2 = new FinishCardController("Annar");

        listi.add(fch);
        listi.add(fch2);

        fxPassengerInfoCont.getChildren().addAll(listi);

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadFisnishCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
