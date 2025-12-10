package com.healthcare;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class HealthcareServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Healthcare Management System</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); margin: 0; padding: 20px; }");
        out.println(".container { max-width: 900px; margin: 50px auto; background: white; padding: 40px; border-radius: 15px; box-shadow: 0 20px 60px rgba(0,0,0,0.3); }");
        out.println("h1 { color: #667eea; text-align: center; margin-bottom: 10px; }");
        out.println(".subtitle { text-align: center; color: #666; margin-bottom: 30px; }");
        out.println(".features { display: grid; grid-template-columns: 1fr; gap: 15px; margin: 30px 0; }");
        out.println(".feature { background: linear-gradient(135deg, #f0f4ff 0%, #e8edff 100%); padding: 20px; border-radius: 10px; border-left: 5px solid #667eea; transition: transform 0.3s; }");
        out.println(".feature:hover { transform: translateX(10px); }");
        out.println(".feature-title { font-weight: bold; color: #667eea; margin-bottom: 5px; }");
        out.println(".success { background: #d4edda; color: #155724; padding: 15px; border-radius: 10px; text-align: center; font-weight: bold; margin-bottom: 30px; border: 2px solid #c3e6cb; }");
        out.println(".footer { text-align: center; color: #666; margin-top: 40px; padding-top: 20px; border-top: 2px solid #eee; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>🏥 Healthcare Management System</h1>");
        out.println("<p class='subtitle'>Complete CI/CD Pipeline - Project 9</p>");
        out.println("<div class='success'>✅ Application Successfully Deployed on Tomcat Server!</div>");
        out.println("<h2 style='color: #667eea;'>Key Features:</h2>");
        out.println("<div class='features'>");
        out.println("<div class='feature'><div class='feature-title'>📋 Patient Registration</div>Comprehensive patient information management system</div>");
        out.println("<div class='feature'><div class='feature-title'>📅 Appointment Scheduling</div>Smart doctor appointment booking and management</div>");
        out.println("<div class='feature'><div class='feature-title'>📊 Medical History</div>Complete medical history tracking and retrieval</div>");
        out.println("<div class='feature'><div class='feature-title'>💊 Prescription Management</div>Digital prescription recording and tracking</div>");
        out.println("<div class='feature'><div class='feature-title'>📄 Report Generation</div>Automated medical report generation system</div>");
        out.println("</div>");
        out.println("<div class='footer'>");
        out.println("<p><strong>Pipeline Status:</strong> ✅ All stages passed</p>");
        out.println("<p>Built with Jenkins | Quality checked by SonarQube | Stored in Nexus</p>");
        out.println("<p style='color: #999; margin-top: 10px;'>© 2025 Healthcare Management System</p>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
