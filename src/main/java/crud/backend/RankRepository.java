package crud.backend;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RankRepository extends JpaRepository<Rank, Long> {

    List<Rank> findByNameLikeIgnoreCase(String nameFilter);

    List<Rank> findByNameLikeIgnoreCase(String nameFilter, Pageable pageable);

    long countByNameLike(String nameFilter);

    Rank findById(Long id);
}
