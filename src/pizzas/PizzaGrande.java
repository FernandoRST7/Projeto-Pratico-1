package pizzas;

public class PizzaGrande extends Pizza {

	// Construtor
	
	public PizzaGrande(int id_mesa) {
		super(id_mesa);
		maximoSabores = 3;
	}

	// Métodos
	
	public float calcularPreco() {
		int preco = 0;
		//cobra pelo sabor mais caro
		for (int i = 0; i < sabores.size(); i++) {
			if(sabores.get(i).preco > preco) {
				preco = sabores.get(i).preco;
			}
		}
		return preco;
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pizza grande (8 fatias): ");
        for (int i = 0; i < sabores.size(); i++) {
            sb.append(sabores.get(i));
            if (i < sabores.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
