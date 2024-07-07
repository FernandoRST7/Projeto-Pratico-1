# Projeto-Pratico-1
Projeto prático 1 de MC322 1°Semestre de 2024 do grupo composto por Fernando Rodigues, Victor Ogitsu, Gabriel de Macedo C. Montagna, Pietro Magaldi

*Resumo:*
    O projeto do sistema de administração de pizzaria desenvolvido pelo grupo tem por
objetivo colocar em prática as habilidades desenvolvidas na disciplina de Programação
Orientada a Objetos (MC322). O sistema permite que o usuário (garçom ou gerente, por
exemplo), organize os pedidos de cada mesa em uma pizzaria; a ideia é facilitar a visualização
do estado atual da lotação do restaurante e acompanhar as pizzas e as bebidas pedidas pelos
clientes.
    O código tem uma classe Menu, que é responsável por receber as entradas do usuário e
imprimir na tela as informações que ele deseja acessar. Dentro dessa classe, funções como
registra_pizza, registra_bebida e monta_pizza permitem que o garçom passe para o sistema
qual o pedido de uma certa mesa do restaurante. Como todas as mesas e todos os pedidos são
indexados, fica simples organizar a relação entre mesas e pedidos. Existe também, no projeto,
uma classe Restaurante, que contém um atributo pedidos do tipo ArrayList com todos os
pedidos sendo preparados pela cozinha, além de um vetor mesas, com todas as mesas do
restaurante.
    Além disso, há a classe Interface, que transforma o Menu em uma interface gráfica, de modo
que o usuário registre clientes e pedidos no restaurante por meio de botões interativos. Algumas
funcionalidades dessa classe são: atribuir um novo cliente à uma mesa vazia, adicionar um pedido de 
pizza a uma mesa, adicionar um pedido de bebida a uma mesa, visualizar os sabores de pizza disponíveis,
ver os pedidos de uma mesa específica, ver quais mesas estão livres, ver os pedidos sendo preparados em ordem
de qual foi pedido primeiro, e a função de encerrar o expediente, que, ao final, mostra na interface o número
total de clientes atendidos, número total de pedidos realizados e saldo total ganho na noite.
    Com relação aos itens do cardápio, existe uma classe abstrata Pedido, que é classe-mãe
das classes Bebida e Pizza. Pizza, por sua vez, também é uma classe abstrata que é classe-mãe
de quatro tipos de pizza: as classes PizzaBrotinho, PizzaMedia, PizzaGrande e PizzaFamilia. A
ideia dessas subclasses é facilitar a organização do código, além de implementar alguns
atributos e métodos específicos, como o atributo maximoSabores e o método toString. Também
criamos um enum Sabores para estabelecer de forma absoluta quais são os sabores de pizza
disponíveis no cardápio.
    Existem, além das classes e da estrutura já descritas, algumas outras classes no projeto,
como as classes Bebida, Mesa e Funcionário. Elas também têm suas funções no código, mas não
é o intuito do resumo abranger suas descrições específicas.
