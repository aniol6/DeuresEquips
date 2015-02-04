package com.example.aniolcivit.deuresequips.BBDD;



import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "JUGADORS")



public class JugadorDao {
    @DatabaseField(generatedId = true)
    private int id;
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


    public JugadorDao(String name, int rate, String type) {
        this.name = name;

    }
}