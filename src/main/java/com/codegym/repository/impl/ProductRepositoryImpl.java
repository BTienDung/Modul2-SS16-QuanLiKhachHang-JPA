package com.codegym.repository.impl;

import com.codegym.model.Product;
import com.codegym.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> findAll(){
        TypedQuery<Product> query = em.createQuery("select p from Product p",Product.class);
        return query.getResultList();
    }

    @Override
    public void save(Product product) {
        if (product.getId()!=null){
            em.merge(product);
        }else {
            em.persist(product);
        }
    }

    @Override
    public Product findById(Long id) {
        TypedQuery<Product> query = em.createQuery("select p from  Product p WHERE p.id =:id ", Product.class);
        query.setParameter("id", id);
        try{
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void delete(Product product) {
        Product productFind = findById(product.getId());
        if (productFind!=null){
            em.remove(productFind);
        }
    }

}
