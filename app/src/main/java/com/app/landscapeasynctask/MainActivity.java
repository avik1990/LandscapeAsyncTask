package com.app.landscapeasynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url = "http://shop.dhanwantary.com/mobile-app/all_products_json_direct.php?search_param";
    ProgressDialog progressDialog;
    Context mContext;
    List<Medicine> list_medicine = new ArrayList<>();
    MedicineListAdapter adapter;
    ListView lv_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading");
        progressDialog.setCanceledOnTouchOutside(false);
        lv_view = findViewById(R.id.lv_view);

        if (savedInstanceState != null) {
            list_medicine = (List<Medicine>) savedInstanceState.getSerializable("key");
            Log.d("PostCompletexxxx","CompleteSave");
            inflatelist();
        } else {
            new DownloadTask().execute();
        }

//        if (list_medicine.size() == 0) {
//            list_medicine.clear();
//            Log.d("PostCompletexxxx","Download");
//            new DownloadTask().execute();
//        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("key", (Serializable) list_medicine);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    class DownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            try {
                String jsonStr = sh.makeServiceCall(url);
                if (jsonStr != null) {
                    JSONArray arr = new JSONArray(jsonStr);
                    if (arr.length() > 0) {
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject post = arr.optJSONObject(i);
                            Medicine products = new Medicine();
                            products.setEntity_id(post.optString("entity_id"));
                            products.setName(post.optString("name"));
                            products.setPrice(post.optString("price"));
                            products.setOldprice(post.optString("oldprice"));
                            list_medicine.add(products);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            if (list_medicine.size() > 0) {
                Toast.makeText(mContext,"Completed",Toast.LENGTH_SHORT).show();
                inflatelist();
                Toast.makeText(mContext,"Completed123dsasdasdadadad",Toast.LENGTH_SHORT).show();

            }
            Log.d("PostCompletexxxx","CompletePost");

        }
    }

    private void inflatelist() {
        if (list_medicine.size() > 0) {
            Toast.makeText(mContext,"inflate",Toast.LENGTH_SHORT).show();
            adapter = new MedicineListAdapter(mContext, R.layout.row_medicine, list_medicine);
            lv_view.setAdapter(adapter);
            adapter.setNotifyOnChange(true);
            adapter.notifyDataSetChanged();
        }
    }
}
