package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;


public class Restaurante {
	private ArrayList<Pedido> pedidos;
	private Mesa mesas[];
	private float dinheiro;
	private int clientes;
	private File registro;
	private String nomeRegistro;
	
	//construtor
	public Restaurante() {
		pedidos = new ArrayList<Pedido>();
		
		mesas = new Mesa[50];
		for (int i = 0; i < 50; i++) { //restaurante com 50 mesas.
			mesas[i] = new Mesa();
		}

		dinheiro = 0;
		
		for (int i = 0; ; i++) {
            String fileName = "log" + i + ".txt";
            File file = new File(fileName);

            if (!file.exists()) {
                try {
                    Formatter output = new Formatter(file);
                    System.out.println("Arquivo criado: " + fileName);
                    // Faça o que precisa com o arquivo aqui
                    this.registro = file;
                    this.nomeRegistro = "log" + i + ".txt";
                    output.close();
                    break; // Saia do loop quando encontrar um nome disponível
                } catch (IOException e) {
                    System.err.println("Erro ao criar o arquivo: " + e.getMessage());
                }
            }
		}
	}
	
	public void salvaLog(String mensagem) {
		/*try {
            Formatter formatter = new Formatter(this.file);
            formatter.format("%s%n", registro); // Escreve a string no arquivo
            formatter.close();
            System.out.println("Registro gravado no arquivo.");
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        }*/
		
		 try {
		        FileWriter fileWriter = new FileWriter(registro, true); // Abre o arquivo em modo de anexação
		        PrintWriter printWriter = new PrintWriter(fileWriter);
		        printWriter.println(mensagem); // Escreve a string no arquivo seguida de uma nova linha
		        printWriter.close();
		        System.out.println("Registro gravado no arquivo.");
		    } catch (IOException e) {
		        System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
		    }
    }
	
	//getters
	public File getRegistro() {
		return registro;
	}
	
	public String getNomeRegistro() {
		return nomeRegistro;
	}
	
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
