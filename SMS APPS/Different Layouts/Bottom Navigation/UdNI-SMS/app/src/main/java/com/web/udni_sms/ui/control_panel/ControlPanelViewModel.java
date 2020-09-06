package com.web.udni_sms.ui.control_panel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ControlPanelViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ControlPanelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to UdNi-SMS");
    }

    public LiveData<String> getText() {
        return mText;
    }
}