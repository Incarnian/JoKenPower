package com.example.jokenpower.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
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
    private MediaPlayer mediaPlayer;

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
                    try {
                        Player player = new Player(editTextName.getText().toString(), 0, 0, 1);
                        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                        intent.putExtra("player", player);
                        startActivity(intent);
                    }
                    catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"Nome inválido",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.faroeste);
        playMusic();
    }

    public void playMusic() {
        if( mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if( mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}