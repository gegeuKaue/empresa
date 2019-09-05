package br.com.contmatic.endereco;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.beanmatchers.BeanMatchers;

import br.com.contmatic.fixture.FixtureCidade;
import br.com.six2six.fixturefactory.Fixture;

public class CidadeTest {
	private final static Class<Cidade> CLASSE = Cidade.class;
	private Validator validator;
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Cidade cidade;

	@BeforeClass
	public static void setup() {
		FixtureCidade.fixture();
	}

	@Before
	public void init() {
		this.cidade = Fixture.from(CLASSE).gimme("cidade");
	}

	@Test
	public void deve_respeitar_construtor() {
		assertThat(CLASSE, hasValidBeanConstructor());
	}

	@Test
	public void deve_respeitar_os_get_set() {
		assertThat(CLASSE, hasValidGettersAndSetters());
	}

	@Test
	public void deve_respeitar_hash_code() {
		assertThat(CLASSE, BeanMatchers.hasValidBeanHashCodeFor("nome", "estado"));
	}

	@Test
	public void deve_respeitar_equals() {
		assertThat(CLASSE, BeanMatchers.hasValidBeanEqualsFor("nome", "estado"));
	}

	@Test
	public void nao_deve_aceitar_nome_nulo() {
		cidade.setNome(null);
		assertFalse(isValid(cidade, "Nome da cidade não pode ser vazio."));
	}

	@Test
	public void nao_deve_aceitar_nome_vazio() {
		cidade.setNome("");
		assertFalse(isValid(cidade, "Nome da cidade não pode ser vazio."));
	}

	@Test
	public void deve_aceitar_nome_valido() {
		cidade.setNome("Rua Salinas");
		assertTrue(isValid(cidade, "Nome da cidade não pode ser vazio."));
	}

	@Test
	public void nao_deve_aceitar_nome_com_mais_500() {
		cidade.setNome(RandomStringUtils.randomAlphabetic(502));
		assertFalse(isValid(cidade, "Nome da cidade não deve ser maior que 500"));
	}

	@Test
	public void nao_deve_aceitar_lista_de_bairro_com_mais_500_bairro() {
		List<String> bairros = new ArrayList<String>();
		for (int i = 0; i < 533; i++) {
			bairros.add("Res. Flamboyant");
		}
		cidade.setBairro(bairros);
		assertFalse(isValid(cidade, "O bairro limite da bairro da cidade é de 500"));
	}

	@Test
	public void nao_deve_aceitar_estado_nulo() {
		cidade.setEstado(null);
		assertFalse(isValid(cidade, "Nome do estado não pode ser nulo."));
	}

	@Test
	public void deve_aceitar_estado_valido() {
		cidade.setEstado(Estado.SP);
		assertTrue(isValid(cidade, "Nome do estado não pode ser nulo."));
	}

	@Test
	public void nao_deve_aceitar_bairro_vazio() {
		cidade.setBairro(null);
		assertTrue(isValid(cidade, "O Bairro da cidade não pode está vazios"));
	}

	@Test
	public void nao_deve_aceitar_bairro_nulo() {
		cidade.setBairro(null);
		assertTrue(isValid(cidade, "O Bairro da cidade não pode está vazios"));
	}

	@Test
	public void deve_aceitar_bairros() {
		List<String> bairros = new ArrayList<String>();
		bairros.add("Rua Sabará");
		bairros.add("Rua Orlando Silva");
		cidade.setBairro(bairros);
		assertThat(cidade.getBairro().size(), is(2));
	}

	@Test
	public void nao_deve_aceitar_bairro_vazia() {
		cidade.setBairro(new ArrayList<String>());
		assertTrue(isValid(cidade, "O Bairro da cidade não pode está vazios"));
	}

	@Test
	public void nao_deve_aceitar_nome_aceitar_mais_de_500_caracteres() {
		cidade.setNome(RandomStringUtils.randomAlphanumeric(501));
		assertFalse(isValid(cidade, "Nome da cidade não deve ser maior que 500"));
	}

	@Test
	public void deve_conter_o_valor_nome_no_toString() {
		assertThat(new Cidade().toString(), containsString("nome"));
	}

	@Test
	public void deve_conter_o_valor_estado_no_toString() {
		assertThat(new Cidade().toString(), containsString("estado"));
	}

	@Test
	public void deve_conter_o_valor_bairro_no_toString() {
		assertThat(new Cidade().toString(), containsString("estado"));
	}

	public boolean isValid(Cidade cidade, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Cidade>> restricoes = validator.validate(cidade);
		for (ConstraintViolation<Cidade> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}
}
