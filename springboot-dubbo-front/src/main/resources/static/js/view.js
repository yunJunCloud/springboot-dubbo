$(function () {
    $("#nameSerach").click(function () {
        serach();
    })
})

function search() {
    var name = $("#serach").val();
    var url = "/moview/searchMoview";
    if(null!=name&&""!=name){
        url=url+"?name="+name;
    }
    $.ajax({
        type:"get",
        url:"/moview/searchMoview?name="+name,
        success:succFunction,
        error:errFunction
    })
}

function succFunction(data) {
    var contents = data.map.get("moviews");
    $.each(contents, function (i, vaule) {
        var str = "<div style=\"height: 100px\">\n" +
                    "<label style=\"margin-left: 150px;font-size: 24px\" for=\"mm\" >" +
                    "<b>"+vaule.name+"</b></label><a href='"+vaule.url+"' id=\"mm\">"+vaule.name+"</a>\n" +
                    " </div>"
    })
}

function errFunction(data) {
        alert(data.message);
}

