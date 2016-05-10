package io.github.u2ware.browser.demo.onetoone.foo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FooRepository extends PagingAndSortingRepository<Foo, Long>{

	
}
