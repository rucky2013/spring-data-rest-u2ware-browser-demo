package io.github.u2ware.browser.demo.onetoone.bar;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BarRepository extends PagingAndSortingRepository<Bar, Long>{

	
}
