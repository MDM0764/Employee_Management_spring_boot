$(document).ready(function () {
    $.get("http://localhost:8080/api/employees", function (data) {
        var empTable = '';
        $.each(data, function (key, value) {
            empTable += '<tr id="emp'+ value.empId + '">';
            empTable += '<td>' + value.empId + '</td>';
            empTable += '<td>' + value.name + '</td>';
            empTable += '<td>' + value.phoneNo + '</td>';
            empTable += '<td>' + value.email + '</td>';
            empTable += '<td>' + value.address + '</td>';
            empTable += '<td>' + '<button class="btn" onclick="deleteEmployee('+value.empId+');">Delete</button>' + '</td>';
            empTable += '<td>' + '<button class="btn" onclick="editEmployee('+value.empId+');">Edit</button>' + '</td>';
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
       trId = 'emp' + id
       var tr = $('#' + trId);
       var td = tr.find('td');
       $('#editId').val(td[0].outerText);
       $('#editName').val(td[1].outerText);
       $('#editPhoneNo').val(td[2].outerText);
       $('#editEmail').val(td[3].outerText);
       $('#editAddress').val(td[4].outerText);
}

$(function () {
$("#editForm").submit(function (e) {
    e.preventDefault();
    let id = $("#editId").val();
    var form = $(this);
        var url = 'employee/' + id;

         $.ajax({
                   type: "PUT",
                   url: url,
                   data: form.serialize(), // serializes the form's elements.
                   success: function(data)
                   {
                   location.reload();
                   }
                 });
                   });
    });

