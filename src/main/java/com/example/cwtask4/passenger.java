package com.example.cwtask4;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class passenger extends Application {

    private String firstName;
    private String lastName;
    private String vehicleNumber;
    private int noOfLiters;


    public String getFirstName() {
        return firstName;
    }

    public void setFirst_Name(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getNoOfLiters() {
        return noOfLiters;
    }

    public void setNoOfLiters(int noOfLiters) {
        this.noOfLiters = noOfLiters;
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FuelQueue.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("View Queues");
        stage.setScene(scene);
        stage.show();
    }
}

