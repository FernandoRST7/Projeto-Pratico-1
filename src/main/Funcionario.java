package main;

public class Funcionario {
    private boolean livre;
    private final String cargo; // Funcionario nao muda de emprego no meio do turno

    public Funcionario(String cargo){
        this.livre = true;
        this.cargo = cargo;
    }

    // getters e setters
    public boolean getLivre(){
        return livre;
    }

    public String getCargo(){
        return cargo;
    }

    public void setLivre(boolean livre){
        this.livre = livre;
    }
}
