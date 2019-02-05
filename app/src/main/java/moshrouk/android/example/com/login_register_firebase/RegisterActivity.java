package moshrouk.android.example.com.login_register_firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    //Declaration of variable
    TextView link_login;
    AppCompatButton Register;
    EditText Name,Email,Password,Cpassword;
    FirebaseAuth mAuth;
    ProgressDialog loading;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Casting of variable
        Name = (EditText) findViewById(R.id.input_name);
        Email = (EditText) findViewById(R.id.input_email);
        Password = (EditText) findViewById(R.id.input_password);
        Cpassword = (EditText) findViewById(R.id.input_cpassword);
        mAuth = FirebaseAuth.getInstance();
        loading= new ProgressDialog(this);

        //open login activity
        link_login=(TextView) findViewById(R.id.link_login);
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent link_login = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(link_login);
            }
        });

        //create account of new user
        Register=(AppCompatButton) findViewById(R.id.btn_register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Register();
            }
        });
    }

    private void Register() {
        // getText() method get the content inside editText
        // toString() will convert it to string format.
        String name = Name.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        String cpassword = Cpassword.getText().toString();


        //check the user enter the input or not
        if(TextUtils.isEmpty(name))
        { Toast.makeText(this, "Enter your Name ", Toast.LENGTH_SHORT).show(); }

        else if(TextUtils.isEmpty(email))
        { Toast.makeText(this, "Enter your Email ", Toast.LENGTH_SHORT).show(); }

        else if(TextUtils.isEmpty(password))
        { Toast.makeText(this, "Enter your Password ", Toast.LENGTH_SHORT).show(); }

        else if (TextUtils.isEmpty(cpassword))
        { Toast.makeText(this, "Enter your Confirm password ", Toast.LENGTH_SHORT).show(); }

        else if(! password.equals(cpassword))
        { Toast.makeText(this, "Your Password don't match your confirm password ", Toast.LENGTH_SHORT).show(); }

        else {
            loading.setMessage(" Please Wait ..... ");
            loading.setTitle("Creating New Account ");
            loading.show();
            loading.setCanceledOnTouchOutside(true);
           mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete( @NonNull Task<AuthResult> task ) {
                   if (task.isSuccessful())
                   {
                       link_login();
                       Toast.makeText(RegisterActivity.this, "Successful .... ", Toast.LENGTH_SHORT).show();
                       loading.dismiss();
                   }
                   else
                   {
                      String massage = task.getException().getMessage();
                       Toast.makeText(RegisterActivity.this, "Error.." + massage, Toast.LENGTH_SHORT).show();
                       loading.dismiss();

                   }

               }
           });
        }
    }

    private void link_login() {
        Intent linklogin = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(linklogin);
    }

}
