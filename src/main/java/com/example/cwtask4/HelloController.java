package com.example.cwtask4;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {


    @FXML
    private TableView<passenger> QueueViewTable;


    @FXML
    protected void searchCustomer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FuelQueue.class.getResource("searchCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Search Fuel Queue");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void queue1Btn(){
        ObservableList<passenger> details= FXCollections.observableArrayList();
        for(int i=0;i<6;i++){
            if(FuelQueue.queues[0][i] != null){
                details.add(FuelQueue.queues[0][i]);
            }
        }
        QueueViewTable.setItems(details);
    }
    @FXML
    protected void queue2Btn(){
        ObservableList<passenger> details= FXCollections.observableArrayList();
        for(int i=0;i<6;i++){
            if(FuelQueue.queues[1][i] != null){
                details.add(FuelQueue.queues[1][i]);
            }
        }
        QueueViewTable.setItems(details);

    }
    @FXML
    protected void queue3Btn(){
        ObservableList<passenger> details= FXCollections.observableArrayList();
        for(int i=0;i<6;i++){
            if(FuelQueue.queues[2][i] != null){
                details.add(FuelQueue.queues[2][i]);
            }
        }
        QueueViewTable.setItems(details);

    }
    @FXML
    protected void queue4Btn(){
        ObservableList<passenger> details= FXCollections.observableArrayList();
        for(int i=0;i<6;i++){
            if(FuelQueue.queues[3][i] != null){
                details.add(FuelQueue.queues[3][i]);
            }
        }
        QueueViewTable.setItems(details);

    }
    @FXML
    protected void queue5Btn(){
        ObservableList<passenger> details= FXCollections.observableArrayList();
        for(int i=0;i<6;i++){
            if(FuelQueue.queues[4][i] != null){
                details.add(FuelQueue.queues[4][i]);
            }
        }
        QueueViewTable.setItems(details);

    }
    @FXML
    protected void waitingBtn(){
        waitingQueue waiting = new waitingQueue();
        ObservableList<passenger> details= FXCollections.observableArrayList();
        for(int i=0;i<5;i++){
            if(waiting.waiting[i] != null){
                details.add(FuelQueue.queues[0][i]);
            }
        }
        QueueViewTable.setItems(details);

    }

    @FXML
    protected void exitBtn(){
        Platform.exit();

    }
}