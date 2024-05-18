package main;

import java.util.ArrayList;

public class Restaurante {
	private ArrayList<Pedido> pedidos;
	private Mesa mesas[];
	
	//construtor
	public Restaurante() {
		pedidos = new ArrayList<Pedido>();
		
		mesas = new Mesa[50];
		for (int i = 0; i < 50; i++) { //restaurante com 50 mesas.
			mesas[i] = new Mesa();
		}
	}
	
	//getters
	public ArrayList<Pedido> getPedidos(){
		return pedidos;
	}
	
	public Mesa[] getMesas(){
		return mesas;
	}
	
	//demais metodos
	public void addPedido(Pedido novoPedido) {
		pedidos.add(novoPedido);
		
		mesas[novoPedido.getIdMesa()].addPedido(novoPedido);
			
	}
	
	public void entregaPedido(Pedido pedidoEntregue) {
		pedidos.remove(pedidoEntregue);
	}
}
