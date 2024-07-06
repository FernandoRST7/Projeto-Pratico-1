package main;

public class Bebida extends Pedido {
	private String nome;
	private float preco;
	
	//construtor
	public Bebida(int idMesa, String nome) {
		super(idMesa);
		
		this.nome = nome;
		
		if (nome.equals("Água")) {
			this.preco = 3;
		}
		
		else if (nome.equals("Refrigerante")) {
			this.preco = 6;
		}
		
		else if (nome.equals("Vinho")) {
			this.preco = 30;
		}
		
		else if (nome.equals("Suco")) {
			this.preco = 8;
		}
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
	    return "Bebida: " + this.nome + ", " + this.preco + " reais. " + "\n" +
		           "Mesa " + (this.getIdMesa() + 1);
	}
}
