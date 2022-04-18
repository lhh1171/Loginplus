const shopping = document.getElementById('btnselect');

shopping.addEventListener('click', (e) => {
    var a=document.getElementById("username1").value;
    var b=document.getElementById("tel1").value;
    var c=document.getElementById("password1").value;

    $.ajax({
        url: "/signup",
        type: 'POST',
        async: true,
        dataType: "text",
        data: {
            'name': a.toString(),
            'tel': b.toString(),
            'password': c.toString()
        },
        xhrFields: {
            withCredentials: true //允许跨域带Cookie
        },
        success: function (data) {
            if (data === "success") {
                alert(data)
            } else {
                alert(data);
            }
        },
        error: function () {
            alert("error");
        }
    });
});