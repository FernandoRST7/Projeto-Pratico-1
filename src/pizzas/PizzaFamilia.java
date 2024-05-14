package pizzas;

public class PizzaFamilia extends Pizza {

	// Construtor
	
	public PizzaFamilia(int id_mesa) {
		super(id_mesa);
	}

	// Métodos
	
	public float calcularPreco() {
		int preco = 0;
		for (int i = 0; i < 4; i++) {
			preco += sabores.get(i).getPreco()/4 * (2);
		}
		return preco;
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sabores da Pizza família (12 fatias): ");
        for (int i = 0; i < sabores.size(); i++) {
            sb.append(sabores.get(i));
            if (i < sabores.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
