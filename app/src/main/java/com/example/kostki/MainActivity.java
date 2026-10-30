package com.example.kostki;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editTextLiczbaKostek;
    Button buttonLosuj, buttonRosnaco, buttonMalejaco;
    GridLayout gridKostki;
    TextView textViewWyniki;

    ArrayList<Integer> wynikiKostek;
    boolean czyWylosowano = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        buttonLosuj = findViewById(R.id.buttonLosuj);
        buttonRosnaco = findViewById(R.id.buttonRosnaco);
        buttonMalejaco = findViewById(R.id.buttonMalejaco);
        gridKostki = findViewById(R.id.gridKostki);
        textViewWyniki = findViewById(R.id.textViewWyniki);

        wynikiKostek = new ArrayList<>();

        buttonLosuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tekst = editTextLiczbaKostek.getText().toString();

                if (tekst.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Wpisz liczbę kostek!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int liczba = Integer.parseInt(tekst);

                if (liczba <= 0 || liczba > 100) {
                    Toast.makeText(MainActivity.this, "Liczba kostek musi być od 1 do 100!", Toast.LENGTH_SHORT).show();
                    return;
                }

                wynikiKostek.clear();
                gridKostki.removeAllViews();
                Random rand = new Random();

                for (int i = 0; i < liczba; i++) {
                    int wylosowanaLiczba = rand.nextInt(6) + 1; // od 1 do 6
                    wynikiKostek.add(wylosowanaLiczba);

                    TextView kostka = new TextView(MainActivity.this);
                    kostka.setText(String.valueOf(wylosowanaLiczba));
                    kostka.setTextSize(16);
                    kostka.setPadding(10, 10, 10, 10);
                    gridKostki.addView(kostka);
                }

                wyswietlTekstowo();
                czyWylosowano = true;
            }
        });

        buttonRosnaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!czyWylosowano) {
                    Toast.makeText(MainActivity.this, "Najpierw wylosuj kostki!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Collections.sort(wynikiKostek);
                odswiezWidokGrid();
                wyswietlTekstowo();
            }
        });

        buttonMalejaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!czyWylosowano) {
                    Toast.makeText(MainActivity.this, "Najpierw wylosuj kostki!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Collections.sort(wynikiKostek, Collections.reverseOrder());
                odswiezWidokGrid();
                wyswietlTekstowo();
            }
        });
    }

    private void wyswietlTekstowo() {
        String wynikS = "";
        for (int i = 0; i < wynikiKostek.size(); i++) {
            wynikS += wynikiKostek.get(i);
            if (i < wynikiKostek.size() - 1) {
                wynikS += ", ";
            }
        }
        textViewWyniki.setText(wynikS);
    }

    private void odswiezWidokGrid() {
        gridKostki.removeAllViews();
        for (int i = 0; i < wynikiKostek.size(); i++) {
            TextView kostka = new TextView(MainActivity.this);
            kostka.setText(String.valueOf(wynikiKostek.get(i)));
            kostka.setTextSize(16);
            kostka.setPadding(10, 10, 10, 10);
            gridKostki.addView(kostka);
        }
    }
}