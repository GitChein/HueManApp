package com.app.hueman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayPaletteActivity extends AppCompatActivity {

    TextView hexText;

    TextView p11;
    TextView p12;
    TextView p13;
    TextView p14;
    TextView p15;
    TextView p21;
    TextView p22;
    TextView p23;
    TextView p24;
    TextView p25;
    TextView p31;
    TextView p32;
    TextView p33;
    TextView p34;
    TextView p35;
    TextView p41;
    TextView p42;
    TextView p43;
    TextView p44;
    TextView p45;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_palette);

        hexText =  findViewById(R.id.pHexText);

        Intent intent = getIntent();
        String hex = intent.getStringExtra("key");

        int r = Integer.valueOf( hex.substring( 1, 3 ), 16 );
        int g = Integer.valueOf( hex.substring( 3, 5 ), 16 );
        int b = Integer.valueOf( hex.substring( 5, 7 ), 16 );

        p11 = findViewById(R.id.p11);
        p12 = findViewById(R.id.p12);
        p13 = findViewById(R.id.p13);
        p14 = findViewById(R.id.p14);
        p15 = findViewById(R.id.p15);

        p21 = findViewById(R.id.p21);
        p22 = findViewById(R.id.p22);
        p23 = findViewById(R.id.p23);
        p24 = findViewById(R.id.p24);
        p25 = findViewById(R.id.p25);

        p31 = findViewById(R.id.p31);
        p32 = findViewById(R.id.p32);
        p33 = findViewById(R.id.p33);
        p34 = findViewById(R.id.p34);
        p35 = findViewById(R.id.p35);

        p41 = findViewById(R.id.p41);
        p42 = findViewById(R.id.p42);
        p43 = findViewById(R.id.p43);
        p44 = findViewById(R.id.p44);
        p45 = findViewById(R.id.p45);

        int[][][] p = ColorCategorizer.getColorPalettes(r,g,b);

        p11.setBackgroundColor(Color.rgb(p[0][0][0], p[0][0][1], p[0][0][2]));
        p12.setBackgroundColor(Color.rgb(p[0][1][0], p[0][1][1], p[0][1][2]));
        p13.setBackgroundColor(Color.rgb(p[0][2][0], p[0][2][1], p[0][2][2]));
        p14.setBackgroundColor(Color.rgb(p[0][3][0], p[0][3][1], p[0][3][2]));
        p15.setBackgroundColor(Color.rgb(p[0][4][0], p[0][4][1], p[0][4][2]));

        p21.setBackgroundColor(Color.rgb(p[1][0][0], p[1][0][1], p[1][0][2]));
        p22.setBackgroundColor(Color.rgb(p[1][1][0], p[1][1][1], p[1][1][2]));
        p23.setBackgroundColor(Color.rgb(p[1][2][0], p[1][2][1], p[1][2][2]));
        p24.setBackgroundColor(Color.rgb(p[1][3][0], p[1][3][1], p[1][3][2]));
        p25.setBackgroundColor(Color.rgb(p[1][4][0], p[1][4][1], p[1][4][2]));

        p31.setBackgroundColor(Color.rgb(p[2][0][0], p[2][0][1], p[2][0][2]));
        p32.setBackgroundColor(Color.rgb(p[2][1][0], p[2][1][1], p[2][1][2]));
        p33.setBackgroundColor(Color.rgb(p[2][2][0], p[2][2][1], p[2][2][2]));
        p34.setBackgroundColor(Color.rgb(p[2][3][0], p[2][3][1], p[2][3][2]));
        p35.setBackgroundColor(Color.rgb(p[2][4][0], p[2][4][1], p[2][4][2]));

        p41.setBackgroundColor(Color.rgb(p[3][0][0], p[3][0][1], p[3][0][2]));
        p42.setBackgroundColor(Color.rgb(p[3][1][0], p[3][1][1], p[3][1][2]));
        p43.setBackgroundColor(Color.rgb(p[3][2][0], p[3][2][1], p[3][2][2]));
        p44.setBackgroundColor(Color.rgb(p[3][3][0], p[3][3][1], p[3][3][2]));
        p45.setBackgroundColor(Color.rgb(p[3][4][0], p[3][4][1], p[3][4][2]));

        hexText.setText(hex);

    }
}