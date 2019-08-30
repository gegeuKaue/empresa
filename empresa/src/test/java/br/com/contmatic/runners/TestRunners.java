package br.com.contmatic.runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.contmatic.empresa.EmpresaTest;
import br.com.contmatic.empresa.FuncionarioTest;
import br.com.contmatic.telefone.TelefoneTest;

@RunWith(Suite.class)
@SuiteClasses({ EmpresaTest.class, FuncionarioTest.class, TelefoneTest.class })
public class TestRunners {

}
