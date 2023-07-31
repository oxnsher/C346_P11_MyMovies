package sg.edu.rp.c346.id22017424.p11_mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "movies.db";

    private static final String TABLE_MOVIES = "movies";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private  static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    // INSERT MOVIE
    public void insertMovie(String title, String genre, int year, String rating){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for the db operation
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);

        db.insert(TABLE_MOVIES, null, values);
        // Close the database connection
        db.close();
    }

    // SHOW MOVIES
    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_MOVIES, columns, null,null,null,null,null, null);

        if (cursor.moveToFirst()){
            do {
                int _id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movie obj = new Movie(_id, title, genre, year, rating);
                movies.add(obj);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return movies;
    }

    //FILTER RATING
    public ArrayList<Movie>getRatingMovie(String keyword){
        ArrayList<Movie> movies = new ArrayList<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID,COLUMN_TITLE,COLUMN_GENRE,COLUMN_YEAR,COLUMN_RATING};
        String condition = COLUMN_RATING+ " Like ?";
        String [] args = {"%" +keyword+"%"};
        Cursor cursor = db.query(TABLE_MOVIES, columns, condition, args, null,null,null,null);

        if (cursor.moveToFirst()){
            do {
                int _id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movie obj = new Movie(_id, title, genre, year, rating);
                movies.add(obj);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return movies;
    }

    public ArrayList<Movie> getPG13Rating(){
        ArrayList<Movie> movies = new ArrayList<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID,COLUMN_TITLE,COLUMN_GENRE,COLUMN_YEAR,COLUMN_RATING};
        String condition = COLUMN_RATING + " Like ?";
        String [] args = {"%" +"PG13"+"%"};
        Cursor cursor = db.query(TABLE_MOVIES, columns, condition, args, null,null,null,null);

        if (cursor.moveToFirst()){
            do {
                int _id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movie obj = new Movie(_id, title, genre, year, rating);
                movies.add(obj);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return movies;
    }

    // UPDATE MOVIE
    public int updateMovie(Movie data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_GENRE, data.getGenre());
        values.put(COLUMN_YEAR,data.getYear());
        values.put(COLUMN_RATING,data.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_MOVIES,values,condition,args);
        db.close();
        return result;
    }

    // DELETE MOVIE
    public int deleteMovie(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID+ "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIES, condition,args);
        db.close();
        return result;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_MOVIES +"("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE +" TEXT,"
                + COLUMN_GENRE +" TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_RATING + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }
}
