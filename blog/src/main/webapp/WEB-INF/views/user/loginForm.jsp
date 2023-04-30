<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file = "../layout/header.jsp"%>

<div class="container">
  <form action="/auth/loginProc" method="POST">
    <div class="form-group">
      <label for="username">Username</label>
      <input type="username" class="form-control" placeholder="Enter username" id="username" name="username">
    </div>

    <div class="form-group">
      <label for="password">Password</label>
      <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
    </div>

    <button id="btn-login" class="btn btn-primary">로그인</button>
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=2f424e16978d71f3cf8e447554a3f3ff&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img src="/image/kakao_login_button.png" height="37.6px"></a>

  </form>

</div>

<script src="/js/user.js"></script>
<%@ include file = "../layout/footer.jsp"%>