/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Reader;
import entity.Role;
import entity.User;
import entity.UserRole;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ReaderFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRoleFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {
    "/showLogin",
    "/Login",
    "/logout"})
public class LoginServlet extends HttpServlet {
    
    @EJB private UserFacade userFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private UserRoleFacade userRoleFacade;
    @EJB private ReaderFacade readerFacade;
    
    @Override
    public void init() throws ServletException {
        super.init();
        List<User> users = userFacade.findAll();
        if(users.isEmpty()){
            User user = new User();
            user.setLogin("admin");
            user.setPassword("12345");
            Reader reader = new Reader();
            reader.setFirstname("admin");
            reader.setLastname("admin");
            reader.setPhone("123456");
            readerFacade.create(reader);
            user.setReader(reader);
            userFacade.create(user);
            Role role = new Role();
            role.setRoleName("ADMINISTRATOR");
            roleFacade.create(role);
            UserRole userRole = new UserRole();
            userRole.setUserId(user);
            userRole.setRoleId(role);
            userRoleFacade.create(userRole);
            role = new Role();
            roleFacade.create(role);
            role.setRoleName("MANAGER");
            userRole = new UserRole();
            userRole.setUserId(user);            
            userRole.setRoleId(role);
            userRoleFacade.create(userRole);
            role = new Role();
            role.setRoleName("READER");
            roleFacade.create(role);
            userRole = new UserRole();
            userRole.setUserId(user);
            userRole.setRoleId(role);            
            userRole.setRoleId(role);
            userRoleFacade.create(userRole);
            
            
        }
    }
    
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String path = request.getServletPath();
        request.setCharacterEncoding("UTF-8");
        switch (path) {
            case "/showLogin":
                
                request.getRequestDispatcher("showLogin.jsp").forward(request, response);
                break;
            case "/login":
                
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "/logout":
                
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
