package pikino.requestapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pikino.requestapplication.domain.Course;
import pikino.requestapplication.io.io.ApiClient;
import pikino.requestapplication.io.io.model.SearchCourseResponse;
import retrofit.Callback;
import retrofit.RetrofitError;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,CustomAdapter.MyOnItemClickListener, Callback<SearchCourseResponse> {

    RecyclerView recyclerView;
    android.widget.SearchView mySearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setUpRecicleView();

        mySearchView = (android.widget.SearchView) findViewById(R.id.searchView);
        mySearchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Llamamos a consumir el rest a traves de volley
                //loaCourses("https://api.coursera.org/api/catalog.v1/courses?q=search&query=" + query + "&fields=id,shortName,name,language,largeIcon,photo,universityLogo,recommendedBackground,shortDescription");

                //Llamamos a traves de retrofit.
                ApiClient.getInstance().searchCourses(query, MainActivity.this);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }



    private void setUpRecicleView() {
        this.recyclerView                                = (RecyclerView)findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager linearLayoutManager   = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        /**La parte comentada es cuando estaba fijo los registros*/

        //CustomAdapter myadapter                          = new CustomAdapter(this, this.loaCourses());
        //myadapter.setMyOnItemClickListener(this);
        //recyclerView.setAdapter(myadapter);

        /**Aqui terminan lo de cuando estaba fijo*/

        this.loaCourses("https://api.coursera.org/api/catalog.v1/courses?q=search&query=en&fields=id,shortName,name,language,largeIcon,photo,universityLogo,recommendedBackground,shortDescription");

    }


    /**
     * Cargamos los cursos.
     * @return List
     */
    private List<CourseEntity> loaCourses() {

        List<CourseEntity> courseList = new ArrayList<>();


        courseList.add(new CourseEntity("A", "BBBBBB", "https://d15cw65ipctsrr.cloudfront.net/06/cab010352b11e49a597321ba86b5f4/Naubahar-Course-Banner.png", "EN"));
        courseList.add(new CourseEntity("A", "BBBBBB", "https://d15cw65ipctsrr.cloudfront.net/06/cab010352b11e49a597321ba86b5f4/Naubahar-Course-Banner.png", "EN"));
        courseList.add(new CourseEntity("A", "BBBBBB", "https://d15cw65ipctsrr.cloudfront.net/06/cab010352b11e49a597321ba86b5f4/Naubahar-Course-Banner.png", "EN"));
        courseList.add(new CourseEntity("A", "BBBBBB", "https://d15cw65ipctsrr.cloudfront.net/06/cab010352b11e49a597321ba86b5f4/Naubahar-Course-Banner.png", "EN"));

        return courseList;
    }








    private void loaCourses(String url) {


        Log.d("resultado", "la url es: " +  url);


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                CustomAdapter myadapter = null;
                try {
                    myadapter = new CustomAdapter(MainActivity.this, parseCourseFromString(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myadapter.setMyOnItemClickListener(MainActivity.this);
                recyclerView.setAdapter(myadapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("VolleyError", error.getMessage());

            }
        });


        VolleySingleton.getInstance(this).addToRequestQueue(request);


    }



    private List<CourseEntity> parseCourseFromString(String response) throws JSONException {

        Log.d("resultado", "este es el resultado:" + response);

        List<CourseEntity> courseList = new ArrayList<>();
        JSONObject respuestaJson = new JSONObject(response);

        JSONArray items = respuestaJson.getJSONArray("elements");

        int tamanio = items.length();

        for (int i = 0; i < tamanio; i++) {
            JSONObject course = items.getJSONObject(i);

            String name         = course.getString("name");
            String description  = course.getString("shortDescription");
            String urlImage     = course.getString("largeIcon");
            String language     = course.getString("language");

            courseList.add(new CourseEntity(name,description,urlImage,language));



        }

        return courseList;


    }










    @Override
    public void onItemClick(int position) {
        
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }



    /***Metodos de retrofit :::::::::::::::::::::::**/
    @Override
    public void success(SearchCourseResponse searchCourseResponse, retrofit.client.Response response) {

        Log.d("resultado", response.toString());
        List<Course> courses = searchCourseResponse.getCourses();
        List<CourseEntity> finalCourses = new ArrayList<>();
        int ln = courses.size();
        for(int i = 0; i < ln; i++){

            Course course = courses.get(i);
            finalCourses.add(new CourseEntity(course.getName(),course.getDescription(), course.getUrlImage(), course.getLanguage()));

        }
        CustomAdapter myadapter = null;
        myadapter = new CustomAdapter(MainActivity.this, finalCourses);
        myadapter.setMyOnItemClickListener(MainActivity.this);
        recyclerView.setAdapter(myadapter);



    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("resultado", "Fallamos ======================================");
        Log.d("resultado", error.getMessage());

    }
}
