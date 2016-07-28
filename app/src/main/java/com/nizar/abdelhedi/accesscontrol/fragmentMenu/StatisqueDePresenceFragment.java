package com.nizar.abdelhedi.accesscontrol.fragmentMenu;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nizar.abdelhedi.accesscontrol.R;
import com.nizar.abdelhedi.accesscontrol.model.Attendance;


import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by abdelhedi on 26/07/2016.
 */
public class StatisqueDePresenceFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_statisque_de_presence, container, false);

        List<Attendance> attendancesList = TableauDeBordFragment.attendancesList;
        int numberOfEmployeesInsideCompany=0;
        for(Attendance attendance:attendancesList)
        {
            if(attendance.isEmployeeIsInsideCompany())
            {
                numberOfEmployeesInsideCompany++;
            }

        }

        PieChartView chart = (PieChartView) view.findViewById(R.id.chart);
        List<SliceValue> values = new ArrayList<>();
        values.add(new SliceValue(30,Color.BLUE));
        values.add(new SliceValue(numberOfEmployeesInsideCompany, Color.parseColor("#FE6DA8")));
        values.add(new SliceValue(9, Color.parseColor("#FED70E")));
        values.add(new SliceValue(attendancesList.size()-numberOfEmployeesInsideCompany, Color.parseColor("#56B7F1")));
        PieChartData data = new PieChartData();
        data.setValues(values);
        chart.setPieChartData(data);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}