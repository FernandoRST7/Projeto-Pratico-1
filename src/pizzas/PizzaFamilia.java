package pizzas;

public class PizzaFamilia extends Pizza {

	// Construtor
	public PizzaFamilia(int id_mesa) {
		super(id_mesa);
		maximoSabores = 4;
	}

	// Métodos
	public float calcularPreco() {
		float preco = 0;

	    // Encontra o sabor mais caro
	    for (Sabor sabor : sabores) {
	        if (sabor.getPreco() > preco) {
	            preco = sabor.getPreco();
	        }
	    }

	    // Multiplica o preço encontrado por 4
	    preco = preco * 4;

	    return preco;
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pizza família (12 fatias): ");
        for (int i = 0; i < sabores.size(); i++) {
            sb.append(sabores.get(i));
            if (i < sabores.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(" -> (mesa " + super.getIdMesa() + ")");
        return sb.toString();
    }
}
