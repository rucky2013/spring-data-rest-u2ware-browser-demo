package io.github.u2ware.browser.demo.onetomany.simple;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SimpleOneRepository extends PagingAndSortingRepository<SimpleOne, Long>{

}
