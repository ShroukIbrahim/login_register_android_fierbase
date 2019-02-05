package moshrouk.android.example.com.login_register_firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    //Declaration of variable
    Button login;
    TextView register;
    EditText Email, Password;
    ProgressDialog loading;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //casting of variable
        login = (Button) findViewById(R.id.btn_login);
        register = (TextView) findViewById(R.id.link_register);
        Email = (EditText) findViewById(R.id.input_email) ;
        Password = ( EditText) findViewById(R.id.input_password);
        loading = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        //when user don't create account >> go to create account
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);

            }
        });

        //login with user
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Login();
            }
        });
    }

    private void Login() {
        // getText() method get the content inside editText
        // toString() will convert it to string format.
        String email = Email.getText().toString();
        String password = Password.getText().toString();


        if (TextUtils.isEmpty(email))
        {

            Toast.makeText(this, "Enter your Email ", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Enter your Password ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loading.setMessage(" Please Wait ..... ");
            loading.setTitle("Login .... ");
            loading.show();
            loading.setCanceledOnTouchOutside(true);
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete( @NonNull Task<AuthResult> task )
                {
                    if (task.isSuccessful())
                    {
                        link_MainActivity();
                    Toast.makeText(LoginActivity.this, "Successful .... ", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                    }
                   else
                    {
                    String massage = task.getException().getMessage();
                    Toast.makeText(LoginActivity.this, "Error.." + massage, Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                    }
            }
        });
    }
}

    private void link_MainActivity() {
        Intent linkmainactivity = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(linkmainactivity);
    }


    }



