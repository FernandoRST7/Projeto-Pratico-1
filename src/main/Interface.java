package main;

import javax.swing.*;

import pizzas.Pizza;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    
    class BotaoVoltar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(mainPanel, "Menu Inicial");
        }
    }

    class BotaoConfirmarId implements ActionListener {
        private JTextField campo;
        private Restaurante restaurante;
        private String id;

        BotaoConfirmarId(JTextField campo, Restaurante restaurante, JPanel painel){
            this.campo = campo;
            this.restaurante = restaurante;
        }

        public void actionPerformed(ActionEvent e) {
        	int idi = 0;
            	try {
            		
    	        	id = campo.getText(); 
		            idi = Integer.parseInt(id); // transformando id em int
		            
		            if (restaurante.getMesas()[idi - 1].estaOcupada()) throw new SystemError("Mesa Ocupada");
		            
		            restaurante.getMesas()[idi - 1].ocuparMesa(); 
		            restaurante.adicionaCliente();
		            frameMensagem("Mesa " + idi + " foi ocupada.", "Confirmação");
			        
			        restaurante.salvaLog("Mesa" + idi + " ocupada.");
    	        	
    	        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
    	        	frameMensagem("Digite um número de 1 a 50.", "Erro");
    	        } catch (SystemError ex) {
    	        	frameMensagem("Mesa já ocupada.", "Erro");
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

            int idMesa = Integer.parseInt(campoIdMesa.getText()); // fazer try catch aqui tb
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
    
    class BotaoCriarBebida extends JButton implements ActionListener {

		private static final long serialVersionUID = 1L;
		private JTextField campoIdMesa;
        private Bebida bebida;
        private Restaurante restaurante;
        
        BotaoCriarBebida(JTextField campoIdMesa, JPanel painel, Restaurante restaurante) {
            this.campoIdMesa = campoIdMesa;
            this.restaurante = restaurante;
        }

    	
		@Override
		public void actionPerformed(ActionEvent e) {
			int idMesa = 0;
			try {
				idMesa = Integer.parseInt(campoIdMesa.getText());
	            bebida = new Bebida((idMesa - 1), this.getText());
	            
	            if (!restaurante.addPedido(bebida)) throw new SystemError("Mesa desocupada.");
	            
	
	            else {
                    restaurante.adicionaDinheiro(bebida.getPreco());
	                frameMensagem(bebida.getNome() + " foi pedido(a) para a mesa " + idMesa, "Confirmação");
	            }
	            restaurante.salvaLog("Mesa" + idMesa + " pediu uma bebida: " + bebida.getNome() + ", R$: " + bebida.getPreco());
	            
		        cardLayout.show(mainPanel, "Menu Inicial");
			} catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
	        	frameMensagem("Digite um número de 1 a 50.", "Erro");
	        } catch (SystemError ex) {
	        	frameMensagem(ex.getMessage() + " Digite o id de uma mesa ocupada.", "Erro");
	        }
	            
		}
    	
    }
		
    class BotaoVerPedidos implements ActionListener {

		private JTextField campoIdMesa;
        private Restaurante restaurante;

    	BotaoVerPedidos(JTextField campoIdMesa, JPanel painel, Restaurante restaurante) {
            this.campoIdMesa = campoIdMesa;
            this.restaurante = restaurante;
    	}

		@Override
		public void actionPerformed(ActionEvent e) {
            try{
                int idMesa = Integer.parseInt(campoIdMesa.getText());
    
                if (!restaurante.getMesas()[idMesa - 1].estaOcupada()) throw new SystemError("Mesa desocupada.");
                else {
                    frameMensagem(restaurante.getMesas()[idMesa - 1].toString(), "Pedidos mesa " + idMesa);
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
	        	frameMensagem("Digite um número de 1 a 50.", "Erro");
	        } catch(SystemError ex){
                frameMensagem(ex.getMessage() + " Digite o id de uma mesa ocupada.", "Erro");
            }
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
        JPanel menu5 = criarMenu5(restaurante);
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
        mainPanel.add(menu5, "Menu 5"); //5
        mainPanel.add(menu6, "Menu 6"); //6
        mainPanel.add(menu7, "Menu 7");	//7
        mainPanel.add(menu8, "Menu 8"); //8

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
        
        botaoMenu5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Menu 5");
            }
        });

        botaoMenu6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JPanel menu6 = (JPanel) mainPanel.getComponent(6);
            	atualizaMenu6(menu6, restaurante);
                cardLayout.show(mainPanel, "Menu 6");
            }
        });
        
        botaoMenu7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel menu7 = (JPanel) mainPanel.getComponent(7);
                atualizaMenu7(menu7, restaurante);
                cardLayout.show(mainPanel, "Menu 7");
            }
        });

        botaoMenu8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JPanel menu8 = (JPanel) mainPanel.getComponent(8);
            	atualizaMenu8(menu8, restaurante);
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
    
    private static void frameMensagem(String mensagem, String titulo) {
    	/*Abre um JFrame com a mensagem passada no argumento e um botão que quando clicado fecha o JFrame.*/
    	JFrame errorFrame = new JFrame(titulo);
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorFrame.setMinimumSize(new Dimension(400, 150));
        errorFrame.setSize(400, 150);
        errorFrame.setLocationRelativeTo(null);
        
        JLabel errorMessageLabel = new JLabel(mensagem);
        errorMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorFrame.dispose(); // Fecha o JFrame de erro
            }
        });

        JPanel errorPanel = new JPanel();
        errorPanel.add(errorMessageLabel);
        errorPanel.add(closeButton);

        errorFrame.add(errorPanel);
        errorFrame.pack();
        errorFrame.setVisible(true);
    }

    // menu registra cliente
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

    // menu pizzas
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
    
    // menu bebida
    private JPanel criarMenu3(Restaurante restaurante) {
        JPanel painel = new JPanel();


        // Campo para confirmar o id do pedido
        JLabel labelMesa = new JLabel("Digite o ID da mesa: ");
        JTextField campoIdMesa = new JTextField(10);
        
        BotaoCriarBebida btnAguaListener = new BotaoCriarBebida(campoIdMesa, painel, restaurante);
        btnAguaListener.setText("Água");
        JButton btnAgua = new JButton("Água");
        btnAgua.addActionListener(btnAguaListener);
        
        BotaoCriarBebida btnRefriListener = new BotaoCriarBebida(campoIdMesa, painel, restaurante);
        btnRefriListener.setText("Refrigerante");
        JButton btnRefri = new JButton("Refrigerante");
        btnRefri.addActionListener(btnRefriListener);
        
        BotaoCriarBebida btnVinhoListener = new BotaoCriarBebida(campoIdMesa, painel, restaurante);
        btnVinhoListener.setText("Vinho");
        JButton btnVinho = new JButton("Vinho");
        btnVinho.addActionListener(btnVinhoListener);
        
        BotaoCriarBebida btnSucoListener = new BotaoCriarBebida(campoIdMesa, painel, restaurante);
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

    // menu sabores de pizza
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
    
    // menu ver pedidos de uma mesa
    private JPanel criarMenu5(Restaurante restaurante) {
    	JPanel painel = new JPanel();

        // Campo para confirmar o id da mesa
        JLabel labelMesa = new JLabel("Digite o ID da mesa: ");
        JTextField campoIdMesa = new JTextField(10);

        painel.add(labelMesa);
        painel.add(campoIdMesa);

        BotaoVerPedidos pedidosListener = new BotaoVerPedidos(campoIdMesa, painel, restaurante);
        JButton botaoPedidos = new JButton("Ver pedidos");

        botaoPedidos.addActionListener(pedidosListener);
        painel.add(botaoPedidos);


        // Campo para voltar para o menu inicial
        JButton botaoVoltar = new JButton("Voltar ao Menu Inicial");
        botaoVoltar.addActionListener(new BotaoVoltar());
        painel.add(botaoVoltar);

    	return painel;
    }
    
    // menu mesas vazias
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

    // menu pedidos em ordem
    private JPanel criarMenu7(Restaurante restaurante) {
        JPanel painel = new JPanel();
        JLabel label = new JLabel("Mostrando os pedidos em ordem de prioridade: ");
        JButton botaoVoltar = new JButton("Voltar ao Menu Inicial");

        botaoVoltar.addActionListener(new BotaoVoltar());

        painel.add(label);
        painel.add(botaoVoltar);
        return painel;
    }

    private static void atualizaMenu7(JPanel menu7, Restaurante restaurante){
        TextArea pedidos = new TextArea(restaurante.printaPedidos());
        pedidos.setMinimumSize(new Dimension(150, 40)); // Set minimum size
        pedidos.setEditable(false); // Desabilita a edição manual do texto
        try{
            menu7.remove(2); // remove a caixa de texto de pedidos anterior (não faz nada se não tiver caixa de texto)
        } catch(Exception e){}
        menu7.add(pedidos);
    }

    // menu fim do expediente
    private JPanel criarMenu8(Restaurante restaurante) {
        JPanel painel = new JPanel();
        JLabel label = new JLabel("Fim do expediente.");
        painel.add(label);
        painel.add(new JTextArea("Imprimindo os dados da noite..."));
        
        JButton fechar = new JButton("Fechar");
        fechar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//fechar tudo
        		System.exit(0);
        	}
        });
        painel.add(fechar);
        return painel;
    }
    
    private static void atualizaMenu8(JPanel menu8, Restaurante restaurante) {
    	
        TextArea caixaTexto = new TextArea("Registro:");
        caixaTexto.setMinimumSize(new Dimension(150, 40));
        caixaTexto.setEditable(false); // Desabilita a edição manual do texto
        
        TextArea dados = new TextArea(restaurante.imprimeDados());
        dados.setMinimumSize(new Dimension(150, 40));
        dados.setEditable(false); // Desabilita a edição manual do texto
        menu8.add(dados);

        // Definindo outras propriedades opcionais (tamanho, fonte, etc.)
        caixaTexto.setPreferredSize(new Dimension(300, 200)); // Tamanho inicial
        menu8.add(caixaTexto);
        
    	try {
            // Criando o leitor de arquivos
            BufferedReader leitor = new BufferedReader(new FileReader(restaurante.getNomeRegistro()));

            String linha;
            // Lendo o arquivo linha por linha
            while ((linha = leitor.readLine()) != null) {
                // Adicionando a linha à caixa de texto com quebra de linha
                caixaTexto.append(linha + "\n");
            }

            // Fechando o leitor de arquivos
            leitor.close();
        } catch (IOException e) {
            // Tratamento de erros de leitura do arquivo
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        new Interface(restaurante);
    }
}
