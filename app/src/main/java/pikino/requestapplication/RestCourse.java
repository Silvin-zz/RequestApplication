package pikino.requestapplication;

import pikino.requestapplication.io.io.model.SearchCourseResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by silviobravocado on 21/09/15.
 */
public interface RestCourse {

    @GET(Constants.PATH_SEARCH_COURSES)
    void searchCourses(
            @Query(Constants.PARAM_QUERY_KEY) String keySearch,
            Callback<SearchCourseResponse> serverResponse);
}
