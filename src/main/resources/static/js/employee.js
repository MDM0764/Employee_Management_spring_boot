$(document).ready(function () {
    $.get("http://localhost:8080/api/employees", function (data) {
        var empTable = '';
        $.each(data, function (key, value) {
            empTable += '<tr>';
            empTable += '<td>' + value.empId + '</td>';
            empTable += '<td>' + value.name + '</td>';
            empTable += '<td>' + value.phoneNo + '</td>';
            empTable += '<td>' + value.email + '</td>';
            empTable += '<td>' + value.address + '</td>';
            empTable += '<td>' + '<button class="btn" onclick="deleteEmployee('+value.empId+');">Delete</button>' + '</td>';
            empTable += '<td>' + '<button class="btn" onclick="editEmployee('+value.empId+');">Delete</button>' + '</td>';
            empTable += '</tr>';
        });
         $("#table").append(empTable);
    });
});

function deleteEmployee(id){
    $.ajax({
        url: "http://localhost:8080/api/employees/" + id,
        type: "DELETE",
        contentType: "application/json",
        success: function() {
            console.log("success");
            location.reload();
        },
        error: function () {
            console.log("error");
        }
    });
}

function showAddForm(){
    $("#addForm").show();
}

function editEmployee(id){
    var tr = $("#" + id + "")
}

