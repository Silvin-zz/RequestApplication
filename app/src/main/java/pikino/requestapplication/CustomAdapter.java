package pikino.requestapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by silviobravocado on 11/09/15.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CourseViewHolder> {


    private List<CourseEntity>      courseList;
    private LayoutInflater          inflater;
    private MyOnItemClickListener   myOnItemClickListener;
    private Context                 myContext;




    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener){
        this.myOnItemClickListener = myOnItemClickListener;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view                   = inflater.inflate(R.layout.recycle_item, viewGroup, false);
        CourseViewHolder viewHolder = new CourseViewHolder(view,R.id.imgLogo, R.id.txtName, R.id.txtDescription);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(CourseViewHolder viewHolder, int i) {



        CourseEntity course = this.courseList.get(i);
        viewHolder.setImage(course.getUrlImage());
        viewHolder.setName(course.getName());
        viewHolder.setDescription(course.getDescription());


    }

    @Override
    public int getItemCount() {
        return this.courseList.size();
    }


    public CustomAdapter(Context context, List<CourseEntity> objects) {

        this.courseList         = objects;
        this.inflater           = LayoutInflater.from(context);
        this.myContext          = context;


    }


    /*** Clase anonima para el caso de View Holder. ***/

    public class  CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView courseImage;
        private TextView  name;
        private TextView  description;
        private TextView  language;


        public CourseViewHolder(View itemView, int courseImageId, int nameId, int descriptionId) {

            super(itemView);

            this.courseImage    = (ImageView)  itemView.findViewById(courseImageId);
            this.name           = (TextView)   itemView.findViewById(nameId);
            this.description    = (TextView)   itemView.findViewById(descriptionId);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            myOnItemClickListener.onItemClick(getAdapterPosition());

        }



        /**** Seters **/

        public void setName(String name){
            this.name.setText(name);
        }
        public void setImage(String resource){

            Log.d("resultado", "=================================================");
            Log.d("resultado", resource);
            Picasso.with(myContext).load(resource).into(courseImage);

        }

        public void setDescription(String description){
            this.description.setText(description);
        }


    }


    /**
     * Interfaz para pasar datos hacia la actividad.
     */
    public interface MyOnItemClickListener{

        public void onItemClick(int position);

    }



}
