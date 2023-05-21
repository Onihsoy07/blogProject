$(function () {
    $("#btn-loginCheck").on("click", ()=>{
      loginCheck();
    });
    $("#btn-save").on("click", ()=>{
      save();
    });
    $("#btn-update").on("click", ()=>{
          update();
    });
    $("#btn-delete").on("click", ()=>{
          broke();
    });

})

  function loginCheck () {
    let data = {
      username: $("#username").val(),
      password: $("#password").val()
    };

		if(data.username == ""){
			alert("아이디를 입력해 주세요.");
			$("#username").focus();
			return false;
		}

		if(data.password == ""){
			alert("패스워드를 입력해 주세요.");
			$("#password").focus();
			return false;
		}

    $.ajax({
      type : "POST",
      url : "/auth/loginProc",
      contentType: "application/json;charset=utf-8",
      dataType:"json",
      data : JSON.stringify(data)
      }).done(function (res) {
        alert("회원가입이 완료되었습니다.");
        location.href="/";
      }).fail(function (error){
        alert("회원가입이 실패하였습니다.");
      });
  }

  function save () {
    let data = {
      username: $("#username").val(),
      password: $("#password").val(),
      email: $("#email").val()
    };

// data 확인용
//    console.log(data);
//    alert(JSON.stringify(data));

    $.ajax({
      type : "POST",
      url : "/auth/joinProc",
      contentType: "application/json;charset=utf-8",
      dataType:"json",
      data : JSON.stringify(data)
      }).done(function (res) {
        alert("회원가입이 완료되었습니다.");
        location.href="/";
      }).fail(function (error){
        alert("회원가입이 실패하였습니다.");
      });
  }

  function update () {
    let data = {
      id: $("#id").val(),
      password: $("#password").val(),
      email: $("#email").val()
    };

    $.ajax({
      type : "PUT",
      url : "/user",
      contentType: "application/json;charset=utf-8",
      dataType:"json",
      data : JSON.stringify(data)
      }).done(function (res) {
        alert("회원수정이 완료되었습니다.");
        location.href="/";
      }).fail(function (error){
        alert("회원수정이 실패하였습니다.");
      });
  }

  function broke () {
    let id = $("#id").val();

    $.ajax({
      type : "DELETE",
      url : "/user/" + id,
      contentType: "application/json;charset=utf-8",
      dataType:"json"
      }).done(function (res) {
        alert("회원탈퇴가 완료되었습니다.");
        location.href="/";
      }).fail(function (error){
        alert("회원탈퇴가 실패하였습니다.");
      });
  }
