package ipvc.estg.guiaturistico;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Tiago Sousa on 02/04/2015.
 */
public class ListaAlojamento extends ListActivity  {

    int[] toViewIDs;
    String[] fromFieldNames;
    ListView list;
    CheckBox checkBoxSeleciona;
    Cursor obterDocumento;
    Cursor valoresChecked;
    Cursor verificaNaoChecked;

    boolean selecionaTudo = false;

    int idCategoria = 4;
    int checked = 1;
    int nChecked = 0;

    SQLiteDatabase db;
    SimpleCursorAdapter myCursorAdapter;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        CheckedTextView item = (CheckedTextView) v;
        final Object obj = list.getAdapter().getItem(position);

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
            checkBoxSeleciona.setChecked(false);
            ContentValues values = new ContentValues();
            values.put(Contrato.pontos.COLUMN_CHECKED,0);

            String selection = Contrato.pontos._ID + " LIKE ?";
            String[] selectionArgs = {String.valueOf(id2)};
            db.update(
                    Contrato.pontos.TABLE_NAME,
                    values, selection, selectionArgs);


        }
        Cursor c3 = verificarNaoChecked();
        if( c3 != null && c3.getCount()>=1){
            checkBoxSeleciona.setChecked(false);
        }else{
            checkBoxSeleciona.setChecked(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();

        list=getListView();
        // list = (ListView) findViewById(R.id.li);
        list.setChoiceMode(list.CHOICE_MODE_MULTIPLE);

        list.setTextFilterEnabled(true);

        checkBoxSeleciona = (CheckBox) findViewById(R.id.checkBoxSeleciona);


        DbHelper dbHelper= new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        BuildTable();
        obterChecked();

        Cursor check = valoresChecked;
        if( check != null && check.moveToFirst() ) {
            check.moveToFirst();
            do {
                int idChecked = check.getInt(check.getColumnIndex(Contrato.pontos._ID));
                //   Log.i("id",idChecked+"");
                for (int i = 0; i < list.getCount(); i++) {
                    int id = (int) list.getItemIdAtPosition(i);

                    if (id == idChecked) {
                        list.setItemChecked(i, true);
                        //       Toast.makeText(getApplicationContext(), "" + i, Toast.LENGTH_SHORT).show();
                    }
                }

            } while (check.moveToNext());
        }else{
            Toast.makeText(getApplicationContext(),"Cursor nulo ", Toast.LENGTH_SHORT).show();
        }

        Cursor c1 = verificarNaoChecked();
        if( c1 != null && c1.getCount()>=1){
            checkBoxSeleciona.setChecked(false);
        }else{
            checkBoxSeleciona.setChecked(true);
        }


        Button buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor total = obterChecked();
                if( total != null && total.getCount() == 0){
                    aplicacao.setVerificarTransacaoAlojamento(false);

                    Log.i("sair de verde", "sair de verde");
                }else{
                    aplicacao.setVerificarTransacaoAlojamento(true);
                    Log.i("fica verde","fica verde");
                }
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
                    Cursor c = obterMonumentos();
                    if(c != null){
                        c.moveToFirst();
                        do{
                            int idAgenda = c.getInt(c.getColumnIndex(Contrato.pontos._ID));
                            ContentValues values = new ContentValues();
                            values.put(Contrato.pontos.COLUMN_CHECKED,1);
                            String selection = Contrato.pontos._ID + " LIKE ?";
                            String[] selectionArgs = {String.valueOf(idAgenda)};
                            db.update(
                                    Contrato.pontos.TABLE_NAME,
                                    values, selection, selectionArgs);
                        }while (c.moveToNext());
                    }
                    //       Toast.makeText(getApplicationContext(),"esta checked",Toast.LENGTH_SHORT).show();

                }else {
                    for ( int i=0; i<= list.getChildCount(); i++ ) {
                        list.setItemChecked(i, false);
                    }
                    checkBoxSeleciona.setChecked(false);
                    Cursor c = obterMonumentos();
                    if(c != null){
                        c.moveToFirst();
                        do{
                            int idAgenda = c.getInt(c.getColumnIndex(Contrato.pontos._ID));
                            ContentValues values = new ContentValues();
                            values.put(Contrato.pontos.COLUMN_CHECKED,0);
                            String selection = Contrato.pontos._ID + " LIKE ?";
                            String[] selectionArgs = {String.valueOf(idAgenda)};
                            db.update(
                                    Contrato.pontos.TABLE_NAME,
                                    values, selection, selectionArgs);

                        }while (c.moveToNext());
                    }
                    //        Toast.makeText(getApplicationContext(),"n esta checked",Toast.LENGTH_SHORT).show();
                    Cursor c2 = verificarNaoChecked();
                    if( c2 != null && c2.getCount()>=1){
                        checkBoxSeleciona.setChecked(false);
                    }else{
                        checkBoxSeleciona.setChecked(true);
                    }
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
    private Cursor obterChecked() {

        String[] projection = {
                Contrato.pontos.COLUMN_NOME,  Contrato.pontos._ID,
                Contrato.pontos.COLUMN_IdCategoria, Contrato.pontos.COLUMN_CHECKED

        };

        //    String sortOrder = Contrato.pontos.COLUMN_NOME + " ASC ";
        String selection = Contrato.pontos.COLUMN_IdCategoria + " =? and " + Contrato.pontos.COLUMN_CHECKED + "=?";
        String[] selectionArgs = {String.valueOf(idCategoria), String.valueOf(checked)};

        valoresChecked = db.query(
                Contrato.pontos.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        return valoresChecked;
    }

    private Cursor verificarNaoChecked() {

        String[] projection = {
                Contrato.pontos.COLUMN_NOME,  Contrato.pontos._ID,
                Contrato.pontos.COLUMN_IdCategoria, Contrato.pontos.COLUMN_CHECKED

        };
        //    String sortOrder = Contrato.pontos.COLUMN_NOME + " ASC ";
        String selection = Contrato.pontos.COLUMN_IdCategoria + " =? and " + Contrato.pontos.COLUMN_CHECKED + "=?";
        String[] selectionArgs = {String.valueOf(idCategoria), String.valueOf(nChecked)};
        verificaNaoChecked = db.query(
                Contrato.pontos.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        return verificaNaoChecked;
    }


}
