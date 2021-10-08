package com.example.jokenpower.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jokenpower.Model.Player;
import com.example.jokenpower.R;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textName;
    private TextView textGold;

    private Button buttonPedra, buttonPapel, buttonTesoura;
    private Button buy1, buy2;

    private LinearLayout linearBuy1;
    private LinearLayout linearBuy2;

    private boolean vitoria = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Pegando os dados da MainAcitivity
        Bundle dados = getIntent().getExtras();
        Player player = (Player) dados.getSerializable("player");

        textName = findViewById(R.id.textNome);
        textGold = findViewById(R.id.textGold);

        buttonPedra = findViewById(R.id.buttonPedra);
        buttonPapel = findViewById(R.id.buttonPapel);
        buttonTesoura = findViewById(R.id.buttonTesoura);

        buy1 = findViewById(R.id.buttonBuy1);
        linearBuy1 = findViewById(R.id.linearBuy1);
        buy2 = findViewById(R.id.buttonBuy2);
        linearBuy2 = findViewById(R.id.linearBuy2);

        textName.setText(player.getName().toString());
        textGold.setText("Ouro: "+player.getGold());

        buttonPedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String computerResult = sorteio();

                if(computerResult.equals("Pedra")) {
                    Toast.makeText(getApplicationContext(), "Empate: Pedra x "+computerResult, Toast.LENGTH_SHORT).show();
                }

                else if(computerResult.equals("Papel")) {
                    Toast.makeText(getApplicationContext(), "Você perdeu: Pedra x "+computerResult, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Voce vendeu! Pedra x "+computerResult, Toast.LENGTH_SHORT).show();
                    player.earnGold();
                    textGold.setText("Ouro: "+player.getGold());
                }
            }
        });

        buttonPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String computerResult = sorteio();

                if(computerResult.equals("Papel")) {
                    Toast.makeText(getApplicationContext(), "Empate: Papel x "+computerResult, Toast.LENGTH_SHORT).show();
                }

                else if(computerResult.equals("Tesoura")) {
                    Toast.makeText(getApplicationContext(), "Você perdeu: Papel x "+computerResult, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Voce vendeu! Papel x "+computerResult, Toast.LENGTH_SHORT).show();
                    player.earnGold();
                    textGold.setText("Ouro: "+player.getGold());
                }
            }
        });

        buttonTesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String computerResult = sorteio();

                if(computerResult.equals("Tesoura")) {
                    Toast.makeText(getApplicationContext(), "Empate: Tesoura x "+computerResult, Toast.LENGTH_SHORT).show();
                }

                else if(computerResult.equals("Pedra")) {
                    Toast.makeText(getApplicationContext(), "Você perdeu: Tesoura x "+computerResult, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Voce vendeu! Tesoura x "+computerResult, Toast.LENGTH_SHORT).show();
                    player.earnGold();
                    textGold.setText("Ouro: "+player.getGold());
                }
            }
        });

        buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearBuy1.getAlpha() != 0.3f) {
                    if (player.getGold() >= 10) {
                        linearBuy1.setAlpha(0.3f);
                        player.buyItem(10);
                        textGold.setText("Ouro: " + player.getGold());
                        Toast.makeText(getApplicationContext(), "Item comprado", Toast.LENGTH_SHORT).show();
                        player.addGoldPerWin(1);
                        player.calculateGoldPerWin();
                    } else {
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getApplicationContext(), "Item já foi comprado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearBuy2.getAlpha() != 0.3f) {
                    if (player.getGold() >= 20) {
                        linearBuy2.setAlpha(0.3f);
                        player.buyItem(20);
                        textGold.setText("Ouro: " + player.getGold());
                        Toast.makeText(getApplicationContext(), "Item comprado", Toast.LENGTH_SHORT).show();
                        player.multGoldPerWin(1);
                        player.calculateGoldPerWin();
                    } else {
                        Toast.makeText(getApplicationContext(), "Dinheiro insuficiente", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getApplicationContext(), "Item já foi comprado", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public String sorteio() {
        Random random = new Random();
        String result;
        int randomValue = random.nextInt(3);
        if(randomValue==0) {
            result = "Pedra";
        }
        else if(randomValue==1) {
            result = "Papel";
        }
        else {
            result = "Tesoura";
        }
        return result;
    }
}