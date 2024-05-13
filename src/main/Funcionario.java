package main;

public class Funcionario {
    private boolean livre;
    private final String cargo; // coloquei final pq em tese não eh pro cara mudar de emprego no meio do turno
    //private Pedido pedido_atual; vcs acham q eh bom marcar o pedido atual do funcionario?

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

    /*public Pedido getPedidoAtual(){
        return pedido_atual;
    }*/

    public void setLivre(boolean livre){
        this.livre = livre;
    }
}
