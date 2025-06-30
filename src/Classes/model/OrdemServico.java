package src.Classes.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import src.Classes.model.enums.StatusOS;

import java.util.Date;

@DatabaseTable(tableName = "ordem_servico")
public class OrdemServico {
    @DatabaseField(generatedId = true)
    private int id;

    //OS pertence a um cliente, então deve estar relacioando a um cliente id
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "cliente_id", canBeNull = false)
    private Cliente cliente;
    //Mesma coisa com o cachorro, no entando ele é o alvo da os
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "cachorro_id", canBeNull = false)
    private Cachorro cachorro;
    @DatabaseField(dataType = DataType.DATE_STRING, format = "dd-MM-yyy HH:mm:ss")
    private Date dataAbertura;
    @DatabaseField(dataType = DataType.DATE_STRING, format = "dd-MM-yyy HH:mm:ss")
    private Date dataFechamento;
    @DatabaseField(dataType = DataType.ENUM_STRING)
    private StatusOS status;
    @DatabaseField
    private String descricao;
    @DatabaseField
    private double valor;

    public OrdemServico(){

    }

    public Cachorro getCachorro() {
        return cachorro;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public double getValor() {
        return valor;
    }

    public StatusOS getStatus() {
        return status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCachorro(Cachorro cachorro) {
        this.cachorro = cachorro;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setStatus(StatusOS status) {
        this.status = status;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
}
