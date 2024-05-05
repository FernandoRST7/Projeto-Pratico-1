package main;

public abstract class Pedido {
	private int id;
	private static int id_count = 0;
	private int id_mesa;
	
	//contrutor
	public Pedido (int id_mesa) {
		id_count++;
		this.id = id_count;
		this.id_mesa = id_mesa;
	}
	
	//Getters e setters
	public int getId() {
		return id;
	}
	
	public int getIdMesa() {
		return id_mesa;
	}

	@Override
	public String toString() {
		return "Dados do Pedido: " + '\n' + 
				"id: " + this.id + '\n' + 
				"id da mesa: " + this.id_mesa;
	}
}
