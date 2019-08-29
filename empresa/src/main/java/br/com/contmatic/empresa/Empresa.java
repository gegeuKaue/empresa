package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.caelum.stella.bean.validation.CNPJ;
import br.com.contmatic.endereco.Endereco;

public class Empresa {

	@NotBlank(message = "O nome da empresa não pode ser nulo.")
	private String nome;

	/** The email. */
	@Email(message = "O email da empresa está inválido")
	private String email;

	/** The cnpj. */
	@CNPJ(message = "O CNPJ da empresa está invalido")
	private String cnpj;

	/** The endereco. */
	@NotNull(message = "O endereço da empresa está vazio")
	private Set<Endereco> enderecos;

	/** The lista funcionario. */
	@NotNull(message = "Os funcinarios da empresa está nulo;")
	private List<Funcionario> listaFuncionario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Funcionario> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.cnpj).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Empresa empresa = (Empresa) obj;
		return new EqualsBuilder().append(this.cnpj, empresa.getCnpj()).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, JSON_STYLE);
	}
}
