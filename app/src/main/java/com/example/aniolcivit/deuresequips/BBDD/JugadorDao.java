package com.example.aniolcivit.deuresequips.BBDD;



import android.graphics.Bitmap;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "JUGADORS")



public class JugadorDao {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String valoracio;
    @DatabaseField
    private Bitmap foto;
    @DatabaseField
    private Boolean personalitzada;
    @DatabaseField
    private String name;


    JugadorDao(){}
    public int getId(){
        return id;

    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValoracio() {
        return valoracio;
    }

    public void setValoracio(String valoracio) {
        this.valoracio = valoracio;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public Boolean getPersonalitzada() {
        return personalitzada;
    }

    public void setPersonalitzada(Boolean personalitzada) {
        this.personalitzada = personalitzada;
    }

    public JugadorDao(String name, String valoracio, Bitmap foto, Boolean personalitzada ) {
        this.name = name;
        this.valoracio = valoracio;
        this.foto = foto;
        this.personalitzada=personalitzada;


    }
}