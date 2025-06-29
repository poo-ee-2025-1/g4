package src.Classes;

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

    public String getNumeroContato() {
        return numeroContato;
    }
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public ForeignCollection<ClienteDog> getDogs() {
        return this.dogs;
    }

    public int getId() {
        return id;
    }

}
