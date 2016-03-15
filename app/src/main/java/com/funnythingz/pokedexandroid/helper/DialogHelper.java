package com.funnythingz.pokedexandroid.helper;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class DialogHelper extends DialogFragment {

    private static String TITLE = "title";
    private static String MESSAGE = "message";
    private static String CANCELABLE = "cancelable";

    public static DialogHelper newInstance(@Nullable String title, String message, boolean cancelable) {
        DialogHelper fragment = new DialogHelper();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(MESSAGE, message);
        args.putBoolean(CANCELABLE, cancelable);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle safedInstanceState) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle(getArguments().getString(TITLE));
        progressDialog.setMessage(getArguments().getString(MESSAGE));
        progressDialog.setCancelable(getArguments().getBoolean(CANCELABLE));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return progressDialog;
    }

}
