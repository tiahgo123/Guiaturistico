package ipvc.estg.guiaturistico;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by tiago on 06/05/2015.
 */
public class Descricao extends Activity {

    TextToSpeech ttobj;
    TextView txtdescricao ;
    Button anterior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao);
        Intent intent = getIntent();
        String descricao = intent.getStringExtra("descricao");
        Log.e("descricao",descricao);

        txtdescricao = (TextView) findViewById(R.id.textView18);
        txtdescricao.setText(descricao);

        ttobj=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR){
                            ttobj.setLanguage(Locale.ROOT);
                        }
                    }
                });



        ttobj.speak(descricao, TextToSpeech.QUEUE_FLUSH, null);

        anterior= (Button) findViewById(R.id.button2);
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
