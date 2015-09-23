package pikino.requestapplication.io.io.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import pikino.requestapplication.domain.Course;

/**
 * Created by silviobravocado on 21/09/15.
 */
public class SearchCourseResponse {

    @SerializedName("elements")
    ArrayList<Course> elements;




    public ArrayList<Course> getCourses(){

        return elements;

    }



}
