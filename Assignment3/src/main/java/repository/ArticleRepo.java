package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Article;

public class ArticleRepo {
	public void insertArticle(Article a) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.persist(a);
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
	}
	public void updateArticle(Article a) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
        eniEntityManager.merge(a);
        eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
	}
	public void deleteArticle(Article a) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
        eniEntityManager.getTransaction().begin();
		eniEntityManager.remove(eniEntityManager.contains(a) ? a : eniEntityManager.merge(a));
		eniEntityManager.getTransaction().commit();
        eniEntityManager.close();
        entityManagerFactory.close();
	}
	
	public Article readArticle(int id) {
		Article a=new Article();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		a = eniEntityManager.find(Article.class, id);
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
		return a;
	}
	public List<Article> viewAllArticles(){
		List<Article> listOfArticles = new ArrayList<Article>();
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		listOfArticles = eniEntityManager.createQuery("SELECT a FROM Article a", Article.class).getResultList();
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
		return listOfArticles;
	}
	public Article articleGivenName(String name) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		eniEntityManager.getTransaction().begin();
		Query q=eniEntityManager.createQuery("SELECT a FROM Article a WHERE a.title = "+"'"+name+"'");
		Article article = (Article) q.getSingleResult();
		eniEntityManager.getTransaction().commit();
		eniEntityManager.close();
		entityManagerFactory.close();
		return article;
	}
	public List<Article> allArticlesOfAWriter(int id){
		List<Article> allArticles = this.viewAllArticles();
		List<Article> articlesWriter=new ArrayList<Article>();
		for (int i=0;i<allArticles.size();i++) {
			if (allArticles.get(i).getWriter().getId()==id) {
				articlesWriter.add(allArticles.get(i));
			}
		}
		return articlesWriter;
	}
}
