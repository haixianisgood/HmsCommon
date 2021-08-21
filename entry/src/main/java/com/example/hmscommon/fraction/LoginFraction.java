package com.example.hmscommon.fraction;

import com.example.common.component.PlaceholderView;
import com.example.common.component.Toast;
import com.example.common.fraction.PresenterFraction;
import com.example.hmscommon.BuildConfig;
import com.example.hmscommon.ResourceTable;
import com.example.hmscommon.contract.LoginContract;
import com.example.hmscommon.model.api.user.UserRspModel;
import com.example.hmscommon.presenter.LoginPresenter;
import com.example.hmscommon.slice.EntryAbilitySlice;
import ohos.aafwk.abilityjet.activedata.ActiveData;
import ohos.aafwk.abilityjet.activedata.DataObserver;
import ohos.aafwk.abilityjet.databinding.DataBindingUtil;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.data.orm.BindUtils;

import java.io.IOException;

public class LoginFraction extends PresenterFraction<LoginContract.Presenter> implements LoginContract.View{
    private TextField accountField;
    private TextField passwordField;
    private Button loginButton;
    private Text resultText;

    @Override
    protected void initPresenter() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected int layoutId() {
        return ResourceTable.Layout_fraction_login;
    }

    @Override
    protected void before() {

    }

    @Override
    protected void initComponent() {
        accountField = (TextField) getFractionAbility().findComponentById(ResourceTable.Id_input_account);
        passwordField = (TextField) getFractionAbility().findComponentById(ResourceTable.Id_input_password);
        loginButton = (Button) getFractionAbility().findComponentById(ResourceTable.Id_button_login);
        mPlaceholderView = (PlaceholderView) getFractionAbility().findComponentById(ResourceTable.Id_empty_view);
        resultText = (Text) getFractionAbility().findComponentById(ResourceTable.Id_login_result);
        loginButton.setClickedListener(component -> {
            mPlaceholderView.showLoading();
            String account = accountField.getText();
            String password = passwordField.getText();
            mPresenter.login(account, password);
        });
    }


    @Override
    protected void show() {
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void showLoginSuccess(UserRspModel userRspModel) {
        resultText.setText(userRspModel.getToken());
        mPlaceholderView.showSuccess();

        Operation operation = new Intent.OperationBuilder()
                .withAbilityName(EntryAbilitySlice.class)
                .build();
        IntentParams params = new IntentParams();
        params.setParam("user", userRspModel);

        Intent intent = new Intent();
        intent.setOperation(operation);
        intent.setParams(params);


    }

    @Override
    public void showLoginFailure(String message) {
        resultText.setText("failed : "+message);
        mPlaceholderView.showSuccess();
    }
}
