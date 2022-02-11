$(document).ready(function () {
    $.get("http://localhost:8080/api/employees", function (data) {
        createTable(data.content);
        createPagaDetails(data);
    });
});

function getPage(PageNo){
    $("#table").empty();
    $.get("http://localhost:8080/api/employees?pageNo=" +PageNo , function (data) {
        createTable(data.content);
        createPagaDetails(data);
    });
}

function getRequestWithSort(sorId, sortDir) {
 $("#table").empty();
    let pgNo = $("#pgNo").text().split(":")[1].trim();
    let appendUrl = "?sortBy=" + sorId + "&sortDirection=" + sortDir + "&pageNo=" + (pgNo-1);
    $.get("http://localhost:8080/api/employees" + appendUrl, function (data) {
            createTable(data.content);
            createPagaDetails(data);
            var id = '#' + sorId;
            if (sortDir === 'asc') {
                $(id +' .bi-arrow-up').addClass("btn-secondary");
                $('.bi-arrow-down').removeClass("btn-secondary");
            } else {
                $(id +' .bi-arrow-down').addClass("btn-secondary");
                $('.bi-arrow-up').removeClass("btn-secondary");
            }
    });
}

function cancel() {
    $("#addForm").css("display", "none");
    $("#editFormDiv").css("display", "none");
}

function createPagaDetails(data) {
 $("#tfoot").empty();
    var tabFooter = '';
    var pgNo = data.pageable.pageNumber;

    let pagesSize = 5;
    tabFooter += '<tr>';
    tabFooter += '<td id="pgNo"> PageNo : ' + (pgNo+1) + '</td>'
    for (let j = 0; j < 4; j++){
         tabFooter += '<td></td>';
    }
     tabFooter += '<td colspan = "2">';

     if (pgNo < 1) {
        pgNo = 0;
     } else if (pgNo > data.totalPages) {
        pgNo = data.totalPages;
     }

    let startPage;
    let endPage;
    let maxBeforePg;
    let maxAfterPg;
     if (data.totalPages < pagesSize) {
          startPage = 0;
          endPage = data.totalPages;
          tabFooter+= createPgBtn(startPage, endPage, pgNo);
     } else {
         maxBeforePg = Math.floor(pagesSize/2);
         maxAfterPg = Math.ceil(pagesSize/2);
         if (pgNo <= maxBeforePg) {
            startPage = 0;
            endPage = pagesSize;
            tabFooter+= createPgBtn(startPage, endPage, pgNo);
         } else if ((maxAfterPg + pgNo) >= data.totalPages) {
            tabFooter += '...';//add ...
            startPage = data.totalPages - pagesSize + 1;
            endPage = data.totalPages;
            tabFooter+= createPgBtn(startPage, endPage, pgNo);
         } else {
            tabFooter += '...';//add ...
            startPage = pgNo - maxBeforePg;
            endPage = pgNo + maxAfterPg;
            tabFooter+= createPgBtn(startPage, endPage, pgNo);
            tabFooter += '...'; //add ...
         }
     }
     tabFooter += '</tr>';
     $("#tfoot").append(tabFooter);
}

function createPgBtn(startPage, endPage, pgNo) {
    let str = "";
    for(let i = startPage; i < endPage; i++){
        var k = i+1;
            if ( i === (pgNo)) {
                str += '<div class="btn btn-secondary" onclick="getPage('+i+')";>' + k +'</div>';
            } else {
                str += '<div class="btn btn-light" onclick="getPage('+i+')";>' + k +'</div>';
            }
        }
    return str;
}

function createTable (data) {
    var empTable = '';
    $.each(data, function (key, value) {
            empTable += '<tr id="emp'+ value.empId + '">';
            empTable += '<td scope="row">' + value.empId + '</td>';
            empTable += '<td>' + value.name + '</td>';
            empTable += '<td>' + value.phoneNo + '</td>';
            empTable += '<td>' + value.email + '</td>';
            empTable += '<td>' + value.address + '</td>';
            empTable += '<td>' + '<button class="btn btn-danger" onclick="deleteEmployee('+value.empId+');">Delete</button>' + '</td>';
            empTable += '<td>' + '<button class="btn btn-warning" onclick="editEmployee('+value.empId+');">Edit</button>' + '</td>';
            empTable += '</tr>';
        });
    $("#table").append(empTable);
}

function deleteEmployee(id) {
    $.ajax({
        url: "http://localhost:8080/api/employees/" + id,
        type: "DELETE",
        contentType: "application/json",
        success: function() {
            console.log("success");
            location.reload();
        },
        error:  function(xhr, status, error) {
                     var err = eval("(" + xhr.responseText + ")");
                     console.log(err.Message);
                     console.log('xhr: ');
                     console.log(xhr);
                     console.log('status: ' + status);
                     console.log('error: ' + error);
                   }

    });
}

function showAddForm(){
    $("#editFormDiv").css("display", "none");
    $("#addForm").show();
}

function editEmployee(id){
   var trId = 'emp' + id
   var tr = $('#' + trId);
   var td = tr.find('td');
   $('#editId').val(td[0].outerText);
   $('#editName').val(td[1].outerText);
   $('#editPhoneNo').val(td[2].outerText);
   $('#editEmail').val(td[3].outerText);
   $('#editAddress').val(td[4].outerText);
   $("#addForm").css("display", "none");
   $("#editFormDiv").show();
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
            },
            error :  function(xhr, status, error) {
                          var err = eval("(" + xhr.responseText + ")");
                          console.log(err.Message);
                          console.log('xhr: ');
                          console.log(xhr);
                          console.log('status: ' + status);
                          console.log('error: ' + error);
                        }
        });
    });
});

