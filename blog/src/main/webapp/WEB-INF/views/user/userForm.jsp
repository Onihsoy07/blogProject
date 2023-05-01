<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file = "../layout/header.jsp"%>

<div class="container">
  <form>
    <input type="hidden" id="id" value="${principal.users.id}">
    <div class="form-group">
      <label for="username">Username</label>
      <input type="username" value="${principal.users.username}" class="form-control" placeholder="Enter username" id="username" readonly>
    </div>

    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" value="${principal.users.email}" class="form-control" placeholder="Enter email" id="email" readonly>
    </div>

  </form>

  <span>
    <c:if test="${empty principal.users.oauth}">
      <a href="/user/updateForm" class="btn btn-primary">회원수정</a>
    </c:if>
    <button id="btn-delete" class="btn btn-warning">회원탈퇴</button>
  </span>

</div>

<script src="/js/user.js"></script>
<%@ include file = "../layout/footer.jsp"%>