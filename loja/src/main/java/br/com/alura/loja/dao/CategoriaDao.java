package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDao {
	
	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);// tranforma o objeto que esta no estado detached no estado managed,
								  // ou seja, possivel de alterar no framework
	}
	
	public void remove(Categoria categoria) {
		categoria = em.merge(categoria);// tem que reatribuir pq eh o retorno de objeto
		this.em.remove(categoria);//precisa estar no managed
	}

}
