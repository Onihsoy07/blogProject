function replySummit (boardId, replyId, depth) {
    let data = null;
    if (replyId == 0) {
        data = {
            content: $("#comment").val(),
            replyId: null,
            depth: depth
        };
    } else if (depth >= 5) {
        alert("더 대댓작성이 안됩니다.");
        return false;
    } else {
        data = {
            content: $(`#comment${replyId}`).val(),
            replyId: replyId,
            depth: depth
        };
    }


    $.ajax({
    type : "POST",
    url : "/reply/board/" + boardId,
    contentType: "application/json;charset=utf-8",
    dataType:"json",
    data : JSON.stringify(data)
    }).done(function (res) {
      alert("댓글 쓰기가 완료되었습니다.");
      location.reload();
    }).fail(function (error){
      alert("댓글 쓰기가 실패하였습니다.");
    });
}

function replyDelete (boardId, replyId) {
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

function replyModifyWindow(replyId) {
  $('.sh').css('display', 'none');
  $(`#replyModifyWindow${replyId}`).css('display','block');
}

function reReplyWindow(replyId) {
    $('.sh').css('display', 'none');
    $(`#reReplyWindow${replyId}`).css('display','block');
}

function replyModify(replyId) {
  let data = {
    content : $(`#commentModify${replyId}`).val()
  };

  $.ajax({
    type : "PUT",
    url : "/reply/" + replyId,
    contentType: "application/json;charset=utf-8",
    dataType:"json",
    data : JSON.stringify(data)
  }).done(function (res) {
    alert("댓글 수정 완료");
    location.reload();
  }).fail(function (error) {
    console.log(error);
    alert("댓글 수정 실패");
    location.reload();
  })
}