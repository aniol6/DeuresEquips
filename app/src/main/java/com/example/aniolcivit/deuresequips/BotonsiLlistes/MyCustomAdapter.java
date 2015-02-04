package com.example.aniolcivit.deuresequips.BotonsiLlistes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aniolcivit.deuresequips.BotonsiLlistes.Jugador;
import com.example.aniolcivit.deuresequips.R;

import java.util.List;

/**
 * Created by Aniol Civit on 30/01/2015.
 */
public class MyCustomAdapter extends ArrayAdapter {
    private List<Jugador> mJugadors;
    private Context mContext;
    private int mResource;

    public MyCustomAdapter(Context context,List<Jugador> data) {
        super(context, R.layout.jugadors,data);
        mContext=context;
        mJugadors=data;
        mResource=R.layout.jugadors;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = layoutInflater.inflate(mResource,parent,false);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView val = (TextView) rowView.findViewById(R.id.val);
        ImageView foto = (ImageView) rowView.findViewById(R.id.photo);
        name.setText(mJugadors.get(position).name);
        val.setText(mJugadors.get(position).val);
        if (mJugadors.get(position).personalitzada==true){
            foto.setImageBitmap(mJugadors.get(position).foto);
        }else{
            //posar foto per defecte
        }


        return rowView;

    }
}
