package pizzas;

/*public class Sabor {
	private String nome;
	private int preco;
	
	public Sabor(String nome, int preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	//getters e setters
	public int getPreco() {
		return preco;
	}
	
	public String getNome() {
		return nome;
	}

}*/

public enum Sabor{
	CALABRESA(70),
	MUCARELA(60),
	ALCAPARRA(80),
	QUATRO_QUEIJOS(70),
	PORTUGUESA(60);
	// Adicionar mais sabores se quiserem

	public int preco;

	private Sabor(int preco){
		this.preco = preco;
	}

	public int getPreco() {
		return preco;
	}
}
