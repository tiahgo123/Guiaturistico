package ipvc.estg.guiaturistico;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tiago on 25/03/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "categorias.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contrato.SQL_CREATE_ENTRIES);
        db.execSQL(Contrato.SQL_CREATE_ENTRIES2);

        db.execSQL("insert into categoria values (1, 'Monumento');");
        db.execSQL("insert into categoria values (2, 'Cultura');");
        db.execSQL("insert into categoria values (3, 'Gastronomia');");
        db.execSQL("insert into categoria values (4, 'Alojamento');");
        db.execSQL("insert into categoria values (5, 'AgendaCultural');");
        db.execSQL("insert into categoria values (6, 'Praia');");
        db.execSQL("insert into categoria values (7, 'DesportoLazer');");
        db.execSQL("insert into categoria values (8, 'EspaçoNoturno');");
        db.execSQL("insert into categoria values (9, 'Outro');");

        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (1,'Museu do Traje','Praça da República 4900-520 Viana do Castelo Portugal',258809306,'wwww','sem descricao','museutraje@cm-viana-castelo.pt','sem imagem','41.6935222','-8.828055277777777',1);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Contrato.SQL_DELETE_ENTRIES_categoria);
        db.execSQL(Contrato.SQL_DELETE_ENTRIES_pontos);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}
