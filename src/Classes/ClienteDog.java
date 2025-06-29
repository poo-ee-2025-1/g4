package src.Classes;
//Classe que liga um id de cachorro a um cliente

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "cliente_dog")
public class ClienteDog {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, columnName = "cliente_id")
    private Cliente cliente;
    @DatabaseField(foreign = true, columnName = "cachorro_id")
    private Cachorro cachorro;

    public ClienteDog(){

    }

    public ClienteDog(Cliente cliente, Cachorro cachorro) {
        this.cliente = cliente;
        this.cachorro = cachorro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cachorro getCachorro() {
        return cachorro;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public int getId() {
        return id;
    }
}
