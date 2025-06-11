package src.Classes;

public class Cachorro {
    public String nome;
    private String raca;
    private int idade;
    private double peso;
    private String comportamento;
    private String alimentacao;

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAlimentacao(String alimentacao) {
        this.alimentacao = alimentacao;
    }

    public void setComportamento(String comportamento) {
        this.comportamento = comportamento;
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

    public String getAlimentacao() {
        return alimentacao;
    }

    public String getComportamento() {
        return comportamento;
    }

    public String getRaca() {
        return raca;
    }
}
