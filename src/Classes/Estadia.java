package src.Classes;

public class Estadia {
    private Cachorro cachorro;
    private Dono dono;
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

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public void setServicos(String[] servicos) {
        this.servicos = servicos;
    }

    public Cachorro getCachorro() {
        return cachorro;
    }

    public Dono getDono() {
        return dono;
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
