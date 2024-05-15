package main;

import java.util.ArrayList;

public class Restaurante {
	private ArrayList<Pedido> pedidos;
	//private ArrayList<Mesa> mesas;
	private Mesa mesas[];
	
	//construtor
	public Restaurante() {
		pedidos = new ArrayList<Pedido>();
		//mesas = new ArrayList<Mesa>();
		
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
	
	//nao precisa ter um metodo de adicionar mesa, pois todas as mesas ja estao no restaurante.
	// OBS(PIETRO): acho q precisa sim viu, pq senao n tem como adicionar as mesas no array
	//o que podemos fazer eh adicionar um atributo para mesas que indica se ela esta ocupada ou nao.
	//tipo: private boolean esta_ocupada;
	
	public void entregaPedido(Pedido pedidoEntregue) {
		pedidos.remove(pedidoEntregue);
	}
}
