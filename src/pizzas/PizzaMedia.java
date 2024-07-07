package pizzas;

public class PizzaMedia extends Pizza {

	// Construtor
	public PizzaMedia(int id_mesa) {
		super(id_mesa);
		maximoSabores = 2;
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

	    // Multiplica o preço encontrado por 2
	    preco = preco * 2;

	    return preco;
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pizza média (6 fatias): ");
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
