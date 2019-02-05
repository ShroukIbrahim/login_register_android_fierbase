package moshrouk.android.example.com.login_register_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;


public class StartActivity extends AppCompatActivity {

    AppCompatButton Email,Google,Facebook,Twitter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Email =(AppCompatButton)findViewById(R.id.btn_Email);
        Google =(AppCompatButton)findViewById(R.id.btn_google);
        Facebook =(AppCompatButton)findViewById(R.id.btn_facebook);
        Twitter =(AppCompatButton)findViewById(R.id.btn_twitter);

        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent email = new Intent(StartActivity.this ,LoginActivity.class);
                startActivity(email);
            }
        });

    }


}
