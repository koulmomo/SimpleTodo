package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    String initialTodoItemText;
    String todoItemText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar editItemToolbar = (Toolbar) findViewById(R.id.editItemToolbar);
        editItemToolbar.setTitle("Edit Todo Item #" + (getIntent().getIntExtra("todoPosition", 0) + 1));

        EditText todoItemInput = (EditText) findViewById(R.id.editTodoItem);
        initialTodoItemText = getIntent().getStringExtra("todoItem");
        todoItemText = initialTodoItemText;
        todoItemInput.setText(todoItemText);
        todoItemInput.setSelection(todoItemInput.getText().length());

        todoItemInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                todoItemText = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean hasChanged() {
        return !todoItemText.equals(getIntent().getStringExtra("todoItem"));
    }

    public void onEditItemSave(View v) {
        // closes the activity and returns to first screen

        if (this.hasChanged()) {
            Intent data = new Intent();
            Log.i("EditItemActivity", "onEditItemSave: " + todoItemText);
            data.putExtra("todoItem", todoItemText);
            data.putExtra("todoPosition", getIntent().getIntExtra("todoPosition", -1));
            setResult(RESULT_OK, data);
        }

        this.finish();
    }
}
