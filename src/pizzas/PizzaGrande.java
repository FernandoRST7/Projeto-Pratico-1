package pizzas;

import java.util.ArrayList;
import java.util.List;

import main.Sabor;

public class PizzaGrande extends Pizza {

	// Construtor
	
	public PizzaGrande(List<Sabor> sabores) {
		super(new ArrayList<>(3));
	}

	// Métodos
	
	public int calcularPreco() {
		int preco = 0;
		for (int i = 0; i < 3; i++) {
			preco += sabores.get(i).getPreco()/3 * (1.5);
		}
		return preco;
	}
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sabores da Pizza grande (8 fatias): ");
        for (int i = 0; i < sabores.size(); i++) {
            sb.append(sabores.get(i));
            if (i < sabores.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
