package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		cadastrarProduto();
		
		//consultado entidade
		EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        
        //apenas um dado
        Produto p = produtoDao.buscaPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarTodos();
		todos.forEach(p2 -> System.out.println(p2.getNome()));
		
		List<Produto> nome = produtoDao.buscarPorNome("Xiaomi Redmi");
		nome.forEach(nome2 -> System.out.println(nome2.getNome()));
		
		List<Produto> categoria = produtoDao.buscarPorCategoria("CELULAR");
		categoria.forEach(nome3 -> System.out.println(nome3.getNome()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoPorNome("Xiaomi Redmi");
		System.out.println("Preco do produto: "+precoDoProduto);
		
	}
/* to adicionando um metodo que faz so cadastro e nao quero remover esses comentarios da logica
 * 
 * 
		Categoria celulares =  new Categoria("CELULARES");
		//cria o objeto mas o hibernate nao gerencia ainda
		// esta no estado transient(hibernate nao se importa)

		Produto celular = new Produto("Xiaomi Redmi","quase nem usado",new BigDecimal("400"), celulares );
		
		EntityManager em  = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		//o persist() faz esses dados serem geridos pelo hibernate
		// entra em estado managed dops do persist()
		// vai sincronizar com o bd e fica sempre d olho ate o flush(), commit(),close() ou clear()
		
		
		celular.setNome("outro nome");// faz o update e muda no banco
		
		em.flush();//atualiza
		em.clear();//limpa

		celular.setNome("outro nome");// nao vai salvar, estado DETACHED
		
		celulares = em.merge(celulares);
		celulares.setNome("1234");

		celular.setNome("agora salva");//foi por estado managed com o merge()

		em.remove(celular);// remove os dados de celular do BD
		em.flush();
		
		
		em.getTransaction().commit();//commit salva no BD
		em.close();// estado: detached
		
*/
	private static void cadastrarProduto() {
	        Categoria celulares = new Categoria("CELULAR");
	        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );

	        EntityManager em = JPAUtil.getEntityManager();
	        ProdutoDao produtoDao = new ProdutoDao(em);
	        CategoriaDao categoriaDao = new CategoriaDao(em);

	        em.getTransaction().begin();

	        categoriaDao.cadastrar(celulares);
	        produtoDao.cadastrar(celular);

	        em.getTransaction().commit();
	        em.close();
	    }
	}


	