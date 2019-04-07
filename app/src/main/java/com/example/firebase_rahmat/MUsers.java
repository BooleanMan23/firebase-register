package com.example.firebase_rahmat;

public class MUsers {
    private  String email;

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    public String getNomorTelfon() {
        return nomorTelfon;
    }

    public MUsers(String email, String nama, String nomorTelfon) {
        this.email = email;
        this.nama = nama;
        this.nomorTelfon = nomorTelfon;
    }

    private String nama;
    private String nomorTelfon;

}
