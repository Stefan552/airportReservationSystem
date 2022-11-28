package commandsAndMessages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Messages {
    public static String cannotAddUserPasswordDiff(){
        return "Cannot add user! The passwords are different!";
    }

    public static String cannotFindUserWithEmail(String email){
        return "Cannot find user with email: " + email;
    }

    public static String cannotAddUserPasswordTooShort(){return "Cannot add user! Password too short!";}

    public static String incorrectPassword(){return "Incorrect password!";}

    public static String anotherUserIsAlreadyConnected(){return "Another user is already connected!";}

    public static String userAlreadyExists(){return "User already exists!";};

    public static String userWithEmail(){return "User with email: ";}

    public static String successfullyAdded(){return " was successfully added!"; }

    public static String currentUser2() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

     return  " is the current user started from " + dtf.format(now);
    }

    public static String wasNotConnected(){return " was not connected!";}

    public static String wasSuccesfullyDisc() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return  " was successfully disconnected at  " + dtf.format(now);
    }

    public static String canAnotherPersonLogIN(){return "Nobody is logged-in !You may try!";}

    public static String userLog(){return "The logged-in User is : ";}

    public static String thereIsNo(){return "There is no connected user!";}

    public static String theFlightWithId(){return "The flight with id ";}

    public  static String doesNotExist(){return " does not exist!";}

    public static  String alreadyHaveTicket(){return " already have a ticket for  flight with id: ";}

    public static String wasSuccesfullyAdded(){return " was succesfully added for user with email ";}

    public static String doesNotHaveTicket(){ return " does not have a ticket for the flight with id ";}

    public static String succesfullyCanceled(){return  " has successfully canceled his ticket for flight with id ";}

    public static String cannotAddTicket(){return "Cannot add flight! There is already a flight with id  ";}

    public static String theFlightFrom(){return  "Flight from ";}

    public static String succesfullyDeleted(){return " succesfully deleted! ";    }

    public static String wasNotifiedAboutCancellation(){return " was notified that the flight with id ";}

    public static String wasCanceled(){return " was canceled! ";    }

    public static String databaseFlightsSaved() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return "The flights was successfully saved in the database at "+dtf.format(now);
    }

    public static String databaseUsersSaved() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return "The users was successfully saved in the database at "+dtf.format(now);
    }
}
