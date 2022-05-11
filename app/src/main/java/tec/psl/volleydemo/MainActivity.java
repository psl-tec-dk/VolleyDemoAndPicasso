package tec.psl.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageView imgPict;
    Button btnFetch;
    public static final String URL = "https://dog.ceo/api/breeds/image/random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgPict = findViewById(R.id.imgPict);
        btnFetch = findViewById(R.id.btnFetch);

//        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Instantiate the RequestQueue.

                // Request a string response from the provided URL.
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        URL,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    String imgUrl = response.getString("message");
                                    Picasso.get().load(imgUrl).into(imgPict);
                                    //Log.d("PSL_LOG", imgUrl);
                                }
                                catch (JSONException e) {
                                    //e.printStackTrace();
                                    Log.d("PSL_LOG", e.getMessage());
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("PSL_LOG", error.toString());
                    }
                });

                // Add the request to the RequestQueue.
                VolleyReq.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);

            }
        });

    }
}