package br.com.contmatic.telefone;

import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.beanmatchers.BeanMatchers;

import br.com.contmatic.fixture.FixtureFuncionario;
import br.com.six2six.fixturefactory.Fixture;

public class TelefoneTest {
	private final static Class<Telefone> CLASSE = Telefone.class;
	private Validator validator;
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	@BeforeClass
	public static void setUp() {
		FixtureFuncionario.fixture();
	}

	@Test
	public void deve_respeitar_os_get_set() {
		assertThat(CLASSE, hasValidGettersAndSetters());
	}

	@Test
	public void deve_respeitar_hash_code() {
		assertThat(CLASSE, BeanMatchers.hasValidBeanHashCode());
	}

	@Test
	public void deve_respeitar_equals() {
		assertThat(CLASSE, BeanMatchers.hasValidBeanEquals());
	}

	@Test
	public void nao_deve_aceitar_numero_que_nao_comeca_com_9() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefone");
		telefone.setNumero("788519106");
		assertFalse(isValid(telefone, "O número do telefone está invalido."));
	}

	@Test
	public void nao_deve_aceitar_numero_com_letra() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefone");
		telefone.setNumero("9g8519106");
		assertFalse(isValid(telefone, "O número do telefone está invalido."));
	}

	@Test
	public void nao_deve_aceitar_numero_vazio() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefone");
		telefone.setNumero("");
		assertFalse(isValid(telefone, "O número do telefone não pode ser vazio."));
	}

	@Test
	public void nao_deve_aceitar_numero_null() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefone");
		telefone.setNumero(null);
		assertFalse(isValid(telefone, "O número do telefone não pode ser vazio."));
	}

	@Test
	public void nao_deve_aceitar_numero_numeros_com_menos_de_9_numero() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefone");
		telefone.setNumero("985");
		assertFalse(isValid(telefone, "O número do telefone tem que ter 9 numeros"));
	}

	@Test
	public void nao_deve_aceitar_numero_numeros_com_mais_de_9_numero() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefone");
		telefone.setNumero("98599999999999999999999");
		assertFalse(isValid(telefone, "O número do telefone tem que ter 9 numeros"));
	}

	@Test
	public void deve_aceitar_numero_valido() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefone");
		telefone.setNumero("985191604");
		assertTrue(isValid(telefone, "O número do telefone está invalido."));
	}

	@Test
	public void deve_aceitar_ddd_valido() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefone");
		telefone.setDdd(TelefoneDDD.DDD11);
		assertTrue(isValid(telefone, "O ddd do telefone não pode ser vazio."));
	}

	@Test
	public void nao_deve_aceitar_ddd_vazio() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("telefone");
		telefone.setDdd(null);
		assertFalse(isValid(telefone, "O ddd do telefone não pode ser vazio."));
	}

	@Test
	public void deve_conter_o_valor_ddd_no_toString() {
		assertThat(new Telefone().toString(), containsString("ddd"));
	}
	
	@Test
	public void deve_conter_o_valor_numero_no_toString() {
		assertThat(new Telefone().toString(), containsString("numero"));
	}

	public boolean isValid(Telefone telefone, String mensagem) {

		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Telefone>> restricoes = validator.validate(telefone);
		for (ConstraintViolation<Telefone> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}

}
