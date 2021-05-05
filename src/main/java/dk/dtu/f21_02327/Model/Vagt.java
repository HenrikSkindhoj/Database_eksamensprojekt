package dk.dtu.f21_02327.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Vagt {
    LocalDate date;
    int startTime;
    int endTime;
    Medarbejder medarbejder;
    Lokation lokation;

    Vagt(LocalDate date, int startTime, Lokation lokation,Medarbejder medarbejder)
    {
        this.date = date;
        this.startTime = startTime;
        this.endTime = startTime + 10;
        this.medarbejder = medarbejder;
        this.lokation = lokation;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public Lokation getLokation() {
        return lokation;
    }

    public LocalDateTime getLocalDateTime()
    {
        LocalDate dateTime = date;
        int startMin = startTime % 100;
        int startHour = (startTime - startMin)/100;
        LocalDateTime appointmentStart = dateTime.atTime(startHour,startMin);
        return appointmentStart;
    }

}
