package logicUtils;

import dataUsersFlights.Flight;
import dataUsersFlights.User;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AirlineStatistics {

    public static String findMostUsedCityAsDepartureForFlights(AirLineManager airLineManager) {
     AirLineManager airLineManager1=new AirLineManager();
        Optional<Flight> max = airLineManager1.allFlights.stream().max(Comparator.comparing(Flight::getFrom));
        return String.valueOf(max);
    }
    private static User findUserWhoTravelTheMost(AirLineManager manager) {
        List<User> allUsers = manager.getAllUsers();
User currentUser=new User();
        int duration=0;
        List<Flight> userFlights=new ArrayList<>();
        for (User user:allUsers){
            userFlights = user.getUserFlights();
            for (Flight flight:userFlights){
                 duration += flight.getDuration();
                user.setAccumulatedFlyTime(duration);

            }
            user=currentUser;

        }
        return currentUser;
    }
	private static List<User> findAllUsersWhoTraveledToCity(AirLineManager manager, String city){
        city.toUpperCase();
List<User> userList=new ArrayList<>();
        Optional<Flight> first = manager.allFlights.stream().filter(n -> n.getTo() == city).findFirst();
        for (User user: manager.getAllUsers()){
            if (user.getUserFlights().equals(first)){
                userList.add(user);
            }
        }

        return userList;
    }
	private static List<Flight> findAllFlightsBetweenDates(AirLineManager manager, LocalDate startDate, LocalDate endDate) {
List<Flight> allFlightsBetween=new ArrayList<>();
        List<Flight> allFlights = manager.getAllFlights();

      for (Flight flight:allFlights){
          if (flight.getDate().equals(startDate)){
              allFlightsBetween.add(flight);
          }
      }

        for (Flight flight:allFlights){
            if (flight.getDate().equals(endDate)){
                allFlightsBetween.add(flight);
            }
        }
List<LocalDate> idealDateList=new ArrayList<>();
        for (LocalDate date=startDate;date.isBefore(endDate);date.plusDays(1)){
            idealDateList.add(date);
        }
            for (Flight flight:allFlights){
                if (idealDateList.contains(flight.getDate())){
                    allFlightsBetween.add(flight);
                }
            }


        return allFlightsBetween;
    }
    private static Flight findShortestFlight(AirLineManager manager) {

        List<Flight> allFlights = manager.getAllFlights();
       Flight shortestFlight=new Flight();
        for(Flight flight: allFlights){
            if (flight.getDuration()<shortestFlight.getDuration()){

                    shortestFlight.setDuration(flight.getDuration());
                }
        }

    return shortestFlight;}
    private static List<User> findAllUsersWhoTraveledIn(AirLineManager manager, LocalDate date){

        List<Flight> allFlights = manager.getAllFlights();
        List<User> idealUsers=new ArrayList<>();
        List<Flight> idealFlights=new ArrayList<>();
        for (Flight flight:allFlights){
            if (flight.getDate().equals(date)){
                idealFlights.add(flight);
            }
        }

        for (User user: manager.getAllUsers()){
            user.getUserFlights().contains(idealFlights);
            idealUsers.add(user);
        }

        return idealUsers;
    }


}