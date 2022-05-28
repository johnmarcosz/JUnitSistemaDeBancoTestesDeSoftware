 package br.com.johnmarcos;

public class PessoaFisica {
	
	private int idPessoa;
	private static int contadorIdPessoa = 0;
	private String nome;
	private String cpf;
	private ContaCorrente contaCorrente = null;
	
	
// Contrutor
	
	public PessoaFisica(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.idPessoa = contadorIdPessoa++;
	}
	
	public PessoaFisica() {
	}


// M�todo para imprimir valores (GET)
	
	public String getNome(){
		return this.nome;
	}
 
	public String getCPF(){
		return this.cpf;
	}
	
	public int getIdPessoa(){
		return this.idPessoa;
	}
	
	public ContaCorrente getContaCorrenteRelacionada() {
		return this.contaCorrente;
	}
	
// Valida nome da pessoa
	public boolean validacaoNome(String nomeDigitado) throws Exception {
		
		if( nomeDigitado.isEmpty() ) {
			throw new Exception("Erro! O nome n�o pode ser vazio.");
		
		} else if ( nomeDigitado.length() < 3 ) {
			throw new Exception("Erro! O nome precisa ter 3 letras ou mais.");
		
		} else if ( nomeDigitado.length() > 1 ) {
			
			if ( nomeDigitado.matches("^[[ ]|\\p{L}*]+$") == true ) { //verifica se string � s� letras
				return true;
			} else 
				throw new Exception("Erro! O nome parece conter carcteres especiais.");
		
		} else return false;
	}
	
	
// Verifica se CPF � valido
	
	public boolean validacaoCPF(String cpf) throws Exception {
		
		cpf = removeCaracteresEspeciais(cpf);
		
		if(verificaQuantDigitosCPF(cpf) == false) {
			throw new Exception("Erro! O CPF deve ter 11 d�gitos e n�o conter nenhuma letra.");
			
		} else if(verificaNumerosIguaisCPF(cpf) == false) {
			throw new Exception("Erro! O CPF n�o pode conter todos os d�gitos iguais.");
			
		} else if(validarCalculoCPF(cpf) == false) {
			throw new Exception("Erro na valida��o do CPF.");
			
		} else {
			return true;
		}
	}
	
	
// Remove caracteres especiais
	
	public String removeCaracteresEspeciais(String stringRecebida) {
		stringRecebida = stringRecebida.replaceAll("[^0-9]", "");
		return stringRecebida;
	}
	

// Verifica se string � menor que 11 d�gitos
	
	public boolean verificaQuantDigitosCPF(String cpf) {
		
		if(cpf.length() != 11) {
			return false;
		} else {
			return true;
		}
	}
	
	
// Verifica se todos os n�meros s�o iguais
	public boolean verificaNumerosIguaisCPF(String cpf) {
		
		if(	   cpf == "00000000000"	|| cpf == "11111111111" || cpf == "22222222222" 
			|| cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555"
			|| cpf == "66666666666"	|| cpf == "77777777777" || cpf == "88888888888"
			|| cpf == "99999999999") {
			
			return false;
		} else {
			return true;
		}
	}

	
// M�todo para ver se o CPF � v�lido
	
	public boolean validarCalculoCPF(String cpf){

		int moduloDV;
		int somaDos9;
		int primeiroDV;
		int[] vetorCPF = new int[11];
		
		
		// colocando o CPF em vetor para os c�lculos necess�rios
		
		for (int i = 0; i < vetorCPF.length; i++) {
			vetorCPF[i] = Integer.parseInt( cpf.substring(i, i+1) );		
		}
		
		// C�lculo do primeiro D�gito Verificador
		
		somaDos9 = (vetorCPF[0] * 10) + (vetorCPF[1] * 9) + (vetorCPF[2] * 8) + (vetorCPF[3] * 7) + (vetorCPF[4] * 6) + (vetorCPF[5] * 5) + (vetorCPF[6] * 4) + (vetorCPF[7] * 3) + (vetorCPF[8] * 2);
		moduloDV = somaDos9 % 11;
		primeiroDV = 11 - moduloDV;
				
		if(primeiroDV == 10 || primeiroDV == 11){
			primeiroDV = 0;
		}
		
		if(vetorCPF[9] != primeiroDV){
			System.out.println("C�lculo do primeiro d�gito verificador � diferente ao do CPF.");
			return false;
		}
		
		// C�lculo do segundo D�gito Verificador
		

		int segundoDV;
		
		somaDos9 = (vetorCPF[0] * 11) + (vetorCPF[1] * 10) + (vetorCPF[2] * 9) + (vetorCPF[3] * 8) + (vetorCPF[4] * 7) + (vetorCPF[5] * 6) + (vetorCPF[6] * 5) + (vetorCPF[7] * 4) + (vetorCPF[8] * 3) + (vetorCPF[9] * 2);
		moduloDV = somaDos9 % 11;
		segundoDV = 11 - moduloDV;
		
		if(segundoDV == 10 || segundoDV == 11){
			segundoDV = 0;
		}
		
		if(vetorCPF[10] != segundoDV){
			System.out.println("C�lculo do segundo D�gito Verificador � diferente ao do CPF.");
			return false;
		}
		
		// Se CPF for v�lido
		return true;
	}
}
