<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file = "layout/header.jsp"%>

<div class="container">

  <button style="color:blue; height:100px; weight:100px; font-size:30px;" type="button" onclick="location.href='/auth/loginForm'">다시 로그인</button>

</div>

<script>
  window.onload = function () { alert("로그인 실패"); }
</script>

<%@ include file = "layout/footer.jsp"%>