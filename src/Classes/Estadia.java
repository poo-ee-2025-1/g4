package src.Classes;

public class Estadia {
    private Cachorro cachorro;
    private Cliente cliente;
    private String dataEntrada;
    private String dataSaida;
    private String[] servicos;


    public void setCachorro(Cachorro cachorro) {
        this.cachorro = cachorro;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public void setDono(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setServicos(String[] servicos) {
        this.servicos = servicos;
    }

    public Cachorro getCachorro() {
        return cachorro;
    }

    public Cliente getDono() {
        return cliente;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public String[] getServicos() {
        return servicos;
    }
}
