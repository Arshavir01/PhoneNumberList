package com.example.user.phonenumberlist;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 30.06.2017.
 */

public class JsonParser extends AsyncTask<String, String, String> {
    //using this in MainActivity for sending data into UserInfo Activity
    public static ArrayList<String> usersNameArr = new ArrayList<>();
    public static ArrayList<String> phoneNumArr = new ArrayList<>();
    public static ArrayList<String> emailArr = new ArrayList<>();
    public static ArrayList<String> genderArr = new ArrayList<>();
    public static ArrayList<String> addressArr = new ArrayList<>();
    public static int[] imgArray = {R.drawable.aravi_tamada, R.drawable.b_jonny_depp,R.drawable.c_leonardo,R.drawable.d_john_wayne,R.drawable.e_jolie,
                      R.drawable.f_dido, R.drawable.g_adele,R.drawable.hugh,R.drawable.i_will_smith,R.drawable.j_clint,
                      R.drawable.k_obama, R.drawable.l_kate_winslet,R.drawable.m_eminem };

    ProgressDialog pd;
    ListView lv;
    Context c;

    public JsonParser(Context c, ProgressDialog pd, ListView lv){
        this.c = c;
        this.pd = pd;
        this.lv = lv;
    }

//onPreExecute
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setMessage("Loading...");
        pd.show();
    }

 //   doInBackground
    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            String line = "";
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null){
                builder.append(line);
            }

            String finalResult = builder.append(line).toString();
            return finalResult;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;

    }

//onPostExecute

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        //Json Parsing
        try {
            JSONObject parentObject = new JSONObject(result);
            JSONArray parentArray = parentObject.getJSONArray("contacts");
            JSONObject personDataObj;

            usersNameArr.clear();

            for(int i = 0; i < parentArray.length(); i++) {
                personDataObj = parentArray.getJSONObject(i);

                String name = personDataObj.getString("name");
                usersNameArr.add(name);

                JSONObject phoneNumObj = personDataObj.getJSONObject("phone");
                String mobile = phoneNumObj.getString("mobile");
                phoneNumArr.add(mobile);

                String email = personDataObj.getString("email");
                emailArr.add(email);

                String gender = personDataObj.getString("gender");
                genderArr.add(gender);

                String address = personDataObj.getString("address");
                addressArr.add(address);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        CustomAdapter adapter = new CustomAdapter(c);
        lv.setAdapter(adapter);
        pd.dismiss();

    }


    //Create here CustomAdapter.class
    public class CustomAdapter extends BaseAdapter {

        Context context;
        LayoutInflater layoutInflater = null;

        public CustomAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return usersNameArr.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_layout,null);

            ImageView imageView = (ImageView)convertView.findViewById(R.id.imageId);
            TextView nameText = (TextView)convertView.findViewById(R.id.nameTextViewId);
            TextView numberText = (TextView)convertView.findViewById(R.id.numberTVId);

            String[] nameStrArray= usersNameArr.toArray(new String[usersNameArr.size()]);
            nameText.setText(nameStrArray[position]);

            String[] numbrStrArray = phoneNumArr.toArray(new String[phoneNumArr.size()]);
            numberText.append(numbrStrArray[position]);

            imageView.setImageResource(imgArray[position]);

            return convertView;
        }

    }



}
