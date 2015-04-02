package ipvc.estg.guiaturistico;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;


/**
 * Created by Tiago on 11-03-2015.
 */
public class ListaMonumento extends Activity {

    int[] toViewIDs;
    String[] fromFieldNames;
    ListView list;
    CheckBox checkBoxSeleciona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);


        list = (ListView) findViewById(R.id.listView);
        checkBoxSeleciona = (CheckBox) findViewById(R.id.checkBoxSeleciona);

        DbHelper dbHelper= new DbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Button buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
            }
        });

    }
/*
    private void BuildTable() {


        Cursor c = dbAdapter.listarEditarGuia(1);
        startManagingCursor(c);

        // Setup mapping from cursor to view fields:
        fromFieldNames = new String[] { dbhelper.KEY_DT_INI_CAMPO,
                dbhelper.KEY_DT_FIM_CAMPO };
        toViewIDs = new int[] { R.id.textNome, R.id.image };

        // Create adapter to may columns of the DB onto elemesnt in the UI.
        SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(this, // Context
                R.layout.layout_lista, // Row layout template
                c, // cursor (set of DB records to map)
                fromFieldNames, // DB Column names
                toViewIDs // View IDs to put information in
        );

        // Set the adapter for the list view
        list.setAdapter(myCursorAdapter);
    }

*/


}
