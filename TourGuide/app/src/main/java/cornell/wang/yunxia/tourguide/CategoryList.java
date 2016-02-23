package cornell.wang.yunxia.tourguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class CategoryList extends AppCompatActivity {

    Integer[] categoryImageID = {R.drawable.building,R.drawable.attraction,
            R.drawable.dining,R.drawable.parking};
    String[] categoryName = {"Building", "Attraction","Dining","Parking"};

    private ListView categoryListView;
    private CategoryListAdapter categoryListAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        categoryListAdapter = new CategoryListAdapter(this, categoryImageID, categoryName);
        categoryListView = (ListView) findViewById(R.id.category_list);
        categoryListView.setAdapter(categoryListAdapter);
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMain);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_other, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.other_home:
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
                return true;
            case R.id.other_category:
                Intent intentCategory = new Intent(getApplicationContext(), CategoryList.class);
                startActivity(intentCategory);
                return true;
            case R.id.other_account:
                Intent intentAccount = new Intent(getApplicationContext(), MyAccount.class);
                startActivity(intentAccount);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
