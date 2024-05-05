package pizzas;
import java.util.List;

import main.Sabor;

public abstract class Pizza {
	protected List<Sabor> sabores;
	
	// Construtor
	
	public Pizza(List<Sabor> sabores) {
		this.sabores = sabores;
	}
	
	// Getters e Setters
	
	public List<Sabor> getSabores() {
		return sabores;
	}
	
	public void setSabores(List<Sabor> sabores) {
		this.sabores = sabores;
	}
	
	// Métodos
	
	public void adicionarSabores(Sabor sabor) {
		this.sabores.add(sabor);
	}
	
	public void removerSabores(Sabor sabor) {
		this.sabores.remove(sabor);
	}
	
	public abstract int calcularPreco();
	
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
