package ipvc.estg.guiaturistico;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Tiago Sousa on 08/04/2015.
 */
public class Navegacao extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, SensorEventListener {


    static final int check = 1111;
    Cursor obterPonto;
    int checked = 1;
    SQLiteDatabase db;
    List<Categorias> categorias = new ArrayList<>();
    HashMap<Integer, LatLng> myMap = new HashMap<>();
    float valor=0;
    String descricao;
    int telefone;
    Double latitude;
    Double longitude;
    Menu menu;
    boolean onResume = false;
    TextToSpeech ttobj;
    int cont=0;
    GeomagneticField geoField ;
    Sensor accelerometer;
    Sensor magnetometer;
    TextView tvHeading;
    float azimuth ;
    float baseAzimuth;
    float degree;
    float direction;
    float metros1;
    String[] separated;

    ArrayList<Float> distancia = null;
    ArrayList<Location> locations = null;



    //variaveis para saber o lado que esta o monumento
    String bearingText = "N";
    String bearingTextImagem = "N";
    Location locInicio = new Location("Inicio");
    Location locFim = new Location("Fim");
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private TextView txtLat;
    private TextView txtLong;
    private TextView textdistancia;
    private ImageButton ligatelefone;
    private ImageButton vergooglemaps;
    // device sensor manager
    private  SensorManager mSensorManager;
    private WindowManager mWindowManager;
    private Display mDisplay;
    private float currentDegree = 0f;
    private ImageButton imagemButton1;
    private ImageButton imagemButton2;
    private ImageButton imagemButton3;
    private ImageButton imagemButton4;

    private ImageButton imagemButton6;
    private ImageButton imagemButton7;
    private ImageButton imagemButton8;
    private ImageButton imagemButton9;


    private String imagem;
    private Boolean falateste=false;

    private boolean imagem1 = false;
    private boolean imagem2 = false;
    private boolean imagem3 = false;
    private boolean imagem4 = false;

    private boolean imagem6 = false;
    private boolean imagem7 = false;
    private boolean imagem8 = false;
    private boolean imagem9 = false;


    private boolean veSom=true;
    private boolean veSom1;
    private boolean ativarGps = false;

    Integer distance = 0;


    private boolean verificaDialogbox1;
  //  private boolean verificaDialogbox2;
   // private boolean verificaDialogbox3;



  /*  AlertDialog.Builder alert1;
    AlertDialog.Builder alert2;
    AlertDialog.Builder alert3;

    */
    private String nome;
    Location location;

    Aplicacao aplicacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_navegacao);

        locations = new ArrayList<>();
        distancia = new ArrayList<>();
        location = new Location("teste"+cont);

       aplicacao = (Aplicacao) getApplicationContext();


        ttobj=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            ttobj.setLanguage(Locale.ROOT);
                        }
                    }
                });

        tvHeading = (TextView) findViewById(R.id.tvHeading);
        textdistancia = (TextView) findViewById(R.id.textViewDistancia);

        ligatelefone = (ImageButton) findViewById(R.id.imageButton22);
        vergooglemaps = (ImageButton) findViewById(R.id.imageButton23);

        imagemButton1 = (ImageButton) findViewById(R.id.image1);
        imagemButton2 = (ImageButton) findViewById(R.id.image2);
        imagemButton3 = (ImageButton) findViewById(R.id.image3);
        imagemButton4 = (ImageButton) findViewById(R.id.image4);
        imagemButton6 = (ImageButton) findViewById(R.id.image6);
        imagemButton7 = (ImageButton) findViewById(R.id.image7);
        imagemButton8 = (ImageButton) findViewById(R.id.image8);
        imagemButton9 = (ImageButton) findViewById(R.id.image9);



        //iniciar dialog box
    /*    alert1 = new AlertDialog.Builder(this);
        alert2 = new AlertDialog.Builder(this);
        alert3 = new AlertDialog.Builder(this);
    */

        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


        // Get an instance of the WindowManager
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();

        LocationManager manager = (LocationManager) getApplicationContext().getSystemService(getApplicationContext().LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder alert1 = new AlertDialog.Builder(this);
            alert1.setTitle(R.string.atencao);
            alert1.setCancelable(false);
            alert1.setMessage(R.string.ativarGps);
            alert1.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent in = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(in);
                    ativarGps = true;
                    verificaDialogbox1 = false;



               //     Intent on = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                   // startActivity(on);
                }
            });

            alert1.create();


            alert1.show();
            verificaDialogbox1 = true;


        }else {


        }

        valor = aplicacao.getValorRaio();

        DbHelper dbHelper= new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();


        obterCategorias();
        arrayPontos();

        // INICIAR O SERVIÇO DO GOOGLE PLAY
        buildGoogleApiClient();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_navegacao, menu);
        if(onResume){
            if (!veSom1){
                //sem som

                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.speaker));
                veSom = true;
                aplicacao.setVerificaSom(false);

            } else{
                //com som

                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.speakernosound));
                aplicacao.setVerificaSom(true);
                veSom = false;
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
                AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.speaker));
                veSom = true;
                aplicacao.setVerificaSom(false);
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            } else{

                AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);

                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.speakernosound));
                veSom = false;
                aplicacao.setVerificaSom(true);
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);


            }

            return true;
        }

        if (id == R.id.btfala) {
            Intent i  = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Fala agora");
            startActivityForResult(i, check);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1111 & resultCode==RESULT_OK) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);


            for (int i = 0; i < result.size(); i++) {

                if (result.get(i).toString().equals(getResources().getString(R.string.comandosubirvolume))) {
                    AudioManager audioManager =
                            (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
                    // Set the volume of played media to maximum.

                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,12,AudioManager.FLAG_SHOW_UI);
                    // audioManager.setStreamVolume (
                    //      AudioManager.STREAM_MUSIC,
                    //    audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                    //  0);

            }else if(result.get(i).toString().equals(getResources().getString(R.string.comandomediovolume))){

                AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,6,AudioManager.FLAG_SHOW_UI);

            }
                else if(result.get(i).toString().equals(getResources().getString(R.string.comandodescervolume))){

                    AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,2,AudioManager.FLAG_SHOW_UI);

                }
                if(falateste==true) {


                    if (result.get(i).toString().equals(getResources().getString(R.string.telefonar))) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefone));
                        startActivity(intent);

                    } else if (result.get(i).toString().equals(getResources().getString(R.string.irpara))) {
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps?saddr=" + mCurrentLocation.getLatitude() + "," + mCurrentLocation.getLongitude() + "&daddr=+" + latitude + "," + longitude));
                        startActivity(intent);

                    } else if (result.get(i).toString().equals(getResources().getString(R.string.verdescricao))) {
                        Intent intent = new Intent(getApplicationContext(), Descricao.class);
                        intent.putExtra("descricao",descricao);
                        intent.putExtra("telefone",telefone);
                        intent.putExtra("locfimlat",locFim.getLatitude());
                        intent.putExtra("locfimlog",locFim.getLongitude());
                        intent.putExtra("latactu",mCurrentLocation.getLatitude());
                        intent.putExtra("logactu",mCurrentLocation.getLongitude());
                        intent.putExtra("imagem",imagem);
                        intent.putExtra("nome",nome);
                        startActivity(intent);

                    }
                }


            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected synchronized void buildGoogleApiClient() {
        // CONSTRUIR O CLIENTE DA API DO GOOGLE QUE SERA INICIADO NO ONSTART()
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        // CONSTRUIR O PEDIDO DE SINAL
        createLocationRequest();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        // DEFINIR FREQUENCIA DOS PEDIDOS, TEMPO MAXIMO COM QUE A APP CONSEGUE PROCESSAR PEDIDOS E PRECISAO
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(4000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // INICIAR O SERVIÇO DE GOOGLE PLAY
        mGoogleApiClient.connect();
    }
    @Override
    public void onConnected(Bundle connectionHint) {
        // MOMENTO EM QUE O SERVIÇO DO GOOGLE PLAY É CONETADO (DEPOIS DE mGoogleApiClient.connect();)
        startLocationUpdates();
    }
    protected void startLocationUpdates() {
        // PEDIDO DE SINAL PROPRIAMENTE DITO
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }
    @Override
    public void onLocationChanged(Location location) {
        // METODO DISPARA QUANDO UM SINAL É RECEBIDO
        mCurrentLocation = location;

     //   Log.i("Latitude", String.valueOf(mCurrentLocation.getLatitude()));
      //  Log.i("Longitude",String.valueOf(mCurrentLocation.getLongitude()));

       verifica();

;
    }

    private void verifica() {
        int cont=0;

        Float min = null;
        int indice = 0;

        //percorre todo o hasmap com os valores selecionados

        for (LatLng latLng :myMap.values()) {
            location.setLatitude(latLng.latitude);
            location.setLongitude(latLng.longitude);

            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            final NetworkInfo mWifi2 = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if(aplicacao.isValorcaminho()==true){
            if (mWifi.isConnected() || mWifi2.isConnected() )
            {

                LatLng latLng1 = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                LatLng latLng2 = new LatLng(location.getLatitude(), location.getLongitude());

                String url = getDirectionsUrl(latLng1, latLng2);

                DownloadTask downloadTask = new DownloadTask();

                // Start downloading json data from Google Directions API
                downloadTask.execute(url);


                // ver depois


                }
            } else {
                    if (mCurrentLocation.distanceTo(location) <= valor) {
                        //array de distancias onde adiciona a cada distancia esta e a localizacao ao array de localizacao
                        distancia.add(mCurrentLocation.distanceTo(location));
                        locations.add(location);
                        cont++;
                    }


                }

            }


        if(!distancia.isEmpty()) {

            calculamin(locations,distancia,min,indice);

        }

        }

    private void calculamin(ArrayList<Location> locations, ArrayList<Float> distancia, Float min, int indice) {

        min = distancia.get(0);


        for (int i = 0; i < distancia.size(); i++) {




            if (min >= distancia.get(i)) {
                min = distancia.get(i);
                indice = distancia.indexOf(min);


             //   Log.e("mint",""+min);




            }else{

            //    Log.e("min2",""+min);


            }

        }
        getrecursos(locations.get(indice),min);
    }



    private void getrecursos(Location latLng,Float min) {
        Float metros;
        if(min>=1000){
           metros  = min/100;

        }else{
            metros=min;
        }

        metros1 = metros;




        String[] projection = {
                Contrato.pontos.COLUMN_NOME, Contrato.pontos.COLUMN_IMAGEM, Contrato.pontos._ID, Contrato.pontos.COLUMN_IdCategoria,
                Contrato.pontos.COLUMN_DESCRICAO, Contrato.pontos.COLUMN_LATITUDE, Contrato.pontos.COLUMN_LONGITUDE,
                Contrato.pontos.COLUMN_TELEFONE
        };
        String selection = Contrato.pontos.COLUMN_LATITUDE + " =?  and " + Contrato.pontos.COLUMN_LONGITUDE + "=?";
        String[] selectionArgs = {String.valueOf(latLng.getLatitude()), String.valueOf(latLng.getLongitude())};

        obterPonto = db.query(
                Contrato.pontos.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        obterPonto.moveToFirst();



        if(latLng.getLatitude()==obterPonto.getDouble(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_LATITUDE)) && latLng.getLongitude()==obterPonto.getDouble(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_LONGITUDE)) && cont==0 ) {

            //obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_IMAGEM));
            descricao = obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_DESCRICAO));
            telefone = obterPonto.getInt(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_TELEFONE));
            latitude = latLng.getLatitude();
            longitude = latLng.getLongitude();

            locFim.setLatitude(latitude);
            locFim.setLongitude(longitude);

            imagem = obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_IMAGEM));

            nome = obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_NOME));



            verificaImagens();

            if(metros<50){
                ttobj.speak(getResources().getString(R.string.menoscinquenta), TextToSpeech.QUEUE_FLUSH, null);
            }


           else if(metros>50 && metros <=100) {
                ttobj.speak(getResources().getString(R.string.cinquentacem), TextToSpeech.QUEUE_FLUSH, null);


            }else if(metros<=200 && metros>100){
                ttobj.speak(getResources().getString(R.string.cemduzentos), TextToSpeech.QUEUE_FLUSH, null);
            }else{

                ttobj.speak(getResources().getString(R.string.maisduzentos), TextToSpeech.QUEUE_FLUSH, null);
            }



            colocaimagem2();

            cont++;


        }else if(latLng.getLatitude()==obterPonto.getDouble(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_LATITUDE)) && latLng.getLongitude()==obterPonto.getDouble(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_LONGITUDE)) && cont!=0 ) {

            descricao = obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_DESCRICAO));
            telefone = obterPonto.getInt(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_TELEFONE));
            latitude = latLng.getLatitude();
            longitude = latLng.getLongitude();
            nome = obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_NOME));

            locFim.setLatitude(latitude);
            locFim.setLongitude(longitude);

            imagem = obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_IMAGEM));


            if(metros<50){
                ttobj.speak(getResources().getString(R.string.menoscinquenta), TextToSpeech.QUEUE_FLUSH, null);
            }


            else if(metros>50 && metros <=100) {
                ttobj.speak(getResources().getString(R.string.cinquentacem), TextToSpeech.QUEUE_FLUSH, null);


            }else if(metros<=200 && metros>100){
                ttobj.speak(getResources().getString(R.string.cemduzentos), TextToSpeech.QUEUE_FLUSH, null);
            }else{

                ttobj.speak(getResources().getString(R.string.maisduzentos), TextToSpeech.QUEUE_FLUSH, null);
            }

            verificaImagens();
            colocaimagem2();

            cont++;
        }else{
            descricao = obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_DESCRICAO));
            telefone = obterPonto.getInt(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_TELEFONE));
            latitude = latLng.getLatitude();
            longitude = latLng.getLongitude();

            locFim.setLatitude(latitude);
            locFim.setLongitude(longitude);

            nome = obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_NOME));
            imagem = obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_IMAGEM));

            verificaImagens();
            colocaimagem2();


            cont++;
            if(metros<50){
                ttobj.speak(getResources().getString(R.string.menoscinquenta), TextToSpeech.QUEUE_FLUSH, null);
            }

            else if(metros>50 && metros <=100) {
                ttobj.speak(getResources().getString(R.string.cinquentacem), TextToSpeech.QUEUE_FLUSH, null);


            }else if(metros<=200 && metros>100){
                ttobj.speak(getResources().getString(R.string.cemduzentos), TextToSpeech.QUEUE_FLUSH, null);
            }else{

                ttobj.speak(getResources().getString(R.string.maisduzentos), TextToSpeech.QUEUE_FLUSH, null);
            }

        }




    }



    @Override
    protected void onPause() {
        // SE A APLICAÇÃO ENTRA NESTE ESTADO, PARAR OS PEDIDOS PARA POUPAR RECURSOS
        super.onPause();

        stopLocationUpdates();
        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);


        ttobj.stop();
    }
    protected void stopLocationUpdates() {
        // VERIFICACAO SE O SERVICO DO GOOGLE PLAY ESTA CONETADO E SE SIM REMOVER PEDIDOS
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
        }
    }
    @Override
    public void onResume() {
        // QUANDO A APLICAÇÃO ESTA NESTE ESTADO RETOMAR OS PEDIDOS
        super.onResume();

   if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
        }
        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);

        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        if (aplicacao.isVerificaOnResume() ){

            if(aplicacao.isVerificaSom()){
                veSom1 = true;
                AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
            }else{
                veSom1 = false;
                AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            }

            onResume = true;
        }else{
            onResume = false;
        }

        if(ativarGps){

            aplicacao.setVerificaOnResume(true);
            LocationManager manager = (LocationManager) getApplicationContext().getSystemService(getApplicationContext().LOCATION_SERVICE);
            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                //AlertDialog.Builder alert = new AlertDialog.Builder(this);
               /* alert2.setTitle(R.string.atencao);
                alert2.setMessage(R.string.ativarGps);
                alert2.setCancelable(false);
                alert2.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent in = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(in);
                        verificaDialogbox2 = false;
                        ativarGps = true;

                    }
                });
                alert2.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        aplicacao.setVerificaOnResume(true);

                        Intent intent = new Intent(getApplicationContext(), menu.class);
                        startActivity(intent);
                        finish();

                        ativarGps = false;
                        verificaDialogbox2 = false;

                    }
                });
                alert2.create();
                alert2.show();
                verificaDialogbox2 = true;
                */
                Toast.makeText(getApplicationContext(),R.string.ativarGps,Toast.LENGTH_LONG).show();

            }
        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        aplicacao.setVerificaOnResume(true);

        Intent intent = new Intent(getApplicationContext(), menu.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        Log.e("destro","sfas");
        ttobj.stop();
        ttobj.shutdown();
    }

    private Cursor obterPontos() {

        String[] projection = {
                Contrato.pontos.COLUMN_NOME, Contrato.pontos.COLUMN_IMAGEM, Contrato.pontos._ID, Contrato.pontos.COLUMN_IdCategoria,
                Contrato.pontos.COLUMN_DESCRICAO, Contrato.pontos.COLUMN_LATITUDE, Contrato.pontos.COLUMN_LONGITUDE,
                Contrato.pontos.COLUMN_TELEFONE
        };

        String sortOrder = Contrato.pontos.COLUMN_NOME + " ASC ";
        String selection = Contrato.pontos.COLUMN_CHECKED + " =? ";
      String[] selectionArgs = {String.valueOf(checked)};

        obterPonto = db.query(
                Contrato.pontos.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        return obterPonto;
    }

    public void obterCategorias() {

        // 1. build the query
        Cursor cursor = obterPontos();
        // 3. go over each row, build book and add it to list
        Categorias categoria = null;
        if (cursor.moveToFirst()) {
            do {
                categoria = new Categorias();
                categoria.setIdCategoria(cursor.getInt(cursor.getColumnIndex(Contrato.pontos._ID)));
                categoria.setIdTipoCategoria(cursor.getInt(cursor.getColumnIndex(Contrato.pontos.COLUMN_IdCategoria)));
                categoria.setNomeCategoria(cursor.getString(cursor.getColumnIndex(Contrato.pontos.COLUMN_NOME)));
                categoria.setDescricaoCategoria(cursor.getString(cursor.getColumnIndex(Contrato.pontos.COLUMN_DESCRICAO)));
                categoria.setImagemCategoria(cursor.getString(cursor.getColumnIndex(Contrato.pontos.COLUMN_IMAGEM)));
                categoria.setTelefoneCategoria(cursor.getInt(cursor.getColumnIndex(Contrato.pontos.COLUMN_TELEFONE)));
                if(!(cursor==null)) {
                    categoria.setLatitudeCategoria(Double.parseDouble(cursor.getString(cursor.getColumnIndex(Contrato.pontos.COLUMN_LATITUDE))));
                    categoria.setLongitudeCategoria(Double.parseDouble(cursor.getString(cursor.getColumnIndex(Contrato.pontos.COLUMN_LONGITUDE))));
                }
                // Add book to books
                categorias.add(categoria);
            } while (cursor.moveToNext());
        }

       // Log.d("getCategoria", categorias.toString());

        // return books
      //  return categorias;
    }

    private void arrayPontos(){
        if (categorias != null) {
            for (Categorias categoria : categorias) {

                Log.e("latitude", String.valueOf(categoria.getLatitudeCategoria()));
                Log.e("latitude", String.valueOf(categoria.getLatitudeCategoria()));

                LatLng latLng = new LatLng(categoria.getLatitudeCategoria(),categoria.getLongitudeCategoria());

                myMap.put(categoria.getIdCategoria(),latLng);

      //      Log.i("id",""+categoria.getIdCategoria());
      //      Log.i("idCategoria",""+categoria.getIdTipoCategoria());
      //      Log.i("Nome",categoria.getNomeCategoria());
      //      Log.i("latitude",""+categoria.getLatitudeCategoria());

            }
        }

        Log.e("teste", String.valueOf(myMap.values()));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // If we don't have a Location, we break out
        if ( mCurrentLocation == null ){
            return;
        }

        degree = Math.round(event.values[0]);
        Log.i("degre",""+degree);

     //   tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

        currentDegree = -degree;

        azimuth = event.values[0];
        baseAzimuth = azimuth;

         geoField = new GeomagneticField( Double
                .valueOf( mCurrentLocation.getLatitude() ).floatValue(), Double
                .valueOf( mCurrentLocation.getLongitude() ).floatValue(),
                Double.valueOf( mCurrentLocation.getAltitude() ).floatValue(),
                System.currentTimeMillis() );

        azimuth -= geoField.getDeclination(); // converts magnetic north into true north

        // Store the bearingTo in the bearTo variable
        float bearTo = mCurrentLocation.bearingTo( locFim );


        // If the bearTo is smaller than 0, add 360 to get the rotation clockwise.
        if (bearTo < 0) {
            bearTo = bearTo + 360;
        }

        Log.e("graus2",""+bearTo);
        //This is where we choose to point it
        direction = bearTo - azimuth;
        // If the direction is smaller than 0, add 360 to get the rotation clockwise.
        if (direction < 0) {
            direction = direction + 360;
        }
        Log.e("graus",""+direction);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    private void colocaimagem2(){

        if ( (360 >= direction && direction >= 337.5) || (0 <= direction && direction <= 22.5) ){
            bearingText = "N";




//            int resID = getResources().getIdentifier(imagem , "drawable-hdpi", getPackageName());
//            Log.e("res",""+resID);
//            imagemButton2.setImageResource(resID);

          //  String imagemfinal = "R.drawable."+imagem;


         //   Picasso.with(getApplicationContext()).load(imagemfinal);
            //Picasso.with(getApplicationContext()).load(imagem).into(imagemButton2);



            int id = getResources().getIdentifier(imagem, "drawable", getPackageName());
            Log.e("ididididid",""+id);
            Drawable drawable = getResources().getDrawable(id);
            imagemButton2.setImageDrawable(drawable);

        Log.e("nomememememe",nome);
            imagemButton2.setClickable(true);
            imagem2 = true;
            imagemButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),Descricao.class);
                    intent.putExtra("descricao",descricao);
                    intent.putExtra("telefone",telefone);
                    intent.putExtra("locfimlat",locFim.getLatitude());
                    intent.putExtra("locfimlog",locFim.getLongitude());
                    intent.putExtra("latactu",mCurrentLocation.getLatitude());
                    intent.putExtra("logactu",mCurrentLocation.getLongitude());
                    intent.putExtra("imagem",imagem);
                    intent.putExtra("nome",nome);
                    ttobj.stop();


                    aplicacao.setVerificaOnResume(true);
                    startActivity(intent);
                    finish();
                }
            });
            disponiblizabotoes();

                String dist = getResources().getString(R.string.distancia);
                int teste = (int) metros1;
                String dist1 = dist+teste + " M";
                textdistancia.setText(dist1);


            falateste=true;
            //tvHeading.setText("Heading: " + Float.toString(degree) + " degrees" + bearingText);

        }
        else if (direction > 22.5 && direction < 67.5){
            bearingText = "NE";
            //String imagemfinal = "R.drawable."+imagem;
            //imagemButton3.setImageResource(imagemfinal);

         //   Picasso.with(getApplicationContext()).load(imagem).into(imagemButton3);

//            int resID = getResources().getIdentifier(imagem , "drawable-hdpi", getPackageName());
//            Log.e("res",""+resID);
//            imagemButton3.setImageResource(resID);



            int id = getResources().getIdentifier(imagem, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            imagemButton3.setImageDrawable(drawable);

            imagemButton3.setClickable(true);
            imagem3 = true;
            imagemButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),Descricao.class);
                    intent.putExtra("descricao",descricao);
                    intent.putExtra("telefone",telefone);
                    intent.putExtra("locfimlat",locFim.getLatitude());
                    intent.putExtra("locfimlog",locFim.getLongitude());
                    intent.putExtra("latactu",mCurrentLocation.getLatitude());
                    intent.putExtra("logactu",mCurrentLocation.getLongitude());
                    intent.putExtra("imagem",imagem);
                    intent.putExtra("nome",nome);
                    ttobj.stop();

                    aplicacao.setVerificaOnResume(true);
                    startActivity(intent);
                    finish();
                }
            });
            disponiblizabotoes();
            String dist = getResources().getString(R.string.distancia);
            int teste = (int) metros1;
            String dist1 = dist+teste + " M";
            textdistancia.setText(dist1);
            falateste=true;
           // tvHeading.setText("Heading: " + Float.toString(degree) + " degrees" + bearingText);


        }
        else if (direction >= 67.5 && direction <= 112.5){
            bearingText = "E";

           // String imagemfinal = "R.drawable."+imagem;
            //imagemButton6.setImageResource(imagemfinal);

          //  Picasso.with(getApplicationContext()).load(imagem).into(imagemButton6);

//            int resID = getResources().getIdentifier(imagem , "drawable-hdpi", getPackageName());
//            Log.e("res",""+resID);
//            imagemButton6.setImageResource(resID);


            int id = getResources().getIdentifier(imagem, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            imagemButton6.setImageDrawable(drawable);

            imagemButton6.setClickable(true);
            imagem6 = true;
            imagemButton6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),Descricao.class);
                    intent.putExtra("descricao",descricao);
                    intent.putExtra("telefone",telefone);
                    intent.putExtra("locfimlat",locFim.getLatitude());
                    intent.putExtra("locfimlog",locFim.getLongitude());
                    intent.putExtra("latactu",mCurrentLocation.getLatitude());
                    intent.putExtra("logactu",mCurrentLocation.getLongitude());
                    intent.putExtra("imagem",imagem);
                    intent.putExtra("nome",nome);
                    ttobj.stop();

                    aplicacao.setVerificaOnResume(true);
                    startActivity(intent);
                    finish();
                }
            });
            disponiblizabotoes();
            String dist = getResources().getString(R.string.distancia);
            int teste = (int) metros1;
            String dist1 = dist+teste + " M";
            textdistancia.setText(dist1);
            falateste=true;
            //tvHeading.setText("Heading: " + Float.toString(degree) + " degrees" + bearingText);

        }
        else if (direction > 112.5 && direction < 157.5){
            bearingText = "SE";

           // String imagemfinal ="R.drawable."+imagem;
            //imagemButton9.setImageResource(imagemfinal);

            //Picasso.with(getApplicationContext()).load(imagemfinal).into(imagemButton9);

//            int resID = getResources().getIdentifier(imagem , "drawable-hdpi", getPackageName());
//            Log.e("res",""+resID);


            int id = getResources().getIdentifier(imagem, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            imagemButton9.setImageDrawable(drawable);

            imagemButton9.setClickable(true);
            imagem9 = true;
            imagemButton9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),Descricao.class);
                    intent.putExtra("descricao",descricao);
                    intent.putExtra("telefone",telefone);
                    intent.putExtra("locfimlat",locFim.getLatitude());
                    intent.putExtra("locfimlog",locFim.getLongitude());
                    intent.putExtra("latactu",mCurrentLocation.getLatitude());
                    intent.putExtra("logactu",mCurrentLocation.getLongitude());
                    intent.putExtra("imagem",imagem);
                    intent.putExtra("nome",nome);
                    ttobj.stop();

                    aplicacao.setVerificaOnResume(true);
                    startActivity(intent);
                    finish();
                }
            });
            disponiblizabotoes();
            String dist = getResources().getString(R.string.distancia);
            int teste = (int) metros1;
            String dist1 = dist+teste + " M";
            textdistancia.setText(dist1);
            falateste=true;
            // stvHeading.setText("Heading: " + Float.toString(degree) + " degrees" + bearingText);

        }
        else if (direction >= 157.5 && direction <= 202.5){
            bearingText = "S";
            //tvHeading.setText("Heading: " + Float.toString(degree) + " degrees" + bearingText);
           // String imagemfinal = "R.drawable."+imagem;
           // imagemButton8.setImageResource(imagemfinal);

//            Picasso.with(getApplicationContext()).load(imagem).into(imagemButton8);
//
//            int resID = getResources().getIdentifier(imagem , "drawable-hdpi", getPackageName());
//            Log.e("res",""+resID);
//            imagemButton8.setImageResource(resID);



            int id = getResources().getIdentifier(imagem, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            imagemButton8.setImageDrawable(drawable);
            imagemButton8.setClickable(true);

            imagem8 = true;
            imagemButton8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),Descricao.class);
                    intent.putExtra("descricao",descricao);
                    intent.putExtra("telefone",telefone);
                    intent.putExtra("locfimlat",locFim.getLatitude());
                    intent.putExtra("locfimlog",locFim.getLongitude());
                    intent.putExtra("latactu",mCurrentLocation.getLatitude());
                    intent.putExtra("logactu",mCurrentLocation.getLongitude());
                    intent.putExtra("imagem",imagem);
                    intent.putExtra("nome",nome);
                    ttobj.stop();

                    aplicacao.setVerificaOnResume(true);
                    startActivity(intent);
                    finish();
                }
            });
            disponiblizabotoes();
            String dist = getResources().getString(R.string.distancia);
            int teste = (int) metros1;
            String dist1 = dist+teste + " M";
            textdistancia.setText(dist1);
            falateste=true;

        }
        else if (direction > 202.5 && direction < 247.5){
            bearingText = "SW";
           //tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");


            //String imagemfinal = "R.drawable."+imagem;
           // imagemButton7.setImageResource(imagemfinal);
           // Picasso.with(getApplicationContext()).load(imagem).into(imagemButton7);




           // int resID = getResources().getIdentifier(imagem , "drawable-hdpi", getPackageName());
            //Log.e("res",""+resID);
            //imagemButton7.setImageResource(resID);

            int id = getResources().getIdentifier(imagem, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            imagemButton7.setImageDrawable(drawable);

            imagemButton7.setClickable(true);
            imagem7 = true;
            imagemButton7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),Descricao.class);
                    intent.putExtra("descricao",descricao);
                    intent.putExtra("telefone",telefone);
                    intent.putExtra("locfimlat",locFim.getLatitude());
                    intent.putExtra("locfimlog",locFim.getLongitude());
                    intent.putExtra("latactu",mCurrentLocation.getLatitude());
                    intent.putExtra("logactu",mCurrentLocation.getLongitude());
                    intent.putExtra("imagem",imagem);
                    intent.putExtra("nome",nome);
                    ttobj.stop();

                    aplicacao.setVerificaOnResume(true);
                    startActivity(intent);
                    finish();
                }
            });
            disponiblizabotoes();
            String dist = getResources().getString(R.string.distancia);
            int teste = (int) metros1;
            String dist1 = dist+teste + " M";
            textdistancia.setText(dist1);
            falateste=true;

        }
        else if (direction >= 247.5 && direction <= 292.5){
            bearingText = "W";

           // String imagemfinal = "R.drawable."+imagem;
           // imagemButton4.setImageResource(imagemfinal);
          // Picasso.with(getApplicationContext()).load(imagem).into(imagemButton4);

            //int resID = getResources().getIdentifier(imagem , "drawable-hdpi", getPackageName());
            //Log.e("res",""+resID);
            //imagemButton4.setImageResource(resID);
            int id = getResources().getIdentifier(imagem, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            imagemButton4.setImageDrawable(drawable);

            imagemButton4.setClickable(true);
            imagem4 = true;
            imagemButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),Descricao.class);
                    intent.putExtra("descricao",descricao);
                    intent.putExtra("telefone",telefone);
                    intent.putExtra("locfimlat",locFim.getLatitude());
                    intent.putExtra("locfimlog",locFim.getLongitude());
                    intent.putExtra("latactu",mCurrentLocation.getLatitude());
                    intent.putExtra("logactu",mCurrentLocation.getLongitude());
                    intent.putExtra("imagem",imagem);
                    intent.putExtra("nome",nome);
                    ttobj.stop();

                    aplicacao.setVerificaOnResume(true);
                    startActivity(intent);
                    finish();
                }
            });

            disponiblizabotoes();
            String dist = getResources().getString(R.string.distancia);
            int teste = (int) metros1;
            String dist1 = dist+teste + " M";
            textdistancia.setText(dist1);
            falateste=true;

        }
        else if (direction > 292.5 && direction < 337.5){
            bearingText = "NW";
           // tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

          //  String imagemfinal = "R.drawable."+imagem;
            //imagemButton1.setImageResource(imagemfinal);
            //Picasso.with(getApplicationContext()).load(imagem).into(imagemButton1);

//            int resID = getResources().getIdentifier(imagem , "drawable-hdpi", getPackageName());
//            Log.e("res",""+resID);
//            imagemButton1.setImageResource(resID);


            int id = getResources().getIdentifier(imagem, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            imagemButton1.setImageDrawable(drawable);

           // imagemButton1.setImageResource(id);

            imagemButton1.setClickable(true);
            imagem1 = true;
            imagemButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),Descricao.class);
                    intent.putExtra("descricao",descricao);
                    intent.putExtra("telefone",telefone);
                    intent.putExtra("locfimlat",locFim.getLatitude());
                    intent.putExtra("locfimlog",locFim.getLongitude());
                    intent.putExtra("latactu",mCurrentLocation.getLatitude());
                    intent.putExtra("logactu",mCurrentLocation.getLongitude());
                    intent.putExtra("imagem",imagem);
                    intent.putExtra("nome",nome);
                    ttobj.stop();

                    aplicacao.setVerificaOnResume(true);
                    startActivity(intent);
                    finish();
                }
            });
            disponiblizabotoes();
            String dist = getResources().getString(R.string.distancia);
            int teste = (int) metros1;
            String dist1 = dist+teste + " M";
            textdistancia.setText(dist1);
            falateste=true;


        }
        else{
            bearingText = "?";
        }

    }

    private void verificaImagens(){

        if(imagem1){
            Drawable drawable = getResources().getDrawable(R.drawable.left_up);
            imagemButton1.setImageDrawable(drawable);
            imagemButton1.setClickable(false);
            imagem1 = false;
            falateste=false;
        }
        if(imagem2){
            Drawable drawable = getResources().getDrawable(R.drawable.up);
            imagemButton2.setImageDrawable(drawable);
            imagemButton2.setClickable(false);
            imagem2 = false;
            falateste=false;
        }
        if(imagem3){
            Drawable drawable = getResources().getDrawable(R.drawable.right_up);
            imagemButton3.setImageDrawable(drawable);
            imagemButton3.setClickable(false);
            imagem3 = false;
            falateste=false;
        }
        if(imagem4){
            Drawable drawable = getResources().getDrawable(R.drawable.left);
            imagemButton4.setImageDrawable(drawable);
            imagemButton4.setClickable(false);
            imagem4 = false;
            falateste=false;
        }
        if(imagem6){
            Drawable drawable = getResources().getDrawable(R.drawable.right);
            imagemButton6.setImageDrawable(drawable);
            imagemButton6.setClickable(false);
            imagem6 = false;
            falateste=false;
        }
        if(imagem7){
            Drawable drawable = getResources().getDrawable(R.drawable.left_down);
            imagemButton7.setImageDrawable(drawable);
            imagemButton7.setClickable(false);
            imagem7 = false;
            falateste=false;
        }
        if(imagem8){
            Drawable drawable = getResources().getDrawable(R.drawable.down);
            imagemButton8.setImageDrawable(drawable);
            imagemButton8.setClickable(false);
            imagem8 = false;
            falateste=false;
        }
        if(imagem9) {
            Drawable drawable = getResources().getDrawable(R.drawable.right_down);
            imagemButton9.setImageDrawable(drawable);
            imagemButton9.setClickable(false);
            imagem9 = false;
            falateste=false;
        }

    }

    private void disponiblizabotoes(){
        ligatelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefone));
                startActivity(intent);
            }
        });

        vergooglemaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new  Intent ( android . content . Intent . ACTION_VIEW ,
                        Uri . parse ( "http://maps.google.com/maps?saddr="+mCurrentLocation.getLatitude()+","+mCurrentLocation.getLongitude() + "&daddr=+"+latitude+","+longitude));
                startActivity ( intent );

            }
        });
    }


    private String getDirectionsUrl(LatLng origin, LatLng dest)
    {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        Log.e("link",url);

        return url;
    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException
    {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try
        {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e)
        {
          //  Log.d("Exception while downloading url", e.toString());
        } finally
        {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>
    {
        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url)
        {

            // For storing data from web service
            String data = "";

            try
            {
                // Fetching the data from web service
                data = Navegacao.this.downloadUrl(url[0]);
            } catch (Exception e)
            {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, Integer>>>>
    {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, Integer>>> doInBackground(String... jsonData)
        {
            JSONObject jObject;
            List<List<HashMap<String, Integer>>> routes = null;

            try
            {
                jObject = new JSONObject(jsonData[0]);
                JsonParser parser = new JsonParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, Integer>>> result)
        {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();


            if (result.size() < 1)
            {

                return;
            }

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++)
            {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, Integer>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++)
                {
                    HashMap<String, Integer> point = path.get(j);

                    if (j == 0)
                    { // Get distance from the list
                        distance = point.get("distance");
                        continue;
                    }
                   // double lat = Double.parseDouble(point.get("lat"));
                   // double lng = Double.parseDouble(point.get("lng"));
                   // LatLng position = new LatLng(lat, lng);
                    //points.add(position);
                }

                // Adding all the points in the route to LineOptions
               // lineOptions.addAll(points);
              //  lineOptions.width(2);
             //   lineOptions.color(Color.RED);
            }
            if (distance != 0) {

                if (distance <= valor) {
                    distancia.add(Float.valueOf(distance));
                    locations.add(location);


                }
            }


            // Drawing polyline in the Google Map for the i-th route
           // Navegacao.this.map.addPolyline(lineOptions);
        }
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

                LocationManager manager = (LocationManager) getApplicationContext().getSystemService(getApplicationContext().LOCATION_SERVICE);
                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                   // AlertDialog.Builder alert = new AlertDialog.Builder(this);
                /*    alert3.setTitle(R.string.atencao);
                    alert3.setMessage(R.string.ativarGps);
                    alert3.setCancelable(false);
                    alert3.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent in = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(in);
                            ativarGps = true;
                            verificaDialogbox3 = false;

                        }
                    });
                    alert3.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
                            aplicacao.setVerificaOnResume(true);

                            Intent intent = new Intent(getApplicationContext(), menu.class);
                            startActivity(intent);
                            finish();

                            ativarGps = false;
                            verificaDialogbox3 = false;

                        }
                    });
                    alert3.create();
                    alert3.show();
                    verificaDialogbox3 = true;

                    */
                    Toast.makeText(getApplicationContext(),R.string.ativarGps,Toast.LENGTH_LONG).show();

                }else{


                }


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

