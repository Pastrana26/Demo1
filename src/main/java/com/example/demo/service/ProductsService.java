package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.Products;
import com.example.demo.repository.ProductsRepository;

@Service
public class ProductsService {
    @Autowired
	private ProductsRepository repo;
    @Autowired
	private MongoTemplate mongoTemplate;

    public List<Products> getAll() {
		return repo.findAll();
	}

    public Iterable<Products> searchByName(String name) {
        Query query = new Query();
		query.addCriteria(Criteria.where("name").regex("^"+name));
        List<Products> resources = mongoTemplate.find(query, Products.class);
		return resources;
    }

    public void add(Products product){
        repo.save(product);
    }

    public void update(Products product, String id){
        repo.findById(id).get();
		product.setId(id);
		repo.save(product);
    }

    public void delete(String id){
        repo.findById(id).get();
		repo.deleteById(id);
    }
}
