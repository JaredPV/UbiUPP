package com.example.ubiupp.ui.mapa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MapaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Mapa en desarrollo\nProximamente");
    }

    public LiveData<String> getText() {
        return mText;
    }
}