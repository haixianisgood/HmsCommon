package com.example.hmscommon;

import com.example.hmscommon.slice.EntryAbilitySlice;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.content.Intent;

public class MainAbility extends FractionAbility {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(EntryAbilitySlice.class.getName());

    }
}
