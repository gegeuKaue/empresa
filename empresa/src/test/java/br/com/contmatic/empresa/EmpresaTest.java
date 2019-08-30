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

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.fixture.FixtureEmpresa;
import br.com.six2six.fixturefactory.Fixture;

public class EmpresaTest {

	private final static Class<Empresa> CLASSE = Empresa.class;

	private Validator validator;
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	@BeforeClass
	public static void setUp() {
		FixtureEmpresa.fixture();
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
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setNome("Contmatic");
		assertTrue(isValid(empresa, "O nome da empresa deve ser entre 1 e 100"));
	}

	@Test
	public void nao_deve_aceitar_nome_nulo() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setNome(null);
		assertFalse(isValid(empresa, "O nome da empresa não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_nome_vazio() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setNome("");
		assertFalse(isValid(empresa, "O nome da empresa não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_email_invalido() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setEmail("A@");
		assertFalse(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void deve_aceitar_email_valido() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setEmail("A@a.com");
		assertTrue(isValid(empresa, "O email da empresa está inválido"));
	}

	@Test
	public void nao_deve_aceitar_email_nulo() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setEmail(null);
		assertFalse(isValid(empresa, "O email da empresa não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_email_vazio() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setEmail("");
		assertFalse(isValid(empresa, "O email da empresa não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_vazio() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setCnpj("");
		assertFalse(isValid(empresa, "O cnpj não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_nulo() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setCnpj(null);
		assertFalse(isValid(empresa, "O cnpj não pode ser nulo"));
	}

	@Test
	public void deve_aceitar_cnpj_valido() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setCnpj("68263601000120");
		assertTrue(isValid(empresa, "O CNPJ da empresa está invalido"));
	}

	@Test
	public void nao_deve_aceitar_cnpj_invalido() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setCnpj("68263601000121");
		assertFalse(isValid(empresa, "O CNPJ da empresa está invalido"));
	}

	@Test
	public void nao_deve_aceitar_funcionarios_nulos() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setFuncionarios(null);
		assertFalse(isValid(empresa, "Os funcionários da empresa está nulo."));
	}

	@Test
	public void nao_deve_aceitar_funcionarios_invalido() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setIdade(-1);
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(funcionario);
		empresa.setFuncionarios(funcionarios);
		assertFalse(isValid(empresa, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void deve_aceitar_funcionarios_valido() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setIdade(18);
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(funcionario);
		empresa.setFuncionarios(funcionarios);
		assertTrue(isValid(empresa, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void nao_deve_aceitar_enderecos_nulos() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		empresa.setEnderecos(null);
		assertTrue(isValid(empresa, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void nao_deve_aceitar_enderecos_invalidos() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		Endereco endereco = Fixture.from(Endereco.class).gimme("endereco");
		endereco.setBairro(null);
		Set<Endereco> enderecos = new HashSet<Endereco>();
		enderecos.add(endereco);
		empresa.setEnderecos(enderecos);
		assertFalse(isValid(empresa, "O nome do bairro não pode ser vázio."));
	}

	@Test
	public void deve_aceitar_enderecos_validos() {
		Empresa empresa = Fixture.from(CLASSE).gimme("empresa");
		Endereco endereco = Fixture.from(Endereco.class).gimme("endereco");
		endereco.setBairro("Res. Flamboyant");
		Set<Endereco> enderecos = new HashSet<Endereco>();
		enderecos.add(endereco);
		empresa.setEnderecos(enderecos);
		assertTrue(isValid(empresa, "O nome do bairro não pode ser vázio."));
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
