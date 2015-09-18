package pikino.requestapplication;

/**
 * Created by silviobravocado on 11/09/15.
 */
public class CourseEntity {


    private String name;
    private String description;
    private String urlImage;
    private String language;

    public CourseEntity(String name, String description, String urlImage, String language) {
        this.name = name;
        this.description = description;
        this.urlImage = urlImage;
        this.language = language;
    }

    public CourseEntity() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
