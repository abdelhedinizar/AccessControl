package com.nizar.abdelhedi.accesscontrol.fragmentMenu;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nizar.abdelhedi.accesscontrol.MainActivity;
import com.nizar.abdelhedi.accesscontrol.R;
import com.nizar.abdelhedi.accesscontrol.TableDeBordCustum;
import com.nizar.abdelhedi.accesscontrol.URLStorage;
import com.nizar.abdelhedi.accesscontrol.model.Attendance;
import com.nizar.abdelhedi.accesscontrol.model.Duration;
import com.nizar.abdelhedi.accesscontrol.model.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by abdelhedi on 18/06/2016.
 */
public class TableauDeBordFragment extends Fragment {
     public static String SERVEL_URL;
    //= "http://192.168.1.184:8081/access-control-web/rest/services/attendance";

    //String
    static List<Attendance> attendancesList = new ArrayList<>();
    ListView tableauDeBordListview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tableau_de_bord, container, false);
        SERVEL_URL = URLStorage.getDefaults(MainActivity.theURL_KeY, getActivity());
        tableauDeBordListview = (ListView) view.findViewById(R.id.tableaudebordlist);


        Toast.makeText(getActivity(), URLStorage.getDefaults("URL", getActivity()), Toast.LENGTH_LONG).show();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        DownloadJSON downloadJSON = new DownloadJSON();
        downloadJSON.execute();
    }

    public class DownloadJSON extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL theURL = new URL(SERVEL_URL);
                BufferedReader reader = new BufferedReader(new InputStreamReader(theURL.openConnection().getInputStream(), "UTF-8"));
                String jsonStr = reader.readLine();
                JSONArray jsonArray = new JSONArray(jsonStr);
                attendancesList.clear();
                for (int i = 0; i < jsonArray.length(); i++) {

                    Attendance attendanceItem = getAttendanceFromJsonArray(jsonArray, i);

                    attendancesList.add(attendanceItem);

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        private Attendance getAttendanceFromJsonArray(JSONArray jsonArray, int i) throws JSONException {
            Attendance attendanceItem = new Attendance();
            JSONObject jsonAttendanceItem = jsonArray.getJSONObject(i);

            JSONObject jsonMonthlyWorkedHourItem = jsonAttendanceItem.getJSONObject("monthlyWorkedHour");
            attendanceItem.setMonthlyWorkedHour(new Duration(jsonMonthlyWorkedHourItem.getLong("seconds")));

            JSONObject jsonWeeklyWorkedHourItem = jsonAttendanceItem.getJSONObject("weeklyWorkedHour");
            attendanceItem.setWeeklyWorkedHour(new Duration(jsonWeeklyWorkedHourItem.getLong("seconds")));

            JSONObject jsonDailyWorkedHourItem = jsonAttendanceItem.getJSONObject("dailyWorkedHour");
            attendanceItem.setDailyWorkedHour(new Duration(jsonDailyWorkedHourItem.getLong("seconds")));

            attendanceItem.setEmployeeIsInsideCompany(jsonAttendanceItem.getBoolean("employeeIsInsideCompany"));

            JSONObject jsonEmployeeItem = jsonAttendanceItem.getJSONObject("employeeDto");

            attendanceItem.setEmployee(new Employee(jsonEmployeeItem.getString("firstName"), jsonEmployeeItem.getString("lastName"), jsonEmployeeItem.getString("phoneNumber")));
            return attendanceItem;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayAdapter<Attendance> adapter = new TableDeBordCustum(getActivity(), attendancesList);
            tableauDeBordListview.setAdapter(adapter);
        }
    }

}