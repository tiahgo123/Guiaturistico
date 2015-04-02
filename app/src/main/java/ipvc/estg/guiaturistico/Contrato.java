package ipvc.estg.guiaturistico;

import android.provider.BaseColumns;

/**
 * Created by tiago on 25/03/2015.
 */
public final class Contrato {

    private static final String TEXT_TYPE = " TEXT ";
    private static final String INT_TYPE = " INTEGER ";

    public Contrato() {

    }

    public static abstract class categoria implements BaseColumns {
        public static final String TABLE_NAME = "categoria";
        public static final String COLUMN_Descricao = "Descicao";
    }

    public static abstract class pontos implements BaseColumns {
        public static final String TABLE_NAME = "pontos";
        public static final String COLUMN_NOME = "Nome";
        public static final String COLUMN_MORADA = "Morada";
        public static final String COLUMN_TELEFONE = "Telefone";
        public static final String COLUMN_EMAIL = "Email";
        public static final String COLUMN_DESCRICAO = "Descricao";
        public static final String COLUMN_SITE = "Site";
        public static final String COLUMN_IMAGEM = "Imagem";
        public static final String COLUMN_LATITUDE = "Latitude";
        public static final String COLUMN_LONGITUDE = "Longitude";
        public static final String COLUMN_IdCategoria = "Id_categoria";

    }


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + categoria.TABLE_NAME + " (" +
                    categoria._ID + INT_TYPE + " PRIMARY KEY," +
                    categoria.COLUMN_Descricao +  TEXT_TYPE + " )";



    public static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE " + pontos.TABLE_NAME + " (" +
                    pontos._ID + INT_TYPE + " PRIMARY KEY," +
                    pontos.COLUMN_NOME +  TEXT_TYPE  + "," +
                    pontos.COLUMN_MORADA + TEXT_TYPE  + "," +
                    pontos.COLUMN_TELEFONE + INT_TYPE + "," +
                    pontos.COLUMN_EMAIL + TEXT_TYPE + "," +
                    pontos.COLUMN_DESCRICAO + TEXT_TYPE + ","+
                    pontos.COLUMN_SITE + TEXT_TYPE + "," +
                    pontos.COLUMN_IMAGEM + TEXT_TYPE + "," +
                    pontos.COLUMN_LATITUDE + TEXT_TYPE + "," +
                    pontos.COLUMN_LONGITUDE + TEXT_TYPE + "," +
                    pontos.COLUMN_IdCategoria + INT_TYPE +
            ", FOREIGN KEY (" + pontos.COLUMN_IdCategoria + ") "
            + "REFERENCES " + categoria.TABLE_NAME + "(" + categoria._ID + "));";


        public static final String SQL_DELETE_ENTRIES_categoria =
            "DROP TABLE IF EXISTS " + categoria.TABLE_NAME;




        public static final String SQL_DELETE_ENTRIES_pontos =
        "DROP TABLE IF EXISTS " + pontos.TABLE_NAME;











}
