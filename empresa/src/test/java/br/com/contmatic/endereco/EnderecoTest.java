package br.com.contmatic.endereco;

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
	public void deve_respeitar_hash_code() {
		assertThat(CLASSE, BeanMatchers.hasValidBeanHashCodeFor("cep", "numero"));
	}

	@Test
	public void deve_respeitar_equals() {
		assertThat(CLASSE, BeanMatchers.hasValidBeanEqualsFor("cep", "numero"));
	}

	@Test
	public void nao_deve_aceitar_nome_nulo() {
		endereco.setNome(null);
		assertFalse(isValid(endereco, "O nome não pode ser vazio."));
	}

	@Test
	public void nao_deve_aceitar_nome_vazio() {
		endereco.setNome("");
		assertFalse(isValid(endereco, "O nome não pode ser vazio."));
	}

	@Test
	public void nao_deve_aceitar_bairro_vazio() {
		endereco.setBairro("");
		assertFalse(isValid(endereco, "O nome do bairro não pode ser vázio."));
	}

	@Test
	public void nao_deve_aceitar_bairro_nulo() {
		endereco.setBairro(null);
		assertFalse(isValid(endereco, "O nome do bairro não pode ser vázio."));
	}

	@Test
	public void nao_deve_aceitar_cidade_nulo() {
		endereco.setCidade(null);
		assertFalse(isValid(endereco, "O nome da cidade não deve ser vázio."));
	}

	@Test
	public void nao_deve_aceitar_cidade_vazio() {
		endereco.setCidade("");
		assertFalse(isValid(endereco, "O nome da cidade não deve ser vázio."));
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
	public void nao_deve_aceitar_estado_nulo() {
		endereco.setEstado(null);
		assertFalse(isValid(endereco, "O estado não pode ser nulo."));
	}

	@Test
	public void deve_conter_o_valor_nome_no_toString() {
		assertThat(new Endereco().toString(), containsString("nome"));
	}

	@Test
	public void deve_conter_o_valor_bairro_no_toString() {
		assertThat(new Endereco().toString(), containsString("bairro"));
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

	@Test
	public void deve_conter_o_estado_no_toString() {
		assertThat(new Endereco().toString(), containsString("estado"));
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
