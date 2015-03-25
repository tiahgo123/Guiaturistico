package ipvc.estg.guiaturistico;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


/**
 * Created by Tiago on 11-03-2015.
 */
public class ListaMonumento extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        DbHelper dbHelper= new DbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();






    }




}
