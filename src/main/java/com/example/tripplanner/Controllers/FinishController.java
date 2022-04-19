package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FinishController implements Initializable {
    // Gera fall sem setur price á textann
    @FXML
    private ComboBox<Integer> fxExpMonth, fxExpYear;
    @FXML
    private TextField fxName, fxCardNo, fxEmail, fxTelNo, fxSsn, fxCvc;
    @FXML
    private CheckBox fxConsent;
    @FXML
    private VBox fxPassengerInfoCont;
    @FXML
    private Pane PaymentCont;
    @FXML
    private Button fxConfirmAndPay;

    private VacationDeal vd;
    private int totalPeople;
    private ArrayList<Integer> months = new ArrayList<>();
    private ArrayList<Integer> years = new ArrayList<>();



    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 1;i < 13 ;i++ ) {
            months.add(i);
        }
        for (int i = 2022;i < 2030 ;i++ ) {
            years.add(i);
        }
        fxConfirmAndPay.setDisable(true);
        fxExpMonth.getItems().addAll(months);
        fxExpYear.getItems().addAll(years);

        fxSsn.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    fxSsn.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        fxCardNo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d")) {
                    fxCardNo.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        fxCvc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    fxCvc.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        fxTelNo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    fxTelNo.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void loadFinishCards() throws IOException {
        ArrayList<FinishCardController> listi = new ArrayList<FinishCardController>();
        if (totalPeople > 1) {
            for (int i = 0; i < totalPeople-1; i++) {
                FinishCardController fch = new FinishCardController("Fyrsti");
                listi.add(fch);
            }
        }
        fxPassengerInfoCont.getChildren().addAll(listi);
    }

    public void setVacationDeal(VacationDeal vd) {
        this.vd=vd;
        totalPeople = vd.getAdultCount() + vd.getChildCount();
    }



    public void consentHandler(ActionEvent actionEvent) {
        try {
            loadFinishCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fxConsent.setDisable(true);
    }

    public void confirmHandler(ActionEvent actionEvent) {
        //Senda uppl á teymin
    }

    public void filledOutHandler(ActionEvent actionEvent) {

        //Ath hvort allir reitir séu fylltir, þá af-disable-a confirm takkann
    }



}
