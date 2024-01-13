import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;

public class ReadJson extends Driver{

    private String jsonFile;
    private Character entrance;
    private Character exit;

    private ArrayList<Deque> arrOfDeque = new ArrayList<Deque>(); //create to store each deque in an arraylist
    private ArrayList<Deque> copyArrOfDeque = new ArrayList<Deque>(); //create a copy deque
    private ArrayList<Integer> numOfChair = new ArrayList<Integer>();//create an arraylist where to store each rows

    public ReadJson(String jsonFile) {
        this.jsonFile = jsonFile;
        Read();
    }

    public void Read() {
        JSONParser jsonparser = new JSONParser();

        try (FileReader reader = new FileReader(jsonFile)) {
            JSONObject generatedJson = (JSONObject) jsonparser.parse(reader);

            String entranceString = (String) generatedJson.get("entrance");
            String exitString = (String) generatedJson.get("exit");

            entrance = entranceString.charAt(0);
            exit = exitString.charAt(0);

            JSONArray arrayRows = (JSONArray) generatedJson.get("rows");

            //get the length of the array of rows
            //stores the number of each rows in an array
            for (int i = 0; i < arrayRows.size(); i++) {
                numOfChair.add((int) (long) arrayRows.get(i)); //[5, 5, 5]
            }

            //create and store each deque in an arraylist for each row
            for (int i = 0; i < numOfChair.size(); i++) {
                Integer rowCap = numOfChair.get(i);
                Deque row = new Deque(rowCap);
                arrOfDeque.add(row);   //[deque, deque, deque]
            }

            //create and store each deque in an arraylist for each row
            for (int i = 0; i < numOfChair.size(); i++) {
                Integer rowCap = numOfChair.get(i);
                Deque row = new Deque(rowCap);
                copyArrOfDeque.add(row);   //[deque, deque, deque]
            }

        } catch (FileNotFoundException e) {
            System.out.println("No File name found, please input again.\n");
            Driver.main(null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Character getEntrance() {
        return entrance;
    }

    public Character getExit() {
        return exit;
    }

    public ArrayList<Deque> getArrOfDeque() {
        return arrOfDeque;
    }

    public ArrayList<Deque> getCopyArrOfDeque() {
        return copyArrOfDeque;
    }

    public ArrayList<Integer> getNumOfChair() {
        return numOfChair;
    }
}