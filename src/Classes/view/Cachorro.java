package src.Classes.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Cachorro {

        private SimpleIntegerProperty id;
        private SimpleStringProperty nome;
        private SimpleStringProperty raca;
        private SimpleIntegerProperty idade;
        private SimpleDoubleProperty peso;

        public Cachorro(int id, String nome, String raca, int idade, double peso) {
            this.id = new SimpleIntegerProperty(id);
            this.nome = new SimpleStringProperty(nome);
            this.raca = new SimpleStringProperty(raca);
            this.idade = new SimpleIntegerProperty(idade);
            this.peso = new SimpleDoubleProperty(peso);
        }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setIdade(int idade) {
        this.idade.set(idade);
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public void setPeso(double peso) {
        this.peso.set(peso);
    }

    public void setRaca(String raca) {
        this.raca.set(raca);
    }

    public double getPeso() {
        return peso.get();
    }

    public int getId() {
        return id.get();
    }

    public int getIdade() {
        return idade.get();
    }

    public String getNome() {
        return nome.get();
    }

    public String getRaca() {
        return raca.get();
    }
}

