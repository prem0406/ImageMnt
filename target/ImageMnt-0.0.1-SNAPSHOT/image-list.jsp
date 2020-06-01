<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>

<style type="text/css">
body {
	font-family: sans-serif;
}

table, td, tr, th {
	padding: 10px;
	border-collapse: collapse;
	border: 1px solid #444;
	letter-spacing: 1px;
}

table {
	width: 100%;
}

button {
	margin: 5px;
}
</style>



</head>
<body>

	<center>
	
	
	<div style="border: 1px solid #333; width: 60%">
		<div
			style="padding: 10px; font-size: 18px; letter-spacing: 1px; border-bottom: 1px solid #333">
			<b>Image Management Utility</b>
			
		</div>
		<br>
		<form action="<%=request.getContextPath()%>/logout" method="post">
		<%String user = (String) session.getAttribute("user");%>
			<h3>Hello : ${user}</h3>
			<input type="submit" value="Logout" >
		</form>
		<br>
		<div style="padding: 10px">
			Please select an image file to upload(Max Size 1MB)<br>
			<br>
			<form action="insert" method="post" enctype="multipart/form-data">
			 
				
				<input type="file" name="image" accept="image/*" required>
				
				<input type="submit" value="Upload" />
				
			</form>

			<br> <br>
			
			<b>Uploaded Images</b>
		</div>

		<table>
			<tr>
				<th>S.No</th>
				<th>Name</th>
				<th>Size</th>
				<th>Preview</th>
				<th>Actions</th>
			</tr>
			<%int i = 1 ;
			//${image.id}
			%>
			<c:forEach var="image" items="${imageList}">
				<tr>
			
				<td> <%=i %> </td>
				<%i++; %>
				<td> ${image.name} </td>
				<td> ${image.size} KB</td>
				
				<td style="text-align: center"><img src="${image.image}"
					style="width: 100px; height: 100px"></td>
				
				<td>
					<a href="edit?id=<c:out value='${image.id}' />"> <img alt="Edit" src="C:\Users\premkumar\eclipse-workspace\ImageMnt\edit.png"
         width="30" height="30"></a>
                     &nbsp;
                     <a href="delete?id=<c:out value='${image.id}' />"> <img alt="Delete" src="C:\Users\premkumar\eclipse-workspace\ImageMnt\delete.png"
         width="30" height="30"></a>                     
                   
				</td>
			</tr>
			</c:forEach>
			
		</table>
		<%
			double tSize = (Integer) request.getAttribute("totalSize");
			String unit = "KB";
			if (tSize > 1023) {
				tSize = tSize/1024;
				tSize = Math.round(tSize * 100);
				tSize = tSize/100;
				unit = "MB";
			}
		%>
		
		
		<p>Total Size: <%=tSize%> <%=unit %> </p>
	</div>
	</center>
</body>
</html>