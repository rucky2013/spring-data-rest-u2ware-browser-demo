package io.github.u2ware.browser.demo.basic.auto;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AutoRepository extends PagingAndSortingRepository<Auto, Long>{

	
}
