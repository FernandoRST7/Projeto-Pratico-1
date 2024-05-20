package pizzas;

public enum Sabor{
	CALABRESA(70),
	MUCARELA(60),
	ALCAPARRA(80),
	QUATRO_QUEIJOS(70),
	PORTUGUESA(60);

	public int preco;

	private Sabor(int preco){
		this.preco = preco;
	}

	public int getPreco() {
		return preco;
	}
}
