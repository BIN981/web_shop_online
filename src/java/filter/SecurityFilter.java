/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import config.Constants;
import config.SecurityConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.Account;
import util.SecurityUtils;

/**
 *
 * @author Dell
 */
@WebFilter("/AuthenticationFilter")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        String[] signPath = {
            "/loginController",
            "/signin.jsp",
            "/signupController",
            "/signup.jsp"
        };

        if (servletPath.equals("/")) {
            RequestDispatcher dispatcher //
                    = request.getServletContext().getRequestDispatcher("/home");

            dispatcher.forward(request, response);
            return;
        }
        if (!SecurityUtils.isSecurityPage(servletPath)) {
            if (account == null) {
                chain.doFilter(request, response);
                return;
            }
            if (account != null && Arrays.asList(signPath).contains(servletPath)) {
                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher("/home");

                dispatcher.forward(request, response);
                return;
            }
        } else {
            Set<Integer> roles = SecurityConfig.getAllAppRoles();
            if (account != null && roles.contains(account.getRoleId())) {
                List<String> url = SecurityConfig.getUrlPatternsForRole(account.getRoleId());

                if (url.contains(servletPath)) {
                    chain.doFilter(request, response);
                    return;
                } else {
                    RequestDispatcher dispatcher //
                            = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");

                    dispatcher.forward(request, response);
                    return;
                }
            } else {
                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher("/signin.jsp");

                dispatcher.forward(request, response);
                return;
            }
//            if (account != null && account.getRoleId() == Constants.ROLE_ADMIN) {
//                List<String> url = SecurityConfig.getUrlPatternsForRole(account.getRoleId());
//                if (url.contains(servletPath)) {
//                    chain.doFilter(request, response);
//                    return;
//                } else {
//                    RequestDispatcher dispatcher //
//                            = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
//
//                    dispatcher.forward(request, response);
//                    return;
//                }
//            } else if (account != null && account.getRoleId() == Constants.ROLE_CUSTOMER) {
//                List<String> url = SecurityConfig.getUrlPatternsForRole(account.getRoleId());
//                if (url.contains(servletPath)) {
//                    chain.doFilter(request, response);
//                    return;
//                } else {
//                    RequestDispatcher dispatcher //
//                            = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
//
//                    dispatcher.forward(request, response);
//                    return;
//                }
//            } else if (account != null && account.getRoleId() == Constants.ROLE_MKT) {
//                List<String> url = SecurityConfig.getUrlPatternsForRole(account.getRoleId());
//                if (url.contains(servletPath)) {
//                    chain.doFilter(request, response);
//                    return;
//                } else {
//                    RequestDispatcher dispatcher //
//                            = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
//
//                    dispatcher.forward(request, response);
//                    return;
//                }
//            } else if (account != null && account.getRoleId() == Constants.ROLE_MKTMANAGER) {
//                List<String> url = SecurityConfig.getUrlPatternsForRole(account.getRoleId());
//                if (url.contains(servletPath)) {
//                    chain.doFilter(request, response);
//                    return;
//                } else {
//                    RequestDispatcher dispatcher //
//                            = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
//
//                    dispatcher.forward(request, response);
//                    return;
//                }
//            } else if (account != null && account.getRoleId() == Constants.ROLE_SALE) {
//                List<String> url = SecurityConfig.getUrlPatternsForRole(account.getRoleId());
//                if (url.contains(servletPath)) {
//                    chain.doFilter(request, response);
//                    return;
//                } else {
//                    RequestDispatcher dispatcher //
//                            = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
//
//                    dispatcher.forward(request, response);
//                    return;
//                }
//            } else {
//                RequestDispatcher dispatcher //
//                        = request.getServletContext().getRequestDispatcher("/signin.jsp");
//
//                dispatcher.forward(request, response);
//                return;
//            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }
}
