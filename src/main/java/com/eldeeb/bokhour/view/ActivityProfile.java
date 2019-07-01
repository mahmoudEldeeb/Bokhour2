package com.eldeeb.bokhour.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.balysv.materialripple.MaterialRippleLayout;
import com.eldeeb.bokhour.R;
import com.eldeeb.bokhour.models.dataModels.UserInfo;
import com.eldeeb.bokhour.models.local.SaveInPrefrence;
import com.eldeeb.bokhour.utils.CProgressDialog;
import com.eldeeb.bokhour.utils.Constants;
import com.eldeeb.bokhour.utils.Tools;
import com.eldeeb.bokhour.viewModels.ProfileViewModel;
import com.eldeeb.bokhour.viewModels.RegisterViewModel;

public class ActivityProfile extends AppCompatActivity {

    private TextInputLayout first_name_lyt, email_lyt, phone_lyt, last_name_lyt, password_lyt,confirm_password_lyt
            ,country_lyt,city_lyt,address_line_lyt,
            Credit_Card_No_lyt,Credit_Card_PIN_No_lyt,expiration_year_lyt,expiration_month_lyt;
    private EditText first_name,last_name, email, phone, password, confirm_password
            ,city,address_line,country
            ,Credit_Card_No,Credit_Card_PIN_No,expiration_year,expiration_month;
    private MaterialRippleLayout lyt_register;
ProfileViewModel profileViewModel;
    RadioGroup gender;
    UserInfo userInfo;
    int type=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Constants.context=this;
        profileViewModel= ViewModelProviders.of(this).get(ProfileViewModel.class);
        iniComponent();
    }
    private void iniComponent() {
        lyt_register = (MaterialRippleLayout) findViewById(R.id.lyt_register);

        // form view
        userInfo=new UserInfo();
        first_name =  findViewById(R.id.first_name);
        last_name =  findViewById(R.id.last_name);
        email =  findViewById(R.id.email);
        phone =  findViewById(R.id.phone);
        password =  findViewById(R.id.password);
        confirm_password =  findViewById(R.id.confirm_password);

        country =  findViewById(R.id.country);
        city =  findViewById(R.id.city);
        address_line =  findViewById(R.id.address_line);
        Credit_Card_No =  findViewById(R.id.Credit_Card_No);
        Credit_Card_PIN_No =  findViewById(R.id.Credit_Card_PIN_No);
        expiration_year =  findViewById(R.id.expiration_year);
        expiration_month =  findViewById(R.id.expiration_month);


        first_name_lyt=findViewById(R.id.first_name_lyt);
        phone_lyt=findViewById(R.id.phone_lyt);
        email_lyt=findViewById(R.id.email_lyt);
        phone_lyt=findViewById(R.id.phone_lyt);
        last_name_lyt=findViewById(R.id.last_name_lyt);
        confirm_password_lyt=findViewById(R.id.confirm_password_lyt);
        password_lyt=findViewById(R.id.password_lyt);

        country_lyt=findViewById(R.id.country_lyt);
        city_lyt=findViewById(R.id.city_lyt);
        address_line_lyt=findViewById(R.id.address_line_lyt);

        Credit_Card_No_lyt=findViewById(R.id.Credit_Card_No_lyt);

        Credit_Card_PIN_No_lyt=findViewById(R.id.Credit_Card_PIN_No_lyt);

        expiration_month_lyt=findViewById(R.id.expiration_month_lyt);

        expiration_year_lyt=findViewById(R.id.expiration_year_lyt);


        gender=findViewById(R.id.gender);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                type=1;
                if(R.id.male==checkedId)
                    userInfo.cgender= String.valueOf(R.string.hint_male);
                else userInfo.cgender= String.valueOf(R.string.hint_female);
            }

        });
        lyt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               submitForm();
            }
        });
        fillData();

    }

    private void fillData() {
       // CProgressDialog.show();
        profileViewModel.getprofile().observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(@Nullable UserInfo userInfo) {
             //  CProgressDialog.dismiss();

                email.setText(userInfo.cemail+"");
                first_name.setText(userInfo.cfirstname);
                last_name.setText(userInfo.clastname);
               // Log.v("ssss",userInfo.cfirstname+" jjjj");
                phone.setText(userInfo.cmobile);
                password.setText(SaveInPrefrence.getPassword());
                Log.v("ssss",userInfo.cgender+"     bbbb ");
                confirm_password.setText(SaveInPrefrence.getPassword());
              if(userInfo.cgender.equals(Constants.MALE))
                    gender.check(R.id.male);
                else gender.check(R.id.female);
                type=1;

                country.setText(userInfo.ccountry+"");
                city.setText(userInfo.ccity+"");
                address_line.setText(userInfo.caddress+"");
                Credit_Card_No.setText(userInfo.cccno+"");
                Credit_Card_PIN_No.setText(userInfo.cseccode+"");
                if(userInfo.expireMM!=0)
                expiration_month.setText(userInfo.expireMM+"");
                if(userInfo.expireYY!=0)
                expiration_year.setText(userInfo.expireYY+"");

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

        if(!validateAddress())
            return;
        if(!validateCredit())
            return;

       // registerViewModel= ViewModelProviders.of(this).get(RegisterViewModel.class);
        //registerViewModel.register(userInfo);
profileViewModel.saveProfile(userInfo);
        hideKeyboard();

    }
    public boolean validateAddress(){
        String str = country.getText().toString().trim();
        if (str.isEmpty()) {
            country_lyt.setError(getString(R.string.invalid_address));
            requestFocus(country);
            return false;
        } else {
            country_lyt.setErrorEnabled(false);
        }
        userInfo.ccountry=str;

         str = city.getText().toString().trim();
        if (str.isEmpty()) {
            city_lyt.setError(getString(R.string.invalid_address));
            requestFocus(city);
            return false;
        } else {
            city_lyt.setErrorEnabled(false);
        }
        userInfo.ccity=str;

         str = address_line.getText().toString().trim();
        if (str.isEmpty()) {
            address_line_lyt.setError(getString(R.string.invalid_address));
            requestFocus(address_line);
            return false;
        } else {
            address_line_lyt.setErrorEnabled(false);
        }
        userInfo.caddress=str;
        return true;

    }

    public boolean validateCredit(){
        String str = Credit_Card_No.getText().toString().trim();
        if (str.isEmpty()) {
            Credit_Card_No_lyt.setError(getString(R.string.invalid_crdit));
            requestFocus(Credit_Card_No);
            return false;
        } else {
            Credit_Card_No_lyt.setErrorEnabled(false);
        }
        userInfo.cccno=str;

        str = Credit_Card_PIN_No.getText().toString().trim();
        if (str.isEmpty()) {
            Credit_Card_PIN_No_lyt.setError(getString(R.string.invalid_crdit));
            requestFocus(Credit_Card_PIN_No);
            return false;
        } else {
            Credit_Card_PIN_No_lyt.setErrorEnabled(false);
        }
        userInfo.cseccode=str;

        str = expiration_year.getText().toString().trim();
        if (str.isEmpty()) {
            expiration_year_lyt.setError(getString(R.string.invalid_crdit));
            requestFocus(expiration_year);
            return false;
        } else {
            expiration_year_lyt.setErrorEnabled(false);
        }
        userInfo.expireYY= Integer.parseInt(str);

        str = expiration_month.getText().toString().trim();
        if (str.isEmpty()) {
            expiration_month_lyt.setError(getString(R.string.invalid_crdit));
            requestFocus(expiration_month);
            return false;
        } else {
            expiration_month_lyt.setErrorEnabled(false);
        }
        userInfo.expireMM= Integer.parseInt(str);
        return true;

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
