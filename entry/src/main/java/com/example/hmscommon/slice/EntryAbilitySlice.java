package com.example.hmscommon.slice;

import com.example.common.base.BaseAbilitySlice;
import com.example.common.component.Toast;
import com.example.hmscommon.AbilityEntryBinding;
import com.example.hmscommon.BuildConfig;
import com.example.hmscommon.ResourceTable;
import ohos.aafwk.abilityjet.activedata.ActiveData;
import ohos.aafwk.abilityjet.databinding.DataBindingUtil;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

import java.io.IOException;

public class EntryAbilitySlice extends BaseAbilitySlice {
    AbilityEntryBinding binding;
    private Button button;
    private ViewModel viewModel;

    @Override
    protected void beforeShow() {
        super.beforeShow();
        viewModel = new ViewModel();
        ActiveData<String> activeData = new ActiveData<>();
        activeData.setData("0");
        viewModel.setActiveData(activeData);

        button = (Button) findComponentById(ResourceTable.Id_button);
        button.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                viewModel.add(10);
            }
        });
    }

    @Override
    protected void show() {
        try {
            binding = DataBindingUtil.createBinding(layoutId(), this, BuildConfig.PACKAGE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(binding != null) {
            binding.initComponent(mComponentContainer);
            binding.setLifecycle(getLifecycle());
            binding.setViewModel(viewModel);

        } else {
            Toast.show(this, "binding failed");
        }
    }

    @Override
    protected int layoutId() {
        return ResourceTable.Layout_ability_entry;
    }
}
