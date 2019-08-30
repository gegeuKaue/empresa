package br.com.contmatic.empresa;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.beanmatchers.BeanMatchers;
import com.google.code.beanmatchers.ValueGenerator;

import br.com.contmatic.fixture.FixtureFuncionario;
import br.com.contmatic.telefone.Telefone;
import br.com.six2six.fixturefactory.Fixture;

public class FuncionarioTest {

	private final static Class<Funcionario> CLASSE = Funcionario.class;
	private Validator validator;
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	@BeforeClass
	public static void setUp() {
		beanMatcherData();
		FixtureFuncionario.fixture();
	}

	@Test
	public void deve_respeitar_os_get_set() {
		assertThat(CLASSE, hasValidGettersAndSetters());
	}

	@Test
	public void deve_respeitar_hash_code() {
		assertThat(CLASSE, hasValidBeanHashCodeFor("cpf"));
	}

	@Test
	public void deve_respeitar_equals() {
		assertThat(CLASSE, hasValidBeanEqualsFor("cpf"));
	}

	public static void beanMatcherData() {
		BeanMatchers.registerValueGenerator(new ValueGenerator<LocalTime>() {
			public LocalTime generate() {
				return LocalTime.now();
			}
		}, LocalTime.class);

		BeanMatchers.registerValueGenerator(new ValueGenerator<LocalDate>() {
			public LocalDate generate() {
				return LocalDate.now();
			}
		}, LocalDate.class);
	}

	@Test
	public void nao_deve_aceitar_nome_nulo() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setNome(null);
		assertFalse(isValid(funcionario, "O nome do funcionario não deve ser vázio."));
	}

	@Test
	public void nao_deve_aceitar_nome_vazio() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setNome("");
		assertFalse(isValid(funcionario, "O nome do funcionario não deve ser vázio."));
	}

	@Test
	public void nao_deve_aceitar_cargo_vazio() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setCargo("");
		assertFalse(isValid(funcionario, "O cargo do funcionario não deve ser vázio."));
	}

	@Test
	public void nao_deve_aceitar_cargo_nulo() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setCargo(null);
		assertFalse(isValid(funcionario, "O cargo do funcionario não deve ser vázio."));
	}

	@Test
	public void nao_deve_aceitar_idade_negativa() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setIdade(-5);
		assertFalse(isValid(funcionario, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void nao_deve_aceitar_idade_igual_a_zero() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setIdade(-5);
		assertFalse(isValid(funcionario, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void deve_aceitar_idade_valida() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setIdade(5);
		assertTrue(isValid(funcionario, "A idade do funcionario não pode ser negativa."));
	}

	@Test
	public void deve_aceitar_entrada_valida() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setEntrada(null);
		assertFalse(isValid(funcionario, "O horario de entrada funcionario não pode ser nulo."));
	}

	@Test
	public void deve_aceitar_saida_valida() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setSaida(null);
		assertFalse(isValid(funcionario, "O horario de saida funcionario não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_cpf_vazio() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setCpf("");
		assertFalse(isValid(funcionario, "O cep do funcionario não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_cpf_nulo() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setCpf(null);
		assertFalse(isValid(funcionario, "O cep do funcionario não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_cpf_ivalido() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setCpf("47878244064");
		assertFalse(isValid(funcionario, "O CPF do funcionario está inválido"));
	}

	@Test
	public void deve_aceitar_cpf_valido() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setCpf("47878244065");
		assertTrue(isValid(funcionario, "O CPF do funcionario está inválido"));
	}

	@Test
	public void deve_aceitar_cpf_com_letras() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setCpf("478782d4065");
		assertFalse(isValid(funcionario, "O CPF do funcionario está inválido"));
	}

	@Test
	public void nao_deve_aceitar_telefones_nulos() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setTelefones(null);
		assertFalse(isValid(funcionario, "O telefone do funcionario não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_telefones_invalidos() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		Set<Telefone> telefones = new HashSet<Telefone>();
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefone");
		telefone.setNumero("");
		telefones.add(telefone);
		funcionario.setTelefones(telefones);
		assertFalse(isValid(funcionario, "O número do telefone está invalido."));
	}

	@Test
	public void nao_deve_aceitar_email_invalido() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setEmail("aaaa@aaaaaa");

		assertFalse(isValid(funcionario, "O email do funcionario está invalido."));
	}

	@Test
	public void deve_aceitar_email_valido() {
		Funcionario funcionario = Fixture.from(Funcionario.class).gimme("funcionario");
		funcionario.setEmail("geovane@gmail.com");

		assertTrue(isValid(funcionario, "O email do funcionario está invalido."));
	}

	@Test
	public void deve_conter_o_valor_nome_no_toString() {
		assertThat(new Funcionario().toString(), containsString("nome"));
	}

	@Test
	public void deve_conter_o_valor_cargo_no_toString() {
		assertThat(new Funcionario().toString(), containsString("cargo"));
	}

	@Test
	public void deve_conter_o_valor_idade_no_toString() {
		assertThat(new Funcionario().toString(), containsString("idade"));
	}

	@Test
	public void deve_conter_o_valor_entrada_no_toString() {
		assertThat(new Funcionario().toString(), containsString("entrada"));
	}

	@Test
	public void deve_conter_o_valor_saida_no_toString() {
		assertThat(new Funcionario().toString(), containsString("saida"));
	}

	@Test
	public void deve_conter_o_valor_dataContratacao_no_toString() {
		assertThat(new Funcionario().toString(), containsString("dataContratacao"));
	}

	@Test
	public void deve_conter_o_valor_cpf_no_toString() {
		assertThat(new Funcionario().toString(), containsString("cpf"));
	}

	@Test
	public void deve_conter_o_valor_telefones_no_toString() {
		assertThat(new Funcionario().toString(), containsString("telefones"));
	}

	@Test
	public void deve_conter_o_valor_email_no_toString() {
		assertThat(new Funcionario().toString(), containsString("email"));
	}

	public boolean isValid(Funcionario funcionario, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Funcionario>> restricoes = validator.validate(funcionario);
		for (ConstraintViolation<Funcionario> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}
}
