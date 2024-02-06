<html>
	<head>
		<title> welcome Page</title>
	</head>
	<body>
	    <%@ include file = "common/navigation.jspf"%>
	    <div>
            <div>
                Welcome to the welcome page from login page !!!
            </div>
            <div>
                Name : ${name}
            </div>

            <div>
                <a href = "todo-list">manage</a> your todos
            </div>
	    </div>

	</body>
</html>