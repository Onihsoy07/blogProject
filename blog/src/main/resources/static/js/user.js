let index = {
  init: function() {
    $("#btn-save").on("click", ()=>{
      this.save();
    });
  },

  save: function() {
//    alert('user의 save 함수 호출');
    let data = {
      usernaem: $("#username").val(),
      password: $("#password").val(),
      email: $("#email").val()
    }

//    console.log(data);

    $.ajax().done().fail();
  }
}

index.init();