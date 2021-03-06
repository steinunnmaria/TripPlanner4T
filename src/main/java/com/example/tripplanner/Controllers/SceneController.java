package com.example.tripplanner.Controllers;

import com.example.tripplanner.Classes.VacationDeal;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSceneBooking(ActionEvent actionEvent, VacationDeal vd) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/tripplanner/booking-process-view.fxml")));
        root = loader.load();
        BookingProcessController bookingControl = loader.getController();
        bookingControl.setVacationDeal(vd);
        bookingControl.setThisLoader(loader);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setX(100);
        stage.setY(100);
        scene = new Scene((root));
        stage.setTitle("Booking process");
        stage.setScene(scene);
        stage.show();
        bookingControl.loadFlightCards();
    }
    public void switchToSceneReview(ActionEvent actionEvent, VacationDeal vd) throws Exception {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/tripplanner/review-booking-view.fxml")));
        root = loader.load();
        ReviewBookingController reviewBookingController = loader.getController();
        reviewBookingController.setVacationDeal(vd);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setTitle("Review Booking");
        stage.setScene(scene);
        stage.show();
        reviewBookingController.setOverview(vd);
    }

    public void switchToSceneFront(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/tripplanner/front-page-view.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setTitle("Trip Planner");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneFinish(ActionEvent actionEvent, VacationDeal vd) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/tripplanner/finish-view.fxml")));
        root = loader.load();
        FinishController finishController = loader.getController();
        finishController.setVacationDeal(vd);
        //finishController.setThisLoader(loader);
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setTitle("Trip Planner");
        stage.setScene(scene);
        stage.show();
        finishController.setPrice(vd.getTotalPrice());
    }

    public void closeButtonAction(ActionEvent actionEvent){
        // get a handle to the stage
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
