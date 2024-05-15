package main;

import java.util.ArrayList;

public class Restaurante {
	private ArrayList<Pedido> pedidos;
	private ArrayList<Mesa> mesas;
	
	//construtor
	public Restaurante() {
		pedidos = new ArrayList<Pedido>();
		mesas = new ArrayList<Mesa>();
	}
	
	//getters
	public ArrayList<Pedido> getPedidos(){
		return pedidos;
	}
	
	public ArrayList<Mesa> getMesas(){
		return mesas;
	}
	
	//demais metodos
	public void addPedido(Pedido novoPedido) {
		//verifica se o novo pedido ja esta no array
		if(!pedidos.contains(novoPedido)) {
			pedidos.add(novoPedido);
		}
	}
	
	//nao precisa ter um metodo de adicionar mesa, pois todas as mesas ja estao no restaurante.
	// OBS(PIETRO): acho q precisa sim viu, pq senao n tem como adicionar as mesas no array
	//o que podemos fazer eh adicionar um atributo para mesas que indica se ela esta ocupada ou nao.
	//tipo: private boolean esta_ocupada;
	
	public void entregaPedido(Pedido pedidoEntregue) {
		pedidos.remove(pedidoEntregue);
	}
}
