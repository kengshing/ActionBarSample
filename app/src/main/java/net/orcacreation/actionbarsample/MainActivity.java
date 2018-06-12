package net.orcacreation.actionbarsample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String quickAccessValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        refreshScreen();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);

        //Setting up the spinner
        MenuItem item = menu.findItem(R.id.action_qa);
        Spinner qASpinner = (Spinner) item.getActionView();
        ArrayAdapter<CharSequence> qAAdaptor = ArrayAdapter.createFromResource(this, R.array.quick_acces_list, R.layout.spinner_text_layout);
        qASpinner.setAdapter(qAAdaptor);

        qASpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        quickAccessValue = getResources().getStringArray(R.array.quick_acces_list)[0];
                        refreshScreen();
                        break;

                    case 1:
                        quickAccessValue = getResources().getStringArray(R.array.quick_acces_list)[1];
                        refreshScreen();
                        break;
                    case 2:
                        quickAccessValue = getResources().getStringArray(R.array.quick_acces_list)[2];
                        refreshScreen();
                        break;
                    case 3:
                        quickAccessValue = getResources().getStringArray(R.array.quick_acces_list)[3];
                        refreshScreen();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                quickAccessValue = getString(R.string.quick_access_not_selected);
                refreshScreen();
            }
        });
        //Setting up the spinner - end

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_qa:
                Toast.makeText(this, "Quick Access selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_favorite_1:
                refreshFavoriteImage(R.drawable.ic_directions_walk_880000_60dp);
                return true;

            case R.id.action_favorite_2:
                refreshFavoriteImage(R.drawable.ic_directions_run_880000_60dp);
                return true;

            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refreshScreen(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView txtNumberView = findViewById(R.id.txt_number);
        String settingNumber = sharedPreferences.getString(
                getString(R.string.settings_number_key),
                getString(R.string.settings_number_default));
        txtNumberView.setText(settingNumber);

        TextView txtListView = findViewById(R.id.txt_list);
        String settingList = sharedPreferences.getString(
                getString(R.string.settings_list_key),
                getString(R.string.settings_list_default));
        txtListView.setText(settingList);

        TextView txtQuickAccessView = findViewById(R.id.txt_quick_access);
        if (quickAccessValue != null) {
            txtQuickAccessView.setText(quickAccessValue);
        }

    }

    private void refreshFavoriteImage (int resourceId){
        ImageView imgFavoriteView = findViewById(R.id.img_favorite);
        imgFavoriteView.setImageResource(resourceId);
    }
}
