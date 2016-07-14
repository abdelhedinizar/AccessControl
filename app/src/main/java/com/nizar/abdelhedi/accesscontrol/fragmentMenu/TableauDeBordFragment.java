package com.nizar.abdelhedi.accesscontrol.fragmentMenu;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nizar.abdelhedi.accesscontrol.R;
import com.nizar.abdelhedi.accesscontrol.TableDeBordCustum;
import com.nizar.abdelhedi.accesscontrol.URLStorage;
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
//import java.util.concurrent.ExecutionException;

/**
 * Created by abdelhedi on 18/06/2016.
 */
     public class TableauDeBordFragment extends Fragment {
  public static final String SERVEL_URL = "http://192.168.2.120:8081/access-control-web/rest/services/attendance";


    ListView tableauDeBordListview;
    List<Employee> employes = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        List<Employee> employes = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_tableau_de_bord, container, false);
        tableauDeBordListview = (ListView) view.findViewById(R.id.tableaudebordlist);
        DownloadJSON downloadJSON = new DownloadJSON();
        downloadJSON.execute();

/*
        int SDK_INT = Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
            String nizar = "nizar";
            try {
                nizar   = InetAddress.getByName("192.168.2.120").getHostName();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            Log.d("nizarab",nizar+"");

        }

        */
      Toast.makeText(getActivity(),URLStorage.getDefaults("URL",getActivity()),Toast.LENGTH_LONG).show();




        // Log.d("nizarab",inetAddress.getHostAddress());
    return view;
    }

    public class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL theURL = new URL(SERVEL_URL);
                BufferedReader reader = new BufferedReader(new InputStreamReader(theURL.openConnection().getInputStream(), "UTF-8"));

                String jsonStr =  reader.readLine();
                JSONArray jsonArray = new JSONArray(jsonStr);
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonAttendanceItem = jsonArray.getJSONObject(i);
                    JSONObject jsonEmployeeItem = jsonAttendanceItem.getJSONObject("employee");
                    Employee employe = new Employee();
                    employe.setFirstName(jsonEmployeeItem.getString("firstName"));
                    employe.setLastName(jsonEmployeeItem.getString("lastName"));
                    employe.setPhoneNumber(jsonEmployeeItem.getString("phoneNumber"));
                    employes.add(employe);
           //         setEmployes(getEmployes().add(jsonItem.getString("firstname")));
                }
               Log.e("nizarab",jsonArray.getJSONObject(2).getString("firstName")+"");


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

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d("nizarab",employes.size()+"");
            super.onPostExecute(aVoid);
             ArrayAdapter<Employee> adapter = new TableDeBordCustum(getActivity(), employes);
            tableauDeBordListview.setAdapter(adapter);

        }
    }

}