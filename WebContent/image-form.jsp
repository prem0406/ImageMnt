<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Update Image</title>
</head>
<style>
    td {
        padding: 5px;
    }
    input[type=text] {
        border: none;
    }
    
</style>
<body>
    <div style="text-align:center">
        <h1>Image Management</h1>
    </div>

    <div align="center">

        <form action="update" method="post" enctype="multipart/form-data">

            <h2> Edit Image </h2>

            <input type="hidden" name="id" value="${image.id}" />
            <table border="1" >


                <tr >
                    <th>Image Name: </th>
                    <td>
                        <input type="text" name="name" size="45" value="${image.name}" />
                    </td>
                </tr>

                <tr>
                    <th>Image: </th>
                    <td>
                        <input type="file" name="image" size="15" accept="image/*" value="${image.image}" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>

</html>