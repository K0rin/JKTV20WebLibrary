/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.sun.xml.ws.security.impl.policy.Constants;
import entity.Book;
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
import javax.servlet.http.HttpSession;
import session.AutorFacade;
import session.BookFacade;
import session.ReaderFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRoleFacade;
import tools.EncryptPassword;

/**
 *
 * @author pupil
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {
    "/index.jsp",
    "/showLogin",
    "/login",
    "/logout"})
public class LoginServlet extends HttpServlet {
    
    @EJB private UserFacade userFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private UserRoleFacade userRoleFacade;
    @EJB private ReaderFacade readerFacade;
    @EJB private BookFacade bookFacade;
    private EncryptPassword encryptPassword;
    
    @Override
    public void init() throws ServletException {
        super.init();
        List<User> users = userFacade.findAll();
        if(users.isEmpty()){
            User user = new User();
            user.setLogin("admin");
            encryptPassword = new EncryptPassword();
            String salt = encryptPassword.createSalt();
            user.setSalt(salt);
            String hashPassword = encryptPassword.createHash("12345", salt);
            user.setPassword(hashPassword);
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
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/index.jsp":
                List<Book> books = bookFacade.findAll();
                request.setAttribute("books", books);
                request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
                break;
            case "/showLogin":        
                request.getRequestDispatcher("showLogin.jsp").forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User authUser = userFacade.find(login);
                //authentification
                if(authUser == null){
                    request.setAttribute("info", "Произошла ошибка обратитесь к разработчику");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                    break;
                }
                encryptPassword = new EncryptPassword();
                String hashPassword = encryptPassword.createHash(password, authUser.getSalt());
                if(hashPassword == null){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                    break;
                }
                if(!hashPassword.equals(authUser.getPassword())){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                    break;
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("authUser", authUser);
                
                String info = authUser.getReader().getFirstname()+", Вы успешно вошли";
                request.setAttribute("info", info);
                request.getRequestDispatcher("/showLogin").forward(request, response);
                break;
            case "/logout":
                session = request.getSession(false);
                if(session != null){
                    session.invalidate();
                    request.setAttribute("info", "Вы успешно вышли");
                }
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
