package com.rba.androidfingerprintjava;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;

import com.rba.library.AuthenticationEntity;
import com.rba.library.AuthenticationListener;
import com.rba.library.AuthenticationManager;

public class MainActivity extends AppCompatActivity implements AuthenticationListener {

    //BottomSheet
    private static final String TITLE = "Login";
    private static final String SUBTITLE = "Login in the app";
    private static final String DESCRIPTION = "Place your finger or face on the device";
    private static final String BUTTON = "Cancel";

    //Errors
    private static final String ERROR_SDK = "SDK version not supported";
    private static final String ERROR_HARDWARE = "Device doesn't support biometric authentication";
    private static final String ERROR_FINGERPRINT = "Device doesn't have fingerprint";
    private static final String ERROR_PERMISSION = "Permission is not granted by user";
    private static final String ERROR_FAILURE = "Login not successfull";
    private static final String ERROR_CANCEL = "Authentication cancelled by user";

    private static final String SUCCESS = "Login successful";


    private ConstraintLayout clData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        clData = findViewById(R.id.cl_data);


        AppCompatButton btnAuthenticate = findViewById(R.id.btn_authenticate);
        btnAuthenticate.setOnClickListener(view -> {

            AuthenticationEntity authenticationEntity = new AuthenticationEntity.AuthenticationBuilder()
                    .setTitle(TITLE)
                    .setSubtitle(SUBTITLE)
                    .setDescription(DESCRIPTION)
                    .setButtonText(BUTTON)
                    .build();

            AuthenticationManager authenticationManager = new AuthenticationManager();
            authenticationManager.authenticate(MainActivity.this, authenticationEntity, this);

        });

    }

    private void showSnackbar(String message) {
        Snackbar.make(clData, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSdkVersionNotSupported() {
        showSnackbar(ERROR_SDK);
    }

    @Override
    public void onAuthenticationNotSupported() {
        showSnackbar(ERROR_HARDWARE);
    }

    @Override
    public void onAuthenticationNotAvailable() {
        showSnackbar(ERROR_FINGERPRINT);
    }

    @Override
    public void onAuthenticationPermissionNotGranted() {
        showSnackbar(ERROR_PERMISSION);
    }

    @Override
    public void onAuthenticationInternalError(String error) {
        showSnackbar(error);
    }

    @Override
    public void onAuthenticationFailed() {
        showSnackbar(ERROR_FAILURE);
    }

    @Override
    public void onAuthenticationCancelled() {
        showSnackbar(ERROR_CANCEL);
    }

    @Override
    public void onAuthenticationSuccessful() {
        showSnackbar(SUCCESS);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence message) {
        showSnackbar(message.toString());
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence message) {
        showSnackbar(message.toString());
    }

}
