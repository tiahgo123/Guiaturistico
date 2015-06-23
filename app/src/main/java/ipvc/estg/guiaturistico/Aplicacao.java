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

    public void setValorRaio(float valorRaio) {
        this.valorRaio = valorRaio;
    }

    public float getValorRaio() {
        return valorRaio;
    }

    public void setVerificarentraMonumento(boolean verificarentraMonumento) {
        this.verificarentraMonumento = verificarentraMonumento;
    }

    public void setVerificarentrarOutro(boolean verificarentrarOutro) {
        this.verificarentrarOutro = verificarentrarOutro;
    }

    public void setVerifcarentrarEspaco(boolean verifcarentrarEspaco) {
        this.verifcarentrarEspaco = verifcarentrarEspaco;
    }

    public void setVerificarentrarDesporto(boolean verificarentrarDesporto) {
        this.verificarentrarDesporto = verificarentrarDesporto;
    }

    public void setVerificaenrrarPraia(boolean verificaenrrarPraia) {
        this.verificaenrrarPraia = verificaenrrarPraia;
    }

    public void setVerificarentrarAgenda(boolean verificarentrarAgenda) {
        this.verificarentrarAgenda = verificarentrarAgenda;
    }

    public void setVericificarentrarAlojamento(boolean vericificarentrarAlojamento) {
        this.vericificarentrarAlojamento = vericificarentrarAlojamento;
    }

    public void setVerificarentrarGastronomia(boolean verificarentrarGastronomia) {
        this.verificarentrarGastronomia = verificarentrarGastronomia;
    }

    public void setVerificarentrarCultura(boolean verificarentrarCultura) {
        this.verificarentrarCultura = verificarentrarCultura;
    }

    boolean verificarlinearMonumento;
    boolean verificarlinearCultura;
    boolean verificarlinearGastronomia;
    boolean verificarlinearAlojamento;
    boolean verificarlinearAgenda;
    boolean verificarlinearPraia;
    boolean verificarlinearDesporto;
    boolean verificarlinearEspaco;
    boolean verificarlinearOutro;

    boolean verificarentraMonumento;

    public boolean isVerificarentrarCultura() {
        return verificarentrarCultura;
    }

    public boolean isVerificarentrarGastronomia() {
        return verificarentrarGastronomia;
    }

    public boolean isVericificarentrarAlojamento() {
        return vericificarentrarAlojamento;
    }

    public boolean isVerificarentrarAgenda() {
        return verificarentrarAgenda;
    }

    public boolean isVerificarentrarDesporto() {
        return verificarentrarDesporto;
    }

    public boolean isVerificaenrrarPraia() {
        return verificaenrrarPraia;
    }

    public boolean isVerifcarentrarEspaco() {
        return verifcarentrarEspaco;
    }

    public boolean isVerificarentrarOutro() {
        return verificarentrarOutro;
    }

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



    public boolean isVerificarentraMonumento(){return verificarentraMonumento;}



}
