package com.b2w.Planets.api.responses;

import java.util.List;

public class Response<T> {
	
	private long total_items;
	
	private long pages;	
	
	private String pagina;
	
	private T data;
	
	private List<String> erros;
	
	
	public Response(T data) { 
		
		this.data = data;
		
	}
	
	public Response(List<String> erros) { 
		
		this.erros = erros;
	}
	
	public Response() { 
		
		
	}
	

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public long getTotal_items() {
		return total_items;
	}

	public void setTotal_items(long total_items) {
		this.total_items = total_items;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}


	
	

}
