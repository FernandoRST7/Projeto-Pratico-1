package pizzas;

public class PizzaBrotinho extends Pizza {

	// Construtor

	public PizzaBrotinho(int id_mesa) {
		super(id_mesa);
		maximoSabores = 1;
	}
	
	// Métodos
	
	public float calcularPreco() {
		int preco = sabores.get(0).getPreco();
		return preco;
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sabores da Pizza brotinho (4 fatias): ");
        for (int i = 0; i < sabores.size(); i++) {
            sb.append(sabores.get(i));
            if (i < sabores.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
