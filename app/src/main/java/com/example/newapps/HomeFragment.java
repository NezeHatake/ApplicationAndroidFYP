package com.example.newapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);  // Make sure you have the correct layout file name here

        // Initialize the buttons
        Button buttonborangd = view.findViewById(R.id.button_borang_d);
        Button buttonborange = view.findViewById(R.id.button_borang_e);

        // Set click listeners for the buttons
        buttonborangd.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), borangdactivity.class);
            startActivity(intent);
        });

        buttonborange.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), borangeactivity.class);
            startActivity(intent);
        });

        return view; // Return the inflated view
    }
}
