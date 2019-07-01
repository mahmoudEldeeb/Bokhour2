package com.eldeeb.bokhour.view;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.eldeeb.bokhour.R;
import com.eldeeb.bokhour.models.dataModels.UserInfo;
import com.eldeeb.bokhour.utils.CProgressDialog;
import com.eldeeb.bokhour.utils.Constants;
import com.eldeeb.bokhour.utils.Tools;
import com.eldeeb.bokhour.viewModels.RegisterViewModel;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {

    private MaterialRippleLayout lyt_register;
    int type=-1;
    private TextInputLayout first_name_lyt, email_lyt, phone_lyt, last_name_lyt, password_lyt,confirm_password_lyt;
    private EditText first_name,last_name, email, phone, password, confirm_password;
    RegisterViewModel registerViewModel;
    RadioGroup gender;
    UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        iniComponent();
        userInfo=new UserInfo();
        Constants.context=this;

    }

    private void iniComponent() {
        lyt_register = (MaterialRippleLayout) findViewById(R.id.lyt_register);

        // form view
        first_name =  findViewById(R.id.first_name);
        last_name =  findViewById(R.id.last_name);
        email =  findViewById(R.id.email);
        phone =  findViewById(R.id.phone);
        password =  findViewById(R.id.password);
        confirm_password =  findViewById(R.id.confirm_password);
        first_name_lyt=findViewById(R.id.first_name_lyt);
        phone_lyt=findViewById(R.id.phone_lyt);
        email_lyt=findViewById(R.id.email_lyt);
        phone_lyt=findViewById(R.id.phone_lyt);
        last_name_lyt=findViewById(R.id.last_name_lyt);
        confirm_password_lyt=findViewById(R.id.confirm_password_lyt);
        password_lyt=findViewById(R.id.password_lyt);
        gender=findViewById(R.id.gender);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                type=1;
                if(R.id.male==checkedId)
                    userInfo.cgender= Constants.MALE;
                else userInfo.cgender= Constants.FEMALE;
            }

        });
        lyt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    private void submitForm() {
        if (!validateName()) {
            return;
        }
        if (!validateLastName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }
        if(!validateConfermPassword())
            return;

        if(!validatePhone())
            return;
        if(!validateGender())
            return;
            registerViewModel= ViewModelProviders.of(this).get(RegisterViewModel.class);
        registerViewModel.register(userInfo);

        hideKeyboard();

    }
    public boolean validateLastName(){
        String str = last_name.getText().toString().trim();
        if (str.isEmpty()) {
            last_name_lyt.setError(getString(R.string.invalid_name));
            requestFocus(last_name);
            return false;
        } else {
            last_name_lyt.setErrorEnabled(false);
        }
        userInfo.clastname=str;
        return true;
    }
    private boolean validateEmail() {
        String str = email.getText().toString().trim();
        if (str.isEmpty() || !Tools.isValidEmail(str)) {
            email_lyt.setError(getString(R.string.invalid_email));
            requestFocus(email);
            return false;
        } else {
            email_lyt.setErrorEnabled(false);
        }
        userInfo.cemail=str;
        return true;
    }

    private boolean validateName() {
        String str = first_name.getText().toString().trim();
        if (str.isEmpty()) {
            first_name_lyt.setError(getString(R.string.invalid_name));
            requestFocus(first_name);
            return false;
        } else {
            first_name_lyt.setErrorEnabled(false);
        }
        userInfo.cfirstname=str;
        return true;
    }


    private boolean validatePhone() {
        String str = phone.getText().toString().trim();
        if (str.isEmpty()) {
            phone_lyt.setError(getString(R.string.invalid_phone));
            requestFocus(phone);
            return false;
        } else {
            phone_lyt.setErrorEnabled(false);
        }
        userInfo.cmobile=str;
        return true;
    }

    private boolean validatePassword() {
        String str = password.getText().toString().trim();
        if (str.isEmpty()) {
            password_lyt.setError(getString(R.string.invalid_phone));
            requestFocus(password);
            return false;
        } else {
            password_lyt.setErrorEnabled(false);
        }
        userInfo.user_pwd=str;
        return true;
    }
    private boolean validateConfermPassword() {
        String str = password.getText().toString().trim();
        String confirm = confirm_password.getText().toString().trim();
        if (confirm.isEmpty()||!str.equals(confirm)) {
            confirm_password_lyt.setError(getString(R.string.invalid_phone));
            requestFocus(confirm_password);
            return false;
        } else {
            confirm_password_lyt.setErrorEnabled(false);
        }
        userInfo.user_pwd2=confirm;
        return true;
    }

    private boolean validateGender() {
      if(type==-1){
          CProgressDialog.showToast("choose your gender");
          return false;
      }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
