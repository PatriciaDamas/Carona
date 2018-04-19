package com.carona;

/**
 * Created by Patrícia on 09/04/2018.
 */

public class User {

    private String nome;
    private String urlImg;
    private String email;
    private String estabelecimento;
    private String curso;
    private String telemovel;
    private String saldo;
    private String nib;
    private Carro carro ;
    private int viagens_registadas;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(String nome,String urlImg, String email, String estabelecimento, String curso, String telemovel, String saldo, String nib, Carro carro, int viagens_registadas) {
        this.nome = nome;
        this.urlImg = urlImg;
        this.email = email;
        this.estabelecimento = estabelecimento;
        this.curso = curso;
        this.telemovel = telemovel;
        this.saldo = saldo;
        this.nib = nib;
        this.carro = carro;
        this.viagens_registadas = viagens_registadas;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getNib() {
        return nib;
    }

    public void setNib(String nib) {
        this.nib = nib;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public int getViagens_registadas() {
        return viagens_registadas;
    }

    public void setViagens_registadas(int viagens_registadas) {
        this.viagens_registadas = viagens_registadas;
    }
}
