package com.example.cwtask4;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FuelQueue extends passenger {
    static FuelQueue[][] queues = new FuelQueue[5][6];
    static int stock = 6600;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("""
                -------------------------------------------------------------------
                \t\tFuel Queue Management System
                -------------------------------------------------------------------
                100/VFQ => View all Fuel Queues.
                101/VEQ => View all Empty Queues.
                102/ACQ => Add customer to a Queue.
                103/RCQ => Remove a customer from a Queue.
                104/PCQ => Remove a served customer.
                105/VCS => View Customers Sorted in alphabetical order.
                106/SPD => Store Program Data into file.
                107/LPD => Load Program Data from file.
                108/STK => View Remaining Fuel Stock.
                109/AFS => Add Fuel Stock.
                110/AFQ => Print the income of each queue.
                111/VPD => View passenger details.
                999/EXT => Exit the Program.
                -------------------------------------------------------------------
                Please note that only 10l per vehicle will be sold
                -------------------------------------------------------------------
                                
                """);



        while (true) {
            System.out.println("-------------------------------------------------------------------");
            System.out.print("Enter the number/abbreviation accordingly: ");
            String phrase = input.next();
            System.out.println("-------------------------------------------------------------------");


            if (phrase.equals("100") || phrase.equalsIgnoreCase("VFQ")) {
                viewLine();
            } else if (phrase.equals("101") || phrase.equalsIgnoreCase("VEQ")) {
                viewEmptyLines();
            } else if (phrase.equals("102") || phrase.equalsIgnoreCase("ACQ")) {
                addCustomer();
            } else if (phrase.equals("103") || phrase.equalsIgnoreCase("RCQ")) {
                removeCustomer();
            } else if (phrase.equals("104") || phrase.equalsIgnoreCase("PCQ")) {
                removeServedCustomer();
            } else if (phrase.equals("105") || phrase.equalsIgnoreCase("VCS")) {
                viewCustomerInAlphOrder();
            } else if (phrase.equals("106") || phrase.equalsIgnoreCase("SPD")) {
                storeDataToFile();
            } else if (phrase.equals("107") || phrase.equalsIgnoreCase("LPD")) {
                loadDataFromFile();
            } else if (phrase.equals("108") || phrase.equalsIgnoreCase("STK")) {
                viewRemainingStock();
            } else if (phrase.equals("109") || phrase.equalsIgnoreCase("AFS")) {
                addStock();
            } else if (phrase.equals("110") || phrase.equalsIgnoreCase("IFQ")){
                printIncome();
            } else if (phrase.equals("111") || phrase.equalsIgnoreCase("VPD")){
                launch();
            } else if (phrase.equals("999") || phrase.equalsIgnoreCase("EXT")) {
                System.out.println("Thank you!");
                break;
            } else {
                System.out.println("Invalid input! Please try again");
            }

        }
    }

    static void viewLine() { //Views all the queues
        System.out.println("\t\tThe queue is viewed below");
        for (int i = 0; i < 5; i++) {
            System.out.println("Queue " + (i + 1));
            for (int j = 0; j < 6; j++) {
                if(queues[i][j] == null){
                    System.out.println("empty");
                }
                else {
                    System.out.println(" " + queues[i][j].getFirstName() + " " + queues[i][j].getLastName() + " " + queues[i][j].getVehicleNumber() + " " + queues[i][j].getNoOfLiters() );
                }

            }
            System.out.println();
        }

    }

    static void viewEmptyLines() { //The empty queues will be printed
        int i;
        for (i = 0; i < 5; i++) {
            if (queues[i][0] == null) {
                System.out.println("Queue number " + (i + 1) + " is empty");
            }
            else {
                System.out.println("Queue number " + (i + 1) + " is not empty");
            }
        }
    }

    static int getQueueNumber() {
        int qNum;
        while (true) {
            System.out.print("Enter the queue number: ");
            if (input.hasNextInt()) { //checking whether it's an integer
                qNum = input.nextInt();
                if (qNum < 1 || qNum > 5) {
                    System.out.println("Queue number is not valid!");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid Input!");
                input.next(); //to stop the loop
            }
        }
        return qNum;
    }

    static void addCustomer() { //customer gets added to whichever he/she wants
        if(stock <= 500){
            System.out.println("Warning! Low fuel stock! Available stock is " + stock);
        }
        int maxSpaces = 0;
        int qNum = 0;
        for (int i = 0; i < 5; i++){
            int emptySpaces = 0;
            for (int j = 0; j < 6; j++){
                if (queues[i][j] == null) {
                    emptySpaces += 1;
                }
            }
            if( maxSpaces < emptySpaces){
                maxSpaces = emptySpaces;
                qNum = i + 1;
            }
        }
        if (maxSpaces == 0) {
            System.out.println("""
                    -------------------------------------------------------------------
                    The queues are full""");
            System.out.println("You will get added to the waiting list.");
            FuelQueue customer = new FuelQueue();
            waitingQueue waiting = new waitingQueue();
            System.out.println("Enter the First Name: ");
            customer.setFirst_Name(input.next());
            System.out.println("Enter the Last Name: ");
            customer.setLastName(input.next());
            System.out.println("Enter the Vehicle Number: ");
            customer.setVehicleNumber(input.next());
            System.out.println("Enter the amount of liters required: ");
            customer.setNoOfLiters(input.nextInt());
            waiting.enQueue(customer);
        }
        else {
            input.hasNextInt();
            System.out.println("Enter the First Name: ");
            String name = input.next();
            System.out.println("Enter the Last Name: ");
            String secondName = input.next();
            System.out.println("Enter the Vehicle Number: ");
            String vehicleNumber = input.next();
            System.out.println("Enter the amount of liters required: ");
            int numberOfLitres = input.nextInt();
            for (int i = 0; i < queues[qNum - 1].length; i++) {
                if (queues[qNum-1][i] == null && input.hasNextInt()) {
                    queues[qNum-1][i] = new FuelQueue();
                    queues[qNum-1][i].setFirst_Name(name);
                    queues[qNum-1][i].setLastName(secondName);
                    queues[qNum-1][i].setVehicleNumber(vehicleNumber);
                    queues[qNum-1][i].setNoOfLiters(numberOfLitres);
                    stock -= numberOfLitres;
                    break;
                }
            }
        }
    }

    static void removeCustomer() { //Customer gets removed from the queue
        int qNum = getQueueNumber();
        int positionNum;

        while (true) {
            System.out.print("Enter the position number: ");
            if (input.hasNextInt()) {   //checking whether the position number is an integer
                positionNum = input.nextInt();
                if (positionNum < 1 || positionNum > 6) {
                    System.out.println("Position number is not valid!");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid Input!");
                input.next(); //to stop the loop
            }
        }
        if (queues[qNum - 1][0] == null){
            System.out.println("There are no customers to be removed from the queue");
        }
        else {
            System.out.println(queues[qNum - 1][positionNum - 1].getFirstName() + " is removed from the queue");
            stock += queues[qNum - 1][positionNum - 1].getNoOfLiters();
            queues[qNum - 1][positionNum - 1] = null;

            for (int i = positionNum; i < 6; i++) {
                if (queues[qNum - 1][i - 1] == null) {
                    queues[qNum - 1][i - 1] = queues[qNum - 1][i];
                    queues[qNum - 1][i] = null;
                }
            }
            waitingQueue waiting = new waitingQueue();
            for (int i = 0; i < 6; i++){
                if(queues[qNum - 1][i] == null){
                    queues[qNum - 1][i] = waiting.deQueue();
                }
            }
        }
    }


    static void removeServedCustomer() { //Served customers get removed from the queue
        int qNum = getQueueNumber();
        if(queues[qNum - 1][0] == null){
            System.out.println("Queue is empty");
        }
        else{
            System.out.println(queues[qNum - 1][0].getFirstName() + " is removed from the queue");
            queues[qNum - 1][0] = null;
            for (int i = 1; i < 6; i++) {
                if (queues[qNum - 1][i - 1] == null) {
                    queues[qNum - 1][i - 1] = queues[qNum - 1][i];
                    queues[qNum - 1][i] = null;
                }
            }
            waitingQueue waiting = new waitingQueue();
            for (int i = 0; i < 6; i++){
                if(queues[qNum - 1][i] == null){
                    queues[qNum - 1][i] = waiting.deQueue();
                }
            }
        }
    }

    static void viewCustomerInAlphOrder() {//The names of the customers gets sorted alphabetically
        FuelQueue[][] reArrangedQueue = new FuelQueue[5][6];
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 6; j++ ){
                reArrangedQueue[i][j] = (queues[i][j]);
                if(reArrangedQueue[i][j] == null){
                    reArrangedQueue[i][j] = new FuelQueue();
                    reArrangedQueue[i][j].setFirst_Name("|");
                }
            }
        }
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 6; j++){ //bubble sort
                for (int x = 1; x < 6 - j; x++) {
                    if ((reArrangedQueue[i][x - 1].getFirstName().compareToIgnoreCase(reArrangedQueue[i][x].getFirstName())) > 0) {
                        FuelQueue tempElement = reArrangedQueue[i][x - 1]; //the 1st element is assigned to a temporary variable
                        reArrangedQueue[i][x - 1] = reArrangedQueue[i][x]; //next element is assigned to the 1st element
                        reArrangedQueue[i][x] = tempElement; //temporary element is assigned to the next element
                    }
                }
            }
        }
        System.out.println("The sorted queue is viewed below");
        for (int i = 0; i < 5; i++) {
            System.out.print("Queue " + (i + 1) + " => ");
            for (int j = 0; j < 6; j++) {
                if(reArrangedQueue[i][j].getFirstName().equals("|")){ //|'s ASCII value is larger than any letter of the alphabet
                    System.out.print(" empty");
                }
                else {
                    System.out.print(" " + reArrangedQueue[i][j].getFirstName());
                }
            }
            System.out.println(); //skipping a line
        }
    }

    static void storeDataToFile() { //Data gets stored into data.txt file
        try{
            FileWriter writer = new FileWriter("data.txt");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    if(queues[i][j] == null){
                        writer.write("empty" + "\n");
                    }
                    else {
                        writer.write(queues[i][j].getFirstName() + "\n");
                        writer.write(queues[i][j].getLastName() + "\n");
                        writer.write(queues[i][j].getVehicleNumber() + "\n");
                        writer.write(queues[i][j].getNoOfLiters() + "\n");
                    }
                }
            }
            writer.close();
            System.out.println("Data was successfully stored");
        }
        catch (IOException e){
            System.out.println("Data couldn't be stored.");
        }
    }

    static void loadDataFromFile() { //The stored data gets loaded back
        try{
            File text = new File("data.txt");
            Scanner read = new Scanner(text);
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 6; j++){
                    if(read.hasNextLine()){
                        String readingLine = read.nextLine();
                        if(readingLine.equals("empty")){
                            queues[i][j] = null;
                        }
                        else {
                            queues[i][j] = new FuelQueue();
                            queues[i][j].setFirst_Name(readingLine);
                            queues[i][j].setLastName(read.nextLine());
                            queues[i][j].setVehicleNumber(read.nextLine());
                            queues[i][j].setNoOfLiters(Integer.parseInt(read.nextLine()));
                        }
                    }
                }
            }
            read.close();
            System.out.println("Data was loaded successfully.");
        }
        catch(IOException e){
            System.out.println("File cannot be read.");
        }
    }

    static void viewRemainingStock() { //prints the remaining fuel stock
        System.out.println("The remaining stock is " + stock);
    }

    static void addStock() { //adds stock to the petrol shed
        System.out.print("How much fuel do you want to add to the stock? ");
        int newStock = input.nextInt();
        stock += newStock;
        System.out.println("Stock got added successfully");
    }

    static void printIncome(){
        int litrePrice = 430;
        int cost = 0;
        for (int i = 0; i < 5; i++){
            System.out.print("The income got from queue  " + (i + 1));
            for (int j = 0; j < 6; j++){
                if(queues[i][j] == null){
                    cost += 0;
                }
                else{
                    cost += litrePrice * queues[i][j].getNoOfLiters();
                }
            }
            System.out.println(" => " + cost);
            cost = 0;
        }
    }

}

