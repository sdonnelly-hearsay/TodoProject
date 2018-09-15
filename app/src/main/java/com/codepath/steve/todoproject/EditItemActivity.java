package com.codepath.steve.todoproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    public static final String EDIT_ITEM = "editItem";

    private EditText etItem;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        etItem = findViewById(R.id.etItem);
        btnSave = findViewById(R.id.btnSave);

        String itemText = getIntent().getStringExtra(EDIT_ITEM);
        etItem.setText(itemText);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra(EDIT_ITEM, etItem.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
