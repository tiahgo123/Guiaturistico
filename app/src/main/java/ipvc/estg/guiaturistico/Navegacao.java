package ipvc.estg.guiaturistico;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Tiago Sousa on 08/04/2015.
 */
public class Navegacao extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


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
    private ImageButton imagem;
    private ImageButton ligatelefone;
    private ImageButton vergooglemaps;



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

       textdistancia = (TextView) findViewById(R.id.textView16);
       imagem = (ImageButton) findViewById(R.id.imageView);
       ligatelefone = (ImageButton) findViewById(R.id.imageButton22);
       vergooglemaps = (ImageButton) findViewById(R.id.imageButton23);

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

        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Descricao.class);
                intent.putExtra("descricao",descricao);
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
        for (LatLng latLng :myMap.values()) {
            location.setLatitude(latLng.latitude);
            location.setLongitude(latLng.longitude);
            Log.e("distancialocalizacao", "" + mCurrentLocation.distanceTo(location));
            Log.e("valor5",""+valor);
            if (mCurrentLocation.distanceTo(location) >= valor) {
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
                    getrecursos(locations.get(i));

                }else{
                    getrecursos(locations.get(i));

                }

            }

            String teste = String.valueOf(min);
            ttobj.speak("Está a uma distancia de "+teste+" Metros", TextToSpeech.QUEUE_FLUSH, null);
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


    private void getrecursos(Location latLng) {

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
       //obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_IMAGEM));
        descricao = obterPonto.getString(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_DESCRICAO));
        Log.e("descricao",descricao);
        telefone = obterPonto.getInt(obterPonto.getColumnIndex(Contrato.pontos.COLUMN_TELEFONE));
        latitude = latLng.getLatitude();
        longitude = latLng.getLongitude();




          Picasso.with(getApplicationContext()).load(R.drawable.monumentos).into(imagem);






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

}
