package ipvc.estg.guiaturistico;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.lang.reflect.Method;


public class EscolheLingua extends ActionBarActivity {

    SQLiteDatabase db;

    CheckBox checkSom;
    CheckBox checkDados;
    CheckBox checkwifi;
    CheckBox checkcaminho;
  //  boolean isEnabled;

    WifiManager wifiManager;
    TelephonyManager telephonyManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolhe_lingua);

        //realizar o reset da aplicação quando inicia
        resetApp();
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();

        checkSom = (CheckBox) findViewById(R.id.checkBoxSom);
        checkSom.setChecked(true);
        aplicacao.setVerificaSom(true);
        checkDados = (CheckBox) findViewById(R.id.checkBoxDados);
        checkwifi = (CheckBox) findViewById(R.id.checkBoxwifi);
        checkcaminho = (CheckBox) findViewById(R.id.checkBoxcaminho);



        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        telephonyManager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

        functionVeWifi();
        functionVeDados();


        checkSom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
                if (((CheckBox) v).isChecked()) {
                    //o que te diz se tem som ou não
                    aplicacao.setVerificaSom(true);
                }else {
                    aplicacao.setVerificaSom(false);
                }

            }
        });

        checkDados.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {

                    Intent intent = new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
                    startActivity(intent);


      /*              try {
                        setMobileDataEnabled(getApplicationContext(),true);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }


            */

                }else {

                    Intent intent = new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
                    startActivity(intent);

        /*            try {
                        setMobileDataEnabled(getApplicationContext(),false);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    */


                }

            }
        });


        checkwifi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                if(((CheckBox) v).isChecked()){
                    openWifiSettings();

                }else{
                    wifiManager.setWifiEnabled(false);
                    checkwifi.setChecked(false);



                }




            }
        });

        checkcaminho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked()){
                    aplicacao.setvalorcaminho(true);

                }else{

                    aplicacao.setvalorcaminho(false);


                }
            }
        });


       Button avancar = (Button) findViewById(R.id.buttonAvancar);
        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aplicacao.setVerificaOnResume(true);
                aplicacao.setSelecionaTudo(false);
                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
                finish();
            }
        });


    }

    //detetar wifi para a checkbox
    void functionVeWifi(){
        if(wifiManager.isWifiEnabled()){
            checkwifi.setChecked(true);
        }else{
            checkwifi.setChecked(false);
        }
    }

    //detetar dados para a checkbox
    void functionVeDados(){
        if(telephonyManager.getDataState() == TelephonyManager.DATA_CONNECTED){

            checkDados.setChecked(true);

        }else{

            checkDados.setChecked(false);

        }
    }

/*
    //alterar os valores da net
    private void setMobileDataEnabled(Context context, boolean enabled) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final ConnectivityManager conman = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final Class conmanClass = Class.forName(conman.getClass().getName());
        final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
        connectivityManagerField.setAccessible(true);
        final Object connectivityManager = connectivityManagerField.get(conman);
        final Class connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
        final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
        setMobileDataEnabledMethod.setAccessible(true);

        try {
            setMobileDataEnabledMethod.invoke(connectivityManager, enabled);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    */

    //abrir as settings do wifi
    public void openWifiSettings(){

        final Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
        intent.setComponent(cn);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity( intent);
    }


    // quando iniciar a aplicação zera tudo

    private void resetApp() {

        DbHelper dbHelper= new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        //colocar todos os valores na bd a zero
        ContentValues valores = new ContentValues();
        valores.put(Contrato.pontos.COLUMN_CHECKED,"0");
        String selection = Contrato.pontos.COLUMN_CHECKED + " =? ";
        String[] selectionArgs = {"1"};
        db.update(Contrato.pontos.TABLE_NAME,valores,selection,selectionArgs);



        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();

        //colocar as cores todas a normal
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

        aplicacao.setVerificaOnResume(false);

    }

    @Override
    protected void onResume() {

      //  verificar os dados para a checkbox
        functionVeWifi();
        functionVeDados();

        super.onResume();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    //detetar se a barra é utilizada
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        try
        {
            if(!hasFocus)
            {
                Object service  = getSystemService("statusbar");
                Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
                Method collapse = statusbarManager.getMethod("collapse");
                collapse .setAccessible(true);
                collapse .invoke(service);

            }else{

                functionVeWifi();
                functionVeDados();

            }
        }
        catch(Exception ex)
        {
            if(!hasFocus)
            {
                try {
                    Object service  = getSystemService("statusbar");
                    Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
                    Method collapse = statusbarManager.getMethod("collapse");
                    collapse .setAccessible(true);
                    collapse .invoke(service);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ex.printStackTrace();
            }

        }
    }



    }

