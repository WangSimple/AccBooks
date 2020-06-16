package treemenu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import treemenu.domain.MenuNode;
import treemenu.service.MenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MenuNodeController {
    @Autowired
    private MenuService service;

    @RequestMapping(value = "/getMenus/{rule}")
    @ResponseBody
    public Map<String,Object> getMenus(@PathVariable int rule){
        Map<String,Object> result=new HashMap<String,Object>();
        List<MenuNode> list= null;
        try {
            list = service.getMenuTreeByRule(rule);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("rstCode","500");
            result.put("menus",null);
            result.put("info","exception ："+e);
            return result;
        }
        if (list!=null){
            result.put("rstCode","200");
            result.put("menus",list);
            result.put("info","success");
        }else{
            result.put("rstCode","-1");
            result.put("menus",null);
            result.put("info","there is no result");
        }
        return result;
    }
}
