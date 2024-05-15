package pizzas;
import java.util.ArrayList;

import main.Pedido;

public abstract class Pizza extends Pedido {
	protected ArrayList<Sabor> sabores;
	
	// Construtor
	
	public Pizza(int id_mesa) {
		super(id_mesa);
		sabores = new ArrayList<Sabor>();
	}
	
	// Getters
	
	public ArrayList<Sabor> getSabores() {
		return sabores;
	}
	
	//acho que nao precisa de setter de sabores
	
	// Métodos
	
	public void adicionarSabor(Sabor sabor) {
		if (!sabores.contains(sabor)) {
			this.sabores.add(sabor);
		}
	}
	
	public void removerSabor(Sabor sabor) {
		this.sabores.remove(sabor);
	}
	
	public abstract float calcularPreco();
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sabores da Pizza: ");
        for (int i = 0; i < sabores.size(); i++) {
            sb.append(sabores.get(i));
            if (i < sabores.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
