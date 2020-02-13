package com.freelance.ramya.multispinnerlibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import androidx.appcompat.widget.AppCompatSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ramyapsr on 13/02/2020
 */
public class MultiSpinner  extends AppCompatSpinner implements DialogInterface.OnMultiChoiceClickListener
{

    String[] mLocationCode = null;
    boolean[] mSelection = null;
    boolean[] mSelectionAtStart = null;
    String mLocationCodeAtStart = null;
    ArrayAdapter<String> location_adapter;
    private OnMultipleItemsSelectedListener listener;


    public MultiSpinner(Context context) {
        super(context);

        location_adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(location_adapter);
    }

    public MultiSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        location_adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(location_adapter);
    }

    public void setListener(OnMultipleItemsSelectedListener listener){
        this.listener = listener;
    }

    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (mSelection != null && which < mSelection.length) {
            mSelection[which] = isChecked;
            location_adapter.clear();
            location_adapter.add(setSelectedItemString());
        } else {
            throw new IllegalArgumentException(
                    "Argument 'which' is out of bounds.");
        }
    }

    @Override
    public boolean performClick() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMultiChoiceItems(mLocationCode, mSelection, this);
        mLocationCodeAtStart = getSelectedItemsAsString();
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                listener.selectedIndices(getSelectedIndices());
                listener.selectedStrings(getSelectedStrings());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                location_adapter.clear();
                location_adapter.add(mLocationCodeAtStart);

            }
        });
        if(mLocationCode!=null) {
            builder.show();
        }


        return true;


    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        throw new RuntimeException(
                "setAdapter is not supported by MultiSelectionSpinner.");
    }

    //  // set location code
    public void setItems(List<String> items) {
        mLocationCode = items.toArray(new String[items.size()]);
        mSelection = new boolean[mLocationCode.length];
        mSelectionAtStart  = new boolean[mLocationCode.length];
        location_adapter.clear();
        location_adapter.add(mLocationCode[0]);
        Arrays.fill(mSelection, false);
        mSelection[0] = true;
    }

    // set default selection

    public void setSelection(List<String> selection) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
            mSelectionAtStart[i] = false;
        }
        for (String sel : selection) {
            for (int j = 0; j < mLocationCode.length; ++j) {
                if (mLocationCode[j].equals(sel)) {
                    mSelection[j] = true;
                    mSelectionAtStart[j] = true;
                }
            }
        }
        location_adapter.clear();
        location_adapter.add(setSelectedItemString());
    }

    public void setSelection(int index) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
            mSelectionAtStart[i] = false;
        }
        if (index >= 0 && index < mSelection.length) {
            mSelection[index] = true;
            mSelectionAtStart[index] = true;
        } else {
            throw new IllegalArgumentException("Index " + index
                    + " is out of bounds.");
        }
        location_adapter.clear();
        location_adapter.add(setSelectedItemString());
    }


    public List<String> getSelectedStrings() {
        List<String> selection = new LinkedList<>();
        for (int i = 0; i < mLocationCode.length; ++i) {
            if (mSelection[i]) {
                selection.add(mLocationCode[i]);
            }
        }
        return selection;
    }

    public List<Integer> getSelectedIndices() {
        List<Integer> selection = new LinkedList<>();
        for (int i = 0; i < mLocationCode.length; ++i) {
            if (mSelection[i]) {
                selection.add(i);
            }
        }
        return selection;
    }

    private String setSelectedItemString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;
        if(mLocationCode!=null) {
            for (int i = 0; i < mLocationCode.length; ++i) {
                if (mSelection[i]) {
                    if (foundOne) {
                        sb.append(", ");
                    }
                    foundOne = true;

                    sb.append(mLocationCode[i]);
                }
            }
        }
        return sb.toString();
    }


    public String getSelectedItemsAsString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;
        if(mLocationCode!=null) {
            for (int i = 0; i < mLocationCode.length; ++i) {
                if (mSelection[i]) {
                    if (foundOne) {
                        sb.append(", ");
                    }
                    foundOne = true;
                    sb.append(mLocationCode[i]);
                }
            }
        }
        return sb.toString();
    }


    public interface OnMultipleItemsSelectedListener{
        void selectedIndices(List<Integer> indices);
        void selectedStrings(List<String> strings);
    }


    // selected Index values
    public void setItems(String[] items) {
        mLocationCode = items;
        mSelection = new boolean[mLocationCode.length];
        mSelectionAtStart = new boolean[mLocationCode.length];
        location_adapter.clear();
        location_adapter.add(mLocationCode[0]);
        Arrays.fill(mSelection, false);
        mSelection[0] = true;
        mSelectionAtStart[0] = true;
    }

    public void setSelection(String[] selection) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
            mSelectionAtStart[i] = false;
        }
        for (String cell : selection) {
            for (int j = 0; j < mLocationCode.length; ++j) {
                if (mLocationCode[j].equals(cell)) {
                    mSelection[j] = true;
                    mSelectionAtStart[j] = true;
                }
            }
        }
        location_adapter.clear();
        location_adapter.add(setSelectedItemString());
    }
    // to set items for seleted
    public void setSelection(int[] selectedIndices) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
            mSelectionAtStart[i] = false;
        }
        for (int index : selectedIndices) {
            if (index >= 0 && index < mSelection.length) {
                mSelection[index] = true;
                mSelectionAtStart[index] = true;
            } else {
                throw new IllegalArgumentException("Index " + index
                        + " is out of bounds.");
            }
        }
        location_adapter.clear();
        location_adapter.add(setSelectedItemString());
    }



}
