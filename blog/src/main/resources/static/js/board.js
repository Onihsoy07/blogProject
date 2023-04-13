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

  },

  save: function() {
    let data = {
      title: $("#title").val(),
      content: $("#content").val(),
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
    let data = {
      username: $("#username").val(),
      password: $("#password").val(),
      email: $("#email").val()
    };

    $.ajax({
      type : "PUT",
      url : "/board/joinProc",
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
    var id = $("#id").text();

    $.ajax({
      type : "DELETE",
      url : "/api/board/"+id,
      contentType: "application/json;charset=utf-8",
      dataType:"json"
      }).done(function (res) {
        alert("삭제가 완료되었습니다.");
        location.href="/";
      }).fail(function (error){
        alert("삭제가 실패하였습니다.");
      });
  }

};

index.init();