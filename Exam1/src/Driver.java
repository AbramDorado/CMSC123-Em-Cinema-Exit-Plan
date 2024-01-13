import java.util.Scanner;

//The Driver of the Exit Plan Program
public class Driver {
    public static String jsonFile;

    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);
        System.out.println("DORADO ABRAM EXIT PLAN");

        System.out.println("[1] Enter Desired Json File Name");
        System.out.println("[2] Use Default Json File");
        System.out.print("Choose: ");
        int option = keyboardInput.nextInt();

        switch(option){
            case 1 ->{
                System.out.print("Enter File Name: ");
                jsonFile = keyboardInput.next();
                Control cinema = new Control();
                cinema.startMenuProgram();
            }
            case 2 ->{
                jsonFile = "generated.json";
                Control cinema = new Control();
                cinema.startMenuProgram();
            }
        }

    }
}