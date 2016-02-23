package cornell.wang.yunxia.tourguide;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class AttractionDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view as the activity layout
        setContentView(R.layout.listdetail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("Attraction Detail");
        // Get the message from the intent
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.NAME_EXTRA);
        Integer imageID = intent.getIntExtra(MainActivity.IMAGE_EXTRA, R.drawable.empty_photo);
        String startTime = intent.getStringExtra(MainActivity.STIME_EXTRA);
        String endTime = intent.getStringExtra(MainActivity.ETIME_EXTRA);
        String location = intent.getStringExtra(MainActivity.LOCATION_EXTRA);
        String category = intent.getStringExtra(MainActivity.CATEGORY_EXTRA);
        String description = intent.getStringExtra(MainActivity.DESCRIPTION_EXTRA);
        String mapLocation = "geo:0,0?q=(" + location + ")";
        // Create the view
        TextView Name = (TextView) findViewById(R.id.detail_name);
        Name.setText(name);
        ImageView imageView = (ImageView) findViewById(R.id.detail_image);
        imageView.setImageResource(imageID);
        TextView StartT = (TextView) findViewById(R.id.detail_start_time);
        StartT.setText(startTime);
        TextView EndT = (TextView) findViewById(R.id.detail_end_time);
        EndT.setText(endTime);
        final TextView Location = (TextView) findViewById(R.id.detail_location);
        Location.setText(location);
        Location.setHint(mapLocation);
        TextView Category = (TextView) findViewById(R.id.detail_category);
        Category.setText(category);
        TextView Description = (TextView) findViewById(R.id.detail_description);
        Description.setText(description);
        //floating action button link to google map
        FloatingActionButton getLocation = (FloatingActionButton) findViewById(R.id.map_location);
        getLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Build the intent
                Uri location = Uri.parse((String) Location.getHint());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                // Verify it resolves
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
                boolean isIntentSafe = activities.size() > 0;
                // Start an activity if it's safe
                if (isIntentSafe) {
                    startActivity(mapIntent);
                }
            }
        });
    }
}
