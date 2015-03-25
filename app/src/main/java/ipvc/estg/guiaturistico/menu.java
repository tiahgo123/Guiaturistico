package ipvc.estg.guiaturistico;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by tiago on 04/03/2015.
 */
public class menu extends Activity {

    String lingua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        ImageButton imagemMonumento = (ImageButton) findViewById(R.id.imageButtonMonumento);
        imagemMonumento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Configuration config = new Configuration();
             //   config.locale = Locale.ROOT;
             //   getResources().updateConfiguration(config, null);

                Intent intent = new Intent(getApplicationContext(),ListaMonumento.class);
                startActivity(intent);
            }
        });




    }

}
