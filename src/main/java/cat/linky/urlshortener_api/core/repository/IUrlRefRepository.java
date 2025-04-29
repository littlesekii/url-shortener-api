package cat.linky.urlshortener_api.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.linky.urlshortener_api.core.model.UrlRef;

@Repository
public interface IUrlRefRepository extends JpaRepository<UrlRef, Long> {
    UrlRef findByUrlRef(String urlRef);
}
