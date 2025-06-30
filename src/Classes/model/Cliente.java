package src.Classes.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "cliente")
public class Cliente {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(dataType = DataType.STRING)
    private String nomeCompleto;
    @DatabaseField(dataType = DataType.STRING)
    private String numeroContato;
    @DatabaseField(dataType = DataType.STRING)
    private  String CPF;
    @ForeignCollectionField
    private ForeignCollection<ClienteDog> dogs;

    public Cliente(){

    }
    public Cliente(String nomeCompleto, String numeroContato, String CPF){
        this.nomeCompleto = nomeCompleto;
        this.numeroContato = numeroContato;
        this.CPF = CPF;
    }

    public String getNumeroContato() {
        return numeroContato;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCPF() {return CPF;}

    public void setId(int id) {this.id = id;}

    public void setCPF(String CPF) {this.CPF = CPF;}

    public void setDogs(ForeignCollection<ClienteDog> dogs) {this.dogs = dogs;}

    public void setNomeCompleto(String nomeCompleto) {this.nomeCompleto = nomeCompleto;}

    public void setNumeroContato(String numeroContato) {this.numeroContato = numeroContato;}

    public ForeignCollection<ClienteDog> getDogs() {
        return this.dogs;
    }

    public int getId() {
        return id;
    }

}
