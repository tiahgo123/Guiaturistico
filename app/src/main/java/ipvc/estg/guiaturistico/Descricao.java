package ipvc.estg.guiaturistico;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by tiago on 06/05/2015.
 */
public class Descricao extends ActionBarActivity {

    TextToSpeech ttobj2;
    Button anterior;


    private boolean veSom=false;
    private boolean veSom1;
    Menu menu;
    boolean onResume = false;
    static final int check = 1111;

    int telefone;
    Double locfimlat;
    Double  locfimlog;
    Double latactu;
    Double  logactu;
    String descricao;
    String nome;
    String imagem;


    TextView txtdescricao ;
    TextView txtNome;
    ImageView image;

    AudioManager audioManager;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao);

        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);


        Intent intent = getIntent();
        descricao = intent.getStringExtra("descricao");
        telefone = intent.getIntExtra("telefone",0);
        locfimlat = intent.getDoubleExtra("locfimlat",0);
        locfimlog = intent.getDoubleExtra("locfimlog",0);
        latactu= intent.getDoubleExtra("latactu",0);
        logactu = intent.getDoubleExtra("logactu",0);
        nome = intent.getStringExtra("nome");
        imagem = intent.getStringExtra("imagem");

        ttobj2=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            ttobj2.setLanguage(Locale.ROOT);
                        }
                    }
                });


        ttobj2.speak(descricao,TextToSpeech.QUEUE_FLUSH,null);


        txtdescricao = (TextView) findViewById(R.id.textViewDescricao);
        txtdescricao.setMovementMethod(new ScrollingMovementMethod());
        txtNome = (TextView) findViewById(R.id.textViewNome);
        image = (ImageView) findViewById(R.id.image);

        txtdescricao.setText(descricao);
        txtNome.setText(nome);

        int id = getResources().getIdentifier(imagem, "drawable", getPackageName());

        Drawable drawable = getResources().getDrawable(id);
        image.setImageDrawable(drawable);

        anterior= (Button) findViewById(R.id.button2);
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
                aplicacao.setVerificaOnResume(true);
                Intent intent = new Intent(getApplicationContext(),Navegacao.class);
                startActivity(intent);
                finish();
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        aplicacao.setVerificaOnResume(true);
        Intent intent = new Intent(getApplicationContext(),Navegacao.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();

        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_navegacao, menu);
        if(onResume){
            if (!veSom1){

                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.speaker));
                veSom = true;
                aplicacao.setVerificaSom(false);

            } else{

                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.speakernosound));
                veSom = false;
                aplicacao.setVerificaSom(true);

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
    protected void onStart() {
    super.onStart();
        ttobj2.speak(descricao,TextToSpeech.QUEUE_FLUSH,null);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        ttobj2.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
     //   ttobj2.speak(descricao, TextToSpeech.QUEUE_FLUSH, null);
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        if (aplicacao.isVerificaOnResume()){

            if(aplicacao.isVerificaSom()){
                veSom1 = true;
                AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
            }else{
                veSom1 = false;
                AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            }

            onResume = true;
        }else{
            onResume = false;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1111 & resultCode==RESULT_OK) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).toString().equals(getResources().getString(R.string.comandosubirvolume))) {

                    AudioManager audioManager =(AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,12,AudioManager.FLAG_SHOW_UI);

                }else if(result.get(i).toString().equals(getResources().getString(R.string.comandomediovolume))){

                    AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,6,AudioManager.FLAG_SHOW_UI);

                }else if(result.get(i).toString().equals(getResources().getString(R.string.comandodescervolume))){

                    AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,2,AudioManager.FLAG_SHOW_UI);

                }else if (result.get(i).toString().equals(getResources().getString(R.string.telefonar))){
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefone));
                    startActivity(intent);

                }else if (result.get(i).toString().equals(getResources().getString(R.string.irpara))){
                    Intent intent =  new  Intent ( android . content . Intent . ACTION_VIEW ,
                            Uri . parse ( "http://maps.google.com/maps?saddr="+locfimlat+","+locfimlog + "&daddr=+"+latactu+","+logactu));
                    startActivity ( intent );

                }else if(result.get(i).toString().equals(getResources().getString(R.string.verdescricao))){
                    ttobj2.speak(descricao, TextToSpeech.QUEUE_FLUSH, null);

                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }




}
