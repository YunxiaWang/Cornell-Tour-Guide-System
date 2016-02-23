package cornell.wang.yunxia.tourguide;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import java.util.ArrayList;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener {

    public final static String NAME_EXTRA = "cornell.wang.yunxia.tourguide.dataextra";
    public final static String IMAGE_EXTRA = "cornell.wang.yunxia.tourguide.imageextra";
    public final static String STIME_EXTRA = "cornell.wang.yunxia.tourguide.stimeextra";
    public final static String ETIME_EXTRA = "cornell.wang.yunxia.tourguide.etimeextra";
    public final static String LOCATION_EXTRA = "cornell.wang.yunxia.tourguide.locationextra";
    public final static String CATEGORY_EXTRA = "cornell.wang.yunxia.tourguide.categoryextra";
    public final static String DESCRIPTION_EXTRA = "cornell.wang.yunxia.tourguide.descriptionextra";
    Event a = new Event("John M. Olin Library", R.drawable.olin_library, "8:00 am", "2:00 am",
            "Olin Library, Ithaca", "Building",
            "Olin’s collection of analog materials, nearly 2,000,000 print volumes, 2,000,000 microforms, and 650,000 maps. It comprises the University’s largest information resource.\n\n Students and scholars praise this collection for its depth and breadth, its completeness, its physical condition and its accessibility.Olin Library supports the teaching and research needs of the Cornell community by maintaining an intelligent balance of print and digital collections and through creative and flexible approaches to emerging technologies useful for selection and acquisition of print and digital materials.");
    Event b = new Event("Carpenter Hall", R.drawable.carpenter_hall, "24/7", "24/7",
            "Carpenter Hall, Ithaca", "Building",
            "Carpenter Hall is the building for Civil and Environment Engineering department.");
    Event c = new Event("Phillips Hall", R.drawable.phillips_hall, "24 HOURS", "24 HOURS",
            "Phillips Hall, Ithaca", "Building",
            "Phillips Hall is the building for Electrical and Computer Engineering department.");
    Event d = new Event("McGraw Tower", R.drawable.tower, "1868", "1873",
            "McGraw Tower, Ithaca", "Attraction",
            "The Cornell Chimes located in McGraw Tower has been the heartbeat of campus life for more than a century, marking the hours and chiming concerts.");
    Event e = new Event("Uris Library", R.drawable.uris_library, "24 HOURS", "24 HOURS",
            "Uris Library, Ithaca", "Building",
            "Uris Library opened in 1891 as Cornell’s first library building.Designed by William Henry Miller, Cornell's first student of architecture, in the Richardsonian Romansque style. The library garnered national acclaim for its combination of beauty and utility. The University Library, as it was known, was refurbished in 1962 with funds from Harold '26 and Percy Uris and was renamed in recognition of their generous contribution.");
    Event f = new Event("Becker House Dining Room", R.drawable.becker_house_dining, "7 AM", "8 PM",
            "Becker House Dining Room, Ithaca", "Dining",
            "Located in Carl Becker House, the Becker House Dining Room is an All You Care To Eat dining room for the House's residents, and for the whole Cornell community.\n\nHot breakfast Monday-Friday & Brunch/lunch on Saturday and Sunday & Dinner seven nights a week");
    Event g = new Event("West Avenue Parking", R.drawable.parking, "07:30am", "05:00pm",
            "West Avenue, Ithaca", "Parking",
            "Hourly rate: $1.50 (max.: 8 hours)\n\nDaily rate: $10 (max.: 1 day)\n\nRate Days: Mon-Fri\n\nHours: 07:30am - 05:00pm\n\nDay Rate Exp.: Next Day at 7:30");
    private ListView eventListView;
    private MenuItem searchMenuItem;
    private SearchView searchView;
    private EventListAdapter eventListAdapter;
    private ArrayList<Event> eventList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initEventList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.main_search);
        searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button,
        // so long as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.main_search) {
            return true;
        }
        return super.onOptionsItemSelected(item); */
        switch (item.getItemId()) {
            case R.id.main_search:
                return true;
            case R.id.main_category:
                Intent intentCategory = new Intent(getApplicationContext(), CategoryList.class);
                startActivity(intentCategory);
                return true;
            case R.id.main_account:
                Intent intentAccount = new Intent(getApplicationContext(), MyAccount.class);
                startActivity(intentAccount);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Initialize friend list
     */
    private void initEventList() {
        eventList = new ArrayList<Event>();
        eventList.add(a);
        eventList.add(d);
        eventList.add(f);
        eventList.add(b);
        eventList.add(c);
        eventList.add(e);
        eventList.add(g);
        eventListAdapter = new EventListAdapter(this, eventList);
        eventListView = (ListView) findViewById(R.id.main_list);
        eventListView.setAdapter(eventListAdapter);
        eventListView.setTextFilterEnabled(false);
        // set up click listener
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // close search view if its visible
                if (searchView.isShown()) {
                    searchMenuItem.collapseActionView();
                    searchView.setQuery("", false);
                }
                // pass selected event to share activity
                Intent intent = new Intent(getApplicationContext(), AttractionDetail.class);
                String name = eventList.get(+position).getName();
                Integer imageID = eventList.get(+position).getImageID();
                String startTime = eventList.get(+position).getStartTime();
                String endTime = eventList.get(+position).getEndTime();
                String location = eventList.get(+position).getLocation();
                String category = eventList.get(+position).getCategory();
                String description = eventList.get(+position).getDescription();
                intent.putExtra(NAME_EXTRA, name);
                intent.putExtra(IMAGE_EXTRA, imageID);
                intent.putExtra(STIME_EXTRA, startTime);
                intent.putExtra(ETIME_EXTRA, endTime);
                intent.putExtra(LOCATION_EXTRA, location);
                intent.putExtra(CATEGORY_EXTRA, category);
                intent.putExtra(DESCRIPTION_EXTRA, description);
                startActivity(intent);
            }
        });
    }

    /* Search when input and submit text query     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        eventListAdapter.getFilter().filter(query.toString());
        return true;
    }

    /* Search when input text query     */
    @Override
    public boolean onQueryTextChange(String newText) {
        eventListAdapter.getFilter().filter(newText.toString());
        return true;
    }
}
