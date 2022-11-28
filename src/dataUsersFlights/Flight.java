package dataUsersFlights;

import java.util.Objects;

public class Flight {


    private int id;

    private String from;
    private String to;
    private String date;
    private int duration;

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Flight() {
    }


    public Flight(int id, String from, String to, StringBuilder date, int duration) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = String.valueOf(date);
        this.duration = duration;
    }






    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && duration == flight.duration && Objects.equals(from, flight.from) && Objects.equals(to, flight.to) && Objects.equals(date, flight.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, date, duration);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                '}';
    }
}
