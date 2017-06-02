package com.example.sabrina.davincimarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Sabrina on 19/05/2017.
 */

public class AboutMeActivity extends AppCompatActivity {


  Bundle bun;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.aboutme_activity);
    bun = getIntent().getExtras();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();

    Intent in = new Intent(AboutMeActivity.this, CategoriaActivity.class);
    in.putExtras(bun);
    startActivity(in);

  }


}
