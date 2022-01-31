/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Autor;
import entity.Book;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
import session.UserRoleFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "ManagerServlet", urlPatterns = {
    "/addBook", 
    "/createBook", 
    "/editBook", 
    "/updateBook", 
    "/addAuthor", 
    "/createAuthor"})
public class ManagerServlet extends HttpServlet {
    
    @EJB private AutorFacade autorFacade;
    @EJB private BookFacade bookFacade;
    @EJB private UserRoleFacade userRolesFacade;
    
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
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет прав. Войдите с правами менеджера");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "У вас нет прав. Войдите с правами менеджера");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        
        if(!userRolesFacade.isRole("ADMINISTRATOR", authUser)){
            request.setAttribute("info", "У вас нет прав. Войдите с правами менеджера");
            request.getRequestDispatcher("/showLogin").forward(request, response);
            return;
        }
        String path = request.getServletPath();       
        switch (path) {
            
            case "/addBook":
                List<Autor> authors = autorFacade.findAll();
                request.setAttribute("authors", authors);
                request.getRequestDispatcher("/addBook.jsp").forward(request, response);
                break;
            case "/createBook":
                String caption = request.getParameter("caption");
                String [] bookAuthors = request.getParameterValues("authors");
                String publicationYear = request.getParameter("publicationYear");
                String quantity = request.getParameter("quantity");
                Book book = new Book();
                book.setCaption(caption);
                List<Autor> listBookAutors = new ArrayList<>();
                for (int i = 0; i < bookAuthors.length; i++) {
                    String authorId = bookAuthors[i];
                    listBookAutors.add(autorFacade.find(Long.parseLong(authorId)));
                }
                book.setAuthor(listBookAutors);
                book.setQuantity(Integer.parseInt(quantity));
                book.setPublication_year(Integer.parseInt(publicationYear));
                book.setCount(book.getQuantity());
                bookFacade.create(book);
                request.getRequestDispatcher("/addBook.jsp").forward(request, response);
                break;
            case "/editBook":
                request.getRequestDispatcher("/editBook.jsp").forward(request, response);
                break;
            case "/updateBook":
                request.getRequestDispatcher("/updateBook.jsp").forward(request, response);
                break;
            case "/addAuthor":
                request.getRequestDispatcher("/addAuthor.jsp").forward(request, response);
                break;
            case "/createAuthor":
                request.getRequestDispatcher("/createAuthor.jsp").forward(request, response);
                break;
            case "/editAuthor":
                request.getRequestDispatcher("/editAuthor.jsp").forward(request, response);
                break;
            case "/updateAuthor":
                request.getRequestDispatcher("/updateAuthor.jsp").forward(request, response);
                break;
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
