package ipvc.estg.guiaturistico;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

/**
 * Created by Tiago Sousa on 02/04/2015.
 */
public class ListaAgenda extends Activity {

    int[] toViewIDs;
    String[] fromFieldNames;
    ListView list;
    CheckBox checkBoxSeleciona;
    Cursor obterDocumento;
    int idCategoria = 5;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        list = (ListView) findViewById(R.id.listView);



        DbHelper dbHelper= new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        BuildTable();


        Button buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
            }
        });

        checkBoxSeleciona = (CheckBox) findViewById(R.id.checkBoxSeleciona);
        checkBoxSeleciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    checkBoxSeleciona.setChecked(true);
                    for ( int i=0; i < list.getCount(); i++ ) {
                        list.setItemChecked(i, false);
                    }
                    for ( int i=0; i < list.getChildCount(); i++ ) {
                        list.setItemChecked(i, false);
                    }

                    //       Toast.makeText(getApplicationContext(),"esta checked",Toast.LENGTH_SHORT).show();
                }else {
                    for ( int i=0; i< list.getChildCount(); i++ ) {
                        list.setItemChecked(i, false);
                    }
                    for ( int i=0; i < list.getChildCount(); i++ ) {
                        list.setItemChecked(i, false);
                    }

                    checkBoxSeleciona.setChecked(false);
                    //        Toast.makeText(getApplicationContext(),"n esta checked",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void BuildTable() {

        obterMonumentos();
        Cursor c = obterDocumento;
        startManagingCursor(c);

        // Setup mapping from cursor to view fields:
        fromFieldNames = new String[] {Contrato.pontos.COLUMN_NOME,
                Contrato.pontos.COLUMN_IMAGEM };
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

    private Cursor obterMonumentos() {

        String[] projection = {
                Contrato.pontos.COLUMN_NOME, Contrato.pontos.COLUMN_IMAGEM, Contrato.pontos._ID
        };

        String sortOrder = Contrato.pontos.COLUMN_NOME + " ASC ";
        String selection = Contrato.pontos.COLUMN_IdCategoria + " =? ";
        String[] selectionArgs = {String.valueOf(idCategoria)};

        obterDocumento = db.query(
                Contrato.pontos.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        return obterDocumento;
    }

}
