package pikino.requestapplication.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by silviobravocado on 21/09/15.
 */
public class Course {

    String name;
    String id;
    private String language;


    @SerializedName("shortDescription")     // Nombre de la fuente
    String description;                     // Nombre de mi propiedad

    @SerializedName("largeIcon")
    String urlImage;


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlImage() {
        return urlImage;
    }



}
