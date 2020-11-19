package ghadban.mariam.mariamproject.MyUl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ghadban.mariam.mariamproject.Data.MyTask;
import ghadban.mariam.mariamproject.R;

public class AddTaskyActivity extends AppCompatActivity {
    private EditText etTitle, etSubject;
    private SeekBar skbrImportant, skbrNeccesary;
    private Button btnSaveTask;
    private ImageButton imgBtnl;
    private Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasky);

        etTitle = findViewById(R.id.etTitle);
        etSubject = findViewById(R.id.etSubject);
        skbrImportant = findViewById(R.id.skbrImportant);
        skbrNeccesary = findViewById(R.id.skbrNeccesary);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        imgBtnl = findViewById(R.id.imgBtnl);
        btnUpload = findViewById(R.id.btnUpload);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });
    }

    public void validateForm() {
        String title = etTitle.getText().toString();
        String subject = etSubject.getText().toString();
        int imp = skbrImportant.getProgress();
        int nec = skbrNeccesary.getProgress();
        boolean isok = true;
        if (title.length() == 0) {
            isok = false;
            etTitle.setError("at least one char");
        }
        if (isok) {
            //save on fire base
            MyTask myTask = new MyTask();
            myTask.setTitle(title);
            myTask.setSubject(subject);
            myTask.setNecessary(nec);
            myTask.setImportant(imp);
            // build your data object:
            saveTask(myTask);
        }

    }

    private void saveTask(MyTask myTask) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        String key = reference.child("AllTasks").push().getKey();
        myTask.setOwner(uid);
        myTask.setKey(key);

        ((DatabaseReference) reference).child("AllTasks").child(uid).child(key).setValue(myTask).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddTaskyActivity.this, "add successful", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddTaskyActivity.this, "add failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }
}