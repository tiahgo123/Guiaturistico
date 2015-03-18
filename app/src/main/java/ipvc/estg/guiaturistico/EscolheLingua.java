package ipvc.estg.guiaturistico;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;


public class EscolheLingua extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolhe_lingua);


        ImageButton imagemPortugal = (ImageButton) findViewById(R.id.imageButtonPortugal);
        imagemPortugal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = new Configuration();
                config.locale = Locale.ROOT;
                getResources().updateConfiguration(config, null);

                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
            }
        });

        ImageButton imagemInglaterra = (ImageButton) findViewById(R.id.imageButtonInglaterra);
        imagemInglaterra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getApplicationContext().getResources();
                // Change locale settings in the app.
                DisplayMetrics dm = res.getDisplayMetrics();
                android.content.res.Configuration conf = res.getConfiguration();
                conf.locale = new Locale("en");
                res.updateConfiguration(conf, dm);

                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
            }
        });

        ImageButton imagemFranca = (ImageButton) findViewById(R.id.imageButtonFranca);
        imagemFranca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = new Configuration();
                config.locale = Locale.FRANCE;
                getResources().updateConfiguration(config, null);


                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
            }
        });

        ImageButton imagemEspanha = (ImageButton) findViewById(R.id.imageButtonEspanha);
        imagemEspanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = new Configuration();
                Locale spanish = new Locale("es", "ES");
                config.locale = spanish;
                getResources().updateConfiguration(config, null);


                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
            }
        });

        ImageButton imagemAlemanha = (ImageButton) findViewById(R.id.imageButtonAlemanha);
        imagemAlemanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = new Configuration();
                config.locale = Locale.GERMAN;
                getResources().updateConfiguration(config, null);


                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
            }
        });
        ImageButton imagemChina = (ImageButton) findViewById(R.id.imageButtonChina);
        imagemChina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = new Configuration();
                config.locale = Locale.CHINA;
                getResources().updateConfiguration(config, null);

                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
            }
        });






    }


    @Override
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
}
