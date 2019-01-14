package com.b2w.Planets.api.model;


import java.util.List;

public class data {
	
	
	private int count;	
	
	private List<Planeta> results;
	
	private String erros;
	
	
	public data() {
		
	}


	public List<Planeta> getResults() {
		return results;
	}


	public void setResults(List<Planeta> results) {
		this.results = results;
	}


	public String getErros() {
		return erros;
	}


	public void setErros(String erros) {
		this.erros = erros;
	}



	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public String toString() {
		return "data [count=" + count + ", results=" + results + ", erros=" + erros + "]";
	}


	

}
