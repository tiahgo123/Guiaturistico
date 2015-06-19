package ipvc.estg.guiaturistico;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by tiago on 04/03/2015.
 */
public class menu extends ActionBarActivity {

    boolean verificarLongButtonMonumento = false;
    boolean verificarLongButtonCultura = false;
    boolean verificarLongButtonGastronomia = false;
    boolean verificarLongButtonAlojamento = false;
    boolean verificarLongButtonAgenda = false;
    boolean verificarLongButtonPraia = false;
    boolean verificarLongButtonDesporto = false;
    boolean verificarLongButtonEspaco = false;
    boolean verificarLongButtonOutro = false;

    boolean verificarlinearMonumento = false;
    boolean verificarlinearCultura = false;
    boolean verificarlinearGastronomia = false;
    boolean verificarlinearAlojamento = false;
    boolean verificarlinearAgenda = false;
    boolean verificarlinearPraia = false;
    boolean verificarlinearDesporto = false;
    boolean verificarlinearEspaco = false;
    boolean verificarlinearOutro = false;

    LinearLayout linearMonumento;
    LinearLayout linearCultura;
    LinearLayout linearGastronomia;
    LinearLayout linearAlojamento;
    LinearLayout linearAgenda;
    LinearLayout linearPraia;
    LinearLayout linearDesporto;
    LinearLayout linearEspaco;
    LinearLayout linearOutro;

    TextView resultadoProgresso;
    SeekBar progresso;
    float valor=1;

    SQLiteDatabase db;

    //Realizar a verificação da actionbar, para o som
     Menu menu;
     boolean onResume = false;
     boolean veSom = true;
     boolean veSom1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        linearMonumento = (LinearLayout) findViewById(R.id.layoutMomumento);
        linearCultura = (LinearLayout) findViewById(R.id.layoutCultura);
        linearGastronomia = (LinearLayout) findViewById(R.id.layoutGastronomia);
        linearAlojamento = (LinearLayout) findViewById(R.id.layoutAlojamento);
        linearAgenda = (LinearLayout) findViewById(R.id.layoutAgenda);
        linearPraia = (LinearLayout) findViewById(R.id.layoutPraia);
        linearDesporto = (LinearLayout) findViewById(R.id.layoutDesporto);
        linearEspaco = (LinearLayout) findViewById(R.id.layoutEspaco);
        linearOutro = (LinearLayout) findViewById(R.id.layoutOutro);

        final TextView resultadoProgresso = (TextView) findViewById(R.id.textView3);
        progresso = (SeekBar) findViewById(R.id.seekBar);

        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        verificarLongButtonMonumento = aplicacao.isVerificarLongButtonMonumento();
        verificarLongButtonCultura = aplicacao.isVerificarLongButtonCultura();
        verificarLongButtonGastronomia = aplicacao.isVerificarLongButtonGastronomia();
        verificarLongButtonAlojamento = aplicacao.isVerificarLongButtonAlojamento();
        verificarLongButtonAgenda = aplicacao.isVerificarLongButtonAgenda();
        verificarLongButtonPraia = aplicacao.isVerificarLongButtonPraia();
        verificarLongButtonDesporto = aplicacao.isVerificarLongButtonDesporto();
        verificarLongButtonEspaco = aplicacao.isVerificarLongButtonEspaco();
        verificarLongButtonOutro = aplicacao.isVerificarLongButtonOutro();

        verificarlinearMonumento = aplicacao.isVerificarlinearMonumento();
        verificarlinearCultura = aplicacao.isVerificarlinearCultura();
        verificarlinearGastronomia= aplicacao.isVerificarlinearGastronomia();
        verificarlinearAlojamento = aplicacao.isVerificarlinearAlojamento();
        verificarlinearAgenda = aplicacao.isVerificarlinearAgenda();
        verificarlinearPraia = aplicacao.isVerificarlinearPraia();
        verificarlinearDesporto = aplicacao.isVerificarlinearDesporto();
        verificarlinearEspaco = aplicacao.isVerificarlinearEspaco();
        verificarlinearOutro = aplicacao.isVerificarlinearOutro();


        resultadoProgresso.setText("1 Metro");

        DbHelper dbHelper= new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();


        ImageButton imagemMonumento = (ImageButton) findViewById(R.id.imageButtonMonumento);
        imagemMonumento.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!verificarLongButtonMonumento) {
            Intent intent = new Intent(getApplicationContext(), ListaMonumento.class);
            startActivity(intent);
            finish();
            }else{
                verificarLongButtonMonumento = false;

            }
            aplicacao.setVerificarLongButtonMonumento(verificarLongButtonMonumento);
             }
       });

        imagemMonumento.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
            if (!verificarlinearMonumento){
                verificarLongButtonMonumento = true;
                linearMonumento.setBackgroundColor(Color.GREEN);
                verificarlinearMonumento = true;

                ContentValues values = new ContentValues();
                values.put(Contrato.pontos.COLUMN_CHECKED,1);
                String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                String[] selectionArgs = {String.valueOf("1")};
                db.update(
                        Contrato.pontos.TABLE_NAME,
                        values, selection, selectionArgs);
            }else{
                verificarLongButtonMonumento = true;
                verificarlinearMonumento = false;
                linearMonumento.setBackgroundColor(Color.WHITE);

                ContentValues values = new ContentValues();
                values.put(Contrato.pontos.COLUMN_CHECKED,0);
                String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                String[] selectionArgs = {String.valueOf("1")};
                db.update(
                        Contrato.pontos.TABLE_NAME,
                        values, selection, selectionArgs);
            }
                aplicacao.setVerificarLongButtonMonumento(verificarLongButtonMonumento);
                aplicacao.setVerificarlinearMonumento(verificarlinearMonumento);
                return false;
            }
        });

        ImageButton imagemCultura = (ImageButton) findViewById(R.id.imageButtonCultura);
        imagemCultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verificarLongButtonCultura) {
                        Intent intent = new Intent(getApplicationContext(),ListaCultura.class);
                        startActivity(intent);
                        finish();
                } else {
                    verificarLongButtonCultura = false;
                }
                aplicacao.setVerificarLongButtonCultura(verificarLongButtonCultura);
            }

        });

        imagemCultura.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearCultura){
                    verificarLongButtonCultura = true;
                    linearCultura.setBackgroundColor(Color.GREEN);
                    verificarlinearCultura = true;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,1);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("2")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }else{
                    linearCultura.setBackgroundColor(Color.WHITE);
                    verificarLongButtonCultura = true;
                    verificarlinearCultura = false;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,0);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("2")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }
                aplicacao.setVerificarLongButtonCultura(verificarLongButtonCultura);
                aplicacao.setVerificarlinearCultura(verificarlinearCultura);
                return false;
            }
        });

        ImageButton imagemGastronomia = (ImageButton) findViewById(R.id.imageButtonGastronomia);
        imagemGastronomia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!verificarLongButtonGastronomia){
                    Intent intent = new Intent(getApplicationContext(),ListaGastronomia.class);
                    startActivity(intent);
                    finish();
            }else {
                verificarLongButtonGastronomia = false;
            }
                aplicacao.setVerificarLongButtonGastronomia(verificarLongButtonGastronomia);
            }
        });

        imagemGastronomia.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearGastronomia){
                    verificarLongButtonGastronomia = true;
                    linearGastronomia.setBackgroundColor(Color.GREEN);
                    verificarlinearGastronomia = true;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,1);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("3")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);

                }else{
                    linearGastronomia.setBackgroundColor(Color.WHITE);
                    verificarLongButtonGastronomia = true;
                    verificarlinearGastronomia = false;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,0);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("3")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }
                aplicacao.setVerificarLongButtonGastronomia(verificarLongButtonGastronomia);
                aplicacao.setVerificarlinearGastronomia(verificarlinearGastronomia);
                return false;
            }
        });

        ImageButton imagemAlojamento = (ImageButton) findViewById(R.id.imageButtonAlojamento);
        imagemAlojamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarLongButtonAlojamento){
                         Intent intent = new Intent(getApplicationContext(),ListaAlojamento.class);
                         startActivity(intent);
                         finish();
                }else {
                    verificarLongButtonAlojamento = false;
                }
                aplicacao.setVerificarLongButtonAlojamento(verificarLongButtonAlojamento);
            }
        });

        imagemAlojamento.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearAlojamento){
                    verificarLongButtonAlojamento = true;
                    linearAlojamento.setBackgroundColor(Color.GREEN);
                    verificarlinearAlojamento = true;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,1);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("4")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }else{
                    linearAlojamento.setBackgroundColor(Color.WHITE);
                    verificarLongButtonAlojamento = true;
                    verificarlinearAlojamento = false;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,0);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("4")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }
                aplicacao.setVerificarLongButtonAlojamento(verificarLongButtonAlojamento);
                aplicacao.setVerificarlinearAlojamento(verificarlinearAlojamento);
                return false;
            }
        });

        ImageButton imagemAgenda = (ImageButton) findViewById(R.id.imageButtonAgenda);
        imagemAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarLongButtonAgenda){
                        Intent intent = new Intent(getApplicationContext(),ListaAgenda.class);
                        startActivity(intent);
                        finish();
                }else {
                    verificarLongButtonAgenda = false;
                }
                aplicacao.setVerificarLongButtonAgenda(verificarLongButtonAgenda);
            }
        });

        imagemAgenda.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearAgenda){
                    verificarLongButtonAgenda = true;
                    linearAgenda.setBackgroundColor(Color.GREEN);
                    verificarlinearAgenda = true;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,1);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("5")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }else{
                    linearAgenda.setBackgroundColor(Color.WHITE);
                    verificarLongButtonAgenda = true;
                    verificarlinearAgenda = false;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,0);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("5")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }
                aplicacao.setVerificarLongButtonAgenda(verificarLongButtonAgenda);
                aplicacao.setVerificarlinearAgenda(verificarlinearAgenda);
                return false;
            }
        });

        ImageButton imagemPraia = (ImageButton) findViewById(R.id.imageButtonPraia);
        imagemPraia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarLongButtonPraia){
                         Intent intent = new Intent(getApplicationContext(),ListaPraia.class);
                         startActivity(intent);
                          finish();
                }else {
                    verificarLongButtonPraia = false;
                }
                aplicacao.setVerificarLongButtonPraia(verificarLongButtonPraia);
            }
        });

        imagemPraia.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearPraia){
                    verificarLongButtonPraia = true;
                    linearPraia.setBackgroundColor(Color.GREEN);
                    verificarlinearPraia = true;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,1);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("6")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }else{
                    linearPraia.setBackgroundColor(Color.WHITE);
                    verificarLongButtonPraia = true;
                    verificarlinearPraia = false;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,0);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("6")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }
                aplicacao.setVerificarLongButtonPraia(verificarLongButtonPraia);
                aplicacao.setVerificarlinearPraia(verificarlinearPraia);
                return false;
            }
        });

        ImageButton imagemDesporto = (ImageButton) findViewById(R.id.imageButtonDesporto);
        imagemDesporto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarLongButtonDesporto){
                         Intent intent = new Intent(getApplicationContext(),ListaDesporto.class);
                         startActivity(intent);
                         finish();
                }else {
                    verificarLongButtonDesporto = false;
                }
                aplicacao.setVerificarLongButtonDesporto(verificarLongButtonDesporto);
            }
        });

        imagemDesporto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearDesporto){
                    verificarLongButtonDesporto = true;
                    linearDesporto.setBackgroundColor(Color.GREEN);
                    verificarlinearDesporto = true;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,1);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("7")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }else{
                    linearDesporto.setBackgroundColor(Color.WHITE);
                    verificarLongButtonDesporto = true;
                    verificarlinearDesporto = false;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,0);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("7")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }
                aplicacao.setVerificarLongButtonDesporto(verificarLongButtonDesporto);
                aplicacao.setVerificarlinearDesporto(verificarlinearDesporto);
                return false;
            }
        });

        ImageButton imagemEspaco = (ImageButton) findViewById(R.id.imageButtonEspaco);
        imagemEspaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarLongButtonEspaco){
                         Intent intent = new Intent(getApplicationContext(),ListaEspaco.class);
                         startActivity(intent);
                         finish();
                }else {
                    verificarLongButtonEspaco = false;
                }
                aplicacao.setVerificarLongButtonEspaco(verificarLongButtonEspaco);
            }
        });

        imagemEspaco.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearEspaco){
                    verificarLongButtonEspaco = true;
                    linearEspaco.setBackgroundColor(Color.GREEN);
                    verificarlinearEspaco = true;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,1);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("8")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }else{
                    linearEspaco.setBackgroundColor(Color.WHITE);
                    verificarLongButtonEspaco = true;
                    verificarlinearEspaco = false;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,0);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("8")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }
                aplicacao.setVerificarLongButtonEspaco(verificarLongButtonEspaco);
                aplicacao.setVerificarlinearEspaco(verificarlinearEspaco);
                return false;
            }
        });

        ImageButton imagemOutro = (ImageButton) findViewById(R.id.imageButtonOutro);
        imagemOutro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarLongButtonOutro){
                         Intent intent = new Intent(getApplicationContext(),ListaOutro.class);
                         startActivity(intent);
                         finish();
                }else {
                    verificarLongButtonOutro = false;
                }
                aplicacao.setVerificarLongButtonOutro(verificarLongButtonOutro);
            }
        });

        imagemOutro.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearOutro){
                    verificarLongButtonOutro = true;
                    linearOutro.setBackgroundColor(Color.GREEN);
                    verificarlinearOutro = true;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,1);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("9")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }else{
                    linearOutro.setBackgroundColor(Color.WHITE);
                    verificarLongButtonOutro = true;
                    verificarlinearOutro = false;

                    ContentValues values = new ContentValues();
                    values.put(Contrato.pontos.COLUMN_CHECKED,0);
                    String selection = Contrato.pontos.COLUMN_IdCategoria + " LIKE ?";
                    String[] selectionArgs = {String.valueOf("9")};
                    db.update(
                            Contrato.pontos.TABLE_NAME,
                            values, selection, selectionArgs);
                }
                aplicacao.setVerificarLongButtonOutro(verificarLongButtonOutro);
                aplicacao.setVerificarlinearOutro(verificarlinearOutro);
                return false;
            }
        });




        progresso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                float min=1;

                if(progress<min){
                    valor=1;

                    aplicacao.setValorSeekBar(valor);
                    resultadoProgresso.setText(valor + " Metros");
                }else {


                    valor = progress;
                    aplicacao.setValorSeekBar(valor);
                    resultadoProgresso.setText(valor + " Metros");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        Button buttonIniciar = (Button) findViewById(R.id.buttonIniciar);
        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Navegacao.class);
                intent.putExtra("valor",valor);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        Log.i("entrei no menu","entrei no menu");
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        if(onResume){
            if (!veSom1){
                //sem som
                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_launcher));
                veSom = false;
            } else{
                //com som
                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.abc_ic_voice_search_api_mtrl_alpha));
                veSom = true;
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.btSom) {
            if (!veSom){
                // par
                //coloca com som
                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.abc_ic_voice_search_api_mtrl_alpha));
                veSom = true;
                aplicacao.setVerificaSom(true);
            } else{
                //impar
                //coloca sem som
                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_launcher));
                veSom = false;
                aplicacao.setVerificaSom(false);
            }
            Log.i("verificaSom",""+aplicacao.isVerificaSom());

            return true;
        }

        if (id == R.id.btAjuda) {
            Intent intent = new Intent(getApplicationContext(), Ajuda.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.btSair) {
            pergunta();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        pergunta();

    }

    public void pergunta(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle(R.string.atencao);

        // set dialog message
        alertDialogBuilder
                .setMessage(R.string.aviso)
                .setCancelable(false)
                .setPositiveButton(R.string.sim,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                ContentValues valores = new ContentValues();
                                valores.put(Contrato.pontos.COLUMN_CHECKED,"0");
                                String selection = Contrato.pontos.COLUMN_CHECKED + " =? ";
                                String[] selectionArgs = {"1"};
                                db.update(Contrato.pontos.TABLE_NAME,valores,selection,selectionArgs);

                                final Aplicacao aplicacao = (Aplicacao) getApplicationContext();


                                aplicacao.setVerificarlinearMonumento(false);
                                aplicacao.setVerificaTransacaoMonumento(false);

                                aplicacao.setVerificarlinearCultura(false);
                                aplicacao.setVerificarTransacaoCultura(false);

                                aplicacao.setVerificarlinearGastronomia(false);
                                aplicacao.setVerificarTransacaoGastronomia(false);

                                aplicacao.setVerificarlinearAlojamento(false);
                                aplicacao.setVerificarTransacaoAlojamento(false);

                                aplicacao.setVerificarlinearAgenda(false);
                                aplicacao.setVerificarTransacaoAgenda(false);

                                aplicacao.setVerificarlinearPraia(false);
                                aplicacao.setVerificarTransacaoPraia(false);

                                aplicacao.setVerificarlinearDesporto(false);
                                aplicacao.setVerificarTransacaoDesporto(false);

                                aplicacao.setVerificarlinearEspaco(false);
                                aplicacao.setVerificarTransacaoEspaco(false);

                                aplicacao.setVerificarlinearOutro(false);
                                aplicacao.setVerificarTransacaoOutro(false);



                                finish();

                            }
                        })
                .setNegativeButton(R.string.nao,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("entrei onResume","adsada");
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();


        if (aplicacao.isVerificarlinearMonumento()|| aplicacao.isVerificaTransacaoMonumento()){
            linearMonumento.setBackgroundColor(Color.GREEN);
        }else{
            linearMonumento.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearCultura()||aplicacao.isVerificarTransacaoCultura()){
            linearCultura.setBackgroundColor(Color.GREEN);
        }else{
            linearCultura.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearGastronomia()|| aplicacao.isVerificarTransacaoGastronomia()){
            linearGastronomia.setBackgroundColor(Color.GREEN);
        }else{
            linearGastronomia.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearAlojamento()|| aplicacao.isVerificarTransacaoAlojamento()){
            linearAlojamento.setBackgroundColor(Color.GREEN);
        }else{
            linearAlojamento.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearAgenda()|| aplicacao.isVerificarTransacaoAgenda()){
            linearAgenda.setBackgroundColor(Color.GREEN);
        }else{
            linearAgenda.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearPraia()|| aplicacao.isVerificarTransacaoPraia()){
            linearPraia.setBackgroundColor(Color.GREEN);
        }else{
            linearPraia.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearDesporto()|| aplicacao.isVerificarTransacaoDesporto()){
            linearDesporto.setBackgroundColor(Color.GREEN);
        }else{
            linearDesporto.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearEspaco()|| aplicacao.isVerificarTransacaoEspaco()){
            linearEspaco.setBackgroundColor(Color.GREEN);
        }else{
            linearEspaco.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearOutro()|| aplicacao.isVerificarTransacaoOutro()){
            linearOutro.setBackgroundColor(Color.GREEN);
        }else{
            linearOutro.setBackgroundColor(Color.WHITE);
        }


        if (aplicacao.isVerificaOnResume()){
          //  Log.i("verificaSom",""+aplicacao.isVerificaSom());
            if(aplicacao.isVerificaSom()){
                veSom1 = true;
            }else{
                veSom1 = false;
            }
            onResume = true;
        }else{
             onResume = false;
        }


        progresso.setProgress((int) aplicacao.getValorSeekBar());


    }
}
