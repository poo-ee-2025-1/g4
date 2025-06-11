package src.Classes;

public class Dono {
    public String nome;
    private String telefone;
    private int ano;
    private String endereco;
    private String contatoEmergencia;

    public String getNome() {
        return nome;
    }

    public int getAno() {
        return ano;
    }

    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setContatoEmergencia(String contatoEmergencia) {
        this.contatoEmergencia = contatoEmergencia;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}



