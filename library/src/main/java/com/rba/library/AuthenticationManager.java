package com.rba.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;

public class AuthenticationManager extends AuthenticationManagerV23 {

    private Context context;
    private AuthenticationEntity authenticationEntity;


    public void authenticate(@NonNull Context context, @NonNull AuthenticationEntity authenticationEntity,
                             @NonNull final AuthenticationListener authenticationListener) {

        this.context = context;
        this.authenticationEntity = authenticationEntity;

        if (authenticationEntity.getTitle() == null) {
            authenticationListener.onAuthenticationInternalError("Authentication Dialog title cannot be null");
        }


        if (authenticationEntity.getSubtitle() == null) {
            authenticationListener.onAuthenticationInternalError("Biometric Dialog subtitle cannot be null");
        }


        if (authenticationEntity.getDescription() == null) {
            authenticationListener.onAuthenticationInternalError("Biometric Dialog description cannot be null");
        }

        if (authenticationEntity.getButtonText() == null) {
            authenticationListener.onAuthenticationInternalError("Biometric Dialog negative button text cannot be null");
        }


        if (!AuthenticationUtil.isSdkVersionSupported()) {
            authenticationListener.onSdkVersionNotSupported();
        }

        if (!AuthenticationUtil.isPermissionGranted(context)) {
            authenticationListener.onAuthenticationPermissionNotGranted();
        }

        if (!AuthenticationUtil.isHardwareSupported(context)) {
            authenticationListener.onAuthenticationNotSupported();
        }

        if (!AuthenticationUtil.isFingerprintAvailable(context)) {
            authenticationListener.onAuthenticationNotAvailable();
        }

        displayBiometricDialog(authenticationListener);
    }


    private void displayBiometricDialog(@NonNull AuthenticationListener authenticationListener) {
        if (AuthenticationUtil.isBiometricPromptEnabled()) {
            displayBiometricPrompt(authenticationListener);
        } else {
            displayBiometricPromptV23(context, authenticationEntity, authenticationListener);
        }
    }

    @TargetApi(Build.VERSION_CODES.P)
    private void displayBiometricPrompt(final AuthenticationListener biometricCallback) {
        new BiometricPrompt.Builder(context)
                .setTitle(authenticationEntity.getTitle())
                .setSubtitle(authenticationEntity.getSubtitle())
                .setDescription(authenticationEntity.getDescription())
                .setNegativeButton(authenticationEntity.getButtonText(), context.getMainExecutor(),
                        (DialogInterface dialogInterface, int i) ->
                                biometricCallback.onAuthenticationCancelled()
                )
                .build()
                .authenticate(new CancellationSignal(), context.getMainExecutor(),
                        new AuthenticationListenerV28(biometricCallback));
    }

}
