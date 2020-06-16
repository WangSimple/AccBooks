package treemenu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import treemenu.domain.MenuNode;

import java.util.List;

@Repository
public interface IMenuDao extends JpaRepository<MenuNode,Integer>{

    List<MenuNode> findAllByRule(int rule);

}
