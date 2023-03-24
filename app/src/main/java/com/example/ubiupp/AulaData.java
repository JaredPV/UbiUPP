package com.example.ubiupp;

public class AulaData {
    private String aula;
    private String edificio;

    public AulaData(String aula, String edificio) {
        this.aula = aula;
        this.edificio = edificio;
    }

    public AulaData() {
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }
}
