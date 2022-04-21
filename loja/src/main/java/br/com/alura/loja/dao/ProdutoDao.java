package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public Produto buscaPorId(Long id) {
		return em.find(Produto.class, id);//é da entidade produto e como é ORM tem q falar q é a classe
	}

	public List<Produto> buscarTodos(){
		//Produto é o nome da entidade,
		String JPQL = "SELECT p FROM Produto p";// carrega o proprio OBJETO p com todos os atributos
		return em.createQuery(JPQL,Produto.class).getResultList();//Java Persistence Query Language
	}
	
	public List<Produto> buscarPorNome(String nome){
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";// nome é o nome do ATRIBUTO DA CLASSE NO CASO É O MESMO DA ENTIDADE
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)// substitui o paramentro do metodo pelo parametro da query
				.getResultList();
	} // poderia ser "... p.nome = :nome AND p.categoria = :categoria" adicionando setParameter
	
	
	public List<Produto> buscarPorCategoria(String nome){// mesmo sendo d tabela diferente mas tem o relacionamento e existe em Produto (classe tbm)
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";//JPQL é um SQL orientado a objeto por isso nao precisa fazer join igual o sql
		return em.createQuery(jpql, Produto.class)
				.setParameter(1, nome)// substitui o paramentro do metodo pelo parametro da query				.getResultList();
				.getResultList();
	}
	
	
	public BigDecimal buscarPrecoDoProdutoPorNome(String nome){
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";//traz apenas um unico atributo da entidade 
		return em.createQuery(jpql, BigDecimal.class)// agora esse é o retorno entao tem q mudar
				.setParameter("nome", nome)
				.getSingleResult();//como o nome diz so tras um item da query
	}

	
	
	
}
