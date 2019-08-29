package br.com.contmatic.empresa;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEqualsFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCodeFor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.code.beanmatchers.BeanMatchers;

public class EmpresaTest {

	private final static Class<Empresa> CLASSE = Empresa.class;

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

	

}
