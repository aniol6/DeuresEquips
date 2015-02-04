package com.example.aniolcivit.deuresequips;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aniolcivit.deuresequips.BBDD.JugadorDao;
import com.example.aniolcivit.deuresequips.BBDD.JugadorHelper;
import com.example.aniolcivit.deuresequips.BotonsiLlistes.Jugador;
import com.example.aniolcivit.deuresequips.BotonsiLlistes.MyCustomAdapter;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends OrmLiteBaseActivity<JugadorHelper> {

    private EditText mEditTextN;
    private EditText mEditTextV;
    private Button blau;
    private Button vermell;
    private Button brandom;
    private ListView llblau;
    private ListView llvermell;
    private MyCustomAdapter adapterblau;
    private MyCustomAdapter adaptervermell;
    private ArrayList<Jugador> jblaus;
    private ArrayList<Jugador> jvermells;
    private JugadorDao jugadorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        jblaus = new ArrayList<>();
        jvermells = new ArrayList<>();

    }


    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);


        blau = (Button) findViewById(R.id.blau);
        vermell = (Button) findViewById(R.id.vermell);
        mEditTextN = (EditText) findViewById(R.id.editnom);
        llblau = (ListView) findViewById(R.id.equipblau);
        llvermell = (ListView) findViewById(R.id.equipvermell);
        mEditTextV = (EditText) findViewById(R.id.editvalor);
        brandom = (Button) findViewById(R.id.random);



        adapterblau = new MyCustomAdapter(getApplicationContext(),jblaus);
        llblau.setAdapter(adapterblau);
        adaptervermell = new MyCustomAdapter(getApplicationContext(),jvermells);
        llvermell.setAdapter(adaptervermell);

        jugadorDao = new JugadorDao();

        List<JugadorDao> llista = jugadorDao.queryForAll();

        //errors
        for (JugadorDao rest : llista) {
            if (rest.getEquip() == "Blau") {
                adapterblau.add(rest);

            }else if (rest.getEquip() == "Vermell") {
                adaptervermell.add(rest);
            }
        }

        blau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextN.getText().toString().equals("") || mEditTextV.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Falten dades", Toast.LENGTH_SHORT).show();
                } else {
                    Jugador j = new Jugador(mEditTextN.getText().toString(),mEditTextV.getText().toString());
                    adapterblau.add(j);
                    jugadorDao = new JugadorDao(mEditTextN.getText().toString(),mEditTextV.getText().toString(),"Blau",new byte[0],false);

                    try {
                        Dao<JugadorDao,Integer> dao = getHelper().getDao();
                        dao.create(jugadorDao);
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }



                    mEditTextN.setText("");
                    mEditTextV.setText("");

                }
            }
        });

        vermell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextN.getText().toString().equals("")|| mEditTextV.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Falten Dades",Toast.LENGTH_SHORT).show();
                }else{
                    Jugador j = new Jugador(mEditTextN.getText().toString(),mEditTextV.getText().toString());
                    adaptervermell.add(j);

                    jugadorDao = new JugadorDao(mEditTextN.getText().toString(),mEditTextV.getText().toString(),"Vermell",new byte[0],false);

                    try {
                        Dao<JugadorDao,Integer> dao = getHelper().getDao();
                        dao.create(jugadorDao);
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }

                    mEditTextN.setText("");
                    mEditTextV.setText("");

                }
            }
        });
        brandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextN.getText().toString().equals("")|| mEditTextV.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Falten dades",Toast.LENGTH_SHORT).show();
                }else{
                    Random random = new Random();
                    int r = random.nextInt(2);
                    Jugador j = new Jugador(mEditTextN.getText().toString(),mEditTextV.getText().toString());
                    switch (r){
                        case 0:

                            adapterblau.add(j);

                            jugadorDao = new JugadorDao(mEditTextN.getText().toString(),mEditTextV.getText().toString(),"Blau",new byte[0],false);

                            try {
                                Dao<JugadorDao,Integer> dao = getHelper().getDao();
                                dao.create(jugadorDao);
                            }catch (SQLException e) {
                                e.printStackTrace();
                            }
                            mEditTextV.setText("");
                            mEditTextN.setText("");
                            break;
                        case 1:
                            adaptervermell.add(j);

                            jugadorDao = new JugadorDao(mEditTextN.getText().toString(),mEditTextV.getText().toString(),"Vermell",new byte[0],false);

                            try {
                                Dao<JugadorDao,Integer> dao = getHelper().getDao();
                                dao.create(jugadorDao);
                            }catch (SQLException e) {
                                e.printStackTrace();
                            }
                            mEditTextN.setText("");
                            mEditTextV.setText("");
                            break;
                        default:
                            break;
                    }


                }
            }
        });

        llblau.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Informacio.class);
                intent.putExtra("Nom",jblaus.get(position).name);
                intent.putExtra("Valoració",jblaus.get(position).val);
                intent.putExtra("Foto",jblaus.get(position).foto);
                intent.putExtra("Personalitzada",jblaus.get(position).personalitzada);
                intent.putExtra("Equip","Equip blau");
                startActivity(intent);
                return false;
            }
        });

        llvermell.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Informacio.class);
                intent.putExtra("Nom",jvermells.get(position).name);
                intent.putExtra("Valoració",jvermells.get(position).val);
                intent.putExtra("Foto",jvermells.get(position).foto);
                intent.putExtra("Personalitzada",jvermells.get(position).personalitzada);
                intent.putExtra("Equip","Equip vermell");
                startActivity(intent);
                return false;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
