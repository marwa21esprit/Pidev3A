package test;


import tn.esprit.pidev.entity.User;
import tn.esprit.pidev.services.UsersServices;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        User u = new User(1,"Marwa", "7539");
        User u1 = new User(2,"Sarra", "9527");
        User u2 = new User(3,"Louli", "7596");
        User u3 = new User(4,"Ahmed", "8621");
        User u4 = new User(5,"Kyout", "5973");
        User u5 = new User(6,"mar", "96374");




        UsersServices us = new UsersServices();


       /* try {
            us.addUser(u5);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/



        try {
            us.update(new User(1, "Mriwa", "Marmar"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
       /* us.addUser(u);
        us.addUser(u1);
        us.addUser(u2);
        us.addUser(u3);
        us.addUser(u4);*/



       /* try {
            System.out.println(us.getAll());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.out.println(us.getById(u1.getId()));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            us.delete(6);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
}