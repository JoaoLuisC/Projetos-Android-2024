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
        btnTrabalhoNetflix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NetflixActivity.class);
                startActivity(intent);
            }
        });


    }


}
