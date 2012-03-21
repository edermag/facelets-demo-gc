package br.com.yaw.faceletsdemo.model;

public class Fornecedor {

	public Fornecedor(){}
	
	public Fornecedor(Integer id, String nome, String cnpj){
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
	}
	
	private Integer id;
	
	private String nome;
	
	private String cnpj;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!obj.getClass().equals(this.getClass()))
			return false;
		
		Fornecedor outro = (Fornecedor) obj;
		return this.getId().equals(outro.getId());
	}
	
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
}
