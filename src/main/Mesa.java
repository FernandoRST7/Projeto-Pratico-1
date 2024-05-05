package main;

import java.util.ArrayList;

public class Mesa {
	private int id;
	private static int id_count = 0;
	private ArrayList<Pedido> pedidos;
	
	//Construtor
	public Mesa() {
		id_count++;
		this.id = id_count;
		this.pedidos = new ArrayList<Pedido>();
	}
	
	//GEtters e Setters
	public int getId() {
		return id;
	}
	
	public void addPedido(Pedido pedido) {
		//no cado de uma pessoa pedir varias coisas teria q chamar esse metodo varias vezes
		this.pedidos.add(pedido);
	}
	
	public void removePedido(Pedido pedido) {
		this.pedidos.remove(pedido);
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedidos da Mesa: ");
        for (int i = 0; i < pedidos.size(); i++) {
            sb.append(pedidos.get(i));
            if (i < pedidos.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}