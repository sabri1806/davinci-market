package com.example.sabrina.davincimarket;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dbHelpers.CategorySQLiteHelper;
import models.Category;
import models.Product;
import utils.CartListUtils;
import utils.CategoryUtils;
import utils.ProductUtils;

/**
 * Created by Sabrina on 13/05/2017.
 */

public class CategoriaActivity extends AppCompatActivity {

  public TextView nom;
  public TextView categorias;
  List<Category> categories = new ArrayList<>();
  private CategoryUtils catUtil = new CategoryUtils();
  private ProductUtils prodUtil = new ProductUtils();
  private CartListUtils cartListUtils = new CartListUtils();
  Bundle bun;
  Intent in2;


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.lista:
      //  Toast.makeText(this, "EMail", Toast.LENGTH_LONG).show();
        in2= new Intent(CategoriaActivity.this,CarroActivity.class);
        in2.putExtras(bun);
        startActivity(in2);
        break;
      case R.id.settings:
        in2 = new Intent(CategoriaActivity.this,SettingsActivity.class);
        in2.putExtras(bun);
        startActivity(in2);

      case R.id.aboutme:
        in2 = new Intent(CategoriaActivity.this,AboutMeActivity.class);
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
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.categoria_activity);

    Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
    setSupportActionBar(myToolbar);

    catUtil.initDb(this);
    this.categories = catUtil.getAll();
    prodUtil.initDb(this, categories);
    nom = (TextView) findViewById(R.id.nombre);

    cartListUtils.initDb(this);

    cartListUtils.clearDb();
    bun = getIntent().getExtras();
    final Bundle bun2 = getIntent().getExtras();


    String datoNombre = (String) bun.get("usuario");

    nom.setText(datoNombre);


    categorias = (TextView) findViewById(R.id.categorias);

    RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_category);

    int a = 0;
    int b = 400;
    int i = 2;
    for (Category c : categories) {

      final Button btn = new Button(this);
      final int value = c.getId();
      int drawable = 0;
      switch (c.getName()) {
        case "verduleria":
          drawable = R.drawable.verduleria;
          break;
        case "carniceria":
          drawable = R.drawable.carniceria;
          break;
        case "lacteos":
          drawable = R.drawable.lacteos;
          break;
        case "bebidas":
          drawable = R.drawable.bebidas;
          break;
        case "limpieza":
          drawable = R.drawable.limpieza;
          break;
        case "panaderia":
          drawable = R.drawable.panaderia;
          break;
      }
      //?
      if (drawable == 0) {
        continue;
      }
      btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          bun2.putInt("id", value);
          Intent in = new Intent(CategoriaActivity.this, ProductoActivity.class);
          setContentView(R.layout.producto_activity);
          in.putExtras(bun2);
          startActivity(in);
        }
      });

      RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
      //btn.setId(i);
      if (i % 2 == 0) {
        //margen top
        a = (i * 125);

        //   int top = (i == 0 )? 50 : (i * 300);

        params1.setMargins(50, a, 0, 0);

        btn.setText(c.getName());
        btn.setBackground(getResources().getDrawable(drawable));

        rl.addView(btn, params1);

      } else {

        params1.setMargins(b, a, 0, 0);
        btn.setText(c.getName());
        btn.setBackground(getResources().getDrawable(drawable));

        rl.addView(btn, params1);

      }
      i++;

    }


  }

  @Override
  protected void onDestroy() {
    catUtil.destroyDb();
    super.onDestroy();

  }
}


