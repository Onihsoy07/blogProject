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
  <hr>

  <div class="card">
    <div class="card-body"><textarea id="comment" class="form-control" rows="1"></textarea></div>
    <div class="card-footer"><button id="btn-comment" class="btn btn-primary">댓글달기</button>
  </div>

  <br>

  <div class="card">
    <div class="card-header">댓글</div>
    <ul id="comment--box" class="list-group">
      <c:forEach var="reply" items="${board.replyList}">
        <li id="comment--${reply.id}" class="list-group-item d-flex justify-content-between">
          <div>${reply.content}</div>
          <div class="d-flex">
            <input type="hidden" id="replyId" value="${reply.id}">
            <div class="font-italic">작성자 : ${reply.users.username} &nbsp;</div>
            <button onclick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
            &nbsp;
            <button class="badge">수정</button>
          </div>
        </li>
      </c:forEach>
    </ul>
  </div>

</div>

<script src="/js/board.js"></script>
<script src="/js/reply.js"></script>
<%@ include file = "../layout/footer.jsp"%>