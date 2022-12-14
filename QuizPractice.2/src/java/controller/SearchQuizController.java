/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuizDAO;
import dao.QuizLevelDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Quiz;
import model.QuizLevel;
import model.Subject;

/**
 *
 * @author DELL
 */
public class SearchQuizController extends HttpServlet {

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
        
        String indexPage = request.getParameter("index");
        if(indexPage == null){
            indexPage ="1";
        }
        int index = Integer.parseInt(indexPage);
        
        QuizDAO quizDao = new QuizDAO();
        SubjectDAO subjectDao = new SubjectDAO();
        QuizLevelDAO quizlevelDao = new QuizLevelDAO();
        String search = request.getParameter("search") ;
        
        ArrayList<Quiz> list = quizDao.getQuizByQuizName(index, search);
        ArrayList<Subject> listS = subjectDao.getAllSubjcet();
        ArrayList<QuizLevel> listL = quizlevelDao.getAllQuizLevel();
        
        //số lượng trang
        int numberPage = quizDao.numberOfPages();
                
        request.setAttribute("searchQuiz", true);
        request.setAttribute("numberP", numberPage);
        request.setAttribute("lisQuiz", list);
        request.setAttribute("listSubject", listS);
        request.setAttribute("quizLevel", listL);
        request.setAttribute("tagP", index);
        request.getRequestDispatcher("view/quizList.jsp").forward(request, response);
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
