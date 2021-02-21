package io.github.lapalb.saransh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button searchBtn;
    EditText editText;
    String editTextVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchBtn = findViewById(R.id.serachBtn);
        editText = findViewById(R.id.editText);
    }
    public void transitionToView(View v){
        Intent i = new Intent(this, DetailMainActivity.class);
        editTextVal = editText.getText().toString();
        i.putExtra("username", editTextVal);
        startActivity(i);
        finish();
    }
}
