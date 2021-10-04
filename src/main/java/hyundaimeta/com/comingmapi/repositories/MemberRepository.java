package hyundaimeta.com.comingmapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hyundaimeta.com.comingmapi.entities.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findByAccount(String account); 
    public List<Member> findByAccountLike(String keyword);
    public List<Member> findByName(String name); 
    public List<Member> findByNameLike(String keyword);
    public List<Member> findByAccountAndName(String account, String name);

}
