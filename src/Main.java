import commandsAndMessages.Commands;
import logicUtils.AirLineManager;
import logicUtils.ReaderManager;
import logicUtils.WriterManager;

import java.sql.*;

public class Main {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/airportsystemtabels";
    private static final String USER="root";
    private static final String PASSWORD="";
    public static void main(String[] args) {


        AirLineManager airLineManager = new AirLineManager();
        ReaderManager readerManager = new ReaderManager();
        WriterManager writerManager=new WriterManager();


        String line = readerManager.readLine();
        while (line != null) {
            String[] inputData = line.split(" ");


            System.out.println("------" + inputData[0]+"---------");
            Commands command = Commands.valueOf(inputData[0]);

            switch (command) {
                case SIGNUP: {

                    airLineManager.signUp(inputData);

                    break;
                }
                case LOGIN: {
                    airLineManager.login(inputData);
                    break;
                }
                case LOGOUT: {
                    airLineManager.logout(inputData);
                    break;
                }
                case DISPLAY_MY_FLIGHTS:{
                    airLineManager.display_my_flights(inputData);
                    break;
                }
                case ADD_FLIGHT:{
                    airLineManager.addFlight(inputData);
                    break;
                }

                case CANCEL_FLIGHT: {
                    airLineManager.cancel_flight(inputData);
                    break;
                }
                case ADD_FLIGHT_DETAILS:{
                    airLineManager.add_flight_details(inputData);
                    break;
                }
                case DELETE_FLIGHT:{
                    airLineManager.deleteFlight(inputData);
                    break;
                }
                case DISPLAY_FLIGHT:{
                    airLineManager.display_Flight(inputData);
                    break;
                }
                case PERSIST_FLIGHTS:{

                    try(Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
                        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);

                    ) {
                        airLineManager.persist_flights(statement);


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;

                }
                case PERSIST_USERS:{

                    try(Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
                        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);

                    ) {
                        airLineManager.persist_users(statement);


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                }



            }


            line = readerManager.readLine();
        }


    }


}

