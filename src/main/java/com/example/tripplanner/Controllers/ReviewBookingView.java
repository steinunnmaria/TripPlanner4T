package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ReviewBookingView {
    private VacationDeal vd;

    public void editHandler(ActionEvent actionEvent) {
    }

    public void setVacationDeal(VacationDeal vd) {
        this.vd=vd;

    }

    public void confirmHandler(ActionEvent actionEvent) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToSceneFinish(actionEvent, vd);
    }
}
