package br.com.yaw.faceletsdemo.mb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.yaw.faceletsdemo.model.Cliente;

/**
 * Os dados do cadastro de Cliente são mantidos na sessão, através desse managedbean.
 * 
 * Para simplificar a proposta do projeto não utilizo nenhum mecanismo de persistência em base de dados. 
 * @author eder
 */
public class ClienteMB {

	private Cliente cliente;
	
	private Set<Cliente> clientes;
	
	private DataModel clienteDM;
	
	private String filtroNome;
	
	private List<Cliente> listaPesquisa;
	
	private int sequenciaId;
	
	public ClienteMB() {
		this.clientes = new HashSet<Cliente>();
		this.clientes.add(new Cliente(++sequenciaId, "Ana Paula","5433"));
		this.clientes.add(new Cliente(++sequenciaId, "Pedro Henrique","6655"));
		
		this.listaPesquisa = new ArrayList<Cliente>(this.clientes);
	}
	
	public String doNovo() {
		limparDados();
		
		cliente = new Cliente();
		cliente.setId(++sequenciaId);
		
		return "novoCliente";
	}
	
	public String doPesquisar() {
		if (filtroNome == null || "".equals(filtroNome.trim())) {
			listaPesquisa = new ArrayList<Cliente>(clientes);
			return "";
		}
		
		listaPesquisa = new ArrayList<Cliente>();
		
		for (Cliente f: clientes) {
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
		
		return "listaClientes";
	}
	
	public String doEditar() {
		Cliente c = (Cliente) clienteDM.getRowData();
		if (c == null) {
			return "";
		}
		this.cliente = c;
		return "edicaoCliente";
	}
	
	public String doSalvar(){
		clientes.add(cliente);
		limparDados();
		
		return "listaClientes";
	}
	
	public String doCancelar() {
		limparDados();
		
		return "listaClientes";
	}
	
	public String doExcluir() {
		clientes.remove(cliente);
		limparDados();
		
		return "listaClientes";
	}

	public String getFiltroNome() {
		return filtroNome;
	}
	
	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}
	
	public DataModel getClienteDM() {
		this.clienteDM = new ListDataModel(new ArrayList<Cliente>(listaPesquisa));
		return clienteDM;
	}
	
	public void setClienteDM(DataModel clienteDM) {
		this.clienteDM = clienteDM;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	private void limparDados() {
		cliente = null;
		filtroNome = "";
		listaPesquisa = new ArrayList<Cliente>(clientes);
	}
	
}
