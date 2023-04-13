<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file = "../layout/header.jsp"%>

<div class="container">
  <form action="/auth/loginProc" method="POST">

    <div class="my_box">
      <div style="margin:10px;font-size:20px;">
        ID and PASSWORD is incorrect
      </div>
    </div>
    <br>
    <div class="form-group">
      <label for="username">Username</label>
      <input type="username" class="form-control" placeholder="Enter username" id="username" name="username">
    </div>

    <div class="form-group">
      <label for="password">Password</label>
      <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
    </div>

    <button id="btn-login" class="btn btn-primary">로그인</button>
  </form>
</div>

<style>
  .my_box {
    boarder:1px solid black;
    height:70px;
    width:320px;
    padding:10px;
    background-color:#FFE3EE;
  }
</style>

<script src="/js/user.js"></script>
<%@ include file = "../layout/footer.jsp"%>