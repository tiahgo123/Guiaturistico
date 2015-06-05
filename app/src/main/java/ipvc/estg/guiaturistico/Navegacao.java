package ipvc.estg.guiaturistico;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Display;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Tiago Sousa on 08/04/2015.
 */
public class Navegacao extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, SensorEventListener {


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

    TextToSpeech ttobj;

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private TextView txtLat;
    private TextView txtLong;
    private TextView textdistancia;
    private ImageButton ligatelefone;
    private ImageButton vergooglemaps;
    int cont=0;


    //variaveis para saber o lado que esta o monumento

    GeomagneticField geoField ;

    // device sensor manager
    private  SensorManager mSensorManager;
    Sensor accelerometer;
    Sensor magnetometer;

    private WindowManager mWindowManager;
    private Display mDisplay;

    private float currentDegree = 0f;
    TextView tvHeading;
    float azimuth ;
    float baseAzimuth;

    float degree;
    float direction;

    String bearingText = "N";
    String bearingTextImagem = "N";

    Location locInicio = new Location("Inicio");
    Location locFim = new Location("Fim");


    private ImageButton imagemButton1;
    private ImageButton imagemButton2;
    private ImageButton imagemButton3;
    private ImageButton imagemButton4;
    private ImageButton imagemButton5;
    private ImageButton imagemButton6;
    private ImageButton imagemButton7;
    private ImageButton imagemButton8;
    private ImageButton imagemButton9;

    private boolean imagem1 = false;
    private boolean imagem2 = false;
    private boolean imagem3 = false;
    private boolean imagem4 = false;

    private boolean imagem6 = false;
    private boolean imagem7 = false;
    private boolean imagem8 = false;
    private boolean imagem9 = false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacao);

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






        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


        // Get an instance of the WindowManager
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();

        LocationManager manager = (LocationManager) getApplicationContext().getSystemService(getApplicationContext().LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Atenção");
            alert.setMessage("Por favor active o gps");
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent in = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(in);



               //     Intent on = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                   // startActivity(on);
                }
            });


            alert.show();

        }

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

        // verificar onde colocar
        imagemButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Descricao.class);
                intent.putExtra("descricao",descricao);
                ttobj.stop();

                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        valor=intent.getFloatExtra("valor",0);

        Log.e("valor3",""+valor);


        DbHelper dbHelper= new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();


        obterCategorias();
        arrayPontos();

        // INICIAR O SERVIÇO DO GOOGLE PLAY
        buildGoogleApiClient();

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
      //  txtLat.setText("Latitude: " + String.valueOf(mCurrentLocation.getLatitude()));
      //  txtLong.setText("Longitude: " + String.valueOf(mCurrentLocation.getLongitude()));
        Log.i("Latitude", String.valueOf(mCurrentLocation.getLatitude()));
        Log.i("Longitude",String.valueOf(mCurrentLocation.getLongitude()));

       verifica();


       // Toast.makeText(getApplicationContext(),mCurrentLocation.getLatitude()+","+mCurrentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
    }

    private void verifica() {
        Location location = new Location("teste");
       ArrayList<Float> distancia = null;
        ArrayList<Location> locations = null;
        locations = new ArrayList<>();
        distancia = new ArrayList<>();
        Float min;

        //percorre todo o hasmap com os valores selecionados

        for (LatLng latLng :myMap.values()) {
            location.setLatitude(latLng.latitude);
            location.setLongitude(latLng.longitude);
            Log.e("distancialocalizacao", "" + mCurrentLocation.distanceTo(location));
            Log.e("valor5",""+valor);
            if (mCurrentLocation.distanceTo(location) <= valor) {
                //array de distancias onde adiciona a cada distancia esta e a localizacao ao array de localizacao
                distancia.add(mCurrentLocation.distanceTo(location));
                locations.add(location);
            }     Log.e("distancia",""+distancia.size());
        }
        if(!distancia.isEmpty()) {
            min = distancia.get(0);
            Log.e("distanciaget0",""+distancia.get(0));



            for (int i = 0; i < distancia.size(); i++) {
                if (min >= distancia.get(i)) {
                    min = distancia.get(i);
                   // textdistancia.setText("" + distancia + " Metros");
                    //Toast.makeText(getApplicationContext(), "esta a " + distancia + "metros de distancia", Toast.LENGTH_LONG).show();
                        getrecursos(locations.get(i),min);

                }else{
                    getrecursos(locations.get(i),min);

                }

            }


            Double numero = Double.valueOf(min);
         //  int testeint =  min;
            String teste = String.valueOf(min);

        }

        }






//        Iterator myVeryOwnIterator = myMap.keySet().iterator();
//        while(myVeryOwnIterator.hasNext()) {
//            Integer key=(Integer)myVeryOwnIterator.next();
//            Location location = new Location("teste");
//            LatLng value=myMap.get
//
//            LatLng teste = myMap.get(value);
//           // Double teste2 = myMap.get(value);
//
//
//
//            Log.e("teste",""+value.toString());

           // LatLng latLng2 = myMap.get(value).describeContents();

          //  Double aDouble = latLng2.latitude;
            //Double aDouble1 = latLng2.longitude;
            //Log.e("teste",""+aDouble.toString());


          //  LatLng latLng = new LatLng(myMap.get(value).latitude,myMap.get(value).longitude);
           // location.setLatitude(aDouble);
            //location.setLongitude(aDouble1);

           // float metros= mCurrentLocation.distanceTo(location);
            //Toast.makeText(getApplicationContext(),"esta a "+metros + "de distancia do seu local" ,Toast.LENGTH_LONG).show();
        //    Toast.makeText(getApplicationContext(), "Key: "+key+" Value: "+value, Toast.LENGTH_LONG).show();


    private void getrecursos(Location latLng,Float min) {



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
            Log.e("descricao", descricao);
            telefone = obterPonto.getInt(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_TELEFONE));
            latitude = latLng.getLatitude();
            longitude = latLng.getLongitude();

            locFim.setLatitude(latitude);
            locFim.setLongitude(longitude);

            verificaImagens();
            colocarImagem();


       //     Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton1);
            ttobj.speak("Está a uma distancia de "+min+" Metros", TextToSpeech.QUEUE_FLUSH, null);
            cont++;



        }else if(latLng.getLatitude()==obterPonto.getDouble(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_LATITUDE)) && latLng.getLongitude()==obterPonto.getDouble(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_LONGITUDE)) && cont!=0 ) {

            descricao = obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_DESCRICAO));
            Log.e("descricao", descricao);
            telefone = obterPonto.getInt(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_TELEFONE));
            latitude = latLng.getLatitude();
            longitude = latLng.getLongitude();

            locFim.setLatitude(latitude);
            locFim.setLongitude(longitude);

            verificaImagens();
            colocarImagem();

         //   Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton1);
            cont++;
        }else{
            descricao = obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_DESCRICAO));
            Log.e("descricao", descricao);
            telefone = obterPonto.getInt(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_TELEFONE));
            latitude = latLng.getLatitude();
            longitude = latLng.getLongitude();

            locFim.setLatitude(latitude);
            locFim.setLongitude(longitude);

            verificaImagens();
            colocarImagem();

         //   Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton1);
            cont++;
            ttobj.speak("Está a uma distancia de "+min+" Metros", TextToSpeech.QUEUE_FLUSH, null);
        }




    }




//        Cursor coord = obterLatitudeLongitude();
//        if( coord != null && coord.moveToFirst() ) {
//
//            latitudeString = coord.getString(coord.getColumnIndex("fim_latitude"));
//            longitudeString = coord.getString(coord.getColumnIndex("fim_longitude"));
//            moradaFim = coord.getString(coord.getColumnIndex("fim_morada"));
//
//            dest_lat = Double.parseDouble(latitudeString);
//            dest_long = Double.parseDouble(longitudeString);
//
//        }else{
//            Toast.makeText(getActivity(), R.string.cursorNulo, Toast.LENGTH_SHORT).show();
//        }


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

        tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

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
        //This is where we choose to point it
        direction = bearTo - azimuth;
        // If the direction is smaller than 0, add 360 to get the rotation clockwise.
        if (direction < 0) {
            direction = direction + 360;
        }

        // saber direcao telemovel
        saberDirecao();

        //colocar imagem do telemovel
  //      colocarImagem();

   //     Toast.makeText(getApplicationContext(),"lado do telemovel e: " + bearingText + "e a imagem esta para o lado: " + bearingTextImagem, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void colocarImagem() {
        if(bearingText.equals("N")){
            if ( (360 >= direction && direction >= 337.5) || (0 <= direction && direction <= 22.5) ){
                //colocar imagem em cima do tele
                //correto
                Log.i("Cima","Cima");
                bearingTextImagem = "N";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton2);
                imagem2 = true;

                }
            else if (direction > 22.5 && direction < 67.5){
                //colocar imagem lado direito tele
                //correto
                Log.i("direito","entre norte e este");
                bearingTextImagem = "NE";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton3);
                imagem3 = true;
            }
            else if (direction >= 67.5 && direction <= 112.5){
                //colocar imagem lado direito telemovel
                //correto
                Log.i("direito","direito");
                bearingTextImagem = "E";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton6);
                imagem6 = true;
            }
            else if (direction > 112.5 && direction < 157.5){
                //colocar imagem lado direito telemovel
                //correto
                Log.i("direito","entre sul e sudoeste");
                bearingTextImagem = "SE";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton9);
                imagem9 = true;
            }
            else if (direction >= 157.5 && direction <= 202.5){
                Log.i("baixo","baixo");
                //colocar imagem em baixo telemovel
                //correto
                bearingTextImagem = "S";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton8);
                imagem8 = true;
            }
            else if (direction > 202.5 && direction < 247.5){
                Log.i("baixo","entre sul e oeste");
                //lado esquerdo
                //correto
                bearingTextImagem = "SW";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton7);
                imagem7 = true;
            }
            else if (direction >= 247.5 && direction <= 292.5){
                Log.i("esquerdo","esquerdo");
                //lado esquerdo
                //correto
                bearingTextImagem = "W";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton4);
                imagem4 = true;
            }
            else if (direction > 292.5 && direction < 337.5){
                Log.i("esquerdo","entre norte e oeste");
                // lado esquerdo
                //correto
                bearingTextImagem = "NW";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton1);
                imagem1 = true;
            }

        }else if (degree >= 157.5 && degree <= 202.5){
           // bearingText = "S";
            //imagem virada para sul
            if ( (360 >= direction && direction >= 337.5) || (0 <= direction && direction <= 22.5) ){
                //colocar imagem em baixo do tele

                Log.i("Baixo","Baixo");
                bearingTextImagem = "N";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton8);
                imagem8 = true;
            }
            else if (direction > 22.5 && direction < 67.5){
                //colocar imagem lado direito tele
                Log.i("esquerda","entre norte e este");
                bearingTextImagem = "NE";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton7);
                imagem7 = true;
            }
            else if (direction >= 67.5 && direction <= 112.5){
                //colocar imagem lado direito telemovel
                Log.i("direito","direito");
                bearingTextImagem = "E";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton4);
                imagem4 = true;
            }
            else if (direction > 112.5 && direction < 157.5){
                //colocar imagem lado esquerdo telemovel
                Log.i("esquerdo","esquerdo");
                bearingTextImagem = "SE";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton1);
                imagem1 = true;
            }
            else if (direction >= 157.5 && direction <= 202.5){
                Log.i("cima","cima");
                //colocar imagem em cima telemovel
                bearingTextImagem = "S";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton2);
                imagem2 = true;
            }
            else if (direction > 202.5 && direction < 247.5){
                Log.i("direita","direita");
                //lado direita
                bearingTextImagem = "SW";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton3);
                imagem3 = true;
            }
            else if (direction >= 247.5 && direction <= 292.5){
                Log.i("direita","direita");
                //lado direito
                bearingTextImagem = "W";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton6);
                imagem6 = true;
            }
            else if (direction > 292.5 && direction < 337.5){
                Log.i("direita","direita");
                // lado direito
                bearingTextImagem = "NW";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton9);
                imagem9 = true;
            }
        }else if (degree >= 67.5 && degree <= 112.5){
          //  bearingText = "E";
            if ( (360 >= degree && degree >= 337.5) || (0 <= degree && degree <= 22.5) ){
                Log.i("esquerda","esquerda");
                // lado esquerdo
                //correta
                bearingTextImagem = "N";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton4);
                imagem4 = true;

            }
            else if (degree > 22.5 && degree < 67.5){
                Log.i("esquerda","esquerda");
                // lado esquerdo
                bearingTextImagem = "NE";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton1);
                imagem1 = true;

            }
            else if (degree >= 67.5 && degree <= 112.5){
                Log.i("cima","cima");
                // lado cima
                bearingTextImagem = "E";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton2);
                imagem2 = true;

            }
            else if (degree > 112.5 && degree < 157.5){
                Log.i("direita","direita");
                // lado direito
                bearingTextImagem = "SE";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton3);
                imagem3 = true;

            }
            else if (degree >= 157.5 && degree <= 202.5){
                Log.i("direita","direita");
                // lado direito
                bearingTextImagem = "S";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton6);
                imagem6 = true;

            }
            else if (degree > 202.5 && degree < 247.5){
                Log.i("direita","direita");
                // lado direito
                bearingTextImagem = "SW";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton9);
                imagem9 = true;

            }
            else if (degree >= 247.5 && degree <= 292.5){
                Log.i("baixo","baixo");
                // lado baixo
                bearingTextImagem = "W";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton8);
                imagem8 = true;

            }
            else if (degree > 292.5 && degree < 337.5){
                Log.i("esquerda","esquerda");
                // lado esquerda
                bearingTextImagem = "NW";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton7);
                imagem7 = true;
            }
        }else if (degree >= 247.5 && degree <= 292.5){
          //  bearingText = "W";
            if ( (360 >= degree && degree >= 337.5) || (0 <= degree && degree <= 22.5) ){
                Log.i("direita","direita");
                // lado direita
                //correta
                bearingTextImagem = "N";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton6);
                imagem6 = true;
            }
            else if (degree > 22.5 && degree < 67.5){
                Log.i("direita","direita");
                // lado direita
                bearingTextImagem = "NE";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton9);
                imagem9 = true;
            }
            else if (degree >= 67.5 && degree <= 112.5){
                Log.i("baixo","baixo");
                // lado baixo
                bearingTextImagem = "E";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton8);
                imagem8 = true;
            }
            else if (degree > 112.5 && degree < 157.5){
                Log.i("esuqerda","esquerda");
                // lado esquerdo
                bearingTextImagem = "SE";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton7);
                imagem7 = true;
            }
            else if (degree >= 157.5 && degree <= 202.5){
                Log.i("esquerda","esquerda");
                // lado esquerdo
                bearingTextImagem = "S";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton4);
                imagem4 = true;
            }
            else if (degree > 202.5 && degree < 247.5){
                Log.i("esquerda","esquerda");
                // lado esquerdo
                bearingTextImagem = "SW";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton1);
                imagem1 = true;
            }
            else if (degree >= 247.5 && degree <= 292.5){
                Log.i("cima","cima");
                // lado cima
                //correta
                bearingTextImagem = "W";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton2);
                imagem2 = true;
            }
            else if (degree > 292.5 && degree < 337.5){
                Log.i("direita","direita");
                // lado direito
                bearingTextImagem = "NW";
                Toast.makeText(getApplicationContext(), "lado e: " + bearingTextImagem, Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagemButton3);
                imagem3 = true;
            }

        }

    }
    private void saberDirecao() {
        if ( (360 >= degree && degree >= 337.5) || (0 <= degree && degree <= 22.5) ){
            bearingText = "N";
            tvHeading.setText("Heading: " + Float.toString(degree) + " degrees" + bearingText);

        }
        else if (degree > 22.5 && degree < 67.5){
            bearingText = "NE";
            tvHeading.setText("Heading: " + Float.toString(degree) + " degrees" + bearingText);

        }
        else if (degree >= 67.5 && degree <= 112.5){
            bearingText = "E";
            tvHeading.setText("Heading: " + Float.toString(degree) + " degrees" + bearingText);

        }
        else if (degree > 112.5 && degree < 157.5){
            bearingText = "SE";
            tvHeading.setText("Heading: " + Float.toString(degree) + " degrees" + bearingText);

        }
        else if (degree >= 157.5 && degree <= 202.5){
            bearingText = "S";
            tvHeading.setText("Heading: " + Float.toString(degree) + " degrees" + bearingText);

        }
        else if (degree > 202.5 && degree < 247.5){
            bearingText = "SW";
            tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

        }
        else if (degree >= 247.5 && degree <= 292.5){
            bearingText = "W";
            tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

        }
        else if (degree > 292.5 && degree < 337.5){
            bearingText = "NW";
            tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

        }
        else{
            bearingText = "?";
        }
    }

    private void verificaImagens(){
        if(imagem1){
             imagemButton1.setImageResource(android.R.color.transparent);
            imagem1 = false;
        }
        if(imagem2){
            imagemButton2.setImageResource(android.R.color.transparent);
            imagem2 = false;
        }
        if(imagem3){
            imagemButton3.setImageResource(android.R.color.transparent);
            imagem3 = false;
        }
        if(imagem4){
            imagemButton4.setImageResource(android.R.color.transparent);
            imagem4 = false;
        }
        if(imagem6){
            imagemButton6.setImageResource(android.R.color.transparent);
            imagem6 = false;
        }
        if(imagem7){
            imagemButton7.setImageResource(android.R.color.transparent);
            imagem7 = false;
        }
        if(imagem8){
            imagemButton8.setImageResource(android.R.color.transparent);
            imagem8 = false;
        }
        if(imagem9){
            imagemButton9.setImageResource(android.R.color.transparent);
            imagem9 = false;
        }

    }
}
