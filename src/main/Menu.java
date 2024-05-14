package main;

import java.util.Scanner;

public class Menu { // fazer resistente a burrices
    public static void Entrada(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Deseja registrar uma pizza ou uma bebida? (ou 'sair' para encerrar)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("sair")){
                System.out.println("Fim do expediente.");
                // a gente imprime as coisas por aqui ou na main????
                break;
            }
            else if (input.equalsIgnoreCase("bebida")){
                /*System.out.println("Digite o id da mesa: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.println("Digite o nome da bebida: ");
                String nome = scanner.nextLine();
                System.out.println("Digite o preco da bebida: ");
                int preco = Integer.parseInt(scanner.nextLine()); 
                Bebida bebida = new Bebida(id, nome, preco);*/
                
                // registrar na mesa do mesmo id e no array de pedidos do restaurante
            }
            else if (input.equalsIgnoreCase("pizza")){
                // listar todos os sabores e o usuario escolhe com numeros
            }
        }
        scanner.close();
    }
}
