package com.example.jokenpower.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jokenpower.Enums.GameChoices;
import com.example.jokenpower.Model.Player;
import com.example.jokenpower.Model.Upgrade;
import com.example.jokenpower.R;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ImageView rockBtn, paperBtn, scissorsBtn, shopIcon, cpuImg;
    private TextView textName, textGold, textInfo, textResult;

    private boolean vitoria = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle dados = getIntent().getExtras();
        Player player = (Player) dados.getSerializable("player");

        textName = findViewById(R.id.textNome);
        textGold = findViewById(R.id.textGold);
        textResult = findViewById(R.id.text_result);
        textInfo = findViewById(R.id.textInfo);

        rockBtn = findViewById(R.id.rock_btn);
        paperBtn = findViewById(R.id.paper_btn);
        scissorsBtn = findViewById(R.id.scissors_btn);
        cpuImg = findViewById(R.id.cpu_choice);

        shopIcon = findViewById(R.id.shop_icon);

        rockBtn.setOnClickListener(v -> determineWinner(GameChoices.ROCK, player));
        paperBtn.setOnClickListener(v -> determineWinner(GameChoices.PAPER, player));
        scissorsBtn.setOnClickListener(v -> determineWinner(GameChoices.SCISSORS, player));

        // Vai para a activity loja passando o objeto player
        shopIcon.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MarketActivity.class);
            intent.putExtra("player",player);
            GameActivity.this.finish();
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle dados = getIntent().getExtras();
        Player player = (Player) dados.getSerializable("player");

        textName.setText(player.getName().toString());
        textGold.setText(String.valueOf(player.getGold()));

        //Texto usado em formato html para poder deixar com cores diferentes
        String text;
        text = "Ouro base: <font color = 'yellow'>"+ player.getBaseWin()+"</font>"
        + "<br>Multiplicador: <font color = 'blue'>"+ player.getMultWin() +"</font>"
        +"<br>Ouro por vitória: <font color = 'coral'>" +player.getGoldPerWin()+"</font>"
        + "<br>Crítico: <font color = 'red'>"+ player.getCritChance() + "%</font>";

        textInfo.setText(Html.fromHtml(text));

    }

    private void determineWinner(GameChoices userChoice, Player player) {
        GameChoices cpuChoice = null;

        int randomValue = new Random().nextInt(3);
        switch (randomValue) {
            case 0:
                cpuChoice = GameChoices.ROCK;
                break;
            case 1:
                cpuChoice = GameChoices.PAPER;
                break;

            case 2:
                cpuChoice = GameChoices.SCISSORS;
                break;
        }

        if(userChoice == GameChoices.ROCK) {
            if(cpuChoice == GameChoices.ROCK) {
                cpuImg.setImageResource(R.drawable.pedra);
                textResult.setText("EMPATE!");
            }

            if(cpuChoice == GameChoices.PAPER) {
                cpuImg.setImageResource(R.drawable.papel);
                textResult.setText("VOCÊ PERDEU!");
            }

            if(cpuChoice == GameChoices.SCISSORS) {
                player.earnGold();
                textGold.setText(String.valueOf(player.getGold()));
                cpuImg.setImageResource(R.drawable.tesoura);
                textResult.setText("VOCÊ GANHOU!");

            }
        }

        if(userChoice == GameChoices.PAPER) {
            if(cpuChoice == GameChoices.PAPER) {
                cpuImg.setImageResource(R.drawable.papel);
                textResult.setText("EMPATE!");
            }

            if(cpuChoice == GameChoices.SCISSORS) {
                cpuImg.setImageResource(R.drawable.tesoura);
                textResult.setText("VOCÊ PERDEU!");
            }

            if(cpuChoice == GameChoices.ROCK) {
                player.earnGold();
                textGold.setText(String.valueOf(player.getGold()));
                cpuImg.setImageResource(R.drawable.pedra);
                textResult.setText("VOCÊ GANHOU!");
            }
        }

        if(userChoice == GameChoices.SCISSORS) {
            if(cpuChoice == GameChoices.SCISSORS) {
                cpuImg.setImageResource(R.drawable.tesoura);
                textResult.setText("EMPATE!");
            }

            if(cpuChoice == GameChoices.ROCK) {
                cpuImg.setImageResource(R.drawable.pedra);
                textResult.setText("VOCÊ PERDEU!");
            }

            if(cpuChoice == GameChoices.PAPER) {
                player.earnGold();
                textGold.setText(String.valueOf(player.getGold()));
                cpuImg.setImageResource(R.drawable.papel);
                textResult.setText("VOCÊ GANHOU!");
            }
        }

    }
}