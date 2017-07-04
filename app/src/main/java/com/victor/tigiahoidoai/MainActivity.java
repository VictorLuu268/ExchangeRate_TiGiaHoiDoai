package com.victor.tigiahoidoai;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.victor.adapter.TiGiaAdapter;
import com.victor.model.TiGia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView tiGiaListView;
    ArrayList<TiGia> listTiGia;
    TiGiaAdapter tiGiaAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        tiGiaListView = (ListView) findViewById(R.id.tiGia_LView);
        listTiGia = new ArrayList<>();
        tiGiaAdapter = new TiGiaAdapter(MainActivity.this,R.layout.item,listTiGia);
        tiGiaListView.setAdapter(tiGiaAdapter);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang lấy dữ liệu");
        progressDialog.setCanceledOnTouchOutside(false);

        TiGiaTask task = new TiGiaTask();
        task.execute();
    }

    class TiGiaTask extends AsyncTask<Void,Void,ArrayList<TiGia>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tiGiaAdapter.clear();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<TiGia> tiGias) {
            super.onPostExecute(tiGias);
            tiGiaAdapter.clear();
            tiGiaAdapter.addAll(tiGias);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<TiGia> doInBackground(Void... voids) {
            ArrayList<TiGia> list = new ArrayList<>();
            try{
                URL url = new URL("http://www.dongabank.com.vn/exchange/export");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type","application/json; charset=uft-8");
                connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                connection.setRequestProperty("Accept","*/*");
                // get data from server
                InputStream is = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                StringBuilder builder = new StringBuilder();

                // get json data
                while(line != null){
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                String json = builder.toString();
                json = json.replace("(","");
                json = json.replace(")","");

                // get object from json
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for(int i = 0; i< jsonArray.length();i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    TiGia tiGia = new TiGia();
                    if(item.has("type"))
                        tiGia.setType(item.getString("type"));
                    if(item.has("imageurl")){
                        tiGia.setImageUrl(item.getString("imageurl"));
                        url = new URL(tiGia.getImageUrl());
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                        connection.setRequestProperty("Accept","*/*");
                        Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());

                        tiGia.setBitmap(bitmap);
                    }
                    if(item.has("muatienmat"))
                        tiGia.setMuaTienMat(item.getString("muatienmat"));
                    if(item.has("bantienmat"))
                        tiGia.setBanTienMat(item.getString("bantienmat"));
                    if(item.has("muack"))
                        tiGia.setMuaChuyenKhoan(item.getString("muack"));
                    if(item.has("banck"))
                        tiGia.setBanChuyenKhoan(item.getString("banck"));

                    list.add(tiGia);
                }

            }catch (Exception e){
                Log.e("Error - ",e.toString());
            }
            return list;
        }
    }
}
