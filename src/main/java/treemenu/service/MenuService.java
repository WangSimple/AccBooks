package treemenu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import treemenu.dao.IMenuDao;
import treemenu.domain.MenuNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    private IMenuDao dao;

    public List<MenuNode> getMenuTreeByRule(int rule)throws Exception{
        List<MenuNode> nodes=dao.findAllByRule(rule);
        if (nodes==null){
            return null;
        }
        List<MenuNode> root=new ArrayList<MenuNode>();
        for(MenuNode node:nodes){
            if (0==node.getPid()){
                root.add(node);
            }
        }
        Collections.sort(root,MenuNode.comparator);
        for(MenuNode node:root){
            node.setChildMenu(getChildNode(node.getNodeId(),nodes));
        }

        return root;
    }


    private List<MenuNode> getChildNode(int id,List<MenuNode> nodes){
        List<MenuNode> list=new ArrayList<MenuNode>();
        for (MenuNode node:nodes){
            if (id==node.getPid()){
                list.add(node);
            }
        }
        for (MenuNode node:list){
            node.setChildMenu(getChildNode(node.getNodeId(),nodes));
        }
        Collections.sort(list,MenuNode.comparator);
        /*if (list.size()==0){
            return null;
        }*/
        return list;
    }
}
