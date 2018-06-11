package net.orcacreation.actionbarsample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_qa:
                Toast.makeText(this, "Quick Access selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_favorite_1:
                Toast.makeText(this, "Favorite 1 selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_favorite_2:
                Toast.makeText(this, "Favorite 2 selected", Toast.LENGTH_SHORT).show();
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
    }
}
