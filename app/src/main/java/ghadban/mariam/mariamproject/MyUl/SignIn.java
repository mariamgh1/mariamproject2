package ghadban.mariam.mariamproject.MyUl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import ghadban.mariam.mariamproject.R;

public class SignIn extends AppCompatActivity {

    private EditText etInEmail, etInPassword;
    private Button Loginbtn, Signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etInEmail = findViewById(R.id.etInEmail);
        etInPassword = findViewById(R.id.etInPassword);
        Loginbtn = findViewById(R.id.Loginbtn);
        Signupbtn = findViewById(R.id.Signupbtn);
    }

    private void checkForm()//hl 3bahn sa7(sign up)
    {
        String Email = etInEmail.getText().toString();
        String Password = etInPassword.getText().toString();
        String Login = Loginbtn.getText().toString();
        String Signup = Signupbtn.getText().toString();


    }
}