package sg.edu.rp.c346.id22017424.p11_mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ListView lv;
    Button back;
    Button rating;
    Spinner spn;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv = findViewById(R.id.listView);
        back = findViewById(R.id.buttonBack);
        rating = findViewById(R.id.buttonPG13);
        spn = findViewById(R.id.spinner);

        DBHelper db = new DBHelper(MainActivity2.this);

        ArrayList<Movie> data = db.getMovies();
        CustomAdapter adapter = new CustomAdapter(this, R.layout.row, data);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = data.get(position);
                Intent i = new Intent(MainActivity2.this, MainActivity3.class);
                i.putExtra("movie", movie);
                startActivity(i);
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper db = new DBHelper(MainActivity2.this);
                switch (position){
                    case 0:
                        data.clear();
                        ArrayList<Movie> allMovies = db.getMovies();
                        data.addAll(allMovies);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        data.clear();
                        ArrayList<Movie> ratingG = db.getRatingMovie("G");
                        data.addAll(ratingG);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        data.clear();
                        ArrayList<Movie> ratingPG = db.getRatingMovie("PG");
                        data.addAll(ratingPG);
                        adapter.notifyDataSetChanged();
                        break;
                    case 3:
                        data.clear();
                        ArrayList<Movie> ratingPG13 = db.getRatingMovie("PG13");
                        data.addAll(ratingPG13);
                        adapter.notifyDataSetChanged();
                        break;
                    case 4:
                        data.clear();
                        ArrayList<Movie> ratingNC16 = db.getRatingMovie("NC16");
                        data.addAll(ratingNC16);
                        adapter.notifyDataSetChanged();
                        break;
                    case 5:
                        data.clear();
                        ArrayList<Movie> ratingM18 = db.getRatingMovie("M18");
                        data.addAll(ratingM18);
                        adapter.notifyDataSetChanged();
                        break;
                    case 6:
                        data.clear();
                        ArrayList<Movie> ratingR21 = db.getRatingMovie("R21");
                        data.addAll(ratingR21);
                        adapter.notifyDataSetChanged();
                        break;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity2.this);
                data.clear();
                ArrayList<Movie> PG13Movies = db.getPG13Rating();
                data.addAll(PG13Movies);
                adapter.notifyDataSetChanged();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}