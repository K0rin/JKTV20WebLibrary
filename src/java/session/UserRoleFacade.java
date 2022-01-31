/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.User;
import entity.UserRole;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class UserRoleFacade extends AbstractFacade<UserRole> {

    @PersistenceContext(unitName = "JKTV20WebLibraryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRoleFacade() {
        super(UserRole.class);
    }
    public boolean isRole(String roleName, User user){
        try {
            List<String> userRoles = em.createQuery("SELECT ur.roleId.roleName FROM UserRole ur WHERE ur.userId = :user")
                    .setParameter("user", user)
                    .getResultList();
            if(userRoles.contains(roleName)){
                return true;
            }else{
                return false;
            }
                    
        } catch (Exception e) {
            return false;
        }
    }
    
}
