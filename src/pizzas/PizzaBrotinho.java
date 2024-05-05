package pizzas;

import java.util.ArrayList;
import java.util.List;

import main.Sabor;

public class PizzaBrotinho extends Pizza {

	// Construtor

	public PizzaBrotinho(List<Sabor> sabores, int id_mesa) {
		super(new ArrayList<>(1), id_mesa);
	}
	
	// Métodos
	
	public int calcularPreco() {
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
