package com.carona;

import java.util.List;

/**
 * Created by Patrícia on 07/04/2018.
 */

public class Viagem {
    private String condutor, data, localPartida, localChegada, preco, hora, email;
    private Boolean estadoViagem;
    private int lugaresCarro, lugaresDisponiveis;
    private List<String> passageiros;
    private String imagem;
    public Viagem() {
    }

    public Viagem(String email,String imagem,String condutor,String data, String localPartida, String localChegada, String preco, String hora, Boolean estadoViagem, int lugaresCarro, int lugaresDisponiveis, List<String> passageiros) {
        this.email = email;
        this.imagem = imagem;
        this.condutor = condutor;
        this.data = data;
        this.localPartida = localPartida;
        this.localChegada = localChegada;
        this.preco = preco;
        this.hora = hora;
        this.estadoViagem = estadoViagem;
        this.lugaresCarro = lugaresCarro;
        this.lugaresDisponiveis = lugaresDisponiveis;
        this.passageiros = passageiros;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getCondutor() {
        return condutor;
    }

    public void setCondutor(String condutor) {
        this.condutor = condutor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocalPartida() {
        return localPartida;
    }

    public void setLocalPartida(String localPartida) {
        this.localPartida = localPartida;
    }

    public String getLocalChegada() {
        return localChegada;
    }

    public void setLocalChegada(String localChegada) {
        this.localChegada = localChegada;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Boolean getEstadoViagem() {
        return estadoViagem;
    }

    public void setEstadoViagem(Boolean estadoViagem) {
        this.estadoViagem = estadoViagem;
    }

    public int getLugaresCarro() {
        return lugaresCarro;
    }

    public void setLugaresCarro(int lugaresCarro) {
        this.lugaresCarro = lugaresCarro;
    }

    public int getLugaresDisponiveis() {
        return lugaresDisponiveis;
    }

    public void setLugaresDisponiveis(int lugaresDisponiveis) {
        this.lugaresDisponiveis = lugaresDisponiveis;
    }

    public List<String> getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(List<String> passageiros) {
        this.passageiros = passageiros;
    }
}
