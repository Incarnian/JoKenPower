package com.example.jokenpower.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jokenpower.Model.Player;
import com.example.jokenpower.Model.Upgrade;
import com.example.jokenpower.R;

import java.util.ArrayList;

public class MarketActivity extends AppCompatActivity {

    ImageView buttonVoltar;
    Button buttonComprar1,buttonComprar2,buttonComprar3, buttonComprar4, buttonComprar5;
    TextView textOuro;
    ArrayList<Upgrade> upgrades = new ArrayList<>();
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        buttonVoltar = findViewById(R.id.imageSword);

        buttonComprar1 = findViewById(R.id.buttonComprar1);
        buttonComprar2 = findViewById(R.id.buttonComprar2);
        buttonComprar3 = findViewById(R.id.buttonComprar3);
        buttonComprar4 = findViewById(R.id.buttonComprar4);
        buttonComprar5 = findViewById(R.id.buttonComprar5);

        textOuro = findViewById(R.id.textOuro);


        //Recupera as informações do player
        Bundle dados = getIntent().getExtras();
        Player player = (Player) dados.getSerializable("player");
        addUpgrades();

        textOuro.setText(String.valueOf(player.getGold()));

        //Diminuir a opacidade dos itens compraddos
        if(player.checkUpgrades(0)){
            buttonComprar1.setAlpha(0.3f);
        }
        if(player.checkUpgrades(1)){
            buttonComprar2.setAlpha(0.3f);
        }
        if(player.checkUpgrades(2)){
            buttonComprar3.setAlpha(0.3f);
        }
        if(player.checkUpgrades(3)){
            buttonComprar4.setAlpha(0.3f);
        }
        if(player.checkUpgrades(4)){
            buttonComprar5.setAlpha(0.3f);
        }

        buttonComprar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!player.checkUpgrades(0)) {
                    if(player.canBuy(upgrades.get(0))){
                        player.buyItem(upgrades.get(0));
                        player.changeUpgrades(0);
                        buttonComprar1.setAlpha(0.3f);
                        player.refreshGoldPerWin();
                        textOuro.setText(String.valueOf(player.getGold()));
                        Toast.makeText(getApplicationContext(), "Compra feita com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente", Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    Toast.makeText(getApplicationContext(), "Esse item já foi comprado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonComprar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!player.checkUpgrades(1)) {
                    if(player.canBuy(upgrades.get(1))){
                        player.buyItem(upgrades.get(1));
                        player.changeUpgrades(1);
                        buttonComprar2.setAlpha(0.3f);
                        player.refreshGoldPerWin();
                        textOuro.setText(String.valueOf(player.getGold()));
                        Toast.makeText(getApplicationContext(), "Compra feita com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente", Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    Toast.makeText(getApplicationContext(), "Esse item já foi comprado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonComprar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!player.checkUpgrades(2)) {
                    if(player.canBuy(upgrades.get(2))){
                        player.buyItem(upgrades.get(2));
                        player.changeUpgrades(2);
                        buttonComprar3.setAlpha(0.3f);
                        player.refreshGoldPerWin();
                        textOuro.setText(String.valueOf(player.getGold()));
                        Toast.makeText(getApplicationContext(), "Compra feita com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente", Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    Toast.makeText(getApplicationContext(), "Esse item já foi comprado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonComprar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!player.checkUpgrades(3)) {
                    if(player.canBuy(upgrades.get(3))){
                        player.buyItem(upgrades.get(3));
                        player.changeUpgrades(3);
                        buttonComprar4.setAlpha(0.3f);
                        player.refreshGoldPerWin();
                        textOuro.setText(String.valueOf(player.getGold()));
                        Toast.makeText(getApplicationContext(), "Compra feita com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente", Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    Toast.makeText(getApplicationContext(), "Esse item já foi comprado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonComprar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!player.checkUpgrades(4)) {
                    if(player.canBuy(upgrades.get(4))){
                        player.buyItem(upgrades.get(4));
                        player.changeUpgrades(4);
                        buttonComprar5.setAlpha(0.3f);
                        player.refreshGoldPerWin();
                        textOuro.setText(String.valueOf(player.getGold()));
                        Toast.makeText(getApplicationContext(), "Compra feita com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente", Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    Toast.makeText(getApplicationContext(), "Esse item já foi comprado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("player", player);
                startActivity(intent);
                finish();
            }
        });
   }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.faroeste3);
        playMusic();
    }

    private void playMusic() {
        if( mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }

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

    //Cria os upgrades para colocar na loja, porém não cria os botões nem os textos
   public void addUpgrades() {
        Upgrade upgrade = new Upgrade(10,1,0,0);
        upgrades.add(upgrade);

        upgrade = new Upgrade(20,0,1,0);
        upgrades.add(upgrade);

       upgrade = new Upgrade(50,0,0,10);
       upgrades.add(upgrade);

       upgrade = new Upgrade(100,1,1,0);
       upgrades.add(upgrade);

       upgrade = new Upgrade(1000,1,1,20);
       upgrades.add(upgrade);

   }
}