package cornell.wang.yunxia.tourguide;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Lele on 2015/12/5.
 */
public class CategoryListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final Integer[] imageID;
    private final String[] name;


    public CategoryListAdapter(Activity context, Integer[] imageID, String[] name) {
        super(context, R.layout.categorylist, name);
        this.context = context;
        this.imageID = imageID;
        this.name = name;
    }

    public View getView(final int position,View rowView,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        rowView=inflater.inflate(R.layout.categorylist, null, true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.category_image);
        TextView nameTxt = (TextView) rowView.findViewById(R.id.category_name);

        imageView.setImageResource(imageID[position]);
        nameTxt.setText(name[position]);
        return rowView;

    };
}
