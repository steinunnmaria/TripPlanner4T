package com.example.tripplanner.Controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ReviewBookingView {
    public void editHandler(ActionEvent actionEvent) {
    }

    public void confirmHandler(ActionEvent actionEvent) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToSceneFinish(actionEvent);
    }
}
