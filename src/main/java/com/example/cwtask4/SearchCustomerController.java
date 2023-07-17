package com.example.cwtask4;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SearchCustomerController {

    @FXML
    private Pane detailsPane;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtFirst;

    @FXML
    private TextField txtLast;

    @FXML
    private TextField txtVehicle;

    @FXML
    private TextField txtLitres;

    @FXML
    protected void searchBtn(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 6; j++){
                if (FuelQueue.queues[i][j] != null){
                    if (FuelQueue.queues[i][j].getVehicleNumber().equalsIgnoreCase(txtSearch.getText())){
                        detailsPane.setVisible(true);
                        txtFirst.setText(FuelQueue.queues[i][j].getFirstName());
                        txtLast.setText(FuelQueue.queues[i][j].getLastName());
                        txtVehicle.setText(FuelQueue.queues[i][j].getVehicleNumber());
                        txtLitres.setText(String.valueOf(FuelQueue.queues[i][j].getNoOfLiters()));
                    }
                }
            }
        }
    }

}
