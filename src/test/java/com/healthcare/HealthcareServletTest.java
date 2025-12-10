package com.healthcare;

import org.junit.Test;
import static org.junit.Assert.*;

public class HealthcareServletTest {
    
    @Test
    public void testServletNotNull() {
        HealthcareServlet servlet = new HealthcareServlet();
        assertNotNull("Servlet should not be null", servlet);
    }
    
    @Test
    public void testServletInstance() {
        HealthcareServlet servlet = new HealthcareServlet();
        assertTrue("Should be instance of HttpServlet", servlet instanceof javax.servlet.http.HttpServlet);
    }
}
