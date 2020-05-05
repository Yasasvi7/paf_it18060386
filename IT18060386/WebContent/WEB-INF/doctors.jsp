<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.Doctor"  %>   
    
    
    <!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/doctors.js"></script>
<meta charset="ISO-8859-1">


</head>
<body>


<div class="container">
<div class="row">
<div class="col-6">

<h1>Doctor Management v10</h1>
<form id="formDoctor" name="formDoctor" method="post" action="doctors.jsp">
Doctor code:
<input id="drCode" name="drCode" type="text"
class="form-control form-control-sm">
<br> Doctor name:
<input id="drName" name="drName" type="text"
class="form-control form-control-sm">
<br> Doctor salary:
<input id="drSalary" name="drSalary" type="text"
class="form-control form-control-sm">
<br> Doctor description:
<input id="drDesc" name="drDesc" type="text"
class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save"
class="btn btn-primary">
<input type="hidden" id="hidDocIDSave" name="hidDocIDSave" value="">
</form>


<div id="alertSuccess" class="alert alert-success"></div>

<div id="alertError" class="alert alert-danger"></div>
  
   <br>
   
   <%
   
      Doctor docObj = new Doctor();
      out.print(docObj.readdoctor());
   %>
   
   
   </div>
   </div>
   </div>


</body>
</html>