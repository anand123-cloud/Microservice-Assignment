/**
 * 
 */
package com.demo.foodservice.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.foodservice.entity.MenuItem;

/**
 * @author harikrishna.maris
 *
 */
public interface MenuRepository extends PagingAndSortingRepository<MenuItem, String> {
	
	List<MenuItem> findByMenuNameContainingIgnoreCase(String menuName);

}
