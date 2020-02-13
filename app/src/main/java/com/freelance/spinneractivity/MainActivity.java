package com.freelance.spinneractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.freelance.ramya.multispinnerlibrary.MultiSpinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MultiSpinner.OnMultipleItemsSelectedListener{
    List<String> locationName;
    MultiSpinner multiSelectionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        multiSelectionSpinner = (MultiSpinner) findViewById(R.id.location_code);
        locationName=new ArrayList<>();
        locationName.add("India");
        locationName.add("Iceland");
        locationName.add("Indonesia");
        locationName.add("Iran");
        locationName.add("Iraq");
        locationName.add("Ireland");
        locationName.add("Israel");
        locationName.add("Italy");
        locationName.add("Jamaica");
        locationName.add("Japan");
        locationName.add("Jordan");
        locationName.add("Kazakhstan");
        locationName.add("Kenya");
        locationName.add("Korea");
        locationName.add("Kuwait");
        locationName.add("Laos");
        locationName.add("Lebanon");
        locationName.add("Libya");
        locationName.add("Lebanon");
        locationName.add("Liberia");
        locationName.add("US");
        locationName.add("UK");

        multiSelectionSpinner.setItems(locationName);

        multiSelectionSpinner.setSelection(new String[]{"India","US","UK"});
        multiSelectionSpinner.setListener(MainActivity.this);
        multiSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("djhd", String.valueOf(parent.getSelectedItem()));
                return ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {
        Toast.makeText(this,  strings.toString(), Toast.LENGTH_SHORT).show();
    }
}
