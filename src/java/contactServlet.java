/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author Balra
 */
public class contactServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        HttpSession session = request.getSession(false);
        if(session!=null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fastcart","root","Balram1312");
                Statement stmt = con.createStatement();
                String query="Insert into contactus(name,email,message) values (?,?,?);";    
                PreparedStatement pstmt=con.prepareStatement(query);    
                pstmt.setString(1, name);    
                pstmt.setString(2, email);    
                pstmt.setString(3,message);      
                int x=pstmt.executeUpdate();    
                  out.println("<head>");
               out.println("<style>");
               out.println("h5{");
               out.println("position:static");
               out.println("}");
               out.println("</style>");
               out.println("</head>");
               out.println("<body>");
               out.println("<h5>"+name+", your message has been received , We will contact you soon.<h5>");
               out.println("</body>");
               out.println("</html>");
               RequestDispatcher rd=request.getRequestDispatcher("contact.html");  
               rd.include(request,response);  
                
            if(x==1)    
            {    
                out.println("Values Inserted Successfully");    
            }    
            }catch(Exception p){
                out.print(p);
            }
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
