package main;

import java.util.Scanner;

import pizzas.Pizza;
import pizzas.PizzaBrotinho;
import pizzas.PizzaFamilia;
import pizzas.PizzaGrande;
import pizzas.PizzaMedia;
import pizzas.Sabor;

public class Menu { // fazer resistente a burrices

    private static void imprime_sabores(){
        // imprime os sabores disponíveis
        for (Sabor sabor : Sabor.values()){
            System.out.println("[" + sabor.ordinal() + "] " + sabor);
        }
    }

    private static Sabor busca_sabor(String input){
        // busca um sabor com base em sua posição ordinal no enum
        Sabor[] sabores = Sabor.values();
        Sabor sabor = sabores[Integer.parseInt(input)];
        return sabor;
    }

    //NÃO TO CONSEGUINDO FAZER ISSO FUNCIONAR
    private static Pizza monta_pizza(Scanner scanner, Pizza pizza){
    	String input = "";
    	int n_sabores;
    	
        System.out.println("Digite o número de sabores da pizza:");

    	while(true) {
            n_sabores = Integer.parseInt(scanner.nextLine());
            
            if (n_sabores > pizza.getMaximoSabores()) {
                System.out.println("Mais sabores do que permitido na pizza.");
                System.out.println("Digite um número de sabores válido:");
            }
            
            else {
                break;
            }
    	}

        
        if (n_sabores == 1) {
            System.out.println("Digite o id do sabor da pizza: ");
            imprime_sabores();
            input = scanner.nextLine();
            Sabor sabor = busca_sabor(input);
            pizza.adicionarSabor(sabor);
            
            return pizza;
        }
        
        for (int i = 1; i < n_sabores + 1; i++){
            System.out.println("Digite o id do " + i + "° sabor da pizza: ");
            imprime_sabores();
            input = scanner.nextLine();
            Sabor sabor = busca_sabor(input);
            pizza.adicionarSabor(sabor);
        }
        return pizza;
    } 

    public static void Entrada(Restaurante restaurante){
        // lê a entrada do usuário e registra tudo
        Scanner scanner = new Scanner(System.in);
        while (true){
        	//falta fazer adicionar bebida, adicionar cliente na mesa e ver sabores em ordem.
            System.out.println("- Adicionar cliente na mesa (digite 1);");
            System.out.println("- Adicionar pedido de pizza (digite 2);");
            System.out.println("- Adicionar pedido de bebida (digite 3);");
            System.out.println("- Ver sabores em ordem (digite 4);");
            System.out.println("- Ver pedidos de uma mesa (digite 5);");
            System.out.println("- Encerrar expediente (digite 6);");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("6")){
                System.out.println("Fim do expediente.");
                // imprimir dados do restaurante (a fazer)
                break;
            }
            
            //bebida
            else if (input.equalsIgnoreCase("3")){

            }
            
            //pizza
            else if (input.equalsIgnoreCase("2")){
            	Pizza pizza;
            	
                System.out.println("Digite o id da mesa: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.println("Qual o tamanho da pizza?");
                System.out.println("- Brotinho (digite 1);");
                System.out.println("- Média (digite 2);");
                System.out.println("- Grande (digite 3);");
                System.out.println("- Família (digite 4);");

                input = scanner.nextLine();
                int n_sabores;
                if (input.equalsIgnoreCase("1")){
                    pizza = new PizzaBrotinho(id);
                    n_sabores = 1;
                    for (int i=1; i<n_sabores+1; i++){
                        System.out.println("Digite o id do sabor da pizza: ");
                        imprime_sabores();
                        input = scanner.nextLine();
                        Sabor sabor = busca_sabor(input);
                        pizza.adicionarSabor(sabor);
                    }
                    restaurante.addPedido(pizza);
                }
                else if (input.equalsIgnoreCase("2")){
                    pizza = new PizzaMedia(id);
                    
                    //deixei as partes antigas em comentarios,
                    //mas acho que nao precisamos mais delas porque o monta_pizzza deu certo
                    
                    /*n_sabores = 2;
                    for (int i = 1; i < n_sabores + 1; i++){
                        System.out.println("Digite o id do " + i + "° sabor da pizza: ");
                        imprime_sabores();
                        input = scanner.nextLine();
                        Sabor sabor = busca_sabor(input);
                        pizza.adicionarSabor(sabor);
                    }*/
                    pizza = monta_pizza(scanner, pizza);
                    restaurante.addPedido(pizza);
                }
                else if (input.equalsIgnoreCase("3")){
                    pizza = new PizzaGrande(id);
                    
                    /*n_sabores = 3;
                    for (int i = 1; i < n_sabores + 1; i++){
                        System.out.println("Digite o número do " + i + "° sabor da pizza: ");
                        imprime_sabores();
                        input = scanner.nextLine();
                        Sabor sabor = busca_sabor(input);
                        pizza.adicionarSabor(sabor);
                    }*/
                    pizza = monta_pizza(scanner, pizza);
                    restaurante.addPedido(pizza);
                }
                else if (input.equalsIgnoreCase("4")){
                    pizza = new PizzaFamilia(id);
                    /*n_sabores = 4;
                    for (int i = 1; i < n_sabores + 1; i++){
                        System.out.println("Digite o número do " + i + "° sabor da pizza: ");
                        imprime_sabores();
                        input = scanner.nextLine();
                        Sabor sabor = busca_sabor(input);
                        pizza.adicionarSabor(sabor);
                    }*/
                    
                    pizza = monta_pizza(scanner, pizza);
                    restaurante.addPedido(pizza);
                }
            }
            
            //ver pedidos de uma mesa
            else if (input.equalsIgnoreCase("5")) {
                System.out.println("Digite o id do sabor da mesa a ser visualizada: ");
                int idMesa = Integer.parseInt(scanner.nextLine());
                System.out.println(restaurante.getMesas()[idMesa]);
                

            }
        }
        scanner.close();
    }
}
