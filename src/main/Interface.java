package main;

import javax.swing.*;

import pizzas.Pizza;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Interface extends JFrame {

	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
    private JPanel mainPanel;

    public class SystemError extends Exception {

		private static final long serialVersionUID = 1L;

		public SystemError(String message) {
    	    super(message);
    	  }
    	}
    
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
        	int idi = 0;
            	try {
            		
    	        	id = campo.getText(); // OBS.: AQUI NAO TO CUIDANDO DE EXCECOES!!!
		            idi = Integer.parseInt(id); // transformando id em int
		            
		            if (restaurante.getMesas()[idi - 1].estaOcupada()) throw new SystemError("Mesa Ocupada");
		            
		            restaurante.getMesas()[idi-1].ocuparMesa(); 
		            restaurante.adicionaCliente();
		            TextArea txt = new TextArea("Mesa " + id + " foi ocupada.");
		            txt.setMinimumSize(new Dimension(150, 40)); // Set minimum size
		            txt.setPreferredSize(new Dimension(150, 40)); // Set preferred size
		            txt.setSize(new Dimension(150, 40));
			        painel.add(txt); // aqui nao ta printando n sei pq
			        cardLayout.show(mainPanel, "Menu Inicial");
    	        	
    	        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
    	        	mensagemDeErro("Digite um número de 1 a 50.");
    	        } catch (SystemError ex) {
    	        	mensagemDeErro("Mesa já ocupada.");
    	        }
        }
    }
    
    class BotaoCriarPizza implements ActionListener {

        private JComboBox<String> tamanhoBox;
        private JCheckBox[] saborCheckBoxes;
        private JTextField campoIdMesa;
        private Pizza pizza;

        BotaoCriarPizza(JComboBox<String> tamanhoBox, JCheckBox[] saborCheckBoxes, JTextField campoIdMesa) {
            this.tamanhoBox = tamanhoBox;
            this.saborCheckBoxes = saborCheckBoxes;
            this.campoIdMesa = campoIdMesa;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            int idMesa = Integer.parseInt(campoIdMesa.getText());
            int tamanho = tamanhoBox.getSelectedIndex();
            List<String> sabores = Arrays.asList(getSelectedSabores());
            JOptionPane.showMessageDialog(null, "id " + idMesa);
            JOptionPane.showMessageDialog(null, "tamanho " + tamanho);
            //pizza = new Pizza(tamanho, sabores);
            //JOptionPane.showMessageDialog(null, "Pizza criada: " + pizza);
        }

        private String[] getSelectedSabores() {
            return Arrays.stream(saborCheckBoxes)
                    .filter(JCheckBox::isSelected)
                    .map(JCheckBox::getText)
                    .toArray(String[]::new);
        }

        public Pizza getPizza() {
            return pizza;
        }
    }
    
    class BotaoCriarBebida extends JButton implements ActionListener{

		private static final long serialVersionUID = 1L;
		private JTextField campoIdMesa;
        private Bebida bebida;
        private JPanel painel;
        
        BotaoCriarBebida(JTextField campoIdMesa, JPanel painel) {
            this.campoIdMesa = campoIdMesa;
            this.painel = painel;
        }

    	
		@Override
		public void actionPerformed(ActionEvent e) {
            int idMesa = Integer.parseInt(campoIdMesa.getText());
            bebida = new Bebida(idMesa, this.getText());
            
            TextArea txt = new TextArea("Bebida " + bebida + " foi pedida.");
            txt.setMinimumSize(new Dimension(150, 100)); // Set minimum size
            txt.setPreferredSize(new Dimension(150, 100)); // Set preferred size
            txt.setSize(new Dimension(150, 100));
	        painel.add(txt); // aqui nao ta printando n sei pq
            
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
        JPanel menu2 = criarMenu2(restaurante);
        JPanel menu3 = criarMenu3(restaurante);
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
        mainPanel.add(menu3, "Menu 3"); //3
        mainPanel.add(menu4, "Menu 4"); //4
        mainPanel.add(menu6, "Menu 6"); //5
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
        
        botaoMenu3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Menu 3");
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
    
    private static void mensagemDeErro(String mensagem) {
    	/*Abre um JFrame com a mensagem passada no argumento e um botão que quando clicado fecha o JFrame.*/
    	JFrame errorFrame = new JFrame("Erro");
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close on button click or window close
        errorFrame.setMinimumSize(new Dimension(300, 100));
        errorFrame.setSize(300, 100);
        //errorFrame.pack();
        errorFrame.setLocationRelativeTo(null);
        
        JLabel errorMessageLabel = new JLabel(mensagem);
        errorMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorFrame.dispose(); // Fecha o JFrame de  erro
            }
        });

        JPanel errorPanel = new JPanel();
        errorPanel.add(errorMessageLabel);
        errorPanel.add(closeButton);

        errorFrame.add(errorPanel);
        errorFrame.pack();
        errorFrame.setVisible(true);
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

    private JPanel criarMenu2(Restaurante restaurante) {
        JPanel painel = new JPanel();

        String[] tamanhos = {"Brotinho", "Média", "Grande", "Familia"};
        String[] sabores = {"Calabresa", "Mussarela", "Portuguesa", "Alcaparras", "Quatro queijos"};

        JComboBox<String> tamanhoBox = new JComboBox<>(tamanhos);
        JCheckBox[] saborCheckBoxes = new JCheckBox[sabores.length];
        for (int i = 0; i < sabores.length; i++) {
            saborCheckBoxes[i] = new JCheckBox(sabores[i]);
        }
        JButton botaoCriarPizza = new JButton("Criar Pizza");

        // Campo para confirmar o id do pedido
        JLabel labelMesa = new JLabel("Digite o ID da mesa: ");
        JTextField campoIdMesa = new JTextField(10);

        painel.add(labelMesa);
        painel.add(campoIdMesa);

        BotaoCriarPizza botaoCriarPizzaListener = new BotaoCriarPizza(tamanhoBox, saborCheckBoxes, campoIdMesa);
        botaoCriarPizza.addActionListener(botaoCriarPizzaListener);

        painel.add(new JLabel("Selecione o tamanho:"));
        painel.add(tamanhoBox);
        painel.add(new JLabel("Selecione os sabores:"));
        for (JCheckBox checkBox : saborCheckBoxes) {
            painel.add(checkBox);
        }
        painel.add(botaoCriarPizza);

        // Campo para voltar para o menu inicial
        JButton botaoVoltar = new JButton("Voltar ao Menu Inicial");
        botaoVoltar.addActionListener(new BotaoVoltar());
        painel.add(botaoVoltar);

        // Exemplo de uso do método getPizza
        botaoCriarPizza.addActionListener(e -> {
            Pizza pizza = botaoCriarPizzaListener.getPizza();
            if (pizza != null) {
                System.out.println("Pizza criada: " + pizza);
            }
        });

        return painel;
    }
    
    private JPanel criarMenu3(Restaurante restaurante) {
        JPanel painel = new JPanel();


        // Campo para confirmar o id do pedido
        JLabel labelMesa = new JLabel("Digite o ID da mesa: ");
        JTextField campoIdMesa = new JTextField(10);
        
        BotaoCriarBebida btnAguaListener = new BotaoCriarBebida(campoIdMesa, painel);
        btnAguaListener.setText("Água");
        JButton btnAgua = new JButton("Água");
        btnAgua.addActionListener(btnAguaListener);
        
        BotaoCriarBebida btnRefriListener = new BotaoCriarBebida(campoIdMesa, painel);
        btnRefriListener.setText("Refrigerante");
        JButton btnRefri = new JButton("Refrigerante");
        btnRefri.addActionListener(btnRefriListener);
        
        BotaoCriarBebida btnVinhoListener = new BotaoCriarBebida(campoIdMesa, painel);
        btnVinhoListener.setText("Vinho");
        JButton btnVinho = new JButton("Vinho");
        btnVinho.addActionListener(btnVinhoListener);
        
        BotaoCriarBebida btnSucoListener = new BotaoCriarBebida(campoIdMesa, painel);
        btnSucoListener.setText("Suco");
        JButton btnSuco = new JButton("Suco");
        btnSuco.addActionListener(btnSucoListener);
        
        painel.add(labelMesa);
        painel.add(campoIdMesa);
        painel.add(btnAgua);
        painel.add(btnRefri);
        painel.add(btnVinho);
        painel.add(btnSuco);

        // Campo para voltar para o menu inicial
        JButton botaoVoltar = new JButton("Voltar ao Menu Inicial");
        botaoVoltar.addActionListener(new BotaoVoltar());
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
