import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Control extends Driver{

    private ReadJson read = new ReadJson(jsonFile);

    private Character entrance = read.getEntrance();
    private Character exit = read.getExit();
    private ArrayList<Deque> arrOfDeque = read.getArrOfDeque();
    private ArrayList<Integer> numOfChair = read.getNumOfChair();
    private ArrayList<Deque> copyArrOfDeque = read.getCopyArrOfDeque();

    public void displayCinema() {
        System.out.println("                  THE CINEMA VIEW                   ");
        System.out.println("----------------------------------------------------");
        System.out.println();
        for (int i = 0; i < arrOfDeque.size(); i++) {
            Deque row = arrOfDeque.get(i);

            System.out.print("||" + "  ");
            for (int j = 0; j < row.size(); j++) {
                System.out.print(row.get(j) + "  " + "||" + "  ");
            }
            System.out.println();
        }
    }

    public void copyDisplayCinema() {
        System.out.println("                  THE CINEMA VIEW                   ");
        System.out.println("----------------------------------------------------");
        System.out.println();
        for (int i = 0; i < copyArrOfDeque.size(); i++) {

            Deque row = copyArrOfDeque.get(i);

            System.out.print("||" + "  ");
            for (int j = 0; j < row.size(); j++) {
                System.out.print("null" + "  " + "||" + "  ");
            }
            System.out.println();
        }
    }

    public void insertCustomer(int place, char name) {
        Deque selectedRow = arrOfDeque.get(place - 1);

        if(entrance.equals('r')) {
            selectedRow.insertRear(name);
        } else if (entrance.equals('l')) {
            selectedRow.insertFront(name);
        }
    }

    public void copyInsertCustomer(int place, char name) {
        Deque copySelectedRow = copyArrOfDeque.get(place - 1);
        if(entrance.equals('r')) {
            if(entrance.equals('r')) {
                copySelectedRow.insertRear(name);
            } else if (entrance.equals('l')) {
                copySelectedRow.insertFront(name);
            }
        }
        else {
            if(entrance.equals('r')) {
                copySelectedRow.insertFront(name);
            } else if (entrance.equals('l')) {
                copySelectedRow.insertRear(name);
            }
        }
    }

    public void copyOrigDeque(){
        for(int i = 1; i<arrOfDeque.size() + 1; i++) {

            Deque row = arrOfDeque.get(i - 1);

            for (int j = 0; j < row.size(); j++){
                Character copyName = row.get(j);
                if (copyName != null){
                    copyInsertCustomer(i, copyName);
                }
            }
        }
    }

    public void exitPlan() {

        String orderOfExit = "";
        Integer maximumCap = Collections.max(numOfChair);

        if(exit.equals('r')) {
            for (int i = 0; i < maximumCap; i++) {
                for (int j=0; j<copyArrOfDeque.size(); j++ ){
                    Deque selectedRow = copyArrOfDeque.get(j);

                    if(selectedRow.isEmpty()){
                    }
                    else{
                        orderOfExit += (char) selectedRow.getRear();
                        selectedRow.deleteRear();
                    }
                }
                System.out.println(orderOfExit);
                orderOfExit = "";
            }
        }

        if(exit.equals('l')){
            for (int i = 0; i < maximumCap; i++) {
                for (int j=0; j<copyArrOfDeque.size(); j++ ){
                    Deque selectedRow = copyArrOfDeque.get(j);

                    if(selectedRow.isEmpty()){
                    }
                    else{
                        orderOfExit += (char) selectedRow.getFront();
                        selectedRow.deleteFront();
                    }
                }
                System.out.println(orderOfExit);
                orderOfExit = "";
            }
        }
    }

    //adding customer also with the case of overflow
    public void auxiliaryMethod(int place, char name, Scanner keyboardInput){
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);

        PrintStream old = System.out; // IMPORTANT: Save the old System.out!
        System.setOut(ps); // Tell Java to use your special stream

        //Print some output: goes to your special stream
        insertCustomer(place, name);

        System.out.flush(); // Put things back
        System.setOut(old);
        // Show what happened
        System.out.println(baos.toString());

        String expectedOutput = "Sorry, that row is full.";
        if (expectedOutput.equals(baos.toString())){
            System.out.print("Choose another one: ");
            place = keyboardInput.nextInt();
            System.out.println();
            insertCustomer(place, name);
        }
    }

    void cinemaSetup(){
        System.out.println("\nCINEMA SETUP");
        System.out.println("Entrance: " + entrance);
        System.out.println("Exit: " + exit);
        System.out.println("Rows: " + numOfChair);
    }

    void startMenuProgram() {

        cinemaSetup();

        System.out.println("\n[1] Input a customer");
        System.out.println("[2] Print exit plan");
        System.out.println("[3] Exit");

        System.out.print("\nAnswer: ");
        try{
        Scanner keyboardInput = new Scanner(System.in);
        int option = keyboardInput.nextInt();

        switch (option) {
            case 1 -> {
                System.out.println("\nWhat is the customer's name? ");
                System.out.print("Name: ");

                String nameIn = keyboardInput.next();

                for (int j = 0; j < numOfChair.size(); j++) {
                    for (int k = 0; k < arrOfDeque.size(); k++) {
                        Deque row = arrOfDeque.get(k);
                        while(row.contains(nameIn.charAt(0))) {
                            System.out.println("Invalid input, same name. Please try again!");
                            System.out.print("Enter customer name: ");
                            nameIn = keyboardInput.next();
                        }
                    }
                }
                //duplicateNameChecker(nameIn, keyboardInput);
                Character name = nameIn.charAt(0);

                if (Character.isLetter(name)) {
                    System.out.println("\nWhat is the customer's row? ");
                    displayCinema();
                } else {
                    System.out.println("\nPlease input a letter for name. (a-z, A-Z)");
                    startMenuProgram();
                }

                System.out.print("\nRow: ");
                try {
                    Integer place = keyboardInput.nextInt();
                    auxiliaryMethod(place, name, keyboardInput);
                    displayCinema();
                } catch (Exception e) {
                    System.out.println("Wrong input");
                    System.out.println("Please enter a number row. (+integer)");
                }
            }
            case 2 -> {
                System.out.println("Exit plan:");
                copyOrigDeque();
                exitPlan();
                System.out.print("Do you want to proceed exit? (y or n): ");
                String answer = keyboardInput.next();
                if (answer.equals("y")) {
                    copyDisplayCinema();
                    System.exit(0);
                }
                else if (answer.equals("n")){
                }
                else
                    System.out.println("Wrong input, choose again");
            }

            case 3 -> System.exit(0);

            default -> {
                System.out.println("Invalid choice");
                startMenuProgram();
            }
        }
        }catch(Exception e) {
            System.out.println("Wrong input! Enter again.");
            startMenuProgram();
        }
            startMenuProgram();
    }
}
