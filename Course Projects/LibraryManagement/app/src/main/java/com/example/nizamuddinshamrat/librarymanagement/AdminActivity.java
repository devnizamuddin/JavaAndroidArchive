package com.example.nizamuddinshamrat.librarymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void addNewBooks(View view) {

        startActivity(new Intent(AdminActivity.this,BookActivity.class));

    }

    public void UpdateBooksInfo(View view) {

        Intent intent = new Intent(AdminActivity.this,ListActivity.class);
        intent.putExtra("Admin",true);
        startActivity(intent);

    }
}
