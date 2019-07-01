package com.eldeeb.bokhour.view;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.eldeeb.bokhour.MainActivity;
import com.eldeeb.bokhour.R;
import com.eldeeb.bokhour.utils.Constants;
import com.eldeeb.bokhour.utils.Tools;
import com.eldeeb.bokhour.viewModels.LoginViewModel;

public class Login extends AppCompatActivity {
    MaterialRippleLayout lyt_login;
EditText password,email;
LoginViewModel loginViewModel;
TextView new_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Constants.context=this;
        lyt_login=findViewById(R.id.lyt_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        new_user=findViewById(R.id.new_user);
        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);
        lyt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void login() {
       loginViewModel.login(email.getText().toString().trim(),password.getText().toString().trim());
    }

}
