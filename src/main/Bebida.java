package main;

public class Bebida extends Pedido {
	private String nome;
	private int preco;
	
	//construtor
	public Bebida(int idMesa, String nome, int preco) {
		super(idMesa);
		
		this.nome = nome;
		this.preco = preco;
	}
	
	//getters
	public String getNome() {
		return nome;
	}
	
	public int getPreco() {
		return preco;
	}
	
	// Métodos
	
	public String toString() {
	    return "Bebida: " + this.nome + ", " + this.preco + " reais" + "\n" +
	           "Mesa: " + this.getIdMesa();
	}
}
