package ipvc.estg.guiaturistico;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by tiago on 04/03/2015.
 */
public class menu extends Activity {

    boolean verificarLongButtonMonumento = false;
    boolean verificarLongButtonCultura = false;
    boolean verificarLongButtonGastronomia = false;
    boolean verificarLongButtonAlojamento = false;
    boolean verificarLongButtonAgenda = false;
    boolean verificarLongButtonPraia = false;
    boolean verificarLongButtonDesporto = false;
    boolean verificarLongButtonEspaco = false;
    boolean verificarLongButtonOutro = false;


    boolean verificarlinearMonumento = false;
    boolean verificarlinearCultura = false;
    boolean verificarlinearGastronomia = false;
    boolean verificarlinearAlojamento = false;
    boolean verificarlinearAgenda = false;
    boolean verificarlinearPraia = false;
    boolean verificarlinearDesporto = false;
    boolean verificarlinearEspaco = false;
    boolean verificarlinearOutro = false;

    LinearLayout linearMonumento;
    LinearLayout linearCultura;
    LinearLayout linearGastronomia;
    LinearLayout linearAlojamento;
    LinearLayout linearAgenda;
    LinearLayout linearPraia;
    LinearLayout linearDesporto;
    LinearLayout linearEspaco;
    LinearLayout linearOutro;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        linearMonumento = (LinearLayout) findViewById(R.id.layoutMomumento);
        linearCultura = (LinearLayout) findViewById(R.id.layoutCultura);
        linearGastronomia = (LinearLayout) findViewById(R.id.layoutGastronomia);
        linearAlojamento = (LinearLayout) findViewById(R.id.layoutAlojamento);
        linearAgenda = (LinearLayout) findViewById(R.id.layoutAgenda);
        linearPraia = (LinearLayout) findViewById(R.id.layoutPraia);
        linearDesporto = (LinearLayout) findViewById(R.id.layoutDesporto);
        linearEspaco = (LinearLayout) findViewById(R.id.layoutEspaco);
        linearOutro = (LinearLayout) findViewById(R.id.layoutOutro);

        ImageButton imagemMonumento = (ImageButton) findViewById(R.id.imageButtonMonumento);
        imagemMonumento.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!verificarLongButtonMonumento) {
            Intent intent = new Intent(getApplicationContext(), ListaMonumento.class);
            startActivity(intent);
            }else{
                verificarLongButtonMonumento = false;
            }
             }
       });

        imagemMonumento.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
            if (!verificarlinearMonumento){
                verificarLongButtonMonumento = true;
                linearMonumento.setBackgroundColor(Color.GREEN);
                verificarlinearMonumento = true;
            }else{
                linearMonumento.setBackgroundColor(Color.WHITE);
                verificarLongButtonMonumento = true;
                verificarlinearMonumento = false;
            }
                return false;
            }
        });

        ImageButton imagemCultura = (ImageButton) findViewById(R.id.imageButtonCultura);
        imagemCultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verificarLongButtonCultura) {
                        Intent intent = new Intent(getApplicationContext(),ListaCultura.class);
                        startActivity(intent);
                } else {
                    verificarLongButtonCultura = false;
                }
            }

        });

        imagemCultura.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearCultura){
                    verificarLongButtonCultura = true;
                    linearCultura.setBackgroundColor(Color.GREEN);
                    verificarlinearCultura = true;
                }else{
                    linearCultura.setBackgroundColor(Color.WHITE);
                    verificarLongButtonCultura = true;
                    verificarlinearCultura = false;
                }
                return false;
            }
        });

        ImageButton imagemGastronomia = (ImageButton) findViewById(R.id.imageButtonGastronomia);
        imagemGastronomia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!verificarLongButtonGastronomia){
                    Intent intent = new Intent(getApplicationContext(),ListaGastronomia.class);
                    startActivity(intent);
            }else {
                verificarLongButtonGastronomia = false;
            }

            }
        });

        imagemGastronomia.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearGastronomia){
                    verificarLongButtonGastronomia = true;
                    linearGastronomia.setBackgroundColor(Color.GREEN);
                    verificarlinearGastronomia = true;
                }else{
                    linearGastronomia.setBackgroundColor(Color.WHITE);
                    verificarLongButtonGastronomia = true;
                    verificarlinearGastronomia = false;
                }
                return false;
            }
        });

        ImageButton imagemAlojamento = (ImageButton) findViewById(R.id.imageButtonAlojamento);
        imagemAlojamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarLongButtonAlojamento){
                         Intent intent = new Intent(getApplicationContext(),ListaAlojamento.class);
                         startActivity(intent);
                }else {
                    verificarLongButtonAlojamento = false;
                }
            }
        });

        imagemAlojamento.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearAlojamento){
                    verificarLongButtonAlojamento = true;
                    linearAlojamento.setBackgroundColor(Color.GREEN);
                    verificarlinearAlojamento = true;
                }else{
                    linearAlojamento.setBackgroundColor(Color.WHITE);
                    verificarLongButtonAlojamento = true;
                    verificarlinearAlojamento = false;
                }
                return false;
            }
        });

        ImageButton imagemAgenda = (ImageButton) findViewById(R.id.imageButtonAgenda);
        imagemAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarLongButtonAgenda){
                        Intent intent = new Intent(getApplicationContext(),ListaAgenda.class);
                        startActivity(intent);
                }else {
                    verificarLongButtonAgenda = false;
                }
            }
        });

        imagemAgenda.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearAgenda){
                    verificarLongButtonAgenda = true;
                    linearAgenda.setBackgroundColor(Color.GREEN);
                    verificarlinearAgenda = true;
                }else{
                    linearAgenda.setBackgroundColor(Color.WHITE);
                    verificarLongButtonAgenda = true;
                    verificarlinearAgenda = false;
                }
                return false;
            }
        });

        ImageButton imagemPraia = (ImageButton) findViewById(R.id.imageButtonPraia);
        imagemPraia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarLongButtonPraia){
                         Intent intent = new Intent(getApplicationContext(),ListaPraia.class);
                         startActivity(intent);
                }else {
                    verificarLongButtonPraia = false;
                }
            }
        });

        imagemPraia.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearPraia){
                    verificarLongButtonPraia = true;
                    linearPraia.setBackgroundColor(Color.GREEN);
                    verificarlinearPraia = true;
                }else{
                    linearPraia.setBackgroundColor(Color.WHITE);
                    verificarLongButtonPraia = true;
                    verificarlinearPraia = false;
                }
                return false;
            }
        });

        ImageButton imagemDesporto = (ImageButton) findViewById(R.id.imageButtonDesporto);
        imagemDesporto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarLongButtonDesporto){
                         Intent intent = new Intent(getApplicationContext(),ListaDesporto.class);
                         startActivity(intent);
                }else {
                    verificarLongButtonDesporto = false;
                }
            }
        });

        imagemDesporto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearDesporto){
                    verificarLongButtonDesporto = true;
                    linearDesporto.setBackgroundColor(Color.GREEN);
                    verificarlinearDesporto = true;
                }else{
                    linearDesporto.setBackgroundColor(Color.WHITE);
                    verificarLongButtonDesporto = true;
                    verificarlinearDesporto = false;
                }
                return false;
            }
        });

        ImageButton imagemEspaco = (ImageButton) findViewById(R.id.imageButtonEspaco);
        imagemEspaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarLongButtonEspaco){
                         Intent intent = new Intent(getApplicationContext(),ListaEspaco.class);
                         startActivity(intent);
                }else {
                    verificarLongButtonEspaco = false;
                }
            }
        });

        imagemEspaco.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearEspaco){
                    verificarLongButtonEspaco = true;
                    linearEspaco.setBackgroundColor(Color.GREEN);
                    verificarlinearEspaco = true;
                }else{
                    linearEspaco.setBackgroundColor(Color.WHITE);
                    verificarLongButtonEspaco = true;
                    verificarlinearEspaco = false;
                }
                return false;
            }
        });

        ImageButton imagemOutro = (ImageButton) findViewById(R.id.imageButtonOutro);
        imagemOutro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarLongButtonOutro){
                         Intent intent = new Intent(getApplicationContext(),ListaOutro.class);
                         startActivity(intent);
                }else {
                    verificarLongButtonOutro = false;
                }
            }
        });

        imagemOutro.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!verificarlinearOutro){
                    verificarLongButtonOutro = true;
                    linearOutro.setBackgroundColor(Color.GREEN);
                    verificarlinearOutro = true;
                }else{
                    linearOutro.setBackgroundColor(Color.WHITE);
                    verificarLongButtonOutro = true;
                    verificarlinearOutro = false;
                }
                return false;
            }
        });

    }


}
