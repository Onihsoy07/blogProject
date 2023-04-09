let index = {
  init: function() {
    $("#btn-save").on("click", ()=>{
      this.save();
    });
    $("#btn-login").on("click", ()=>{
          this.login();
        });

  },

  save: function() {
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
      url : "/api/user",
      contentType: "application/json;charset=utf-8",
      dataType:"json",
      data : JSON.stringify(data)
      }).done(function (res) {
        alert("회원가입이 완료되었습니다.");
        location.href="/";
      }).fail(function (error){
        alert("회원가입이 실패하였습니다.");
      });
  },

    login: function() {
      let data = {
        username: $("#username").val(),
        password: $("#password").val()
      };

  // data 확인용
  //    console.log(data);
  //    alert(JSON.stringify(data));

      $.ajax({
        type : "POST",
        url : "/api/user/login",
        contentType: "application/json;charset=utf-8",
        dataType:"json",
        data : JSON.stringify(data)
        }).done(function (res) {
          alert();
          location.href="/";
        }).fail(function (error){
          alert("로그인이 실패하였습니다.");
        });
    }
};

index.init();