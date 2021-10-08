package com.example.jokenpower.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jokenpower.Model.Player;
import com.example.jokenpower.R;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private Button buttonEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        buttonEnter = findViewById(R.id.buttonEnter);

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Insira um nome", Toast.LENGTH_SHORT).show();
                }

                else{
                    Player player = new Player(editTextName.getText().toString(),0, 0, 1);
                    Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                    intent.putExtra("player",player);
                    startActivity(intent);
                }
            }
        });


    }
}