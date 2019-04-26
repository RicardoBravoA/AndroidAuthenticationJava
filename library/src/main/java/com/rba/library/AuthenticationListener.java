package com.rba.library;

public interface AuthenticationListener {

    void onSdkVersionNotSupported();

    void onAuthenticationNotSupported();

    void onAuthenticationNotAvailable();

    void onAuthenticationPermissionNotGranted();

    void onAuthenticationInternalError(String error);

    void onAuthenticationFailed();

    void onAuthenticationCancelled();

    void onAuthenticationSuccessful();

    void onAuthenticationHelp(int helpCode, CharSequence message);

    void onAuthenticationError(int errorCode, CharSequence message);

}
