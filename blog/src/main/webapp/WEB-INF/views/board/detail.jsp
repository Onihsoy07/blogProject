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

    <div style="height:50px;margin-bottom:20px;">
        <span class="view-good">
            <button class="badge good-botton" onclick="goodBtn(${board.id}, ${principal.users.id})">
                <div class="good-cnt" id="goodCnt">${likes}</div>
                <img class="good-img" src="https://cdn-icons-png.flaticon.com/512/179/179655.png">
            </button>
        </span>
    </div>

  <div class="card">
    <div class="card-body"><textarea id="comment" class="form-control" rows="1"></textarea></div>
    <div class="card-footer">
      <button id="btn-comment" class="btn btn-primary" onclick="replySummit(${board.id}, 0, 0)">댓글달기</button>
    </div>
  </div>

  <div class="card" style="margin-top:20px;">
    <div class="card-header">댓글</div>
    <ul id="comment--box" class="list-group">
      <c:forEach var="reply" items="${board.replyList}">

        <li id="comment--${reply.id}" class="list-group-item" depth="${reply.depth}" style="margin-left:${reply.depth*30}px">
          <div class="font-italic" style="font-size:3px;font-weight:bold;">
            <span>작성자 : ${reply.users.username}</span> &nbsp;
            <span>${reply.createDate}</span>
            <span>&nbsp;&nbsp;&nbsp;${reply.depth}</span>
          </div>
          <div style="margin:5px 0px;">
            ${reply.content}
          </div>
          <div class="d-flex">
            <input type="hidden" id="replyId" value="${reply.id}">
            <div>
              <c:if test="${reply.users.id eq principal.users.id}">
                <button onclick="replyDelete(${board.id}, ${reply.id})" class="badge reply-button">삭제</button>
                <button onclick="replyModifyWindow(${reply.id})" class="badge reply-button">수정</button>
              </c:if>
              <c:if test="${reply.depth < 4}">
                <button onclick="reReplyWindow(${reply.id})" class="badge reply-button">댓글</button>
              </c:if>
            </div>
          </div>
        </li>

        <div style="display:none" id="replyModifyWindow${reply.id}" class="sh">
          <div class="card-body"><textarea id="commentModify${reply.id}" class="form-control" rows="1">${reply.content}</textarea></div>
          <div style="margin:0px 0px 10px 20px;">
            <button onclick="replyModify(${reply.id})" class"badge">수정</button>
          </div>
        </div>
        <div style="display:none;margin-left:${reply.depth*30}px;" id="reReplyWindow${reply.id}" class="sh">
          <div class="card-body"><textarea id="comment${reply.id}" class="form-control" rows="1"></textarea></div>
          <div style="margin:0px 0px 10px 20px;">
            <button onclick="replySummit(${board.id}, ${reply.id}, ${reply.depth+1})" class"badge">댓글</button>
          </div>
        </div>
      </c:forEach>
    </ul>
  </div>

</div>

<script src="/js/board.js"></script>
<script src="/js/reply.js"></script>
<%@ include file = "../layout/footer.jsp"%>