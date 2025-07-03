<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.String" %>
<%
    String erreur = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - LibraByte</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Inter&display=swap" rel="stylesheet">
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <div class="login-form">
                <h2>Log in</h2>

                <% if(erreur != null) { %>
                    <p style="color: red;"><%= erreur %></p>
                <% } %>

                <form action="${pageContext.request.contextPath}/login" method="post">
                    <label>Name</label>
                    <input type="text" name="username" placeholder="Enter your username" required />

                    <label>Password</label>
                    <input type="password" name="password" placeholder="Enter your password" required />

                    <button type="submit" class="btn-login">Log In</button>
                </form>

                <a href="#" class="forgot-password">Forgot Password?</a>

                <div class="social-login">
                    <p>Log in with</p>
                    <div class="social-icons">
                        <a href="#"><img src="${pageContext.request.contextPath}/img/Google.jpg" alt="Google" /></a>
                        <a href="#"><img src="${pageContext.request.contextPath}/img/Facebook.jpg" alt="Facebook" /></a>
                        <a href="#"><img src="${pageContext.request.contextPath}/img/Instagram.jpg" alt="Instagram" /></a>
                    </div>
                </div>
            </div>

            <div class="login-image">
                <img src="${pageContext.request.contextPath}/img/book1.jpg" alt="Books stack" />
            </div>
        </div>

        <div class="signup-bar">
            <span>Don't have an account?</span>
            <a href="#" class="btn-signup">Sign Up</a>
        </div>
    </div>
</body>
</html>
