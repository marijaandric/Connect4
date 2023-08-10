package com.example.mojprojekat.database;

public class Korisnik {
    private int id;
    private String username;
    private String pass;
    private String role;

    public Korisnik(){}
    public Korisnik(int id, String username, String pass, String role)
    {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "korisnik{id="+id+", username="+username+", password="+
                pass+", role="+role+'\''+"}";
    }
}
