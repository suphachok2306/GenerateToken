package pcc.test.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pcc.test.token.entity.ApiEntity;

@Repository
public interface ApiJpaRepository extends JpaRepository<ApiEntity, Long> {
}



