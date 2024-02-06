

<%@ include file="common/header.jspf"%>
<%@ include file = "common/navigation.jspf"%>
<div class = "container">

    <div>This is todo page </div>
    <div>welcome : ${name}</div>

    <table class="table">
      <tr>
        <th>Description</th>
        <th>TargetDate</th>
        <th>isDone</th>
        <th></th>
        <th></th>
      </tr>
      <c:forEach items = "${toDos}" var = "todo">
          <tr>
                <td>${todo.description}</td>
                <td>${todo.targetDate}</td>
                <td>${todo.done}</td>
                <td>
                    <a href = "delete-todo?id=${todo.id}" class = "btn btn-warning">Delete</a>
                </td>
                <td>
                    <a href = "update-todo?id=${todo.id}" class = "btn btn-primary">Update</a>
                </td>
          </tr>
      </c:forEach>

    </table>

    <a href = "add-todo" class = "btn btn-success">Add TODO</a>
</div>

<%@ include file="common/footer.jspf"%>
</body>

</html>
