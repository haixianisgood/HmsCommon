package com.example.hmscommon.slice;

import ohos.aafwk.abilityjet.activedata.ActiveData;

public class ViewModel {
    private ActiveData<String> activeData;

    public ActiveData<String> getActiveData() {
        return activeData;
    }

    public void setActiveData(ActiveData<String> activeData) {
        this.activeData = activeData;
    }

    public void add(int i) {
        int num = Integer.parseInt(activeData.getData());
        activeData.setData(String.valueOf(num+i));
    }
}
