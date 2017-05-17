package com.example.sabrina.davincimarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;
import models.Product;

/**
 * Created by Sabrina on 17/05/2017.
 */

public class CarroActivity extends AppCompatActivity{

  Bundle bun;

  List<Product> productosSelec = new ArrayList<>();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.carro_activity);
    bun = getIntent().getExtras();
    RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_carro);

    String[] produSelecc = (String[]) bun.get("produ");

    int a = 0;
    int i = 0;
    int b = 50;

    for (String p: produSelecc) {

      Button btn = new Button(this);
      RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
      //btn.setId(i);

      //btn.setOnClickListener(this);
      if(i % 2 == 0 ){
        a = (i * 100);
        params1.setMargins(b, a, 0, a+500);
        btn.setText(p);
        rl.addView(btn,params1);
        b = 400;
      }else {
        params1.setMargins(b, a, 0, a+500);
        btn.setText(p);
        rl.addView(btn,params1);
        b = 50;

      }
      i++;

    }
  }








}
