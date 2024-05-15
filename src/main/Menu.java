package main;

import java.util.Scanner;

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

    // NÃO TO CONSEGUINDO FAZER ISSO FUNCIONAR
    /*private static void monta_pizza(Pizza pizza, int n_sabores){
        Scanner scanner = new Scanner(System.in);
        String input;
        for (int i=1; i<n_sabores+1; i++){
            System.out.println("Digite o número do " + i + "° sabor da pizza: ");
            imprime_sabores();
            input = scanner.nextLine();
            Sabor sabor = busca_sabor(input);
            pizza.adicionarSabor(sabor);
        }
        scanner.close();
    }*/ 

    public static void Entrada(Restaurante restaurante){
        // lê a entrada do usuário e registra tudo
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Deseja registrar uma pizza ou uma bebida? (ou 'sair' para encerrar)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("sair")){
                System.out.println("Fim do expediente.");
                // imprimir dados do restaurante
                break;
            }
            else if (input.equalsIgnoreCase("bebida")){

            }
            else if (input.equalsIgnoreCase("pizza")){ // separar num método registra_pizza()?
                System.out.println("Digite o id da mesa: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.println("Qual o tamanho da pizza? (brotinho, média, grande ou família) ");
                input = scanner.nextLine();
                int n_sabores;
                if (input.equalsIgnoreCase("brotinho")){
                    PizzaBrotinho pizza = new PizzaBrotinho(id);
                    n_sabores = 1;
                    for (int i=1; i<n_sabores+1; i++){
                        System.out.println("Digite o número do " + i + "° sabor da pizza: ");
                        imprime_sabores();
                        input = scanner.nextLine();
                        Sabor sabor = busca_sabor(input);
                        pizza.adicionarSabor(sabor);
                    }
                    restaurante.addPedido(pizza);
                }
                else if (input.equalsIgnoreCase("média") || input.equalsIgnoreCase("media")){
                    PizzaMedia pizza = new PizzaMedia(id);
                    n_sabores = 2;
                    for (int i=1; i<n_sabores+1; i++){
                        System.out.println("Digite o número do " + i + "° sabor da pizza: ");
                        imprime_sabores();
                        input = scanner.nextLine();
                        Sabor sabor = busca_sabor(input);
                        pizza.adicionarSabor(sabor);
                    }
                    restaurante.addPedido(pizza);
                }
                else if (input.equalsIgnoreCase("grande")){
                    PizzaGrande pizza = new PizzaGrande(id);
                    n_sabores = 3;
                    for (int i=1; i<n_sabores+1; i++){
                        System.out.println("Digite o número do " + i + "° sabor da pizza: ");
                        imprime_sabores();
                        input = scanner.nextLine();
                        Sabor sabor = busca_sabor(input);
                        pizza.adicionarSabor(sabor);
                    }
                    restaurante.addPedido(pizza);
                }
                else if (input.equalsIgnoreCase("familia") || input.equalsIgnoreCase("família")){
                    PizzaFamilia pizza = new PizzaFamilia(id);
                    n_sabores = 4;
                    for (int i=1; i<n_sabores+1; i++){
                        System.out.println("Digite o número do " + i + "° sabor da pizza: ");
                        imprime_sabores();
                        input = scanner.nextLine();
                        Sabor sabor = busca_sabor(input);
                        pizza.adicionarSabor(sabor);
                    }
                }
            }
        }
        scanner.close();
    }
}
