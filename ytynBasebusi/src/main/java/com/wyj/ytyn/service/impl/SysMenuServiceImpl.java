package com.wyj.ytyn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyj.ytyn.entity.SysMenu;
import com.wyj.ytyn.entity.SysRole;
import com.wyj.ytyn.entity.SysUser;
import com.wyj.ytyn.entity.Ztree;
import com.wyj.ytyn.mapper.SysMenuMapper;
import com.wyj.ytyn.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    public static final String PERMISSION_STRING = "perms[\"{0}\"]" ;

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> selectMenusByUser(SysUser user) {
        List<SysMenu> menus = new LinkedList<>();
        // 管理员显示所有菜单信息
        if (user.isAdmin()) {
            menus = this.baseMapper
                    .selectList(new QueryWrapper<SysMenu>()
                            .in("menu_type", "M", "C")
                            .eq("visible", 0)
                            .orderByAsc("parent_id", "order_num"));
        } else {
            // todo 多表
            menus = this.baseMapper
                    .selectList(new QueryWrapper<SysMenu>());
        }
        return menus;
    }

    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId) {
        List<SysMenu> menuList = null;
        if (SysUser.isAdmin(userId)) {
            QueryWrapper<SysMenu> ew = new QueryWrapper<>();
            if (StringUtils.isNotBlank(menu.getMenuName())) {
                ew = ew.apply("menu_name like concat('%',{0},'%')", menu.getMenuName());
            }
            if (StringUtils.isNotBlank(menu.getVisible())) {
                ew = ew.eq("visible", menu.getVisible());
            }
            menuList = this.baseMapper.selectList(ew);
        } else {
            menu.getParams().put("userId", userId);
            // todo 多表
            menuList = this.baseMapper.selectList(new QueryWrapper<SysMenu>());
        }
        return menuList;
    }

    @Override
    public List<SysMenu> selectMenuAll(Long userId) {
        return null;
    }

    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Ztree> roleMenuTreeData(SysRole role, Long userId) {
        return null;
    }

    @Override
    public List<Ztree> menuTreeData(Long userId) {
        return null;
    }

    @Override
    public Map<String, String> selectPermsAll(Long userId) {
        return null;
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return 0;
    }

    @Override
    public SysMenu selectMenuById(Long menuId) {
        return null;
    }

    @Override
    public int selectCountMenuByParentId(Long parentId) {
        return 0;
    }

    @Override
    public int selectCountRoleMenuByMenuId(Long menuId) {
        return 0;
    }

    @Override
    public int insertMenu(SysMenu menu) {
        return 0;
    }

    @Override
    public int updateMenu(SysMenu menu) {
        return 0;
    }

    @Override
    public String checkMenuNameUnique(SysMenu menu) {
        return null;
    }


    /**
     * 根据父节点ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 父节点ID
     * @return 子节点
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId) {
        ArrayList<SysMenu> returnList = new ArrayList<>();
        for (SysMenu t : list) {
            // 由父节点ID遍历子节点
            if (t.getParentId() == parentId) {
                recurionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归生成每个菜单层级关系
     *
     * @param list 菜单list
     * @param t    当前节点菜单
     */
    private void recurionFn(List<SysMenu> list, SysMenu t) {
        // 获取子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList) {
            if (hasChild(list, tChild)) {
                for (SysMenu nChild : childList) {
                    recurionFn(list, nChild);
                }
            }
        }
    }

    /**
     * 得到当前节点的子节点列表
     *
     * @param list 菜单list
     * @param t    当前节点
     * @return 子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        ArrayList<SysMenu> tlist = new ArrayList<>();
        for (SysMenu n : list) {
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * p判断当前节点是否有子节点
     *
     * @param list 菜单list
     * @param t    当前节点菜单
     * @return 是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0;
    }
}
