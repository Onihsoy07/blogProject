let index = {
  init: function() {
    $("#btn-save").on("click", ()=>{
      this.save();
    });
    $("#btn-update").on("click", ()=>{
          this.update();
    });
    $("#btn-delete").on("click", ()=>{
          this.deleteById();
    });
    $("#btn-comment").on("click", ()=>{
          this.replySave();
    });
    $("#btn-replyDelete").on("click", ()=>{
          this.replyDelete();
    });

  },

  save: function() {
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
  },

  update: function() {
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
  },

  deleteById: function() {
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
  },

  replySave: function() {
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
        location.reload();
        alert("댓글 쓰기가 완료되었습니다.");
      }).fail(function (error){
        alert("댓글 쓰기가 실패하였습니다.");
      });
  },

  replyDelete: function() {
    let id = $("#replyId").val();

    $.ajax({
      type : "DELETE",
      url : `/reply/${id}`,
      dataType:"json"
      }).done(function (res) {
        alert("댓글 삭제가 완료되었습니다.");
        location.reload();
      }).fail(function (error){
        alert("댓글 삭제가 실패하였습니다.");
      });
  }

};

index.init();