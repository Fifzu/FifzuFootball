package com.fifzu.fifzufootball.ui.settings;

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

import com.fifzu.fifzufootball.MainActivity;
import com.fifzu.fifzufootball.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private List<String> spielplanList;
    private List<TextInputLayout> spielplanListLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        spielplanList=((MainActivity)getActivity()).getSpielplanList();
        spielplanListLayout=new ArrayList<>();

        for (int i =0;i<spielplanList.size();i++)
        {
            final TextInputLayout spielplanLayout = root.findViewById(R.id.spielplan);
            spielplanLayout.getEditText().setText(spielplanList.get(i));
            spielplanListLayout.add(spielplanLayout);
        }

        final Button button = root.findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).setSpielplanList(spielplanList);
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