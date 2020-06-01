<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<style type="text/css">
body {
	font-family: sans-serif;
}

#login_wrap {
	margin-top: 10%;
	float: center;
	vertical-align: center;
	width: 60%;
	min-width: 320px;
	background-color: #fcfffe;
}

input[type=text], input[type=password] {
	width: 50%;
	padding: 3px;
	font-size: 18px;
	float: left;
	border: 1px solid silver;
}

b {
	font-size: 20px;
	vertical-align: middle;
}

input[type=submit] {
	border-radius: 4px;
	float: right;
	font-size: 17px;
	padding: 4px;
	color: #333;
	font-weight: 500;
	width: 100px;
	border: 1px solid #14b4c9;
}

label {
	float: left;
	margin-right: 10px;
}

a {
	color: #14b4c9;
	font-weight: 500;
}

@media screen and (max-width: 1200px) {
	#forgot {
		margin-left: 130px;
	}
}

@media screen and (max-width: 600px) {
	#login_wrap {
		width: 96%;
		text-align="center";
	}
}
</style>

</head>


<body>
<center>
	
	<div id="login_wrap">
		<form name = "form" action="<%=request.getContextPath()%>/login" method="post" >
			<div
				style="padding: 10px; background-color: #f7f7f7; text-align: left; border-top: 1px solid #d8e1eb; border-bottom: 1px solid #d8e1eb; color: #091c69; font-weight: 700; font-size: 19px">
				Login</div>
			<br>
			<div
				style="padding: 10px; font-weight: 500; color: #444; font-size: 17px">

				<label style="float: left">Username <b>:</b></label>

				<div style="width: 75%;">
					<label style="color: red; margin-left: 5%"><b>*</b></label> <input
						type="text" name="username" required>
				</div>

				<br> <br> <br> <label style="float: left">Password
					<b>:</b>
				</label>
				<div style="width: 75%;">
					<label style="color: red; margin-left: 5%"><b>*</b></label> <input
						type="password" name="password" required>
				</div>
				<br> <br> <br> <br>
			</div>
			<div
				style="padding: 6px; background-color: #f7f7f7; height: 30px; border-top: 1px solid #d8e1eb; border-bottom: 1px solid #d8e1eb;">
				<input type="submit" value="Login">
			</div>
		</form>

	</div>
	</center>
</body>
</html>