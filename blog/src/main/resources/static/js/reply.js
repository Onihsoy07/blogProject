$(function () {
    $('#btn-comment').click(function() {
      let id = $("#id").text();
      let data = {
      content: $("#comment").val()
      };

      $.ajax({
        type : "POST",
        url : "/reply/board/" + id,
        contentType: "application/json;charset=utf-8",
        dataType:"json",
        data : JSON.stringify(data)
        }).done(function (res) {
          alert("댓글 쓰기가 완료되었습니다.");
          location.reload();
        }).fail(function (error){
          alert("댓글 쓰기가 실패하였습니다.");
        });
    })
})

function replyDelete (boardId, replyId) {
  alert(replyId);
  $.ajax({
    type : "DELETE",
    url : `/reply/${replyId}/board/${boardId}`,
    dataType:"json"
    }).done(function (res) {
      alert("댓글 삭제가 완료되었습니다.");
      location.reload();
    }).fail(function (error){
      alert("댓글 삭제가 실패하였습니다.");
    });
}