console.clear();

const loginBtn = document.getElementById('login');
const signupBtn = document.getElementById('signup');
const button1=document.getElementById('submit1');
const button2=document.getElementById('submit2');


loginBtn.addEventListener('click', (e) => {
	let parent = e.target.parentNode.parentNode;
	Array.from(e.target.parentNode.parentNode.classList).find((element) => {
		if(element !== "slide-up") {
			parent.classList.add('slide-up')
		}else{
			signupBtn.parentNode.classList.add('slide-up')
			parent.classList.remove('slide-up')
		}
	});
});

signupBtn.addEventListener('click', (e) => {
	let parent = e.target.parentNode;
	Array.from(e.target.parentNode.classList).find((element) => {
		if(element !== "slide-up") {
			parent.classList.add('slide-up')
		}else{
			loginBtn.parentNode.parentNode.classList.add('slide-up')
			parent.classList.remove('slide-up')
		}
	});
});

button1.addEventListener('click',()=>{
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
		// success: function (data) {
		// 	if (data === "success") {
		// 		alert(data)
		// 	} else {
		// 		alert(data);
		// 	}
		// },
		// error: function () {
		// 	alert("error");
		// }
	});
});

button2.addEventListener('click',()=>{
	var a=document.getElementById("tel2").value;
	var b=document.getElementById("password2").value;
	$.ajax({
		url: "/login",
		type: 'POST',
		async: true,
		dataType: "text",
		data: {
			'tel': a.toString(),
			'password': b.toString(),
		},
		xhrFields: {
			withCredentials: true //允许跨域带Cookie
		},

		success: function (data) {
			if (data === "redirect:toIndexPage") {
				alert(data);
				window.location.href="/index.html";
			} else {
				alert("1error");
			}
		},
		error: function () {
			alert("2error");
		}
	});
});

