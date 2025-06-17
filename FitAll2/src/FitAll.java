import java.util.ArrayList;
import java.util.Scanner;

//Bookable interface containing the book and cancel methods
interface Bookable {
    void book(int SessionID, String participantName);
    void cancel(int SessionID, String participantName);
}

//abstract class session implementing bookable
abstract class Session implements Bookable {
    protected int SessionID; //protected is being used here to allow access for subclasses.
    protected String SessionName;
    protected String RequiredFitnessLevel;
    protected String Day;
    protected String StartTime;
    protected String Duration;
    protected int SpacesLeft;
    protected static int IdCounter = 1; //A static method automatically generates a value for the SessionID

    //Initialise session details with a constructor
    public Session(String SessionName, String RequiredFitnessLevel, String Day, String StartTime, String Duration, int SpacesLeft) {
        this.SessionID = IdCounter++; //Increment the sessionID automatically
        this.SessionName = SessionName;
        this.RequiredFitnessLevel = RequiredFitnessLevel;
        this.Day = Day;
        this.StartTime = StartTime;
        this.Duration = Duration;
        this.SpacesLeft = SpacesLeft;
    }

    //method to display session details
    public void showDetails() {
        System.out.printf("%-10s | %-11s | %-13s | %-10s | %-11s | %-13s | %-10s %n", SessionID, SessionName, RequiredFitnessLevel, Day, StartTime, Duration, SpacesLeft);
    }

    //method to update spaces when a session is booked
    public void updateSpaces (){
        if (SpacesLeft > 0){
            SpacesLeft--; //decrement the spaces available
            System.out.println("Remaining Spaces: " + SpacesLeft);
        }
    }
}

//Yogasession subclass
class YogaSession extends Session { //Inheritance is being used here to inherit the properties and methods from the Session class, being an example of Polymorphism
    private String YogaStyle; //Only this class needs to access the private properties, hence why they are private and not protected
    private String MeditationTime;

    //Constructor to initialise the Yoga Session.
    public YogaSession(String SessionName, String RequiredFitnessLevel, String Day, String StartTime, String Duration, int SpacesLeft) {
        super(SessionName, RequiredFitnessLevel, Day, StartTime, Duration, SpacesLeft); //A superclass is being used to store the common attributes between each session
        this.YogaStyle = "Hatha"; //Set the Yogastyle and meditation time
        this.MeditationTime = "10 min";
    }

    //Use method overriding to override the book method from bookable
    @Override
    public void book(int SessionID, String participantName) {
        if (SpacesLeft > 0){ //Book a session if there are still spaces left
            System.out.println("Registration Successful! " + participantName + " has been added to Yoga (Session ID: " + SessionID + ")");
            updateSpaces(); //call the updateSpaces method
        }
    }

    //Override the cancel method from bookable
    @Override
    public void cancel(int SessionID, String participantName) {
        if (SpacesLeft >= 0){ //Make sure that the spaces left on the session is greater than or equal to 0 for error handling.
            SpacesLeft++; //increment the spaces available
            System.out.println("Successfully cancelled session " + SessionID + " for participant " + participantName);
        }
    }

    //Method overriding to show the Yoga Session details.
    @Override
    public void showDetails() {
        System.out.printf("%-10s | %-11s | %-13s | %-10s | %-11s | %-13s | %-16s  | Style: %-1s, Meditation: %-1s%n", SessionID, SessionName, RequiredFitnessLevel, Day, StartTime, Duration, SpacesLeft, YogaStyle, MeditationTime);
    }
}

//Pilate Session subclass.
class PilateSession extends Session {
    //Private properties
    private String EquipmentNeeded;
    private String CoreFocusLevel;

    //Pilate Session constructor
    public PilateSession(String SessionName, String RequiredFitnessLevel, String Day, String StartTime, String Duration, int SpacesLeft) {
        super(SessionName, RequiredFitnessLevel, Day, StartTime, Duration, SpacesLeft);
        this.EquipmentNeeded = "Mat";
        this.CoreFocusLevel = "Beginner";
    }

    //Override the book method
    @Override
    public void book(int SessionID, String participantName) {
        if (SpacesLeft > 0){
            System.out.println("Registration Successful! " + participantName + " has been added to Pilates (Session ID: " + SessionID + ")");
            updateSpaces();
        }
    }

    //Override the cancel method
    @Override
    public void cancel(int SessionID, String participantName) {
        if (SpacesLeft >= 0){
            SpacesLeft++;
            System.out.println("Successfully cancelled session " + SessionID + " for participant " + participantName);
        }
    }

    //Show the details of the pilate session
    @Override
    public void showDetails() {
        System.out.printf("%-10s | %-11s | %-13s | %-10s | %-11s | %-13s | %-16s  | Equipment: %-1s, Focus: %-1s%n", SessionID, SessionName, RequiredFitnessLevel, Day, StartTime, Duration, SpacesLeft, EquipmentNeeded, CoreFocusLevel);
    }
}

//Zumba Session subclass
class ZumbaSession extends Session {
    //Private properties
    private String DanceStyle;
    private String EnergyLevel;

    //Zumba Session Constructor
    public ZumbaSession(String SessionName, String RequiredFitnessLevel, String Day, String StartTime, String Duration, int SpacesLeft) {
        super(SessionName, RequiredFitnessLevel, Day, StartTime, Duration, SpacesLeft);
        this.DanceStyle = "Salsa";
        this.EnergyLevel = "300 cal";
    }

    //Override the book method
    @Override
    public void book(int SessionID, String participantName) {
        if (SpacesLeft > 0) {
            System.out.println("Registration Successful! " + participantName + " has been added to Zumba (Session ID: " + SessionID + ")");
            updateSpaces();
        }
    }

    //Override the cancel method
    @Override
    public void cancel(int SessionID, String participantName) {
        if (SpacesLeft >= 0) {
            SpacesLeft++;
            System.out.println("Successfully cancelled session " + SessionID + " for participant " + participantName);
        }
    }

    //Show the details for the Zumba Session
    @Override
    public void showDetails() {
        System.out.printf("%-10s | %-11s | %-13s | %-10s | %-11s | %-13s | %-16s  | Dance: %-1s, Energy: %-1s%n", SessionID, SessionName, RequiredFitnessLevel, Day, StartTime, Duration, SpacesLeft, DanceStyle, EnergyLevel);
    }
}

//Main Fitall class
public class FitAll {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Session[] sessions = { //Create an array to store all sessions
                new YogaSession("Yoga","Low","Monday","10:00 AM", "60 min", 10), //Store the details for the Yoga Session
                new PilateSession("Pilates","Medium","Wednesday","2:00 PM","45 min",1), //Store the details for the Pilate Session
                new ZumbaSession("Zumba","High","Friday","6:00 PM", "50 min", 15) //Store the details for the Zumba Session
        };

        ArrayList<String> sessionList = new ArrayList<>(); //An arraylist is being used to store the sessions and the name of the user.

        int option = 0;
        while (option != 5) { //Loop infinitely until the user exits the program
            try {
                System.out.println();
                System.out.println("Welcome to FitAll! Please select your option between 1 and 5:");
                System.out.println("1. Display session timetable");
                System.out.println("2. Register for a session");
                System.out.println("3. Cancel a session");
                System.out.println("4. Show Booked Sessions");
                System.out.println("5. Exit");
                option = input.nextInt();

                boolean found = false; //Used to make sure that the program does not loop for each element in a for loop in data validation, and immidiately exits the loop.
                boolean check = false; //Additional validation
                String participantName = ""; //initialise the participant name
                int SessionID = 0; //initialise the sessionID

                //Switch case statement for each option
                switch (option) {
                    case 1: //Display Session timetable
                        System.out.printf("%-5s %-12s %-15s %-9s %-10s %-15s %10s %18s%n", "Session ID |", "SessionName |", "Fitness Level |", "Day", " | Start Time", " | Duration", " | Available places"," | Additional Info");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
                        for (Session session : sessions) { //A foreach loop is being used to loop through each session stored in the array and display its details
                            session.showDetails(); //call the showDetails() method
                        }
                    break;
                    case 2: //Register for a Session
                        input.nextLine();
                        while (!check) { //validation check for strings
                            System.out.println("Enter Participant Name (must be less than 12 characters):");
                            participantName = input.nextLine().toLowerCase(); //Convert inputs to lowercase for error handling, making sure the name is correct

                            //A presence check is being used so that the user does not leave this value empty.
                            if (participantName.isEmpty()) {
                                System.out.println("ERROR: Participant Name cannot be empty, please try again");
                            }
                            else if (participantName.length() >= 12) { //Length check to make sure the name is less than 12 characters
                                System.out.println("ERROR: Participant Name is too long, please enter a name less than 12 characters.");
                            }
                            else {
                                check = true; //exit the if else statements when input is correct
                            }
                        }

                        while (!found) {
                            try { //use a try catch in case the user enters an exceptional input
                                System.out.println("Enter SessionID:");
                                SessionID = input.nextInt();

                                for (Session session : sessions) {
                                    if (SessionID == session.SessionID) { //make sure that the session ID the user enters matches the session ID from the table
                                        if (session.SpacesLeft > 0) {  //Book a session if there are still spaces left
                                            session.book(SessionID, participantName); //Book the session
                                            sessionList.add(participantName + ":" + SessionID); //add the participant and the sessionID to the arraylist, both are being added to ensure that the user can cancel the correct session assigned to their ID and name
                                        }
                                        else { //Error handling if the user tries to book a session that is full
                                            System.out.println("ERROR: Session is full! Cannot register " + participantName + " for Pilates (Session ID: " + SessionID + ")");
                                        }
                                        found = true; //Exit the if else statements
                                    }
                                }
                                if (!found) { //error handling if the user enters a value less than 0 or greater than 3 for the Session ID
                                    System.out.println("ERROR: Invalid SessionID, please try again");
                                }
                            }
                            catch (Exception e) { //Exceptional input validation
                                System.out.println("ERROR: This is an exceptional input, Please enter a valid SessionID.");
                                input.nextLine();
                            }
                        }
                    break;
                    case 3: //Cancel a Session
                        if (sessionList.isEmpty()) { //Error message to appear it the user has not booked any sessions by checking if the arraylist is empty.
                            System.out.println("ERROR: You have not booked any sessions! please try again");
                            break;
                        }

                        input.nextLine();
                        while (!check) { //string validation
                            System.out.println("Enter Participant Name:");
                            participantName = input.nextLine().toLowerCase();

                            //participant name presence check
                            if (participantName.isEmpty()) {
                                System.out.println("ERROR: Participant Name cannot be empty, please try again");
                            }
                            else {
                                check = true;
                            }
                        }

                        while (!found) { //ID Validation
                            try {
                                System.out.println("Enter SessionID:");
                                SessionID = input.nextInt();

                                for (Session session : sessions) {
                                    if (SessionID == session.SessionID) { //make sure the user input matches a session ID
                                        if (sessionList.contains(participantName +":"+ SessionID)) { //make sure that the arraylist contains the participant name and sessionID
                                            session.cancel(SessionID, participantName); //cancel the session using the cancel method
                                            sessionList.remove(participantName +":"+ SessionID); //remove the participant name and sessionID from the arraylist
                                            found = true; //exit the loop
                                        }
                                        else {
                                            System.out.println("ERROR: This participant has not booked this session, please try cancelling again"); //display an error message if the user puts the wrong participant name or ID
                                            found = true;
                                        }
                                    }
                                }
                                if (!found) {
                                    System.out.println("ERROR: Invalid SessionID, please try again"); //error message if the session ID is above the range
                                }
                            }
                            catch (Exception e) { //exceptional input
                                System.out.println("ERROR: This is an exceptional input, Please enter a valid SessionID.");
                                input.nextLine();
                            }
                        }
                    break;
                    case 4: //View Booked Sessions
                        if (sessionList.isEmpty()) { //Print an error message if the user has not booked any sessions
                            System.out.println("ERROR: You have not booked any sessions! please try again");
                            break;
                        }

                        System.out.println("Booked Sessions:");
                        System.out.printf("%-12s | %-12s%n","Session ID", "Participant"); //show the participant name and the session ID of the session the user booked.
                        System.out.println("------------------------------------");
                        for (String session : sessionList) {
                            System.out.printf("%-12s | %-12s%n",session.split(":")[1],session.split(":")[0]); //Split the name and ID from the variable as two substrings
                        }
                    break;
                    case 5: //Exit the program
                        System.out.println("Thank you for using FitAll! we hope to see you again.");
                    break;
                    default: //Display an error message if the user enters a value outside the range for the option switch case statement
                        System.out.println("ERROR: Invalid Option! please enter a number between 1 and 5");
                    break;
                }
            }
            catch (Exception e) { //use a try... catch to catch an exceptional input, such as a string instead of an integer.
                System.out.println("ERROR: This is an exceptional input, Please enter a valid input.");
                input.nextLine(); //ensures the program loops back again
            }
        }
    }
}