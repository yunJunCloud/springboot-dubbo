$(function () {
    $("#craw").click(function () {
            craw();
    })
    }
)

function craw() {
    var wz = $("#wangzhan").val()
    $.ajax({
        type:"post",
        dataType:"json",
        contentType:"application/json",
        data:JSON.stringify({"seed":wz}),
        url:"/moview/craw",
        success:function (data) {
            if(data.code=="200"){
                alert(data.message);
            }
        }
        }
    )
}