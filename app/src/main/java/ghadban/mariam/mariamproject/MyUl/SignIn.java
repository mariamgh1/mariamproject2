package ghadban.mariam.mariamproject.MyUl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ghadban.mariam.mariamproject.MyUtils.Myvaildations;
import ghadban.mariam.mariamproject.R;

public class SignIn extends AppCompatActivity {

    private EditText etInEmail, etInPassword;
    private Button Loginbtn, Signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //7. check if i signed in before
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null)// user signd in before
        {
            Intent i = new Intent(getBaseContext(),MainActivity.class);
            finish();
            startActivity(i);
        }

        etInEmail = findViewById(R.id.etInEmail);
        etInPassword = findViewById(R.id.etInPassword);
        Loginbtn = findViewById(R.id.Loginbtn);
        Signupbtn = findViewById(R.id.Signupbtn);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });
        Signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignIn.this, SignUp.class);
                startActivity(i);
            }
        });
    }

    private void validateForm() {
        String Email = etInEmail.getText().toString();
        String Password = etInPassword.getText().toString();
        boolean isOK = true;
        if (Email.length() < 5 || (Email.indexOf('@') == 0) || Email.indexOf('@') >= Email.length() - 2 || Email.indexOf('.') == 0
                || Email.indexOf('.') >= Email.length() - 1 || Email.lastIndexOf('.') < Email.indexOf('@')) {
            isOK = false;
            etInEmail.setError("Wrong E-mail syntax");
        }
        Myvaildations myVaildations = new Myvaildations();
        if (myVaildations.validatePasword(Password) == false) {
            isOK = false;
            etInPassword.setError("Invalid Password");
        }

        if (isOK){
            signIn(Email,Password);
        }
    }

    private void signIn (String Email, String Password)
    {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                 Intent i=new Intent(SignIn.this,MainActivity.class);
                 startActivity(i);
                }
                else
                {
                    Toast.makeText(SignIn.this,"Failed", Toast.LENGTH_SHORT).show();
                    etInEmail.setError(task.getException().getMessage());
                }
            }
        });
    }
}