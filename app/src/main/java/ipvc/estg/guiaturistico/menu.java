package ipvc.estg.guiaturistico;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        verificarLongButtonMonumento = aplicacao.isVerificarLongButtonMonumento();
        verificarLongButtonCultura = aplicacao.isVerificarLongButtonCultura();
        verificarLongButtonGastronomia = aplicacao.isVerificarLongButtonGastronomia();
        verificarLongButtonAlojamento = aplicacao.isVerificarLongButtonAlojamento();
        verificarLongButtonAgenda = aplicacao.isVerificarLongButtonAgenda();
        verificarLongButtonPraia = aplicacao.isVerificarLongButtonPraia();
        verificarLongButtonDesporto = aplicacao.isVerificarLongButtonDesporto();
        verificarLongButtonEspaco = aplicacao.isVerificarLongButtonEspaco();
        verificarLongButtonOutro = aplicacao.isVerificarLongButtonOutro();

        verificarlinearMonumento = aplicacao.isVerificarlinearMonumento();
        verificarlinearCultura = aplicacao.isVerificarlinearCultura();
        verificarlinearGastronomia= aplicacao.isVerificarlinearGastronomia();
        verificarlinearAlojamento = aplicacao.isVerificarlinearAlojamento();
        verificarlinearAgenda = aplicacao.isVerificarlinearAgenda();
        verificarlinearPraia = aplicacao.isVerificarlinearPraia();
        verificarlinearDesporto = aplicacao.isVerificarlinearDesporto();
        verificarlinearEspaco = aplicacao.isVerificarlinearEspaco();
        verificarlinearOutro = aplicacao.isVerificarlinearOutro();


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
            aplicacao.setVerificarLongButtonMonumento(verificarLongButtonMonumento);
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
                verificarLongButtonMonumento = true;
                verificarlinearMonumento = false;
                linearMonumento.setBackgroundColor(Color.WHITE);

            }
                aplicacao.setVerificarLongButtonMonumento(verificarLongButtonMonumento);
                aplicacao.setVerificarlinearMonumento(verificarlinearMonumento);
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
                aplicacao.setVerificarLongButtonCultura(verificarLongButtonCultura);
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
                aplicacao.setVerificarLongButtonCultura(verificarLongButtonCultura);
                aplicacao.setVerificarlinearCultura(verificarlinearCultura);
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
                aplicacao.setVerificarLongButtonGastronomia(verificarLongButtonGastronomia);
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
                aplicacao.setVerificarLongButtonGastronomia(verificarLongButtonGastronomia);
                aplicacao.setVerificarlinearGastronomia(verificarlinearGastronomia);
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
                aplicacao.setVerificarLongButtonAlojamento(verificarLongButtonAlojamento);
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
                aplicacao.setVerificarLongButtonAlojamento(verificarLongButtonAlojamento);
                aplicacao.setVerificarlinearAlojamento(verificarlinearAlojamento);
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
                aplicacao.setVerificarLongButtonAgenda(verificarLongButtonAgenda);
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
                aplicacao.setVerificarLongButtonMonumento(verificarLongButtonAgenda);
                aplicacao.setVerificarlinearMonumento(verificarlinearAgenda);
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
                aplicacao.setVerificarLongButtonPraia(verificarLongButtonPraia);
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
                aplicacao.setVerificarLongButtonPraia(verificarLongButtonPraia);
                aplicacao.setVerificarlinearPraia(verificarlinearPraia);
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
                aplicacao.setVerificarLongButtonDesporto(verificarLongButtonDesporto);
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
                aplicacao.setVerificarLongButtonDesporto(verificarLongButtonDesporto);
                aplicacao.setVerificarlinearDesporto(verificarlinearDesporto);
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
                aplicacao.setVerificarLongButtonEspaco(verificarLongButtonEspaco);
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
                aplicacao.setVerificarLongButtonEspaco(verificarLongButtonEspaco);
                aplicacao.setVerificarlinearEspaco(verificarlinearEspaco);
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
                aplicacao.setVerificarLongButtonOutro(verificarLongButtonOutro);
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
                aplicacao.setVerificarLongButtonOutro(verificarLongButtonOutro);
                aplicacao.setVerificarlinearOutro(verificarlinearOutro);
                return false;
            }
        });

        Button buttonIniciar = (Button) findViewById(R.id.buttonIniciar);
        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Navegacao.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        final Aplicacao aplicacao = (Aplicacao) getApplicationContext();
        if (aplicacao.isVerificarlinearMonumento()|| aplicacao.isVerificaTransacaoMonumento()){
            linearMonumento.setBackgroundColor(Color.GREEN);
        }else{
            linearMonumento.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearCultura()){
            linearCultura.setBackgroundColor(Color.GREEN);
        }else{
            linearCultura.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearGastronomia()){
            linearGastronomia.setBackgroundColor(Color.GREEN);
        }else{
            linearGastronomia.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearAlojamento()){
            linearAlojamento.setBackgroundColor(Color.GREEN);
        }else{
            linearAlojamento.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearAgenda()){
            linearAgenda.setBackgroundColor(Color.GREEN);
        }else{
            linearAgenda.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearPraia()){
            linearPraia.setBackgroundColor(Color.GREEN);
        }else{
            linearPraia.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearDesporto()){
            linearDesporto.setBackgroundColor(Color.GREEN);
        }else{
            linearDesporto.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearEspaco()){
            linearEspaco.setBackgroundColor(Color.GREEN);
        }else{
            linearEspaco.setBackgroundColor(Color.WHITE);
        }
        if (aplicacao.isVerificarlinearOutro()){
            linearOutro.setBackgroundColor(Color.GREEN);
        }else{
            linearOutro.setBackgroundColor(Color.WHITE);
        }

    }
}
