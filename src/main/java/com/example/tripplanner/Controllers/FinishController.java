package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
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

    private VacationDeal vd;

    private int totalPeople;

    public void loadFisnishCards() throws IOException {
        //Hér köllum við á getDayTrips og fáum arraylista
        //totalPeople = vd.getAdultCount() + vd.getChildCount();

        ArrayList<FinishCardController> listi = new ArrayList<FinishCardController>();
        //System.out.println(totalPeople);

        for (int i = 0; i < 5; i++) {
            FinishCardController fch = new FinishCardController("Fyrsti");
            listi.add(fch);

        }


        fxPassengerInfoCont.getChildren().addAll(listi);
        //FinishCardController fch2 = new FinishCardController("Annar");


        //listi.add(fch2);

        //fxPassengerInfoCont.getChildren().addAll(listi);

    }

    public void setVacationDeal(VacationDeal vd) {
        this.vd=vd;
        //totalPeople = vd.getAdultCount() + vd.getChildCount();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadFisnishCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
