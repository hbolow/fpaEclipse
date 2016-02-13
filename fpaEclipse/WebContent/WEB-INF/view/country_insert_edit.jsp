<%-- 
    Document   : newjsp
    Created on : 09-Feb-2016, 17:36:10
    Author     : hbolow
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title>Add New or Edit Country</title>
</head>
<body>
    <H1>Add New or Edit Country</h1>
    
    <form action="FlowControl?logic=InsertUpdateCountry&id=${country.id}" method="post">
        <fieldset>
            <div>
        
            	
                <label for="notid">Id</label> <input type="text"
                    name="notid" value="${country.id}"
                    readonly="readonly" placeholder="Id - Automatically provided" />
                <input type="hidden"
                    name="id" value="${country.id}"
                    placeholder="id" />
            </div>
            <div>
                <label for="name">Name</label> <input type="text"
                    name="name" value="${country.name}"
                    placeholder="name" /> ${errorMessageInsertEditCountryName}
            </div>
            <div>
                <label for="lastName">Language</label> <input type="text"
                    name="language" value="${country.language}"
                    placeholder="language" />
            </div>
            
            <div>

                <input type="submit" value="Submit" />
            </div>
        </fieldset>
    </form>
</body>
</html>