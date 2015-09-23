package pikino.requestapplication.io.io;

import pikino.requestapplication.Constants;
import pikino.requestapplication.RestCourse;
import retrofit.RestAdapter;

/**
 * Created by silviobravocado on 21/09/15.
 */
public class ApiClient {

    private static RestCourse API_SERVICE;


    public static RestCourse getInstance(){
        //The adapter will be a singleton
        if(API_SERVICE == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(Constants.URL_BASE)
                    .build();

            API_SERVICE = restAdapter.create(RestCourse.class);

        }
        return API_SERVICE;
    }
}
