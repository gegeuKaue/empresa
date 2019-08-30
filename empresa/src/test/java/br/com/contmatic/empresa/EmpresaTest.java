package br.com.contmatic.empresa;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.fixture.FixtureEmpresa;
import br.com.contmatic.telefone.Telefone;
import br.com.six2six.fixturefactory.Fixture;

public class EmpresaTest {

	private final static Class<Empresa> CLASSE = Empresa.class;
	private Empresa empresa;
	private Validator validator;
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	@BeforeClass
	public static void setup() {
		FixtureEmpresa.fixture();
	}

	@Before
	public void init() {
		this.empresa = Fixture.from(CLASSE).gimme("empresa");
	}

	@Test
	public void deve_respeitar_os_get_set() {
		assertThat(CLASSE, hasValidGettersAndSetters());
	}

	@Test
	public void deve_respeitar_hash_code() {
		assertThat(CLASSE, hasValidBeanHashCodeFor("cnpj"));
	}

	@Test
	public void deve_respeitar_equals() {
		assertThat(CLASSE, hasValidBeanEqualsFor("cnpj"));
	}

	@Test
	public void deve_aceitar_nome_valido() {
		empresa.setNome("Contmatic");
		assertTrue(isValid(empresa, "O nome da empresa deve ser entre 1 e 100"));
	}

	@Test
	public void nao_deve_aceitar_nome_nulo() {
		empresa.setNome(null);
		assertFalse(isValid(empresa, "O nome da empresa não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_nome_vazio() {
		empresa.setNome("");
		assertFalse(isValid(empresa, "O nome da empresa não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_email_invalido() {
		empresa.setEmail("A@");
		assertFalse(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void deve_aceitar_email_valido() {
		empresa.setEmail("A@a.com");
		assertTrue(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_email_nulo() {
		empresa.setEmail(null);
		assertFalse(isValid(empresa, "O email da empresa não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_email_vazio() {
		empresa.setEmail("");
		assertFalse(isValid(empresa, "O email da empresa não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_vazio() {
		empresa.setCnpj("");
		assertFalse(isValid(empresa, "O cnpj não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_nulo() {
		empresa.setCnpj(null);
		assertFalse(isValid(empresa, "O cnpj não pode ser nulo"));
	}

	@Test
	public void deve_aceitar_cnpj_valido() {
		empresa.setCnpj("68263601000120");
		assertTrue(isValid(empresa, "O CNPJ da empresa está invalido"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_invalido() {
		empresa.setCnpj("68263601000121");
		assertFalse(isValid(empresa, "O CNPJ da empresa está invalido"));
	}

	@Test
	public void nao_deve_aceitar_funcionarios_nulos() {
		empresa.setFuncionarios(null);
		assertFalse(isValid(empresa, "Os funcionários da empresa está nulo."));
	}

	@Test
	public void nao_deve_aceitar_funcionarios_invalido() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setIdade(-1);
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(funcionario);
		empresa.setFuncionarios(funcionarios);
		assertFalse(isValid(empresa, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void deve_aceitar_funcionarios_valido() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setIdade(18);
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(funcionario);
		empresa.setFuncionarios(funcionarios);
		assertTrue(isValid(empresa, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void nao_deve_aceitar_enderecos_nulos() {
		empresa.setEnderecos(null);
		assertTrue(isValid(empresa, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void nao_deve_aceitar_url_nula() {
		empresa.setUrl(null);
		assertFalse(isValid(empresa, "A url do site ada empresa não pode ser vazio."));
	}

	@Test
	public void nao_deve_aceitar_url_vazio() {
		empresa.setUrl("");
		assertFalse(isValid(empresa, "A url do site ada empresa não pode ser vazio."));
	}

	@Test
	public void nao_deve_aceitar_somente_o_protocolo_da_url() {
		empresa.setUrl("http://");
		assertFalse(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_somente_o_www_da_url() {
		empresa.setUrl("www");
		assertFalse(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void deve_aceitar_url_valida() {
		empresa.setUrl("http://contmatic.com.br");
		assertTrue(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_somente_o_protocolo_e_www_da_url() {
		empresa.setUrl("http://contmatic.com.br");
		assertTrue(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_somente_o_dominio_da_url() {
		empresa.setUrl("contmatic.com");
		assertFalse(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_somente_o_dominio_da_url_com_dois_ponto_seguido() {
		empresa.setUrl("contmatic..com");
		assertFalse(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_url_com_ponto_no_final() {
		empresa.setUrl("http://www.contmatic.com.");
		assertFalse(isValid(empresa, "A url do site da empresa está invalida."));
	}

	@Test
	public void nao_deve_aceitar_enderecos_invalidos() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("endereco");
		endereco.setBairro(null);
		Set<Endereco> enderecos = new HashSet<Endereco>();
		enderecos.add(endereco);
		empresa.setEnderecos(enderecos);
		assertFalse(isValid(empresa, "O nome do bairro não pode ser vázio."));
	}

	@Test
	public void deve_aceitar_enderecos_validos() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("endereco");
		endereco.setBairro("Res. Flamboyant");
		Set<Endereco> enderecos = new HashSet<Endereco>();
		enderecos.add(endereco);
		empresa.setEnderecos(enderecos);
		assertTrue(isValid(empresa, "O nome do bairro não pode ser vázio."));
	}

	@Test
	public void nao_deve_aceitar_telefones_nulos() {
		empresa.setTelefones(null);
		assertFalse(isValid(empresa, "O telefone da empresa não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_telefones_invalidos() {
		Set<Telefone> telefones = new HashSet<Telefone>();
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefone");
		telefone.setNumero("");
		telefones.add(telefone);
		empresa.setTelefones(telefones);
		assertFalse(isValid(empresa, "O número do telefone está invalido."));
	}

	@Test
	public void deve_conter_o_valor_nome_no_toString() {
		assertThat(new Empresa().toString(), containsString("nome"));
	}

	@Test
	public void deve_conter_o_valor_email_no_toString() {
		assertThat(new Empresa().toString(), containsString("email"));
	}

	@Test
	public void deve_conter_o_valor_cnpj_no_toString() {
		assertThat(new Empresa().toString(), containsString("cnpj"));
	}

	@Test
	public void deve_conter_o_valor_funcionarios_no_toString() {
		assertThat(new Empresa().toString(), containsString("funcionarios"));
	}

	@Test
	public void deve_conter_o_valor_enderecos_no_toString() {
		assertThat(new Empresa().toString(), containsString("enderecos"));
	}

	public boolean isValid(Empresa empresa, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Empresa>> restricoes = validator.validate(empresa);
		for (ConstraintViolation<Empresa> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}
}
