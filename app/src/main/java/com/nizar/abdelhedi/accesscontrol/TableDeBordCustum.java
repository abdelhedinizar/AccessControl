package com.nizar.abdelhedi.accesscontrol;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.LayoutRipple;
import com.nizar.abdelhedi.accesscontrol.fragmentMenu.TableauDeBordFragment;
import com.nizar.abdelhedi.accesscontrol.model.Attendance;
import com.nizar.abdelhedi.accesscontrol.model.Employee;

import java.util.List;

/**
 * Created by abdelhedi on 27/06/2016.
 */
public class TableDeBordCustum extends ArrayAdapter<Attendance> {
    //  List<Employee> employeeList;
    List<Attendance> attendanceList;

    public TableDeBordCustum(Context context, List<Attendance> attendanceList) {
        super(context, R.layout.list_item, attendanceList);
        this.attendanceList = attendanceList;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        final String employeeName = getItem(position).getEmployee().getFirstName() + " " + getItem(position).getEmployee().getLastName();

        TextView tableauDeBordText = (TextView) view.findViewById(R.id.Tableau_de_bord_item);
        tableauDeBordText.setText(employeeName);
        TextView tab = (TextView) view.findViewById(R.id.description);
        tab.setText(getItem(position).getEmployee().getPhoneNumber());
        LayoutRipple itemWidgets = (LayoutRipple) view.findViewById(R.id.itemWidgets);
        itemWidgets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog employeeInformationDialog = new Dialog(convertView.getContext());
                employeeInformationDialog.setTitle("employee information");
                employeeInformationDialog.setContentView(R.layout.tableau_de_bord_item);
                fillingEmployeeInformationInTheDialog(employeeInformationDialog);
                employeeInformationDialog.show();

            }

            private void fillingEmployeeInformationInTheDialog(Dialog employeeInformationDialog) {

                TextView employeeTextViewTableaudeBordItem = (TextView) employeeInformationDialog.findViewById(R.id.employeeTextViewTableaudeBordItem);
                employeeTextViewTableaudeBordItem.setText(employeeName);

                TextView dayTimeworkedTextViewTableaudeBordItem = (TextView) employeeInformationDialog.findViewById(R.id.dayTimeworkedTextViewTableaudeBordItem);
                dayTimeworkedTextViewTableaudeBordItem.setText(getItem(position).getDailyWorkedHour().toString());

                TextView weekTimeworkedTextViewTableaudeBordItem = (TextView) employeeInformationDialog.findViewById(R.id.weekTimeworkedTextViewTableaudeBordItem);
                weekTimeworkedTextViewTableaudeBordItem.setText(getItem(position).getWeeklyWorkedHour().toString());

                TextView monthTimeworkedTextViewTableaudeBordItem = (TextView) employeeInformationDialog.findViewById(R.id.monthTimeworkedTextViewTableaudeBordItem);
                monthTimeworkedTextViewTableaudeBordItem.setText(getItem(position).getMonthlyWorkedHour().toString());

                TextView employeeState = (TextView) employeeInformationDialog.findViewById(R.id.employeeState);
                if(getItem(position).isEmployeeIsInsideCompany())
                 employeeState.setText("present");
                 else {
                    employeeState.setText("absent");
                }
            }
        });
        return view;
    }
}