package ipvc.estg.guiaturistico;

import android.content.ContentValues;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class EscolheLingua extends ActionBarActivity {

    SQLiteDatabase db;

    CheckBox checkSom;
    CheckBox checkDados;

    boolean isEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolhe_lingua);

        resetApp();
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();

        checkSom = (CheckBox) findViewById(R.id.checkBoxSom);
        checkDados = (CheckBox) findViewById(R.id.checkBoxDados);

        //Verificar internet
        TelephonyManager telephonyManager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

        if(telephonyManager.getDataState() == TelephonyManager.DATA_CONNECTED){
            isEnabled = true;
            checkDados.setChecked(true);
           // Toast.makeText(getApplicationContext(),"ligar",Toast.LENGTH_SHORT).show();
        }else{
            isEnabled = false;
            checkDados.setChecked(false);
           // Toast.makeText(getApplicationContext(),"desligada",Toast.LENGTH_SHORT).show();
        }



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

                WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);


                if (((CheckBox) v).isChecked()) {

                    try {
                        setMobileDataEnabled(getApplicationContext(),true);
                        toggleWiFi(true);
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

                    Toast.makeText(getApplicationContext(),"ligar",Toast.LENGTH_SHORT).show();

                }else {
                    toggleWiFi(false);



                    try {
                        setMobileDataEnabled(getApplicationContext(),false);
                        wifiManager.setWifiEnabled(false);
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

                    Toast.makeText(getApplicationContext(),"desligar",Toast.LENGTH_SHORT).show();

                }

            }
        });



       Button avancar = (Button) findViewById(R.id.buttonAvancar);
        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aplicacao.setVerificaOnResume(true);
                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
                finish();
            }
        });


    }
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

    public void toggleWiFi(boolean status) {
        WifiManager wifiManager = (WifiManager) this
                .getSystemService(Context.WIFI_SERVICE);
        if (status == true && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else if (status == false && wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }


    // quando iniciar a aplicação zera tudo

    private void resetApp() {

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

        aplicacao.setVerificaOnResume(false);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_escolhe_lingua, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    }

