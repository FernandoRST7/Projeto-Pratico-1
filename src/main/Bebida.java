package main;

public class Bebida extends Pedido {
	private String nome;
	private float preco;
	
	//construtor
	public Bebida(int idMesa, String nome, float preco) {
		super(idMesa);
		
		this.nome = nome;
		this.preco = preco;
	}
	
	//getters
	public String getNome() {
		return nome;
	}
	
	public float getPreco() {
		return preco;
	}
	
	// Métodos
	public String toString() {
	    return "Bebida: " + this.nome + ", " + this.preco + " reais" + "\n" +
	           "Mesa: " + this.getIdMesa();
	}
}
