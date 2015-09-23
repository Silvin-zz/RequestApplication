package pikino.requestapplication;

/**
 * Created by silviobravocado on 21/09/15.
 */
public class Constants {

    public static final String URL_BASE             = "https://api.coursera.org/api/catalog.v1";
    public static final String PATH_COURSES         = "/courses";
    public static final String PARAM_QUERY_KEY      = "query";
    public static final String PARAM_QUERY_SERCH    = "search";
    public static final String PARAM_QUERY          = "q";
    public static final String PARAM_FIELDS         = "fields";
    public static final String VALUES_FIELDS        = "id,shortName,name,language,largeIcon,photo,universityLogo,recommendedBackground,shortDescription";
    public static final String PATH_SEARCH_COURSES  = PATH_COURSES
                                                    + "?"
                                                    + PARAM_QUERY
                                                    + "="
                                                    +  PARAM_QUERY_SERCH
                                                    + "&"
                                                    + PARAM_FIELDS
                                                    + "="
                                                    + VALUES_FIELDS;

}
