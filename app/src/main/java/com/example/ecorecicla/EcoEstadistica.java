package com.example.ecorecicla;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class EcoEstadistica extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_estadistica);
       PieChart pieChart = findViewById(R.id.graficopastel);
        ImageButton btnback = findViewById(R.id.Buttonbackest);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EcoEstadistica.this, MenuActivity.class);
                startActivity(i);
                finish();

            }
        });




        //Grafica de torta
        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry(508,"plastico"));
        visitors.add(new PieEntry(600, "Carton"));
        visitors.add(new PieEntry(400, "papel"));
        visitors.add(new PieEntry(380,"Vidrio"));
        visitors.add(new PieEntry(250,"metal"));

        PieDataSet pieDataSet = new PieDataSet(visitors,"visitors");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("categorias");
        pieChart.setCenterTextSize(20f);
        pieChart.animate();







    }


}