<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file = "../layout/header.jsp"%>

<div class="container">

  <button class="btn btn-secondary" onclick="location.href='/'">목록</button>
  <c:if test="${board.users.id == principal.users.id}">
    <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
    <button id="btn-delete" class="btn btn-danger">삭제</button>
  </c:if>
  <br/><br/>
  <div>
    작성자:<span><i>${board.users.username}</i>&nbsp;&nbsp;&nbsp;</span>
    글 번호:<span id="id"><i>${board.id}</i></span>
  </div>
  <br/>
  <div>
    <h3>${board.title}</h3>
  </div>
  <hr>
  <div>
    <div>${board.content}</div>
  </div>

</div>

<script src="/js/board.js"></script>
<%@ include file = "../layout/footer.jsp"%>