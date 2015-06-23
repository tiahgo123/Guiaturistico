package ipvc.estg.guiaturistico;

import android.app.Application;

/**
 * Created by Tiago Sousa on 15/04/2015.
 */
public class Aplicacao extends Application {

    boolean verificaSom;
    boolean verificaOnResume = false;

    boolean verificaTransacaoMonumento;
    boolean verificarTransacaoCultura;
    boolean verificarTransacaoGastronomia;
    boolean verificarTransacaoAlojamento;
    boolean verificarTransacaoAgenda;
    boolean verificarTransacaoPraia;
    boolean verificarTransacaoDesporto;
    boolean verificarTransacaoEspaco;
    boolean verificarTransacaoOutro;

    boolean verificarLongButtonMonumento;
    boolean verificarLongButtonCultura;
    boolean verificarLongButtonGastronomia;
    boolean verificarLongButtonAlojamento;
    boolean verificarLongButtonAgenda;
    boolean verificarLongButtonPraia;
    boolean verificarLongButtonDesporto;
    boolean verificarLongButtonEspaco;
    boolean verificarLongButtonOutro;

    float valorRaio;

    public boolean isValorcaminho() {
        return valorcaminho;
    }

    private boolean valorcaminho;

    public void setValorRaio(float valorRaio) {
        this.valorRaio = valorRaio;
    }

    public float getValorRaio() {
        return valorRaio;
    }

    boolean verificarlinearMonumento=false;
    boolean verificarlinearCultura=false;
    boolean verificarlinearGastronomia=false;
    boolean verificarlinearAlojamento=false;
    boolean verificarlinearAgenda=false;
    boolean verificarlinearPraia=false;
    boolean verificarlinearDesporto=false;
    boolean verificarlinearEspaco=false;
    boolean verificarlinearOutro=false;

    public int getNummonumento() {
        return nummonumento;
    }

    public void setNummonumento(int nummonumento) {
        this.nummonumento = nummonumento;
    }

    public int getNumcultura() {
        return numcultura;
    }

    public void setNumcultura(int numcultura) {
        this.numcultura = numcultura;
    }

    public int getMumagenda() {
        return mumagenda;
    }

    public void setMumagenda(int mumagenda) {
        this.mumagenda = mumagenda;
    }

    public int getNumgastronomia() {
        return numgastronomia;
    }

    public void setNumgastronomia(int numgastronomia) {
        this.numgastronomia = numgastronomia;
    }

    public int getMumpraia() {
        return mumpraia;
    }

    public void setMumpraia(int mumpraia) {
        this.mumpraia = mumpraia;
    }

    public int getMumdesporto() {
        return mumdesporto;
    }

    public void setMumdesporto(int mumdesporto) {
        this.mumdesporto = mumdesporto;
    }

    public int getMumespaco() {
        return mumespaco;
    }

    public void setMumespaco(int mumespaco) {
        this.mumespaco = mumespaco;
    }

    public int getMumoutro() {
        return mumoutro;
    }

    public void setMumoutro(int mumoutro) {
        this.mumoutro = mumoutro;
    }

    int nummonumento=0;
    int numcultura=0;
    int  numgastronomia=0;
    int mumagenda=0;
    int  mumpraia=0;
    int mumdesporto=0;
    int mumespaco=0;
    int  mumoutro=0;

    public int getNumalijamento() {
        return numalijamento;
    }

    public void setNumalijamento(int numalijamento) {
        this.numalijamento = numalijamento;
    }

    int numalijamento=0;



    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    int valor = 0;

    public boolean isVerificarentraMonumento() {
        return verificarentraMonumento;
    }

    public void setVerificarentraMonumento(boolean verificarentraMonumento) {
        this.verificarentraMonumento = verificarentraMonumento;
    }

    public boolean isVerificarentrarCultura() {
        return verificarentrarCultura;
    }

    public void setVerificarentrarCultura(boolean verificarentrarCultura) {
        this.verificarentrarCultura = verificarentrarCultura;
    }

    public boolean isVerificarentrarGastronomia() {
        return verificarentrarGastronomia;
    }

    public void setVerificarentrarGastronomia(boolean verificarentrarGastronomia) {
        this.verificarentrarGastronomia = verificarentrarGastronomia;
    }

    public boolean isVericificarentrarAlojamento() {
        return vericificarentrarAlojamento;
    }

    public void setVericificarentrarAlojamento(boolean vericificarentrarAlojamento) {
        this.vericificarentrarAlojamento = vericificarentrarAlojamento;
    }

    public boolean isVerificarentrarAgenda() {
        return verificarentrarAgenda;
    }

    public void setVerificarentrarAgenda(boolean verificarentrarAgenda) {
        this.verificarentrarAgenda = verificarentrarAgenda;
    }

    public boolean isVerificaenrrarPraia() {
        return verificaenrrarPraia;
    }

    public void setVerificaenrrarPraia(boolean verificaenrrarPraia) {
        this.verificaenrrarPraia = verificaenrrarPraia;
    }

    public boolean isVerificarentrarDesporto() {
        return verificarentrarDesporto;
    }

    public void setVerificarentrarDesporto(boolean verificarentrarDesporto) {
        this.verificarentrarDesporto = verificarentrarDesporto;
    }

    public boolean isVerifcarentrarEspaco() {
        return verifcarentrarEspaco;
    }

    public void setVerifcarentrarEspaco(boolean verifcarentrarEspaco) {
        this.verifcarentrarEspaco = verifcarentrarEspaco;
    }

    public boolean isVerificarentrarOutro() {
        return verificarentrarOutro;
    }

    public void setVerificarentrarOutro(boolean verificarentrarOutro) {
        this.verificarentrarOutro = verificarentrarOutro;
    }

    boolean verificarentraMonumento;
    boolean verificarentrarCultura;
    boolean verificarentrarGastronomia;
    boolean vericificarentrarAlojamento;
    boolean verificarentrarAgenda;
    boolean verificaenrrarPraia;
    boolean verificarentrarDesporto;
    boolean verifcarentrarEspaco;
    boolean verificarentrarOutro;


    boolean selecionaTudo;

    float valorSeekBar;

    int posVideo1;

    public void setSelecionaTudo(boolean selecionaTudo) {
        this.selecionaTudo = selecionaTudo;
    }

    public boolean isSelecionaTudo() {
        return selecionaTudo;
    }

    public void setPosVideo(int posVideo1) {
        this.posVideo1 = posVideo1;
    }


    public int getPosVideo() {
        return posVideo1;
    }

    public void setValorSeekBar(float valorSeekBar) {
        this.valorSeekBar = valorSeekBar;
    }


    public float getValorSeekBar() {
        return valorSeekBar;
    }

    public void setVerificaOnResume(boolean verificaOnResume) {
        this.verificaOnResume = verificaOnResume;
    }

    public boolean isVerificaOnResume() {
        return verificaOnResume;
    }

    public void setVerificaSom(boolean verificaSom) {
        this.verificaSom = verificaSom;
    }

    public boolean isVerificaSom() {
        return verificaSom;
    }

    public void setVerificaTransacaoMonumento(boolean verificaTransacaoMonumento) {
        this.verificaTransacaoMonumento = verificaTransacaoMonumento;
    }

    public boolean isVerificaTransacaoMonumento() {
        return verificaTransacaoMonumento;
    }

    public void setVerificarTransacaoAgenda(boolean verificarTransacaoAgenda) {
        this.verificarTransacaoAgenda = verificarTransacaoAgenda;
    }

    public boolean isVerificarTransacaoAgenda() {
        return verificarTransacaoAgenda;
    }

    public void setVerificarTransacaoAlojamento(boolean verificarTransacaoAlojamento) {
        this.verificarTransacaoAlojamento = verificarTransacaoAlojamento;
    }

    public boolean isVerificarTransacaoAlojamento() {
        return verificarTransacaoAlojamento;
    }

    public void setVerificarTransacaoCultura(boolean verificarTransacaoCultura) {
        this.verificarTransacaoCultura = verificarTransacaoCultura;
    }

    public boolean isVerificarTransacaoCultura() {
        return verificarTransacaoCultura;
    }

    public void setVerificarTransacaoDesporto(boolean verificarTransacaoDesporto) {
        this.verificarTransacaoDesporto = verificarTransacaoDesporto;
    }

    public boolean isVerificarTransacaoDesporto() {
        return verificarTransacaoDesporto;
    }

    public void setVerificarTransacaoEspaco(boolean verificarTransacaoEspaco) {
        this.verificarTransacaoEspaco = verificarTransacaoEspaco;
    }

    public boolean isVerificarTransacaoEspaco() {
        return verificarTransacaoEspaco;
    }

    public void setVerificarTransacaoGastronomia(boolean verificarTransacaoGastronomia) {
        this.verificarTransacaoGastronomia = verificarTransacaoGastronomia;
    }

    public boolean isVerificarTransacaoGastronomia() {
        return verificarTransacaoGastronomia;
    }

    public void setVerificarTransacaoOutro(boolean verificarTransacaoOutro) {
        this.verificarTransacaoOutro = verificarTransacaoOutro;
    }

    public boolean isVerificarTransacaoOutro() {
        return verificarTransacaoOutro;
    }

    public void setVerificarTransacaoPraia(boolean verificarTransacaoPraia) {
        this.verificarTransacaoPraia = verificarTransacaoPraia;
    }

    public boolean isVerificarTransacaoPraia() {
        return verificarTransacaoPraia;
    }

    public void setVerificarlinearAgenda(boolean verificarlinearAgenda) {
        this.verificarlinearAgenda = verificarlinearAgenda;
    }

    public boolean isVerificarlinearAgenda() {
        return verificarlinearAgenda;
    }

    public void setVerificarlinearAlojamento(boolean verificarlinearAlojamento) {
        this.verificarlinearAlojamento = verificarlinearAlojamento;
    }

    public boolean isVerificarlinearAlojamento() {
        return verificarlinearAlojamento;
    }

    public void setVerificarlinearCultura(boolean verificarlinearCultura) {
        this.verificarlinearCultura = verificarlinearCultura;
    }

    public boolean isVerificarlinearCultura() {
        return verificarlinearCultura;
    }

    public void setVerificarlinearDesporto(boolean verificarlinearDesporto) {
        this.verificarlinearDesporto = verificarlinearDesporto;
    }

    public boolean isVerificarlinearDesporto() {
        return verificarlinearDesporto;
    }

    public void setVerificarlinearEspaco(boolean verificarlinearEspaco) {
        this.verificarlinearEspaco = verificarlinearEspaco;
    }

    public boolean isVerificarlinearEspaco() {
        return verificarlinearEspaco;
    }

    public void setVerificarlinearGastronomia(boolean verificarlinearGastronomia) {
        this.verificarlinearGastronomia = verificarlinearGastronomia;
    }

    public boolean isVerificarlinearGastronomia() {
        return verificarlinearGastronomia;
    }

    public void setVerificarlinearMonumento(boolean verificarlinearMonumento) {
        this.verificarlinearMonumento = verificarlinearMonumento;
    }

    public boolean isVerificarlinearMonumento() {
        return verificarlinearMonumento;
    }

    public void setVerificarlinearOutro(boolean verificarlinearOutro) {
        this.verificarlinearOutro = verificarlinearOutro;
    }

    public boolean isVerificarlinearOutro() {
        return verificarlinearOutro;
    }

    public void setVerificarlinearPraia(boolean verificarlinearPraia) {
        this.verificarlinearPraia = verificarlinearPraia;
    }

    public boolean isVerificarlinearPraia() {
        return verificarlinearPraia;
    }

    public void setVerificarLongButtonAgenda(boolean verificarLongButtonAgenda) {
        this.verificarLongButtonAgenda = verificarLongButtonAgenda;
    }

    public boolean isVerificarLongButtonAgenda() {
        return verificarLongButtonAgenda;
    }

    public void setVerificarLongButtonAlojamento(boolean verificarLongButtonAlojamento) {
        this.verificarLongButtonAlojamento = verificarLongButtonAlojamento;
    }

    public boolean isVerificarLongButtonAlojamento() {
        return verificarLongButtonAlojamento;
    }

    public void setVerificarLongButtonCultura(boolean verificarLongButtonCultura) {
        this.verificarLongButtonCultura = verificarLongButtonCultura;
    }

    public boolean isVerificarLongButtonCultura() {
        return verificarLongButtonCultura;
    }

    public void setVerificarLongButtonDesporto(boolean verificarLongButtonDesporto) {
        this.verificarLongButtonDesporto = verificarLongButtonDesporto;
    }

    public boolean isVerificarLongButtonDesporto() {
        return verificarLongButtonDesporto;
    }

    public void setVerificarLongButtonEspaco(boolean verificarLongButtonEspaco) {
        this.verificarLongButtonEspaco = verificarLongButtonEspaco;
    }


    public boolean isVerificarLongButtonEspaco() {
        return verificarLongButtonEspaco;
    }

    public void setVerificarLongButtonGastronomia(boolean verificarLongButtonGastronomia) {
        this.verificarLongButtonGastronomia = verificarLongButtonGastronomia;
    }

    public boolean isVerificarLongButtonGastronomia() {
        return verificarLongButtonGastronomia;
    }

    public void setVerificarLongButtonMonumento(boolean verificarLongButtonMonumento) {
        this.verificarLongButtonMonumento = verificarLongButtonMonumento;
    }

    public boolean isVerificarLongButtonMonumento() {
        return verificarLongButtonMonumento;
    }

    public void setVerificarLongButtonOutro(boolean verificarLongButtonOutro) {
        this.verificarLongButtonOutro = verificarLongButtonOutro;
    }

    public boolean isVerificarLongButtonOutro() {
        return verificarLongButtonOutro;
    }

    public void setVerificarLongButtonPraia(boolean verificarLongButtonPraia) {
        this.verificarLongButtonPraia = verificarLongButtonPraia;
    }

    public boolean isVerificarLongButtonPraia() {
        return verificarLongButtonPraia;
    }


    public void setvalorcaminho(boolean valorcaminho) {
        this.valorcaminho = valorcaminho;
    }
}
