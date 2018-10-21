package com.example.jonathanroulet.email_form;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText email;
    EditText userName;
    String materialValue;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner
        spinner = findViewById(R.id.materialSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.material_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        // Email EditText
        email = findViewById(R.id.emailEditText);

        // Name EditText
        userName = findViewById(R.id.nameEditText);

        // Next Btn
        next = findViewById(R.id.nextBtn);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameValue = userName.getText().toString();
                String emailValue = email.getText().toString();

               /* Intent formResultIntent = new Intent(MainActivity.this, FormResult.class);
                formResultIntent.putExtra("com.example.jonathanroulet.email_form.NAME", nameValue);

                startActivity(formResultIntent); */

               // This sends the email with the user input
               Intent email = new Intent(Intent.ACTION_SEND);
               email.setType("message/rfc822");
               email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailValue});
               email.putExtra(Intent.EXTRA_SUBJECT, "Important");
               email.putExtra(Intent.EXTRA_TEXT, "My name is "+nameValue + " and I am selling "+ materialValue);

               try{
                   startActivity(Intent.createChooser(email, "Pick and email client"));
               }catch(ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "There are no clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String materialSelected = parent.getItemAtPosition(position).toString();
                materialValue = materialSelected;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

