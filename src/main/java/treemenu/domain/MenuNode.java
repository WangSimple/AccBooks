package treemenu.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Comparator;
import java.util.List;

@Entity
@Getter
@Setter
public class MenuNode {
    @Id
    private int nodeId;
    private int pid;
    private String nodeName;
    private String url;
    private int nodeOrder;
    private int rule;
    private String icon;
    @Transient
    private List<MenuNode> childMenu;




    public static Comparator<MenuNode> comparator=new Comparator<MenuNode>() {
        @Override
        public int compare(MenuNode o1, MenuNode o2) {
            if (o1.getNodeOrder()!=o2.getNodeOrder()){
                return o1.getNodeOrder()-o2.getNodeOrder();
            }
            return 0;
        }
    };
}
