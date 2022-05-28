package br.com.johnmarcos;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner entradaUsuario = new Scanner(System.in);
		String opcaoDesejada = "-1";
		PessoaFisica pessoaLogada = null;
		ContaCorrente contaCorrente = null;

		while (opcaoDesejada != "0") {

			try {

				System.out.println("\nSISTEMA BANCÁRIO\n"
						+ "1 - Cadastrar Pessoa\n"
						+ "2 - Cadastrar Conta Corrente\n"
						+ "3 - Realizar Depósito\n"
						+ "4 - Realizar Saque\n"
						+ "5 - Consultar Transações\n"
						+ "0 - Sair do Sistema\n"
						+ "Digite a opção desejada:");

				opcaoDesejada = entradaUsuario.nextLine();

				if ( validaEntradaUsuarioInt(opcaoDesejada) == true ) {
					
					switch (Integer.decode(opcaoDesejada)) {

						case 1: // Cadastrar Pessoa
	
							String nome, cpf;
							pessoaLogada = new PessoaFisica();
	
							System.out.println("Nome da Pessoa: ");
							nome = entradaUsuario.nextLine();
							
							pessoaLogada.validacaoNome(nome);
	
							System.out.println("CPF da Pessoa: ");
							cpf = entradaUsuario.nextLine();
								
							pessoaLogada.validacaoCPF(cpf);
	
							pessoaLogada = new PessoaFisica(nome, cpf);
							System.out.println("Pessoa cadastrada com sucesso.\n");
							break;
						
						case 2:
							
							if(pessoaLogada != null) {
								contaCorrente = new ContaCorrente(pessoaLogada);
								System.out.println("Conta corrente criada com sucesso para o usuário cadastrado.");
								break;
							
							} else 
								throw new NullPointerException("Parece que você ainda não completou o cadastro de Pessoa.");
	
						case 3:
							
							if(pessoaLogada != null && contaCorrente != null) {
								
								String valorADepositar;
								
								System.out.println("Digite o valor a ser depositado: ");
								valorADepositar = entradaUsuario.nextLine();
									
								contaCorrente.depositoContaCorrente(Double.valueOf(valorADepositar));
								break;
							
							} else
								throw new NullPointerException("Parece que você ainda não completou o cadastro de Pessoa ou/e de Conta Corrente.");
	
						case 4:
							
							if(pessoaLogada != null && contaCorrente != null) {
								
								String valorASacar;
								
								System.out.println("Digite o valor a ser sacado: ");
								valorASacar = entradaUsuario.nextLine();
								
								contaCorrente.saqueContaCorrente(Double.valueOf(valorASacar));
								break;
							
							} else
								throw new NullPointerException("Parece que você ainda não completou o cadastro de Pessoa ou/e de Conta Corrente.");				
	
						case 5:
							
								if(pessoaLogada != null && contaCorrente != null) {
									contaCorrente.consultarTransacoes();
									break;
							
								} else
									throw new NullPointerException("Parece que você ainda não completou o cadastro de Pessoa ou/e de Conta Corrente.");							
	
						case 0:
							opcaoDesejada = "0";
							break;
						}
				} else {
					System.out.println("\nOpção inválida. Digite novamente:\n");
					entradaUsuario.reset();
				}

			} catch (NullPointerException excecao) {
				System.out.println(excecao.getLocalizedMessage() + "\nNão foi possível concluir a transação. Tente novamente.\n");
			
			} catch (NumberFormatException excecao) {
				System.out.println("Você não digitou um valor válido.\nNão foi possível concluir a transação. Tente novamente.\n");
			
			} catch (Exception excecao) {
				System.out.println(excecao.getLocalizedMessage() + "\nNão foi possível concluir o cadastro. Tente novamente.\n");
			}

		}
		entradaUsuario.close();
		System.out.println("Sistema encerrado.");
		

	}
	
	public static boolean validaEntradaUsuarioInt(String entradaUsuario) {
		
		if( entradaUsuario.isEmpty() ) {
			return false;
		
		} else if ( entradaUsuario.length() > 1 ) {
			return false;
		
		} else if ( entradaUsuario.length() == 1 ) {
			
			if ( entradaUsuario.matches("[+-]?\\d*(\\.\\d+)?") == true ) { //verifica se string tem inteiros
				return true;
			} else return false;
		
		} else return false;
	}

}
