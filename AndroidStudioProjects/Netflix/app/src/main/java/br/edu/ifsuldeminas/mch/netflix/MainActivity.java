package br.edu.ifsuldeminas.mch.netflix;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Button btnTrabalhoNetflix = findViewById(R.id.btnTrabalhoNetflix);
        btnTrabalhoNetflix.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NetflixActivity.class);
            startActivity(intent);
        });

        Button btnTrabalho02 = findViewById(R.id.btnTrabalho02);
        btnTrabalho02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Trabalho02Activity.class);
                startActivity(intent);
            }
        });

        Button btnTrabalho03 = findViewById(R.id.btnTrabalho03);
        btnTrabalho03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Trabalho04Activity.class);
                startActivity(intent);
            }
        });


    }


}
