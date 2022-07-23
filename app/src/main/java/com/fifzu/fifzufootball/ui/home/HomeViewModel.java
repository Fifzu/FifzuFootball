package com.fifzu.fifzufootball.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> spielplaene;

    private int anzahlSpielplaene;


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public MutableLiveData<String> getSpielplaene() {
        if (spielplaene== null) {
            spielplaene = new MutableLiveData<String>();
        }
        return spielplaene;
    }

    public void setSpielplaene(MutableLiveData<String> spielplaene) {
        this.spielplaene = spielplaene;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public List<String> getSpielplanList() {
        List<String> spielplanList =  Arrays.asList(spielplaene.getValue().split("€"));

        return spielplanList;
    }

    public void setSpielplanList(List<String> spielplanList) {
        String spielplaeneString ="";

        for (String spielplan : spielplanList)
        {
            spielplaeneString = spielplaeneString + "€" + spielplan;
        }

        this.spielplaene.setValue(spielplaeneString);

        //saveSpielplane();
    }
}