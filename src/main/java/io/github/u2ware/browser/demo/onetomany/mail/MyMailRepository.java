package io.github.u2ware.browser.demo.onetomany.mail;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MyMailRepository extends PagingAndSortingRepository<MyMail, Long>{

}
