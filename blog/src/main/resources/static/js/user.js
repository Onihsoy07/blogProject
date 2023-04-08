let index = {
  init: function() {
    $("#btn-save").on("click", ()=>{
      this.save();
    });
  },

  save: function() {
    let data = {
      username: $("#username").val(),
      password: $("#password").val(),
      email: $("#email").val()
    }

// data 확인용
//    console.log(data);
//    alert(JSON.stringify(data));

//    $.ajax({
//      type:"POST",
//      url:"/blog/api/user",
//      data:JSON.stringify(data),
//      contentType:"application/json;charset=utf-8",  //body MIME
//      dataType:"json" //응답 데이터 타입
//    }).done(function(res) {
//      alert("회원가입이 완료되었습니다.");
//      location.href="/blog";
//    }).fail(function(error) {
//      alert(JSON.stringify(error));
//    });

    $.ajax({
      type : "POST",
      url : "/blog/api/user",
      contentType: "application/json;charset=utf-8",
      dataType:"json",
      data : JSON.stringify(data)
      }).done(function (res) {
        alert("회원가입이 완료되었습니다.");
        console.log(JSON.stringify(res));
      }).fail(function (error){
        alert(JSON.stringify(error));
      });

  }
}

index.init();