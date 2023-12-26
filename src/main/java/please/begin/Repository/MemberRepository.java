package please.begin.Repository;

import org.springframework.stereotype.Repository;
import please.begin.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
    Optional<MemberEntity> findByEmail(String memberEmail);
}
