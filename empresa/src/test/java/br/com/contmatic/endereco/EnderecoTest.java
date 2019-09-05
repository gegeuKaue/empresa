package br.com.contmatic.endereco;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
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

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.beanmatchers.BeanMatchers;

import br.com.contmatic.fixture.FixtureEndereco;
import br.com.six2six.fixturefactory.Fixture;

public class EnderecoTest {
	private final static Class<Endereco> CLASSE = Endereco.class;
	private Validator validator;
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Endereco endereco;

	@BeforeClass
	public static void setup() {
		FixtureEndereco.fixture();
	}

	@Before
	public void init() {
		this.endereco = Fixture.from(CLASSE).gimme("endereco");
	}

	@Test
	public void deve_respeitar_os_get_set() {
		assertThat(CLASSE, hasValidGettersAndSetters());
	}

	@Test
	public void deve_respeitar_construtor() {
		assertThat(CLASSE, hasValidBeanConstructor());
	}

	@Test
	public void deve_respeitar_hash_code() {
		assertThat(CLASSE, BeanMatchers.hasValidBeanHashCodeFor("cep", "numero"));
	}

	@Test
	public void deve_respeitar_equals() {
		assertThat(CLASSE, BeanMatchers.hasValidBeanEqualsFor("cep", "numero"));
	}

	@Test
	public void nao_deve_aceitar_cidade_nulo() {
		endereco.setCidade(null);
		assertFalse(isValid(endereco, "Os valores da cidade não pode ser nulo"));
	}

	@Test
	public void nao_deve_aceitar_cidade_invalida() {
		Cidade cidade = (Cidade) Fixture.from(Cidade.class).gimme("cidade");
		cidade.setBairro(null);
		endereco.setCidade(cidade);
		assertFalse(isValid(endereco, "O Bairro da cidade não pode está vazio"));
	}

	@Test
	public void deve_aceitar_cidade_valida() {
		Cidade cidade = (Cidade) Fixture.from(Cidade.class).gimme("cidade");
		endereco.setCidade(cidade);
		assertTrue(isValid(endereco, "O Bairro da cidade não pode está vazio"));
	}

	@Test
	public void nao_deve_aceitar_cep_vazio() {
		endereco.setCep("");
		assertFalse(isValid(endereco, "O CEP não pode está vázio."));
	}

	@Test
	public void nao_deve_aceitar_cep_nulo() {
		endereco.setCep(null);
		assertFalse(isValid(endereco, "O CEP não pode está vázio."));
	}

	@Test
	public void nao_deve_aceitar_cep_com_letras() {
		endereco.setCep("266656s");
		assertFalse(isValid(endereco, "Digite um cep valido"));
	}

	@Test
	public void deve_aceitar_cep_valido() {
		endereco.setCep("08588146");
		assertTrue(isValid(endereco, "Digite um cep valido"));
	}

	@Test
	public void nao_deve_aceitar_cep_com_menos_de_8_numeros() {
		endereco.setCep("085");
		assertFalse(isValid(endereco, "O CEP deve ter 8 digitos."));
	}

	@Test
	public void nao_deve_aceitar_numero_do_endereco_negativo() {
		endereco.setNumero(-8);
		assertFalse(isValid(endereco, "O número não deve ser negativo."));
	}

	@Test
	public void deve_aceitar_numero_do_endereco() {
		endereco.setNumero(8);
		assertTrue(isValid(endereco, "O número não deve ser negativo."));
	}

	@Test
	public void nao_deve_aceitar_cep_com_mais_de_8_numeros() {
		endereco.setCep("08588889889");
		assertFalse(isValid(endereco, "O CEP deve ter 8 digitos."));
	}

	@Test
	public void deve_conter_o_valor_cidade_no_toString() {
		assertThat(new Endereco().toString(), containsString("cidade"));
	}

	@Test
	public void deve_conter_o_valor_cep_no_toString() {
		assertThat(new Endereco().toString(), containsString("cep"));
	}

	@Test
	public void deve_conter_o_numero_no_toString() {
		assertThat(new Endereco().toString(), containsString("numero"));
	}

	public boolean isValid(Endereco endereco, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Endereco>> restricoes = validator.validate(endereco);
		for (ConstraintViolation<Endereco> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}
}
