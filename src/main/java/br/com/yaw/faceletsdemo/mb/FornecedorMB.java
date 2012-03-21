package br.com.yaw.faceletsdemo.mb;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


import br.com.yaw.faceletsdemo.model.Fornecedor;

/**
 * Os dados do cadastro de Fornecedores são mantidos na sessão, através desse managedbean.
 * 
 * Para simplificar a proposta do projeto não utilizo nenhum mecanismo de persistência em base de dados. 
 * @author eder
 */
public class FornecedorMB {
	
	private Fornecedor fornecedor;
	
	private Set<Fornecedor> fornecedores;
	
	private DataModel fornecedorDM;
	
	private String filtroNome;
	
	private List<Fornecedor> listaPesquisa;
	
	private int sequenciaId;
	
	public FornecedorMB() {
		this.fornecedores = new HashSet<Fornecedor>();
		this.fornecedores.add(new Fornecedor(++sequenciaId, "JSB Ltda","13311"));
		this.fornecedores.add(new Fornecedor(++sequenciaId, "Zxw Comercio","22321"));
		
		this.listaPesquisa = new ArrayList<Fornecedor>(this.fornecedores);
	}
	
	public String doNovo() {
		limparDados();
		
		fornecedor = new Fornecedor();
		fornecedor.setId(++sequenciaId);
		
		return "novoFornecedor";
	}
	
	public String doPesquisar() {
		if (filtroNome == null || "".equals(filtroNome.trim())) {
			listaPesquisa = new ArrayList<Fornecedor>(fornecedores);
			return "";
		}
		
		listaPesquisa = new ArrayList<Fornecedor>();
		
		for (Fornecedor f: fornecedores) {
			if (f.getNome() != null && !f.getNome().trim().isEmpty()) {
				if (f.getNome().toLowerCase().startsWith(filtroNome.toLowerCase())) {
					listaPesquisa.add(f);
					continue;
				}
				
				if (f.getNome().toLowerCase().endsWith(filtroNome.toLowerCase())) {
					listaPesquisa.add(f);
					continue;
				}
			}
		}
		
		return "listaFornecedores";
	}
	
	public String doEditar() {
		Fornecedor f = (Fornecedor) fornecedorDM.getRowData();
		if (f == null) {
			return "";
		}
		this.fornecedor = f;
		return "edicaoFornecedor";
	}
	
	public String doSalvar(){
		fornecedores.add(fornecedor);
		limparDados();
		
		return "listaFornecedores";
	}
	
	public String doCancelar() {
		limparDados();
		
		return "listaFornecedores";
	}
	
	public String doExcluir() {
		fornecedores.remove(fornecedor);
		limparDados();
		
		return "listaFornecedores";
	}

	public String getFiltroNome() {
		return filtroNome;
	}
	
	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public DataModel getFornecedorDM() {
		this.fornecedorDM = new ListDataModel(new ArrayList<Fornecedor>(listaPesquisa));
		return fornecedorDM;
	}
	
	public void setFornecedorDM(DataModel fornecedorDM) {
		this.fornecedorDM = fornecedorDM;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	private void limparDados() {
		fornecedor = null;
		filtroNome = "";
		listaPesquisa = new ArrayList<Fornecedor>(fornecedores);
	}
}
