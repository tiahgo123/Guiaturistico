package ipvc.estg.guiaturistico;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Tiago Sousa on 29/04/2015.
 */
public class Ajuda extends ActionBarActivity {


    VideoView video;
    MediaController mc;
    AudioManager audioManager;


    //Realizar a verificação da actionbar, para o som
    Menu menu;
    boolean onResume = false;
    boolean veSom = true;
    boolean veSom1;

    int posVideo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ajuda);

        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();

        mc= new MediaController(this);
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        video = (VideoView) findViewById(R.id.videoView);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.omeufilme);
        video.setMediaController(mc);
        video.setVideoURI(uri);


        Button voltar = (Button) findViewById(R.id.button2);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                video.stopPlayback();
                aplicacao.setVerificaOnResume(true);
                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
                finish();


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar

        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_ajuda, menu);
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

        if (id == R.id.btSom) {
            if (!veSom){

                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.speaker));
                veSom = true;
                aplicacao.setVerificaSom(false);
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            } else{

                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.speakernosound));
                veSom = false;
                aplicacao.setVerificaSom(true);
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
            }


            return true;
        }
        if(id == R.id.btAmplia){
           // video.stopPlayback();
            video.pause();
            posVideo = video.getCurrentPosition();
            aplicacao.setPosVideo(posVideo);
            aplicacao.setVerificaOnResume(true);
            Intent intent = new Intent(getApplicationContext(),AjudaVideoGrande.class);
            startActivity(intent);
            finish();

            return true;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();

        posVideo = video.getCurrentPosition();
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        aplicacao.setPosVideo(posVideo);



    }

    @Override
    protected void onRestart() {
        super.onRestart();

        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        video.start();
        video.seekTo(aplicacao.getPosVideo());


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        video.stopPlayback();
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        aplicacao.setVerificaOnResume(true);
        aplicacao.setPosVideo(0);
        Intent intent = new Intent(getApplicationContext(),menu.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        if (aplicacao.isVerificaOnResume()){

            if(aplicacao.isVerificaSom()){
                veSom1 = true;
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                }else{
                veSom1 = false;
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            }

            video.start();
            video.seekTo(aplicacao.getPosVideo());

            onResume = true;
        }else{
            onResume = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
    }
}
