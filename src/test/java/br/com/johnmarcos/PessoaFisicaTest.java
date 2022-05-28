package br.com.johnmarcos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;

class PessoaFisicaTest {
	
	@BeforeAll
	static void inicioConexaoBDTestes() {
    	System.out.println("@BeforeAll | Iniciando os testes, aguarde... \nIniciando a conexão com o banco de dados... \nTestes iniciados.\n");
    }
	
	@AfterAll
	static void encerraConexaoBDTestes() {
    	System.out.println("@AfterAll | Testes concluídos. \nEncerrando a conexão com o banco de dados, aguarde...  \nTestes encerrados.");
    }

	@BeforeEach
    void testeIniciado(TestInfo testInfo) {
    	System.out.println("@BeforeEach | Teste iniciado: '" + testInfo.getDisplayName() + "'.");
    }
	
	@AfterEach
    public void testeFinalizado(TestInfo testInfo) {
    	System.out.println("@AfterEach | Teste finalizado: '" + testInfo.getDisplayName() + "'.\n");
    }

	@DisplayName("Verifica se o CPF inserido tem 11 dígitos")
	@Test
	public void insercaoCPFsem11Digitos() {
		
		PessoaFisica pessoa = new PessoaFisica();
		assertEquals(false, pessoa.verificaQuantDigitosCPF("1234567"));
	}
	
	@DisplayName("Inserindo CPF com dígitos iguais")
	@Test
	public void cpfComNumerosIguais() {
		
		PessoaFisica pessoa = new PessoaFisica();
		assertNotEquals(true, pessoa.verificaNumerosIguaisCPF("88888888888"));
	}
	
	@DisplayName("Testando todos os métodos impeditivos da classe para aceitar um CPF correto")
	@Test
	public void testandoImpeditivosCPF() {
		
		PessoaFisica pessoa = new PessoaFisica();
		assertAll("pessoa", 
				() -> assertEquals(true, pessoa.verificaQuantDigitosCPF("11445573997")),
				() -> assertEquals(true, pessoa.verificaNumerosIguaisCPF("11445573997")),
				() -> assertEquals(true, pessoa.validacaoCPF("11445573997"))
				);
	}
	
	@DisplayName("CPF com menos de 11 dígitos e retorno em Exception")
	@Test
	public void cpfComMenosDe11Digitos() throws Exception {
		
		PessoaFisica pessoa = new PessoaFisica();
		assertThrows(Exception.class, () -> {
			pessoa.validacaoCPF("123");
		});
	}
}
