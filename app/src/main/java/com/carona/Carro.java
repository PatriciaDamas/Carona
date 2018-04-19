package com.carona;

/**
 * Created by Patrícia on 09/04/2018.
 */

public class Carro {

    private String marca_veiculo;
    private String cor;
    private String matricula;
    private String lugares;
    private String modelo;


    public Carro() {
    }

    public Carro(String marca_veiculo, String cor, String matricula, String lugares, String modelo) {
        this.marca_veiculo = marca_veiculo;
        this.cor = cor;
        this.matricula = matricula;
        this.lugares = lugares;
        this.modelo = modelo;
    }

    public String getMarca_veiculo() {
        return marca_veiculo;
    }

    public void setMarca_veiculo(String marca_veiculo) {
        this.marca_veiculo = marca_veiculo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getLugares() {
        return lugares;
    }

    public void setLugares(String lugares) {
        this.lugares = lugares;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
