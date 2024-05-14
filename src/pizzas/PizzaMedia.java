package pizzas;

public class PizzaMedia extends Pizza {

	// Construtor
	
	public PizzaMedia(int id_mesa) {
		super(id_mesa);
	}
	
	// Métodos
	
	public float calcularPreco() {
		int preco = 0;
		for (int i = 0; i < 2; i++) {
			preco += sabores.get(i).getPreco()/2 * (1.25);
		}
		return preco;
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sabores da Pizza média (6 fatias): ");
        for (int i = 0; i < sabores.size(); i++) {
            sb.append(sabores.get(i));
            if (i < sabores.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
