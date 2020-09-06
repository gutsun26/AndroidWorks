package com.web.udni_sms.ui.clients;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClientsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ClientsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}