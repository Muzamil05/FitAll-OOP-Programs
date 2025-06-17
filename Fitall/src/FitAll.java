import java.util.ArrayList;
import java.util.Scanner;

class Session {
    //Setting private properties
    private int SessionID;
    private String SessionName;
    private String LevelOfFitness;
    private String DayOfWeek;
    private String StartTime;
    private String Duration;
    private int NumOfSpaces;
    private static int idCounter = 1; //create a private static method to auto increment the Session ID for each session

    //Creating a constructor to store the session details.
    public Session(String SessionName, String LevelOfFitness, String DayOfWeek, String StartTime, String Duration, int NumOfSpaces) {
        this.SessionID = idCounter++; //set the sessionID to automatically increment by the static counter
        //Setter methods for the private properties
        this.SessionName = SessionName;
        this.LevelOfFitness = LevelOfFitness;
        this.DayOfWeek = DayOfWeek;
        this.StartTime = StartTime;
        this.Duration = Duration;
        this.NumOfSpaces = NumOfSpaces;
    }

    //Getter methods
    public int getSessionID() {
        return SessionID;
    }
    public String getSessionName() {
        return SessionName;
    }
    public String getLevelOfFitness() {
        return LevelOfFitness;
    }
    public String getDayOfWeek() {
        return DayOfWeek;
    }
    public String getStartTime() {
        return StartTime;
    }
    public String getDuration() {
        return Duration;
    }
    public int getNumOfSpaces() {
        return NumOfSpaces;
    }

    //Method to book a session
    public void BookSession() {
        if (NumOfSpaces > 0) { //This if statement ensures validation so that there are enough spaces for that session.
            NumOfSpaces--; //reduce the number of spaces
            System.out.println("Successfully registered for session " + SessionID);
        }
        else {
            System.out.println("This session is fully booked, please try again Later. "); //Print an error messege if there are no more spaces left.
        }
    }

    //Method to cancel sessions
    public void CancelSession() {
        if (NumOfSpaces >= 0) { //cancel the session if there are 0 or more spaces left.
            NumOfSpaces++; //increase the number of spaces
            System.out.println("Successfully cancelled session " + SessionID);
        }
    }

    //method to print the sessions
    public void printSessions() {
        System.out.printf("%-10d %-12s %-15s %-10s %-10s %10s %14d%n", SessionID, SessionName, LevelOfFitness, DayOfWeek, StartTime, Duration, NumOfSpaces); //each private property for the session class is being passed through for printing the details.
    }

    //Method to print the sessions stored by the user.
    public void printUserSessions() {
        System.out.printf("%-10d %-12s %-15s %-10s %-10s %7s%n", SessionID, SessionName, LevelOfFitness, DayOfWeek, StartTime, Duration); //fstrings ensure that the data is properly displayed and outputted as a table.
    }
}

public class FitAll {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Session[] sessions = { //Creating an array object of Sessions from the Session class to store the sessions for output.
                new Session("Pilates", "Low", "Monday", "13:00", "55", 15), //creating a new object to store the information for each element of the array/
                new Session("Yoga", "Low", "Friday", "18:00", "55", 15),
                new Session("Core", "Medium", "Tuesday", "19:00", "55", 20),
                new Session("Pump", "Medium", "Tuesday", "10:00", "55", 20),
                new Session("Yoga", "Medium", "Wednesday", "12:00", "55", 15),
                new Session("Core", "High", "Thursday", "18:00", "55", 20),
                new Session("Cycling", "High", "Wednesday", "9:00", "55", 10)
        };
        ArrayList<Session> userSessions = new ArrayList<Session>(); //An arraylist is being used to store the user's booked sessions, this is to ensure the users can view the sessions that they have booked.

        int option = 0;
        System.out.println("Welcome to FitAll club!");
        while (option != 4) { //A while loop is being used for data validation to ensure the program keeps repeating itself unless the user enters 4.
            try { //A try... catch is being used to ensure the program does not crash when the user enters an exceptional input, such as a string of random characters.
                System.out.println("Group classes timetable");
                System.out.printf("%-5s %-12s %-15s %-9s %-10s %20s %18s%n", "Session ID", "Session Name", "Level of Fitness", "Day", "Start Time", "Duration (mins)", "Available places");
                for (Session session : sessions) { //A for each loop is being used to loop through each item in the sessions array
                    session.printSessions(); //Call the method to print the sessions from the array.
                }
                System.out.println();
                System.out.println("1. book a group session");
                System.out.println("2. Cancel a session");
                System.out.println("3. View Booked Sessions");
                System.out.println("4. Exit");
                System.out.print("Please enter a choice between 1 and 4: ");

                option = input.nextInt();
                boolean found = false; //A boolean variable is created to ensure the program does not loop for each element until it is found, and immidiately exits the loop.

                switch (option) { //A switch case statement is being used to make sure the user enters an option between 1 and 4.
                    case 1: //Book a Session
                        System.out.print("Enter Session ID to register: ");
                        int sessionID = input.nextInt();
                        for (Session session : sessions) { //loop through each session in the array.
                            if (sessionID == session.getSessionID()) { //Ensure that the sessionID the user has input is the same as the one stored in the class.
                                session.BookSession(); //Call the method to book a session.
                                userSessions.add(session); //Add the session the user has booked to the array of stored user sessions.
                                found = true; //once the item in the array is found, exit the loop.
                            }
                        }
                        if (found == false) { //ensures data validation for a value not in the array, such as an ID greater than 7
                            System.out.println("This session does not exist. Please try again.");
                        }
                    break;
                    case 2: //Cancel a Session
                        if (userSessions.size() == 0) { //Print a messege to say the user has not booked any sessions
                            System.out.println("You have currently not booked any sessions.");
                            break; //breaks from the case statement back to the while loop
                        }
                        System.out.print("Enter Session ID to cancel: ");
                        sessionID = input.nextInt();
                        for (Session session : sessions) {
                            if (sessionID == session.getSessionID()) {
                                if (userSessions.contains(session)) { //This validation ensures that the user has actually booked the session by checking if there is a session stored in the array.
                                    session.CancelSession(); //call the method to cancel the session.
                                    userSessions.remove(session); //Remove the session from the user's stored array.
                                    found = true;
                                }
                                else { //validation if a user enters a session that they have not booked.
                                    System.out.println("You have not booked this session yet, please try again.");
                                }
                            }
                        }
                        if (found == false) { //validation for a value greater than the array of objects
                            System.out.println("This session does not exist. Please try again.");
                        }
                    break;
                    case 3: //View Booked Sessions
                        if (userSessions.size() == 0) {
                            System.out.println("You have currently not booked any sessions.");
                            break;
                        }
                        else {
                            System.out.println("Registered Records: " + userSessions.size());
                            System.out.println("Your booked Sessions:"); //output the details of each session
                            System.out.printf("%-5s %-12s %-15s %-9s %-10s %20s%n", "Session ID", "Session Name", "Level of Fitness", "Day", "Start Time", "Duration (mins)");
                            for (Session session : userSessions) {
                                session.printUserSessions(); //call the method to print each session for the user.
                            }
                            System.out.println();
                        }
                    break;
                    case 4: //Exit the program
                        System.out.println("Thank you for using FitAll! we hope to see you again.");
                    break;
                    default: //Data validation if a user enters an option that is not between 1 and 4.
                        System.out.println("Invalid option, please try again.");
                }
            }
            catch (Exception e) { //catch an exceptional input
                System.out.println("This is an exceptional input, Please enter a valid input.");
                input.nextLine(); //ensures that the program does not infinately loop.
            }
        }
    }
}