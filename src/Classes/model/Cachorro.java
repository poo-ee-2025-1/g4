package src.Classes.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.awt.*;

@DatabaseTable(tableName = "cachorro")
public class Cachorro {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(dataType = DataType.STRING)
    public String nome;
    @DatabaseField(dataType = DataType.STRING)
    private String raca;
    @DatabaseField(dataType = DataType.INTEGER)
    private int idade;
    @DatabaseField(dataType = DataType.DOUBLE)
    private double peso;

    public Cachorro(){

    }
    public Cachorro(String nome, String raca, int idade, double peso){
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.peso = peso;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public String getRaca() {
        return raca;
    }
}
