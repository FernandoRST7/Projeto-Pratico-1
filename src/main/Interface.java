package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    class BotaoVoltar implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(mainPanel, "Menu Inicial");
        }
    }

    public Interface(Restaurante restaurante) {
        // Configurações do JFrame
        setTitle("Pizzaria");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializando o CardLayout e o painel principal
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Adicionando os painéis ao CardLayout
        JPanel painel_inicial = criarPainelInicial();
        JPanel menu1 = criarMenu1();
        JPanel menu2 = criarMenu2();
        JPanel menu4 = criarMenu4();
        JPanel menu7 = criarMenu7(restaurante);
        JPanel menu8 = criarMenu8(restaurante);
        mainPanel.add(painel_inicial, "Menu Inicial");
        mainPanel.add(menu1, "Menu 1");
        mainPanel.add(menu2, "Menu 2");
        mainPanel.add(menu4, "Menu 4");
        mainPanel.add(menu7, "Menu 7");
        mainPanel.add(menu8, "Menu 8");

        // Adicionando o painel principal ao JFrame
        add(mainPanel);

        // Tornando o JFrame visível
        setVisible(true);
    }

    // Método para criar o painel inicial
    private JPanel criarPainelInicial() {
        JPanel painel = new JPanel();
        JButton botaoMenu1 = new JButton("Registrar novo cliente");
        JButton botaoMenu2 = new JButton("Pedir Pizza");
        JButton botaoMenu3 = new JButton("Pedir Bebida");
        JButton botaoMenu4 = new JButton("Ver sabores de pizza");
        JButton botaoMenu5 = new JButton("Ver pedidos de uma mesa");
        JButton botaoMenu6 = new JButton("Ver mesas livres");
        JButton botaoMenu7 = new JButton("Ver pedidos sendo preparados");
        JButton botaoMenu8 = new JButton("Encerrar expediente");

        botaoMenu1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Menu 1");
            }
        });

        botaoMenu2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Menu 2");
            }
        });

        botaoMenu4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Menu 4");
            }
        });

        botaoMenu7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Menu 7");
            }
        });

        botaoMenu8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Menu 8"); 
            }
        });

        painel.add(botaoMenu1);
        painel.add(botaoMenu2);
        painel.add(botaoMenu3);
        painel.add(botaoMenu4);
        painel.add(botaoMenu5);
        painel.add(botaoMenu6);
        painel.add(botaoMenu7);
        painel.add(botaoMenu8);
        return painel;
    }

    // Método para criar o Menu 1
    private JPanel criarMenu1() {
        JPanel painel = new JPanel();
        JLabel label = new JLabel("Este é o Menu 1");
        JButton botaoVoltar = new JButton("Voltar ao Menu Inicial");

        botaoVoltar.addActionListener(new BotaoVoltar());

        painel.add(label);
        painel.add(botaoVoltar);
        return painel;
    }

    // Método para criar o Menu 2
    private JPanel criarMenu2() {
        JPanel painel = new JPanel();
        JLabel label = new JLabel("Este é o Menu 2");
        JButton botaoVoltar = new JButton("Voltar ao Menu Inicial");

        botaoVoltar.addActionListener(new BotaoVoltar());

        painel.add(label);
        painel.add(botaoVoltar);
        return painel;
    }

    // Método para criar o Menu 4
    private JPanel criarMenu4() {
        JPanel painel = new JPanel();
        JLabel label = new JLabel("Mostrando sabores das pizzas: ");
        JButton botaoVoltar = new JButton("Voltar ao Menu Inicial");

        botaoVoltar.addActionListener(new BotaoVoltar());

        painel.add(label);
        painel.add(new JTextArea(Menu.imprime_sabores()));
        painel.add(botaoVoltar);
        return painel;
    }

    // Método para criar o Menu 7
    private JPanel criarMenu7(Restaurante restaurante) {
        JPanel painel = new JPanel();
        JLabel label = new JLabel("Mostrando os pedidos em ordem de prioridade: ");
        JButton botaoVoltar = new JButton("Voltar ao Menu Inicial");

        botaoVoltar.addActionListener(new BotaoVoltar());

        painel.add(label);
        painel.add(new JTextArea(restaurante.printaPedidos()));
        painel.add(botaoVoltar);
        return painel;
    }

    // Método para criar o Menu 8
    private JPanel criarMenu8(Restaurante restaurante) {
        JPanel painel = new JPanel();
        JLabel label = new JLabel("Fim do expediente.");
        painel.add(label);
        painel.add(new JTextArea("Imprimindo os dados da noite..."));
        painel.add(new JTextArea(restaurante.imprimeDados()));
        return painel;
    }

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        new Interface(restaurante);
    }
}