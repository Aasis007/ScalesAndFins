package com.example.aasis.scalesandfins;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aasis.scalesandfins.Model.CheckUserResponse;
import com.example.aasis.scalesandfins.Model.User;
import com.example.aasis.scalesandfins.Retrofit.ScalesAPI;
import com.example.aasis.scalesandfins.Utls.Common;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 2000;
    Button login;
    ScalesAPI mservice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mservice = Common.getAPI();
        login = (Button)findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoginPage(LoginType.PHONE);
            }
        });


        //check account kit session

        if (AccountKit.getCurrentAccessToken() != null)
        {

            final android.app.AlertDialog alertDialog = new SpotsDialog(MainActivity.this);
            alertDialog.show();
            alertDialog.setMessage("Please Wait...");
            //auto login

            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    mservice.checkUserExists(account.getPhoneNumber().toString())
                            .enqueue(new Callback<CheckUserResponse>() {
                                @Override
                                public void onResponse(@NonNull Call<CheckUserResponse> call, @NonNull Response<CheckUserResponse> response) {
                                    CheckUserResponse userResponse = response.body();
                                    if (userResponse.isExists())
                                    {
                                        //fetch user info
                                        mservice.getUSerInfo(account.getPhoneNumber().toString())
                                                .enqueue(new Callback<User>() {
                                                    @Override
                                                    public void onResponse(Call<User> call, Response<User> response) {

                                                        //if user exist
                                                        alertDialog.dismiss();
                                                        Common.currentuUser = response.body();
                                                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                                        finish();
                                                    }

                                                    @Override
                                                    public void onFailure(Call<User> call, Throwable t) {


                                                    }
                                                });

                                    }
                                    else
                                    {   //show register dialog
                                        alertDialog.dismiss();
                                        showRegisterDialog(account.getPhoneNumber().toString());
                                        Toast.makeText(MainActivity.this, "Please Register", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<CheckUserResponse> call, @NonNull Throwable t) {
                                    Log.d("error",t.getMessage());
                                    alertDialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                                }
                            });
                }

                @Override
                public void onError(AccountKitError accountKitError) {
                    Log.d("loginERROR",accountKitError.getErrorType().getMessage());

                }
            });
        }
    }






    private void startLoginPage(LoginType phone) {
        Intent intent =  new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder builder = new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE,AccountKitActivity.ResponseType.TOKEN);
                        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,builder.build());
                        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE)
        {
            AccountKitLoginResult result = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if (result.getError() != null)
            {
                Toast.makeText(this, ""+result.getError().getErrorType().getMessage(), Toast.LENGTH_SHORT).show();
            }
            else if (result.wasCancelled())
            {
                Toast.makeText(this, "Login canceld by user ", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if (result.getAccessToken() != null)
                {
                    final android.app.AlertDialog alertDialog = new SpotsDialog(MainActivity.this);
                    alertDialog.show();
                    alertDialog.setMessage("Please Wait...");

                        //Get user phone and check existing user
                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                        @Override
                        public void onSuccess(final Account account) {
                                mservice.checkUserExists(account.getPhoneNumber().toString())
                                        .enqueue(new Callback<CheckUserResponse>() {
                                            @Override
                                            public void onResponse(@NonNull Call<CheckUserResponse> call, @NonNull Response<CheckUserResponse> response) {
                                                CheckUserResponse userResponse = response.body();
                                                if (userResponse.isExists())
                                                {
                                                    //fetch user info
                                                    mservice.getUSerInfo(account.getPhoneNumber().toString())
                                                                .enqueue(new Callback<User>() {
                                                                    @Override
                                                                    public void onResponse(Call<User> call, Response<User> response) {

                                                                        //if user exist
                                                                        alertDialog.dismiss();
                                                                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                                                        finish();
                                                                    }

                                                                    @Override
                                                                    public void onFailure(Call<User> call, Throwable t) {


                                                                    }
                                                                });

                                                }
                                                else
                                                {   //show register dialog
                                                    alertDialog.dismiss();
                                                    showRegisterDialog(account.getPhoneNumber().toString());
                                                    Toast.makeText(MainActivity.this, "Please Register", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NonNull Call<CheckUserResponse> call, @NonNull Throwable t) {
                                               Log.d("error",t.getMessage());
                                               alertDialog.dismiss();
                                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                                            }
                                        });
                        }

                        @Override
                        public void onError(AccountKitError accountKitError) {
                                Log.d("loginERROR",accountKitError.getErrorType().getMessage());

                        }
                    });
                }
            }
        }
    }

    private void showRegisterDialog(final String phone) {

         final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Register");




        View register_layout = getLayoutInflater().inflate(R.layout.register,null);


        final MaterialEditText edt_name = (MaterialEditText)register_layout.findViewById(R.id.edt_name);
        final MaterialEditText edt_address = (MaterialEditText)register_layout.findViewById(R.id.edt_address);
        final MaterialEditText edt_birthdate = (MaterialEditText)register_layout.findViewById(R.id.edt_birthdate);

        Button register = (Button)register_layout.findViewById(R.id.btn_register);

        edt_birthdate.addTextChangedListener(new PatternedTextWatcher("####-##-##"));

        builder.setView(register_layout);

        final  AlertDialog dialog = builder.create();


        //event


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();



                if (TextUtils.isEmpty(edt_name.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please Enter your Name", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(edt_address.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please Enter your Address", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(edt_birthdate.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please Enter your Birthdate", Toast.LENGTH_SHORT).show();
                    return;
                }

                final android.app.AlertDialog waitdialog = new SpotsDialog(MainActivity.this);
                waitdialog.show();
                waitdialog.setMessage("Please Wait");

               mservice.registerNewUser(phone,
                                        edt_name.getText().toString(),
                                        edt_address.getText().toString(),
                                        edt_birthdate.getText().toString())
                                        .enqueue(new Callback<User>() {
                                            @Override
                                            public void onResponse(Call<User> call, Response<User> response) {
                                                                waitdialog.dismiss();
                                                                User user = response.body();
                                                                if (TextUtils.isEmpty(user.getError_msg()))
                                                                {

                                                                    Toast.makeText(MainActivity.this, "Registered Successfuly", Toast.LENGTH_SHORT).show();


                                                                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                                                    finish();
                                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<User> call, Throwable t) {
                                                        waitdialog.dismiss();
                                            }
                                        });
            }

        });


                dialog.show();
    }
}
