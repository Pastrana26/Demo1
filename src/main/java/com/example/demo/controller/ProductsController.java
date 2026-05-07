package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Products;
import com.example.demo.service.ProductsService;

@RestController
@RequestMapping("products")
public class ProductsController {

    @Autowired
	private ProductsService service;
	
	@GetMapping()
	public Iterable<Products> getAll() {
		return service.getAll();
	}
	
	@GetMapping("{name}")
	public Iterable<Products> searchByName(@PathVariable String name) {
		return service.searchByName(name);
	}
	
	@PostMapping()
	public ResponseEntity<?> add(@RequestBody Products product) {
		service.add(product);
		return new ResponseEntity<String>("Saved product", HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@RequestBody Products product, @PathVariable String id) {
		service.update(product, id);
		return new ResponseEntity<String>("Updated product", HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		service.delete(id);
		return new ResponseEntity<String>("Deleted product", HttpStatus.OK);
	}
}
