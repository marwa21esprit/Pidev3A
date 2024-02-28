package com.example.demo;

import Services.ApprenantServices;
import Services.NiveauServices;
import models.Apprenant;
import models.Niveau;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
/*
        ApprenantServices ps = new ApprenantServices();


        Apprenant p1 = new Apprenant(1,"chayma", "chaymariahigmail.com", "payé", "Anglais","Certificate of achievement");
        Apprenant p2 = new Apprenant(3,"baha", "bahariahigmail.com", "Non payé", "Français","Certificate of achievement");

        try {
            ps.add(p1);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            ps.update(new Apprenant("ichrak","ichrakriahigmail.com", "payé","Anglais","Certificate of achievement"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ps.delete(2);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            System.out.println(ps.getById(p1.getId()));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


        try {
            System.out.println(ps.getAll());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
         */
        NiveauServices nv = new NiveauServices();


        Niveau n1 = new Niveau(1,"B1", "base", "2 mois", 10,"chaque 3 jours","achievement","good english");

        try {
            nv.update(new Niveau("B2","base","2 mois",10,"chaque 2 jours","achivv","fluent english"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            nv.add(n1);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }




    }

}
