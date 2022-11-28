package logicUtils;


import dataUsersFlights.FileInfo;
import dataUsersFlights.Flight;
import dataUsersFlights.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static commandsAndMessages.Messages.*;



public class AirLineManager {

    private WriterManager writerManager = new WriterManager(FileInfo.createWriter());

    private List<User> allUsers = new ArrayList<>();
    private User currentUser;

    public List<User> getAllUsers() {
        return allUsers;
    }

    public List<Flight> getAllFlights() {
        return allFlights;
    }

    public List<Flight> allFlights = new ArrayList<>();

    public void signUp(String[] arguments) {
        String email = arguments[1];
        String name = arguments[2];
        String password = arguments[3];
        String password2 = arguments[4];
        User user = new User(email, name, password);
        if (allUsers.contains(user)){
            writerManager.write(userAlreadyExists());
            writerManager.flush();
        }
        else if(!password.equals(password2)){
           writerManager.write(cannotAddUserPasswordDiff());
           writerManager.flush();

        }else if(password.length() < 8){
            writerManager.write(cannotAddUserPasswordTooShort());
           writerManager.flush();
        }else{

            allUsers.add(user);
           writerManager.write(userWithEmail() + user.getEmail() +successfullyAdded());
           writerManager.flush();

    }


    }
    // TODO: 31.10.2022 metodaSignUp Done+correctFunctionabil

    public void login(String[] arguments) {
        String email = arguments[1];
        String pass = arguments[2];

        Optional<User> optionalUser = allUsers.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();

        if(optionalUser.isEmpty()){
           writerManager.write(cannotFindUserWithEmail(email));
           writerManager.flush();

            return;
        }

        User user = optionalUser.get();
        if(!user.getParola().equals(pass)){
           writerManager.write(incorrectPassword());
writerManager.flush();
            return;
        }

        if(currentUser != null){

            writerManager.write(anotherUserIsAlreadyConnected());
writerManager.flush();
            return;
        }
        currentUser = user;
        writerManager.write(userWithEmail()+email+currentUser2());
        writerManager.flush();
    }
    // TODO: 01.11.2022 metodaLogin Done+correctFunctionabil

    public void logout(String[] arguments){
        String email = arguments[1];
        if (!currentUser.getEmail().equalsIgnoreCase(email)){
            writerManager.write(userWithEmail()+email+wasNotConnected());
            writerManager.flush();
        }else{
            writerManager.write(userWithEmail()+email+wasSuccesfullyDisc());
            writerManager.flush();
            currentUser=null;
        }
        if (!(currentUser ==null)){
            writerManager.write(userLog()+currentUser);
            writerManager.flush();
        }
        if (currentUser==null){
            writerManager.write(canAnotherPersonLogIN());
            writerManager.flush();
        }







        //TODO sa facem null userul curent daca sunt verificate toate cerintele

    }
    // TODO: 01.11.2022 metodaLogout Done+correctFunctionabil

    public void addFlight(String[] arguments) {
        Integer ido= Integer.valueOf(arguments[1]);

        Optional<Flight> flightStream = allFlights.stream()
                .filter(n -> n.getId()==Integer.parseInt(arguments[1]))
                .findFirst();

        if (currentUser == null) {
            writerManager.write(thereIsNo());
            writerManager.flush();
        }
        else if (flightStream.isEmpty()){
            writerManager.write(theFlightWithId()+arguments[1]+doesNotExist());
            writerManager.flush();
        }

        if (!flightStream.isEmpty()) {
            Flight fluge=flightStream.get();
            for (User user:allUsers){
                if (user.getUserFlights().contains(fluge)){
                    String email = user.getEmail();
                    writerManager.write(userWithEmail()+email  + alreadyHaveTicket() + arguments[1]);
                    writerManager.flush();

                }
            }


            }else {

            Flight flight = new Flight();
            allFlights.add(flight);
            flight.setId(Integer.parseInt(arguments[1]));
            currentUser.addFlight(flight);
            writerManager.write(theFlightWithId()+flight.getId()+wasSuccesfullyAdded()+ currentUser.getEmail());
            writerManager.flush();
        }



            }
    // TODO: 01.11.2022 metodaAddFlight Done+correctFunctionabil

    public  void display_my_flights(String[] arguments){
String name=arguments[1];

    if (currentUser == null) {
        writerManager.write(thereIsNo());
        writerManager.flush();
    } else if (currentUser.getName().equals(name)) {
        writerManager.write(String.valueOf(currentUser.getUserFlights()));
        writerManager.flush();
    }


    }
    // TODO: 01.11.2022 metodaDisplay_my_flights Done+correctFunctionabil

    public void cancel_flight(String[]arguments) {
        String id = arguments[1];

        Optional<Flight> flightOptional = allFlights.stream()
                .filter(n -> n.getId()==Integer.parseInt(arguments[1]))
                .findFirst();
        if (currentUser == null) {
            writerManager.write(thereIsNo());
            writerManager.flush();
        } else if (flightOptional.isEmpty()){
            writerManager.write(theFlightWithId()+arguments[1]+doesNotExist());
            writerManager.flush();
        }
        if (!(currentUser ==null)){
            if (!flightOptional.isEmpty()) {
                Flight fluge=flightOptional.get();
                for (User user:allUsers){
                    if (!user.getUserFlights().contains(fluge)){
                        String email = user.getEmail();
                        writerManager.write(userWithEmail()+email  + doesNotHaveTicket() + arguments[1]);
                        writerManager.flush();

                    }else {
                        currentUser.userFlights.remove(fluge);
                        allFlights.remove(fluge);
                        writerManager.write(userWithEmail()+currentUser.getEmail()+succesfullyCanceled()+arguments[1]);
                    }
                }


            }
        }
    }
    // TODO: 01.11.2022 metodaCancel_flight Done+correctFunctionabil

    public void add_flight_details(String[] arguments) {
        Integer id = Integer.valueOf(arguments[1]);
        String from = arguments[2];
        String to = arguments[3];
        String dateyear=arguments[4];
        String datemonth=arguments[5];
        String dateday=arguments[6];
StringBuilder datename= new StringBuilder("/"+dateyear+"-"+ datemonth+"-" + dateday+"/");
        Integer duration = Integer.valueOf(arguments[7]);
        Flight flight = new Flight(id, from, to,datename, duration);
        Optional<Flight> details = allFlights.stream()
                .filter(n -> n.getId()==Integer.parseInt(arguments[1]))
                .findFirst();

        if (!details.isEmpty()) {
writerManager.write(cannotAddTicket()+arguments[1]);
writerManager.flush();
            }else{
            allFlights.add(flight);
            currentUser.addFlight(flight);
            writerManager.write(theFlightFrom()+arguments[2]+" to "+arguments[3]+" at date "+datename
            +" wit duration "+arguments[7]+" minutes "+successfullyAdded());
            writerManager.flush();
        }


        }

    // TODO: 01.11.2022 metodaAdd_flight_details Done+correctFunctionabil

    public void deleteFlight(String[] arguments) {
        Integer id= Integer.valueOf(arguments[1]);
        Optional<Flight> optionalFlight = allFlights.stream()
                .filter(flight -> flight.getId() == Integer.parseInt(arguments[1]))
                .findFirst();
        if (optionalFlight.isEmpty()) {
            writerManager.write(theFlightWithId()+ arguments[1] + doesNotExist());
            writerManager.flush();
        }else {

            Flight flight = optionalFlight.get();
            allFlights.remove(flight);
            writerManager.write(theFlightWithId()+flight.getId()+succesfullyDeleted());
            writerManager.flush();
            for (User user : allUsers) {
                if (user.getUserFlights().contains(flight)) {
                    user.deleteFlight(flight);
                    writerManager.write(userWithEmail()+user.getEmail()+ wasNotifiedAboutCancellation()+arguments[1]+wasCanceled());
                    writerManager.flush();
                }
            }
        }

    }
    // TODO: 01.11.2022 metodaDelete_flight Done+correctFunctionabil

    public void display_Flight(String[] arguments){
        writerManager.write("The flights are: ");
        writerManager.flush();
        for (Flight allFlight : allFlights) {
            writerManager.write(String.valueOf(allFlight));
            writerManager.flush();

        }



    }
    // TODO: 01.11.2022 metodaDisplay_Flight+correctFunctionabil

    public  void persist_flights (Statement statement) throws SQLException {

        String select ="SELECT * from `zboruri` ";
        ResultSet resultSet = statement.executeQuery(select);

        for (Flight flight : allFlights) {

                resultSet.moveToInsertRow();

                resultSet.updateString("from", String.valueOf(flight.getFrom()));
                resultSet.updateString("to", String.valueOf(flight.getTo()));
                resultSet.updateString("date", String.valueOf(flight.getDate()));
                resultSet.updateInt("duration", Integer.parseInt(String.valueOf(flight.getDuration())));
                resultSet.insertRow();
                
            }

        writerManager.write(databaseFlightsSaved());
        writerManager.flush();



    }
    // TODO: 01.11.2022 metodaPersist_flights+correctFunctionabil

    public  void persist_users (Statement statement) throws SQLException {

    String select ="SELECT * from `utilizatori` ";
    ResultSet resultSet = statement.executeQuery(select);

    for (User user : allUsers) {

        resultSet.moveToInsertRow();
        resultSet.updateString("email", String.valueOf(user.getEmail()));
        resultSet.updateString("name", String.valueOf(user.getName()));
        resultSet.updateString("password", String.valueOf(user.getParola()));
        resultSet.insertRow();

    }
    writerManager.write(databaseUsersSaved());
    writerManager.flush();



}
    // TODO: 01.11.2022 metodaPersist_users+correctFunctionabil


    }





