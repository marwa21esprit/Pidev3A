package test;


import models.Event;
import services.EventService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EventService es = new EventService();

        LocalDate datePersonnalisee = LocalDate.of(2002, 12, 30);
        Date date = Date.valueOf(datePersonnalisee);
        Event e1 = new Event(1254,"event11",date,30,"hello");

        try {
            es.add(e1);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            es.update(new Event(5,1254,"event11",date,30,"sarra"), e1.getIdEvent());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(es.getAll());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.out.println(es.getById(1));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            es.delete(10);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
}
