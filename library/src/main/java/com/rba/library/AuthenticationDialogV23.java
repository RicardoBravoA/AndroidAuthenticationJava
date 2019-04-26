package com.rba.library;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.TextView;

public class AuthenticationDialogV23 extends BottomSheetDialog {

    private Context context;
    private AppCompatButton btnCancel;
    private AppCompatImageView ivLogo;
    private TextView tvTitle;
    private TextView tvSubtitle;
    private TextView tvDescription;
    private TextView tvStatus;

    private AuthenticationListener authenticationListener;

    public AuthenticationDialogV23(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public AuthenticationDialogV23(@NonNull Context context, AuthenticationListener authenticationListener) {
        super(context);
        this.context = context;
        this.authenticationListener = authenticationListener;
        init();
    }

    public AuthenticationDialogV23(@NonNull Context context, int theme) {
        super(context, theme);
        init();
    }

    protected AuthenticationDialogV23(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
        setContentView(view);

        btnCancel = findViewById(R.id.btn_cancel);

        if (btnCancel != null) {
            btnCancel.setOnClickListener(v -> {
                dismiss();
                if (authenticationListener != null) {
                    authenticationListener.onAuthenticationCancelled();
                }
            });
        }

        ivLogo = findViewById(R.id.iv_logo);
        tvTitle = findViewById(R.id.tv_title);
        tvStatus = findViewById(R.id.tv_status);
        tvSubtitle = findViewById(R.id.tv_subtitle);
        tvDescription = findViewById(R.id.tv_description);

        updateLogo();
    }

    public void setData(AuthenticationEntity authenticationEntity) {
        if (authenticationEntity.getTitle() != null) {
            tvTitle.setText(authenticationEntity.getSubtitle());
        }

        if (authenticationEntity.getSubtitle() != null) {
            tvSubtitle.setText(authenticationEntity.getSubtitle());
        }

        if (authenticationEntity.getSubtitle() != null) {
            tvSubtitle.setText(authenticationEntity.getSubtitle());
        }

        if (authenticationEntity.getDescription() != null) {
            tvDescription.setText(authenticationEntity.getDescription());
        }

        if (authenticationEntity.getButtonText() != null) {
            btnCancel.setText(authenticationEntity.getButtonText());
        }

    }

    public void setStatus(String status) {
        tvStatus.setText(status);
    }

    private void updateLogo() {
        try {
            Drawable drawable = getContext().getPackageManager().getApplicationIcon(context.getPackageName());
            ivLogo.setImageDrawable(drawable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
