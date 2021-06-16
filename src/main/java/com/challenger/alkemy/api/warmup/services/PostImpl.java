package com.challenger.alkemy.api.warmup.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenger.alkemy.api.warmup.models.entity.Post;
import com.challenger.alkemy.api.warmup.models.repository.PostRepository;

@Service
public class PostImpl implements PostService {
	
	@Autowired
	private PostRepository repository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Post> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Post> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Post save(Post post) {
		return repository.save(post);
	}

	@Transactional
	@Override
	public void deleteById(Long id) throws Exception {
		repository.deleteById(id);		
	}

	@Transactional
	@Override
	public void delete(Post post) {
		repository.delete(post);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Iterable<Post> findByFilter(String titulo, Long categoryId) {

		CriteriaBuilder  criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);      
	    Root<Post> postRoot = criteriaQuery.from(Post.class);
	    criteriaQuery.select(postRoot);
	    
	    // ORDER BY
	    criteriaQuery.orderBy(criteriaBuilder.desc(postRoot.get("fechaCreacion")));
	    
	    // For like expression
	    Expression<String> path = postRoot.get("titulo");
	    Expression<String> lowerTitle = criteriaBuilder.lower(path);
	    
	    List<Predicate> predicates = new ArrayList<>();

	    if(titulo!=null && !titulo.isEmpty()) predicates.add(criteriaBuilder.like(lowerTitle, '%' + titulo.toLowerCase() + '%'));
	    	    
	    criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

	    TypedQuery<Post> typedQuery = entityManager.createQuery(criteriaQuery);
	    List<Post> postList = typedQuery.getResultList();
	    
	 // Filter by movie ID
	    if(categoryId!=null) {
	    	List<Post> listFilterByCategory = new ArrayList<>();
	    	
	    	listFilterByCategory= postList.stream().filter(post->post.getCategoria().getId()==categoryId).collect(Collectors.toList());
	    	
	    	return listFilterByCategory;
	    }
	    

		return postList;
	}
}



