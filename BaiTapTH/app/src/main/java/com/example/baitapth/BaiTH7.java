package com.example.baitapth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BaiTH7 extends AppCompatActivity {
     ListView lvDanhSachUser;
     SearchView searchView;
     List<User> data = new ArrayList<>();
     List<User> data_all = new ArrayList<>();
    CustomAdapterUser adapterUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_th7);
        setControl();
        DocDL();
    }

    private void setEvent(){
        adapterUser = new CustomAdapterUser(this, R.layout.layout_user, data);
        lvDanhSachUser.setAdapter(adapterUser);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                data.clear();
                if(newText.equals("")){
                    data.addAll(data_all);
                    adapterUser.notifyDataSetChanged();
                } else {
                    for(User user : data_all){
                        if(user.getLogin().contains(newText)){
                            data.add(user);
                        }
                    }
                }
                adapterUser.notifyDataSetChanged();
                return false;
            }
        });
    }

    private void DocDL() {
        RequestQueue requestQueue = Volley.newRequestQueue(BaiTH7.this);
        String url = "https://api.github.com/users";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            User user = new User();
                            try {
                                JSONObject item = response.getJSONObject(i);
                                user.setLogin(item.getString("login"));
                                user.setUrl(item.getString("url"));
                                user.setAvatar_url(item.getString("avatar_url"));
                                data.add(user);
                                data_all.add(user);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        setEvent();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BaiTH7.this, "Loi " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }

    private void setControl() {
        lvDanhSachUser = findViewById(R.id.lvUser);
        searchView = findViewById(R.id.svTimKiem);
    }
}