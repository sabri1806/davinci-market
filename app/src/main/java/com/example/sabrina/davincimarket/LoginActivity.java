package com.example.sabrina.davincimarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sabrina on 13/05/2017.
 */

public class LoginActivity extends AppCompatActivity {

  public Button ingresar;
  public EditText email;
  public EditText pass;
  public String miEmail = "sabri";
  public String miPass = "123";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_activity);

    email = (EditText) findViewById(R.id.Usuario);
    pass = (EditText) findViewById(R.id.Password);
    ingresar = (Button) findViewById(R.id.Ingresar);

    ingresar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String validMail = email.getText().toString();
        String validPass = pass.getText().toString();

        Intent in = new Intent(LoginActivity.this, CategoriaActivity.class);

        Bundle bun = new Bundle();

        if (validMail.equals(miEmail) && validPass.equals(miPass)) {
          bun.putString("usuario", email.getText().toString());
          bun.putString("contra", pass.getText().toString());

          in.putExtras(bun);
          startActivity(in);
        } else {
          Toast toast = Toast.makeText(getApplicationContext(), "Intente nuevamente", Toast.LENGTH_SHORT);
          toast.show();
        }
        //asi no vuelve
        finish();
      }
    });


  }
}