package main;

import java.util.Scanner;

import pizzas.Pizza;
import pizzas.PizzaBrotinho;
import pizzas.PizzaFamilia;
import pizzas.PizzaGrande;
import pizzas.PizzaMedia;
import pizzas.Sabor;

public class Menu { // (PIETRO) FAZER EXCEÇÕES PARA NAO QUEBRAR O CODIGO!!!!

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

    private static Pizza monta_pizza(Scanner scanner, Pizza pizza){
    	String input;
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
        
        for (int i = 1; i < n_sabores + 1; i++){
            System.out.println("Digite o id do " + i + "° sabor da pizza: ");
            imprime_sabores();
            input = scanner.nextLine();
            Sabor sabor = busca_sabor(input);
            pizza.adicionarSabor(sabor);
        }
        return pizza;
    } 

    private static void registra_pizza(Scanner scanner, Restaurante restaurante){
        String input;
        Pizza pizza;
        System.out.println("Digite o id da mesa: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Qual o tamanho da pizza?");
        System.out.println("- Brotinho (digite 1);");
        System.out.println("- Média (digite 2);");
        System.out.println("- Grande (digite 3);");
        System.out.println("- Família (digite 4);");

        input = scanner.nextLine();
        if (input.equalsIgnoreCase("1")){
            pizza = new PizzaBrotinho(id);
            System.out.println("Digite o id do sabor da pizza: ");
            imprime_sabores();
            input = scanner.nextLine();
            Sabor sabor = busca_sabor(input);
            pizza.adicionarSabor(sabor);
            restaurante.addPedido(pizza);
        }
        else if (input.equalsIgnoreCase("2")){
            pizza = new PizzaMedia(id);
            pizza = monta_pizza(scanner, pizza);
            restaurante.addPedido(pizza);
        }
        else if (input.equalsIgnoreCase("3")){
            pizza = new PizzaGrande(id);
            pizza = monta_pizza(scanner, pizza);
            restaurante.addPedido(pizza);
        }
        else if (input.equalsIgnoreCase("4")){
            pizza = new PizzaFamilia(id);
            pizza = monta_pizza(scanner, pizza);
            restaurante.addPedido(pizza);
        }
    }
    
    private static void registra_bebida(Scanner scanner, Restaurante restaurante){
    	String input;
        Bebida bebida;
        System.out.println("Digite o id da mesa: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Qual a bebida?");
        System.out.println("- Suco (digite 1);");
        System.out.println("- Refrigerante (digite 2);");
        System.out.println("- Vinho (digite 3);");
        System.out.println("- Água (digite 4);");
        
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("1")){
            bebida = new Bebida(id, "Suco", 8);
            restaurante.addPedido(bebida);
        }
        else if (input.equalsIgnoreCase("2")){
        	bebida = new Bebida(id, "Refrigerante", 6);
            restaurante.addPedido(bebida);
        }
        else if (input.equalsIgnoreCase("3")){
        	bebida = new Bebida(id, "Vinho", 30);
            restaurante.addPedido(bebida);
        } 
        else if (input.equalsIgnoreCase("4")){
        	bebida = new Bebida(id, "Água", 3);
            restaurante.addPedido(bebida);
        }
    }

    public static void Entrada(Restaurante restaurante){
        // lê a entrada do usuário e registra tudo
        Scanner scanner = new Scanner(System.in);
        while (true){
        	//falta fazer adicionar bebida e adicionar cliente na mesa.
            System.out.println("- Adicionar cliente na mesa (digite 1);");
            System.out.println("- Adicionar pedido de pizza (digite 2);");
            System.out.println("- Adicionar pedido de bebida (digite 3);");
            System.out.println("- Ver sabores em ordem (digite 4);");
            System.out.println("- Ver pedidos de uma mesa (digite 5);");
            System.out.println("- Encerrar expediente (digite 6);");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("6")){
                System.out.println("Fim do expediente.");
                // (PIETRO) IMPRIMIR DADOS DO RESTAURANTE (A FAZER)
                break;
            }
            
            //bebida
            else if (input.equalsIgnoreCase("3")){
            	registra_bebida(scanner, restaurante);

            }

            // imprime sabores
            else if (input.equalsIgnoreCase("4")){
                imprime_sabores();
            }
            
            //pizza
            else if (input.equalsIgnoreCase("2")){
                
                registra_pizza(scanner, restaurante);
                // (PIETRO) EU MOVI ESSE BLOCO INTEIRO DE CODIGO COMENTADO PRA DENTRO DESSA FUNCAO REGISTRA_PIZZA
                // PARA FICAR MAIS ORGANIZADO ESSE METODO ENTRADA

            	// Pizza pizza;
            	
                /*System.out.println("Digite o id da mesa: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.println("Qual o tamanho da pizza?");
                System.out.println("- Brotinho (digite 1);");
                System.out.println("- Média (digite 2);");
                System.out.println("- Grande (digite 3);");
                System.out.println("- Família (digite 4);");

                input = scanner.nextLine();
                if (input.equalsIgnoreCase("1")){
                    pizza = new PizzaBrotinho(id);
                    System.out.println("Digite o id do sabor da pizza: ");
                    imprime_sabores();
                    input = scanner.nextLine();
                    Sabor sabor = busca_sabor(input);
                    pizza.adicionarSabor(sabor);
                    restaurante.addPedido(pizza);
                }
                else if (input.equalsIgnoreCase("2")){
                    pizza = new PizzaMedia(id);
                    pizza = monta_pizza(scanner, pizza);
                    restaurante.addPedido(pizza);
                }
                else if (input.equalsIgnoreCase("3")){
                    pizza = new PizzaGrande(id);
                    pizza = monta_pizza(scanner, pizza);
                    restaurante.addPedido(pizza);
                }
                else if (input.equalsIgnoreCase("4")){
                    pizza = new PizzaFamilia(id);
                    pizza = monta_pizza(scanner, pizza);
                    restaurante.addPedido(pizza);
                }*/
            }
            
            //ver pedidos de uma mesa
            else if (input.equalsIgnoreCase("5")) {
                System.out.println("Digite o id da mesa a ser visualizada: ");
                int idMesa = Integer.parseInt(scanner.nextLine());
                System.out.println(restaurante.getMesas()[idMesa]);
                

            }
        }
        scanner.close();
    }
}
