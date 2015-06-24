package ipvc.estg.guiaturistico;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;

/**
 * Created by Tiago Sousa on 02/04/2015.
 */
public class ListaGastronomia extends ListActivity {

    //valores para a lista
    int[] toViewIDs;
    String[] fromFieldNames;
    ListView list;

    CheckBox checkBoxSeleciona;

    //Cursores para obter os valores da base dados
    Cursor obterDocumento;
    Cursor valoresChecked;
    Cursor verificaNaoChecked;

    //passar a categoria, se queremos check ou não check para a query
    int idCategoria = 3;
    int checked = 1;
    int nChecked = 0;


    SQLiteDatabase db;
    SimpleCursorAdapter myCursorAdapter;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        CheckedTextView item = (CheckedTextView) v;
        final Object obj = list.getAdapter().getItem(position);

        //obter o id do valor selecionado quando se carrega num valor da lista
        Cursor cursor2 = (Cursor) obj;
        final String id2=cursor2.getString(cursor2.getColumnIndex(Contrato.pontos._ID));

        if (item.isChecked()) {
            //quando fazemos o check em um valor da lista coloca a um na base dados
            ContentValues values = new ContentValues();
            values.put(Contrato.pontos.COLUMN_CHECKED,1);

            String selection = Contrato.pontos._ID + " LIKE ?";
            String[] selectionArgs = {String.valueOf(id2)};
            db.update(
                    Contrato.pontos.TABLE_NAME,
                    values, selection, selectionArgs);


        }else {
            //quando fazemos tiramos o check do valor e pomos a zero na base dados
            checkBoxSeleciona.setChecked(false);
            ContentValues values = new ContentValues();
            values.put(Contrato.pontos.COLUMN_CHECKED,0);

            String selection = Contrato.pontos._ID + " LIKE ?";
            String[] selectionArgs = {String.valueOf(id2)};
            db.update(
                    Contrato.pontos.TABLE_NAME,
                    values, selection, selectionArgs);
        }

        // verifica se existe algum que não esteja check e se não tiver poe a check a falso
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

                for (int i = 0; i < list.getCount(); i++) {
                    int id = (int) list.getItemIdAtPosition(i);

                    if (id == idChecked) {
                        list.setItemChecked(i, true);
                        //colocar os valores na lista check
                        // obter o id dos que estão check
                        //obter os id da lista
                        // quando forem iguais mete a check a true

                    }
                }

            } while (check.moveToNext());
        }else{

        }

        // colocar a check do selecionar tudo selecionada ou não
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
                volta();
            }
        });




        checkBoxSeleciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    //colocar todos os valores da lista a true
                    checkBoxSeleciona.setChecked(true);
                    for ( int i=0; i < list.getCount(); i++ ) {
                        list.setItemChecked(i, true);
                    }
                    Cursor c = obterMonumentos();
                    if(c != null){
                        c.moveToFirst();
                        do{
                            // colocar todos os valores na base dados com valor 1
                            int idGastronomia = c.getInt(c.getColumnIndex(Contrato.pontos._ID));
                            ContentValues values = new ContentValues();
                            values.put(Contrato.pontos.COLUMN_CHECKED,1);
                            String selection = Contrato.pontos._ID + " LIKE ?";
                            String[] selectionArgs = {String.valueOf(idGastronomia)};
                            db.update(
                                    Contrato.pontos.TABLE_NAME,
                                    values, selection, selectionArgs);
                        }while (c.moveToNext());
                    }
                }else {
                    // a check fica sem estar selecionada
                    // colocar na lista os valores sem estarem selecionados
                    for ( int i=0; i< list.getChildCount(); i++ ) {
                        list.setItemChecked(i, false);
                    }
                    checkBoxSeleciona.setChecked(false);
                    Cursor c = obterMonumentos();
                    if(c != null){
                        c.moveToFirst();
                        do{
                            // colocar os valores na base dados a 0
                            int idGastronomia = c.getInt(c.getColumnIndex(Contrato.pontos._ID));
                            ContentValues values = new ContentValues();
                            values.put(Contrato.pontos.COLUMN_CHECKED,0);
                            String selection = Contrato.pontos._ID + " LIKE ?";
                            String[] selectionArgs = {String.valueOf(idGastronomia)};
                            db.update(
                                    Contrato.pontos.TABLE_NAME,
                                    values, selection, selectionArgs);

                        }while (c.moveToNext());
                    }
                    // ve se existe algum que não esteja cheke para mudar a check principal
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
    //construção da tabela
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

    // obter os gastronomia
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

    public void volta(){
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        // obter os check
        Cursor total = obterChecked();
        if( total != null && total.getCount() == 0){
            //como não tem valores check fica o layout a cinza
            aplicacao.setVerificarTransacaoGastronomia(false);
            aplicacao.setVerificarlinearGastronomia(false);
            aplicacao.setSelecionaTudo(false);

        }else{
            //se não poe a verde
            aplicacao.setVerificarTransacaoGastronomia(true);
            aplicacao.setSelecionaTudo(true);

        }

        //verificar se tem alguma não check
        Cursor vv = verificarNaoChecked();
        if( vv != null && vv.getCount()>=1){
            //alterar a check do seleciona tudo para falso para utilizar na classe Menu
            aplicacao.setSelecionaTudo(false);
        }else{

        }

        aplicacao.setVerificaOnResume(true);
        Intent intent = new Intent(getApplicationContext(),menu.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        volta();
    }
}
