package io.github.lapalb.saransh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailMainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RequestQueue queue;
    private StringRequest request;
    TextView txt;
    TextView about, follower_count, id, location, repo_count, gist_count, following_count, name;
    String val;
    String url = "https://api.github.com/users/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);

        //Edit Text view
        name = findViewById(R.id.name);
        about  = findViewById(R.id.about);
        id  = findViewById(R.id.id);
        location  = findViewById(R.id.location);
        repo_count  = findViewById(R.id.repo_count);
        gist_count  = findViewById(R.id.gist_count);
        following_count = findViewById(R.id.following_count);
        follower_count  = findViewById(R.id.follower_count);

        val = getIntent().getExtras().getString("username");
        url = url.concat(val);
        queue = Volley.newRequestQueue(this);
        request = new StringRequest(Request.Method.GET, url,  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String name_response = jsonObject.getString("name");
                    String about_response = jsonObject.getString("bio");
                    String id_response = jsonObject.getString("id");
                    String repo_count_response = jsonObject.getString("public_repos");
                    String gist_count_response = jsonObject.getString("public_gists");
                    String following_count_response = jsonObject.getString("following");
                    String follower_count_response = jsonObject.getString("followers");
                    String location_response = jsonObject.getString("location");

                    name.setText(name_response);
                    about.setText(about_response);
                    id.setText(id_response);
                    repo_count.setText(repo_count_response);
                    gist_count.setText(gist_count_response);
                    follower_count.setText(follower_count_response);
                    following_count.setText(following_count_response);
                    location.setText(location_response);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
            }
        });
        queue.add(request);

        //txt.setText(val);
    }

}
