package com.rba.library;

import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.P)
public class AuthenticationListenerV28 extends BiometricPrompt.AuthenticationCallback {

    private AuthenticationListener authenticationListener;

    public AuthenticationListenerV28(AuthenticationListener authenticationListener) {
        this.authenticationListener = authenticationListener;
    }


    @Override
    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        authenticationListener.onAuthenticationSuccessful();
    }


    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        super.onAuthenticationHelp(helpCode, helpString);
        authenticationListener.onAuthenticationHelp(helpCode, helpString);
    }


    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        authenticationListener.onAuthenticationError(errorCode, errString);
    }


    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        authenticationListener.onAuthenticationFailed();
    }

}
