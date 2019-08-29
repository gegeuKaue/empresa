package br.com.contmatic.endereco;


/**
 * The Enum Estado.
 */
public enum Estado {

	/** The ac. */
	AC("Acre"),

	/** The al. */
	AL("Alagoas"),

	/** The ap. */
	AP("Amapá"),

	/** The am. */
	AM("Amazonas"),

	/** The ba. */
	BA("Bahia"),

	/** The ce. */
	CE("Ceará"),

	/** The df. */
	DF("DistritoFederal"),

	/** The es. */
	ES("EspíritoSanto"),

	/** The go. */
	GO("Goiás"),

	/** The ma. */
	MA("Maranhão"),

	/** The mt. */
	MT("MatoGrosso"),

	/** The ms. */
	MS("MatoGrossodoSul"),

	/** The mg. */
	MG("MinasGerais"),

	/** The pa. */
	PA("Pará"),

	/** The pb. */
	PB("Paraíba"),

	/** The pr. */
	PR("Paraná"),

	/** The pe. */
	PE("Pernambuco"),

	/** The pi. */
	PI("Piauí"),

	/** The rj. */
	RJ("RiodeJaneiro"),

	/** The rn. */
	RN("RioGrandedoNorte"),

	/** The rs. */
	RS("RioGrandedoSul"),

	/** The ro. */
	RO("Rondônia"),

	/** The rr. */
	RR("Roraima"),

	/** The sc. */
	SC("SantaCatarina"),

	/** The sp. */
	SP("SãoPaulo"),

	/** The se. */
	SE("Sergipe"),

	/** The to. */
	TO("Tocantins");

	/** The nome estado. */
	private String nomeEstado;

	/** The length. */
	public static int LENGTH = values().length;

	/**
	 * Instantiates a new estado.
	 *
	 * @param nomeEstado the nome estado
	 */
	private Estado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	/**
	 * Gets the nome estado.
	 *
	 * @return the nome estado
	 */
	public String getNomeEstado() {
		return nomeEstado;
	}

	/**
	 * Sets the nome estado.
	 *
	 * @param nomeEstado the new nome estado
	 */
	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

}
