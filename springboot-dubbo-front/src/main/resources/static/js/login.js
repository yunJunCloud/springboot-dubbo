function login() {
    var user = $("#username").val();
    var password = $("#password").val();
    var user = {
        user:user,
        password:password
    };
    $.ajax({
        type:"POST",
        dataType:"json",
        data:JSON.stringify(user),
        /*data:{
            user:user,
            password:password
        },*/
        contentType:"application/json",
        url:"/user/login",
        success:function (data) {
            if(data.code==200){
                alert("success");
                window.location.href="../moview.html";
            }
        }
    })

}


$(function () {
    $("#login").click(function () {
        login()
    })
    }
   )
