package hyundaimeta.com.comingmapi.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hyundaimeta.com.comingmapi.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findById(String id); 
    public List<User> findByIdLike(String keyword);
    public List<User> findByName(String name); 
    public List<User> findByNameLike(String keyword);
    public List<User> findByIdAndName(String id, String name);

}
