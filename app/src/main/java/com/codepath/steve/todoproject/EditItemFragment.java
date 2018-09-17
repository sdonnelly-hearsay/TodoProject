package com.codepath.steve.todoproject;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class EditItemFragment extends DialogFragment {

    public static final String EDIT_ITEM = "editItem";

    private EditText etItem;
    private Button btnSave;

    private OnCompleteListener listener;

    public EditItemFragment() {
    }

    public static EditItemFragment newInstance(String itemText) {
        EditItemFragment fragment = new EditItemFragment();
        Bundle args = new Bundle();
        args.putString(EDIT_ITEM, itemText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_item, container);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (OnCompleteListener) context;
        } catch (final ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etItem = view.findViewById(R.id.etItem);
        String item = getArguments().getString(EDIT_ITEM, "");
        etItem.setText(item);
        etItem.requestFocus();

        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onComplete(etItem.getText().toString());
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        InputMethodManager imm =
                (InputMethodManager) etItem.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }

        super.onDismiss(dialog);
    }

    public interface OnCompleteListener {
        void onComplete(String itemText);
    }
}
