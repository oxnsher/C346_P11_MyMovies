package sg.edu.rp.c346.id22017424.p11_mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    EditText title;
    EditText genre;
    EditText year;
    Spinner rating;
    Button update;
    Button cancel;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        title = findViewById(R.id.editTextTitle);
        genre = findViewById(R.id.editTextGenre);
        year = findViewById(R.id.editTextYear);
        update = findViewById(R.id.buttonUpdate);
        cancel = findViewById(R.id.buttonCancel);
        delete = findViewById(R.id.buttonDelete);
        rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ratingInp = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity3.this, "Selected: " + ratingInp, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        Intent i = getIntent();
        Movie movie = (Movie) i.getSerializableExtra("movie");
        title.setText(movie.getTitle());
        genre.setText(movie.getGenre());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity3.this);
                movie.setTitle(title.getText().toString());
                movie.setGenre(genre.getText().toString());
                movie.setYear(Integer.parseInt(year.getText().toString()));
                rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String ratingInp = parent.getItemAtPosition(position).toString();
                        Toast.makeText(MainActivity3.this, "Selected: " + ratingInp, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Do nothing
                    }
                });
                db.updateMovie(movie);
                db.close();
                Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity3.this);
                db.deleteMovie(movie.getId());
                db.close();
                Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(i);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(i);
            }
        });

    }
}