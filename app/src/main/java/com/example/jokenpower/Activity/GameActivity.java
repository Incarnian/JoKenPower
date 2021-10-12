package com.example.jokenpower.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jokenpower.Model.Player;
import com.example.jokenpower.Model.Upgrade;
import com.example.jokenpower.R;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textName, textGold;

    private TextView textInfo;

    private Button buttonPedra, buttonPapel, buttonTesoura;
    private Button buttonLoja;

    private boolean vitoria = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle dados = getIntent().getExtras();
        Player player = (Player) dados.getSerializable("player");

        textName = findViewById(R.id.textNome);
        textGold = findViewById(R.id.textGold);

        textInfo = findViewById(R.id.textInfo);

        buttonPedra = findViewById(R.id.buttonPedra);
        buttonPapel = findViewById(R.id.buttonPapel);
        buttonTesoura = findViewById(R.id.buttonTesoura);

        buttonLoja = findViewById(R.id.buttonLoja);

        //Verifica os resultados para Pedra
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
        //Verifica os resultados para Papel
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
        //Verifica os resultados para Tesoura
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
        //Vai para a activity loja passando o objeto player
        buttonLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MarketActivity.class);
                intent.putExtra("player",player);
                GameActivity.this.finish();
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle dados = getIntent().getExtras();
        Player player = (Player) dados.getSerializable("player");

        textName.setText(player.getName().toString());
        textGold.setText("Ouro: "+player.getGold());

        //Texto usado em formato html para poder deixar com cores diferentes
        String text;
        text = "Ouro base: <font color = 'yellow'>"+ player.getBaseWin()+"</font>"
        + "<br>Multiplicador: <font color = 'blue'>"+ player.getMultWin() +"</font>"
        +"<br>Ouro por vitória: <font color = 'coral'>" +player.getGoldPerWin()+"</font>"
        + "<br>Crítico: <font color = 'red'>"+ player.getCritChance() + "%</font>";

        textInfo.setText(Html.fromHtml(text));

    }

    //Função para realizar o sorteio
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