package com.nizar.abdelhedi.accesscontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nizar.abdelhedi.accesscontrol.model.Employee;

import java.util.List;

/**
 * Created by abdelhedi on 27/06/2016.
 */
public class TableDeBordCustum extends ArrayAdapter <Employee> {
    Context context;
    public TableDeBordCustum(Context context, List<Employee> test) {

        super(context,R.layout.list_item ,test);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
        String singeltest = getItem(position).getFirstName()+" "+getItem(position).getLastName();
        TextView tableauDeBordText = (TextView) view.findViewById(R.id.Tableau_de_bord_item);
        tableauDeBordText.setText(singeltest);
        TextView tab = (TextView) view.findViewById(R.id.description);
        tab.setText(getItem(position).getPhoneNumber());


        return view;
    }
}
