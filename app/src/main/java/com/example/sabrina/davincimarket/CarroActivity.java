package com.example.sabrina.davincimarket;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.CartItem;
import models.Product;
import utils.CartListUtils;

/**
 * Created by Sabrina on 17/05/2017.
 */

public class CarroActivity extends AppCompatActivity {

  Bundle bun;
  private CartListUtils cartListUtils = new CartListUtils();
  public Button btnClear;
  Intent in2;

  Context ctx = this;

  @Override
  public void onBackPressed() {
    super.onBackPressed();

    Intent in = new Intent(CarroActivity.this, ProductoActivity.class);
    in.putExtras(bun);
    startActivity(in);

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.lista:

        in2= new Intent(CarroActivity.this,CarroActivity.class);
        in2.putExtras(bun);
        startActivity(in2);
        break;
      case R.id.settings:
        in2 = new Intent(CarroActivity.this,SettingsActivity.class);
        in2.putExtras(bun);
        startActivity(in2);

      case R.id.aboutme:
        in2 = new Intent(CarroActivity.this,AboutMeActivity.class);
        in2.putExtras(bun);
        startActivity(in2);
        break;
    }
    return true;
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menutoolbar, menu);

    return super.onCreateOptionsMenu(menu);


  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.carro_activity);

    Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
    setSupportActionBar(myToolbar);

    bun = getIntent().getExtras();
    RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_carro);


    cartListUtils.initDb(this);

    List<CartItem> cartItems = cartListUtils.getAll();


    int a = 0;
    int i = 0;
    int b = 50;

    for (CartItem cart : cartItems) {
      final int listItemId = cart.getId();
      Button btn = new Button(this);
      RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

      btn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
         cartListUtils.deleteItem(listItemId);
         view.setVisibility(View.GONE);
       }
     });

      //btn.setId(i);

      //btn.setOnClickListener(this);
      if (i % 2 == 0) {
        a = (i * 100);
        params1.setMargins(b, a, 0, a + 500);

        btn.setText(cart.getName());
        rl.addView(btn, params1);
        b = 400;
      } else {
        params1.setMargins(b, a, 0, a + 500);
        btn.setText(cart.getName());
        rl.addView(btn, params1);
        b = 50;

      }
      i++;

    }

    //limpiar pedido
    btnClear = (Button) findViewById(R.id.btnClear);

    btnClear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        new AlertDialog.Builder(ctx)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Vaciar Carrito")
                .setMessage("Esta seguro que desea vaciar el carrito?")
                .setPositiveButton("Mmm Si", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    cartListUtils.clearDb();
                    Intent in = new Intent(CarroActivity.this, CategoriaActivity.class);
                    in.putExtras(bun);
                    startActivity(in);
                  }
                })
                .setNegativeButton("No, no aún!", null)
                .show();
      }
    });

  }


}
