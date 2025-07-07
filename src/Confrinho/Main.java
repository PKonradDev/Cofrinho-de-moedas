/* Esse código é um confre de moedas digital onde o usúario pode guardar seus valores de forma organizada e de facil acesso
 * No momento são aceitos apenas reais, dolares e euros então caso selecione outro tipo de moeda o sistema apresentara erro
 * Mas seguindo o menu inicial não terá grandes problemas para pode usar o sistema. */



/*
 * Aluno: Patryck Konrad de Lima 
 *RU: 4635245
 */


package Confrinho;


import java.util.ArrayList; //importar as listas
import java.util.Scanner; // importar o scanner

abstract class Moeda {
	protected double valor;
    
	public Moeda(double valor) {
				this.valor = valor;
    }
    
	public abstract double converterR$();
    
	public abstract void info();
    
	public double getValor() {
		return valor;
    }
}

class Dolar extends Moeda {
    	public Dolar(double valor) {
    	super(valor);
    }
    
  
    public double converterR$() {
       return valor * 7.0; //cotação ficticia
    }
    
  
    public void info() {
        System.out.println("US$: " + valor);
    }
}

class Euro extends Moeda {
    public Euro(double valor) {
        super(valor);
    }
    
   
    public double converterR$() {
        return valor * 9.5; //cotação cotaçãso ficticia
    }
    
  
    public void info() {
        System.out.println("EUR: " + valor);
    }
}

class Real extends Moeda {
    public Real(double valor) {
        super(valor);
    }
    
    public double converterR$() {
        return valor;
    }
    
    public void info() {
        System.out.println("R$: " + valor);
    }
}

class Cofrinho {
    private ArrayList<Moeda> moedas = new ArrayList<>();
    
    public void adicionarMoeda(Moeda moeda) {
        moedas.add(moeda);
    }
    
    public void removerMoeda(Moeda moeda) {
        moedas.remove(moeda);
    }
    
    public void listarTotaldeMoedas() {
        if (moedas.isEmpty()) {
            System.out.println("Não há moedas no cofrinho.\n ");
        } else {
            for (Moeda moeda : moedas) {
                moeda.info();
            }
        }
    }
    
    public double calcularTotalConvertido() {
        double totalvalor = 0;
        for (Moeda moeda : moedas) {
            totalvalor += moeda.converterR$();
        }
        return totalvalor;
    }
    
    public Moeda buscarMoeda(double valor, int tipo) {
        for (Moeda moeda : moedas) {
            if (moeda.getValor() == valor) {
                if (tipo == 1 && moeda instanceof Real) {
                    return moeda;
                } else if (tipo == 2 && moeda instanceof Dolar) {
                    return moeda;
                } else if (tipo == 3 && moeda instanceof Euro) {
                    return moeda;
                }
            }
        }
        return null; //moeda inexistente ou deletada
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // seu nome (usuário)
        System.out.println("Seja bem-vindo ao iBank \n "); //mensagem de saudação
        System.out.print("Qual o seu nome? \n"); //nome do usuário
       
        String nomeUsuario = scanner.nextLine();
       
        Cofrinho cofrinho = new Cofrinho();
        
        while (true) {
            System.out.println("Por favor escolha uma das opções abaixo!");
            System.out.println("1 - Adicionar moeda");
            System.out.println("2 - Remover moeda");
            System.out.println("3 - Listar moedas");
            System.out.println("4 - Calcular total convertido");
            System.out.println("5 - Sair\n  ");
            System.out.print("Escolha uma opção:\n   "); //laço de repetição para escolha do usuário
            
            int opcao = scanner.nextInt();
            if (opcao == 5) {
                break;
            }
            
            switch (opcao) {
                case 1:
                    System.out.println("Digite o tipo da moeda (1-Real, 2-Dolar, 3-Euro):\n "); //selecvionar moeda dentre as opções
                    int tipo = scanner.nextInt();
                    System.out.print("Digite o valor:\n ");
                    double valor = scanner.nextDouble();
                    
                    if (tipo == 1) {
                        cofrinho.adicionarMoeda(new Real(valor));
                    } else if (tipo == 2) {
                        cofrinho.adicionarMoeda(new Dolar(valor));
                    } else if (tipo == 3) {
                        cofrinho.adicionarMoeda(new Euro(valor));
                    } else {
                        System.out.println("Tipo inválido!"); //usuário selecionou uma opção não válida
                    }
                    break;
                case 2:
                    // retirar moeda do cofrinho
                    System.out.println("Digite o tipo da moeda a remover (1-Real, 2-Dolar, 3-Euro): ");
                    int tipoRemover = scanner.nextInt();
                    System.out.print("Digite o valor da moeda a remover: ");
                    double valorRemover = scanner.nextDouble();
                    
                    Moeda moedaRemover = cofrinho.buscarMoeda(valorRemover, tipoRemover);
                    if (moedaRemover != null) {
                        cofrinho.removerMoeda(moedaRemover);
                        System.out.println("Moeda removida com sucesso!"); //mensagem de sucesso
                    } else {
                        System.out.println("Error, moeda não encontrada no cofrinho."); //moeda inexistente/deletada
                    }
                    break;
                case 3:
                    cofrinho.listarTotaldeMoedas();
                    break;
                case 4:
                    System.out.println("Total em R$: " + cofrinho.calcularTotalConvertido());
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        
        // encerrando o código
        System.out.println("Até logo, " + nomeUsuario + "!"); //mensagem de despedida
        System.out.println("Obrigado por usar o iBank"); //agradacimento
        
        scanner.close();
    }
}
