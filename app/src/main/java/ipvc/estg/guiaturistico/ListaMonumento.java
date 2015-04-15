package ipvc.estg.guiaturistico;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by Tiago on 11-03-2015.
 */
public class ListaMonumento extends ListActivity {

    int[] toViewIDs;
    String[] fromFieldNames;
    ListView list;
    CheckBox checkBoxSeleciona;
    Cursor obterDocumento;
    int idCategoria = 1;

    DbHelper DbHelper;
    SQLiteDatabase db;
    SimpleCursorAdapter myCursorAdapter;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        CheckedTextView item = (CheckedTextView) v;
        final Object obj = list.getAdapter().getItem(position);

        //db = DbHelper.getWritableDatabase();

        Cursor cursor2 = (Cursor) obj;
        final String id2=cursor2.getString(cursor2.getColumnIndex(Contrato.pontos._ID));

        if (item.isChecked()) {
            ContentValues values = new ContentValues();
            values.put(Contrato.pontos.COLUMN_CHECKED,1);

            String selection = Contrato.pontos._ID + " LIKE ?";
            String[] selectionArgs = {String.valueOf(id2)};
            db.update(
                    Contrato.pontos.TABLE_NAME,
                    values, selection, selectionArgs);


        }else {
            ContentValues values = new ContentValues();
            values.put(Contrato.pontos.COLUMN_CHECKED,0);

            String selection = Contrato.pontos._ID + " LIKE ?";
            String[] selectionArgs = {String.valueOf(id2)};
            db.update(
                    Contrato.pontos.TABLE_NAME,
                    values, selection, selectionArgs);


        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        list = getListView();
        // list = (ListView) findViewById(R.id.li);
        list.setChoiceMode(list.CHOICE_MODE_MULTIPLE);

        list.setTextFilterEnabled(true);


        DbHelper dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase();

        BuildTable();

        for (int i = 0; i < list.getCount(); i++) {

            int id = (int) list.getItemIdAtPosition(i);
            if(id==2){
                list.setItemChecked(i,true);
            }

            Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();


        }





        Button buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
                finish();

            }
        });



        checkBoxSeleciona = (CheckBox) findViewById(R.id.checkBoxSeleciona);
        checkBoxSeleciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    checkBoxSeleciona.setChecked(true);
                    for ( int i=0; i < list.getCount(); i++ ) {
                        list.setItemChecked(i, true);
                    }
             //       Toast.makeText(getApplicationContext(),"esta checked",Toast.LENGTH_SHORT).show();
                }else {
                    for ( int i=0; i< list.getChildCount(); i++ ) {
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
        fromFieldNames = new String[] {Contrato.pontos.COLUMN_NOME,Contrato.pontos.COLUMN_DESCRICAO
                };
        toViewIDs = new int[] {android.R.id.text1,android.R.id.text2};

        // Create adapter to may columns of the DB onto elemesnt in the UI.
        myCursorAdapter = new SimpleCursorAdapter(this, // Context
                android.R.layout.simple_list_item_checked, // Row layout template
                c, // cursor (set of DB records to map)
                fromFieldNames, // DB Column names
                toViewIDs // View IDs to put information in
        );

        // Set the adapter for the list view
        list.setAdapter(myCursorAdapter);

    }

    private Cursor obterMonumentos() {

        String[] projection = {
                Contrato.pontos.COLUMN_NOME, Contrato.pontos.COLUMN_DESCRICAO, Contrato.pontos._ID
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
