package main;

import java.util.ArrayList;

public class Restaurante {
	private ArrayList<Pedido> pedidos;
	private Mesa mesas[];
	private float dinheiro;
	private int clientes;
	
	//construtor
	public Restaurante() {
		pedidos = new ArrayList<Pedido>();
		
		mesas = new Mesa[50];
		for (int i = 0; i < 50; i++) { //restaurante com 50 mesas.
			mesas[i] = new Mesa();
		}

		dinheiro = 0;
	}
	
	//getters
	public ArrayList<Pedido> getPedidos(){
		return pedidos;
	}
	
	public Mesa[] getMesas(){
		return mesas;
	}
	
	//demais metodos
	public boolean addPedido(Pedido novoPedido) {
		pedidos.add(novoPedido);
		
		if (mesas[novoPedido.getIdMesa()].estaOcupada()) {
			mesas[novoPedido.getIdMesa()].addPedido(novoPedido);
			return true;
		}
		return false;
	}
	
	public void entregaPedido(Pedido pedidoEntregue) {
		pedidos.remove(pedidoEntregue);
	}

	public void adicionaDinheiro(float valor){
		dinheiro += valor;
	}

	public void adicionaCliente(){
		clientes++;
	}

	public String printaPedidos(){
		String string="";
		for (int i=0; i<pedidos.size(); i++){
			string += pedidos.get(i) + "\n";
		}
		return string;
	}

	public String imprimeDados(){
		String string="";
		string += "Número de clientes: " + clientes + ".\n";
		string += "Número de pedidos: " + pedidos.size() + ".\n";
		string += "Dinheiro ganho: " + dinheiro + ".\n";
		return string;
	}
}
