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

    class BotaoConfirmarId implements ActionListener{
        private JTextField campo;
        private Restaurante restaurante;
        private String id;
        private JPanel painel;

        BotaoConfirmarId(JTextField campo, Restaurante restaurante, JPanel painel){
            this.campo = campo;
            this.restaurante = restaurante;
            this.painel = painel;
        }

        public void actionPerformed(ActionEvent e) {
            id = campo.getText(); // OBS.: AQUI NAO TO CUIDANDO DE EXCECOES!!!
            int idi = Integer.parseInt(id); // transformando id em int
            restaurante.getMesas()[idi-1].ocuparMesa(); 
            restaurante.adicionaCliente();
            painel.add(new TextArea("Mesa " + id + " foi ocupada.")); // aqui nao ta printando n sei pq
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
        JPanel painel_inicial = criarPainelInicial(restaurante);
        JPanel menu1 = criarMenu1(restaurante);
        JPanel menu2 = criarMenu2();
        JPanel menu4 = criarMenu4();
        JPanel menu6 = criarMenu6(restaurante);
        JPanel menu7 = criarMenu7(restaurante);
        JPanel menu8 = criarMenu8(restaurante);
        /*O numeros comentados servem para poder acessar os paineis adicionados no mainPanel.
         * como em "botaoMenu6.addActionListener", ent se adicionar novos componentes no mainPanel tem q atualizar 
         * os lugares onde usa esses indices.*/
        mainPanel.add(painel_inicial, "Menu Inicial"); //0
        mainPanel.add(menu1, "Menu 1"); //1
        mainPanel.add(menu2, "Menu 2"); //2
        mainPanel.add(menu4, "Menu 4"); //3
        mainPanel.add(menu6, "Menu 6"); //4
        mainPanel.add(menu7, "Menu 7");	
        mainPanel.add(menu8, "Menu 8");

        // Adicionando o painel principal ao JFrame
        add(mainPanel);

        // Tornando o JFrame visível
        setVisible(true);
    }

    // Método para criar o painel inicial
    private JPanel criarPainelInicial(Restaurante restaurante) {
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

        botaoMenu6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JPanel menu6 = (JPanel) mainPanel.getComponent(4);

            	atualizaMenu6(menu6, restaurante);
                cardLayout.show(mainPanel, "Menu 6");
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
    private JPanel criarMenu1(Restaurante restaurante) {
        JPanel painel = new JPanel();
        JLabel label = new JLabel("Registrando o cliente: ");
        JLabel label2 = new JLabel("Digite o id da mesa.");
        JTextField campo = new JTextField(10);
        JButton botaoConfirmar = new JButton("Confirme o id");
        BotaoConfirmarId confirmar = new BotaoConfirmarId(campo, restaurante, painel);

        botaoConfirmar.addActionListener(confirmar);


        JButton botaoVoltar = new JButton("Voltar ao Menu Inicial");
        botaoVoltar.addActionListener(new BotaoVoltar());

        painel.add(label);
        painel.add(label2);
        painel.add(campo);
        painel.add(botaoConfirmar);
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
    
    // Método para criar o Menu 6
    private JPanel criarMenu6(Restaurante restaurante) {
    	JPanel painel = new JPanel();  	
    	JButton botaoVoltar = new JButton("Voltar ao Menu Inicial");

    	botaoVoltar.addActionListener(new BotaoVoltar());
    	painel.add(botaoVoltar);
    	return painel;
    }
    
    private static void atualizaMenu6(JPanel menu6, Restaurante restaurante) {
    	for (Component component : menu6.getComponents()) {
    	    if (component instanceof JLabel | component instanceof JTextArea) {
    	        menu6.remove(component);
    	    }
    	}
    	
    	JLabel label = new JLabel("Mostrando as mesas vazias: ");
    	menu6.add(label);
    	
    	for (int i = 0; i < restaurante.getMesas().length; i++) {
    		if (!restaurante.getMesas()[i].estaOcupada()) {
    			//System.out.println("Mesa " + (i + 1) + ";");
    			menu6.add(new JTextArea("Mesa " + (i + 1) + ";"));
    		}
    	}
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
