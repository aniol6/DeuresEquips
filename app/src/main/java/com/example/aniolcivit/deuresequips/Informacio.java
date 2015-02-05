package com.example.aniolcivit.deuresequips;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aniolcivit.deuresequips.BBDD.JugadorDao;
import com.example.aniolcivit.deuresequips.BBDD.JugadorHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;


public class Informacio extends OrmLiteBaseActivity<JugadorHelper> {
    private EditText infonom;
    private EditText infoval;
    private ImageView infofoto;
    private TextView infoequip;
    private Button infook;
    private Button infocancel;
    private Informacio infoActivity;
    private int id;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacio);
        infonom = (EditText) findViewById(R.id.nameinfo);
        infoval = (EditText) findViewById(R.id.valinfo);
        infofoto = (ImageView) findViewById(R.id.photoinfo);
        infoequip= (TextView) findViewById(R.id.equip);
        infook = (Button) findViewById(R.id.buttonok);
        infocancel = (Button) findViewById(R.id.buttoncancel);

        infoActivity = this;




        /*FALTA:
        -assignar funció al botó ok
        -revisar el de cancel
        -modificar la info del main activity des de informació
         */
        infonom.setText(getIntent().getExtras().getString("Nom"));
        infoval.setText(getIntent().getExtras().getString("Valoració"));
        infofoto.setImageBitmap((android.graphics.Bitmap) getIntent().getExtras().get("Foto"));
        infoequip.setText(getIntent().getExtras().getString("Equip"));
        id= getIntent().getExtras().getInt("Id");

        infocancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setAction("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                startActivity(intent);*/
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                infoActivity.finish();
                //comentari

            }
        });

        infook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infonom.getText().toString().equals("") || infoval.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Falten dades", Toast.LENGTH_SHORT).show();
                }else{

                    JugadorDao jugadorDao = new JugadorDao(infonom.getText().toString(),infoval.getText().toString(),infoequip.getText().toString(),new byte[0],false);
                    jugadorDao.setId(id);
                    try {
                        Dao<JugadorDao,Integer> dao = getHelper().getDao();
                        dao.update(jugadorDao);

                    }catch (SQLException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    infoActivity.finish();



                }


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_informacio, menu);
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
