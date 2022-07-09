package sg.edu.rp.c346.id21021785.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTasks, etDate;
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayList<String> alTask;
    ArrayAdapter aaTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTasks = findViewById(R.id.etTask);
        etDate = findViewById(R.id.etDate);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);

        alTask = new ArrayList<String>();
        aaTasks = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alTask);
        lv.setAdapter(aaTasks);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etTasks.getText().toString(), etDate.getText().toString());

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                ArrayList<String> dataDate = db.getTaskDate();
                db.close();

                String txt = "";
                alTask.clear();
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                    Task currentTask = new Task(i, data.get(i), dataDate.get(i));
                    alTask.add(currentTask.toString());
                    aaTasks.notifyDataSetChanged();
                }
                tvResults.setText(txt);
            }
        });

    }

}
