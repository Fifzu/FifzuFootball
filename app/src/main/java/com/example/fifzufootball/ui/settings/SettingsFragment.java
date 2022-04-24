package com.example.fifzufootball.ui.settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fifzufootball.MainActivity;
import com.example.fifzufootball.R;
import com.google.android.material.textfield.TextInputLayout;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);


        final TextInputLayout spielplan1 = root.findViewById(R.id.spielplan1);
        final TextInputLayout spielplan2 = root.findViewById(R.id.spielplan2);
        final TextInputLayout spielplan3 = root.findViewById(R.id.spielplan3);
        final TextInputLayout spielplan4 = root.findViewById(R.id.spielplan4);

        spielplan1.getEditText().setText(((MainActivity)getActivity()).spielplan1);
        spielplan2.getEditText().setText(((MainActivity)getActivity()).spielplan2);
        spielplan3.getEditText().setText(((MainActivity)getActivity()).spielplan3);
        spielplan4.getEditText().setText(((MainActivity)getActivity()).spielplan4);


        spielplan1.getEditText().getText();

        final Button button = root.findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).spielplan1 = (String) spielplan1.getEditText().getText().toString();
                ((MainActivity)getActivity()).spielplan2 = spielplan2.getEditText().getText().toString();
                ((MainActivity)getActivity()).spielplan3 = spielplan3.getEditText().getText().toString();
                ((MainActivity)getActivity()).spielplan4 = spielplan4.getEditText().getText().toString();
                ((MainActivity)getActivity()).saveSpielplane();

                Toast toast = Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT);
                toast.show();

            }
        });



        settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });
        return root;
    }
}