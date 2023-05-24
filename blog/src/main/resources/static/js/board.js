$(function () {
    $("#btn-save").on("click", ()=>{
      save();
    });
    $("#btn-update").on("click", ()=>{
          update();
    });
    $("#btn-delete").on("click", ()=>{
          deleteById();
    });
})

function save() {
  let data = {
    title: $("#title").val(),
    content: $("#content").val()
  };

  $.ajax({
    type : "POST",
    url : "/api/board",
    contentType: "application/json;charset=utf-8",
    dataType:"json",
    data : JSON.stringify(data)
    }).done(function (res) {
      alert("글쓰기가 완료");
      location.href="/";
    }).fail(function (error){
      alert("글쓰기 실패");
    });
}

function update () {
  let id = $("#id").val()

  let data = {
     title: $("#title").val(),
     content: $("#content").val()
  };

  $.ajax({
    type : "PUT",
    url : "/api/board/"+id,
    contentType: "application/json;charset=utf-8",
    dataType:"json",
    data : JSON.stringify(data)
    }).done(function (res) {
      alert("수정이 완료되었습니다.");
      location.href="/";
    }).fail(function (error){
      alert("수정이 실패하였습니다.");
    });
}

function deleteById () {
  let id = $("#id").text();

  $.ajax({
    type : "DELETE",
    url : "/api/board/"+id,
    dataType:"json"
    }).done(function (res) {
      alert("삭제가 완료되었습니다.");
      location.href="/";
    }).fail(function (error){
      alert("삭제가 실패하였습니다.");
    });
}

function goodBtn(boardId, usersId) {
    let data = {
        boardId: boardId,
        usersId: usersId
    };

    $.ajax({
    type : "POST",
    url : "/likes",
    contentType: "application/json;charset=utf-8",
    dataType: "json",
    data : JSON.stringify(data)
    }).done(function (res) {
        if (res.status==200) {
            $('#goodCnt').text(parseInt($('#goodCnt').text()) + 1);
        }
        alert(res.data);
    }).fail(function (error){
      alert("실패");
    });
}