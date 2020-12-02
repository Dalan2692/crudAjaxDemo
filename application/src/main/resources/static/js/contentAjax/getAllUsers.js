$(document).ready(function () {
    $.ajax({
        url : '/api/v1/admin',
        method : 'GET',
        success: function (response) {
            let role ;
            $.each(response,(item , user) => {
                $.each(user.roles, function (item,value) {
                    if (value.role === 'ROLE_ADMIN') {
                        role = 'ADMIN'
                    }
                    if (value.role === 'ROLE_USER'){
                        role = 'USER'
                    }
                })
                let deleteButton = '<button '+'id='+'\"' + 'btn_delete_' + user.id + '\"'+
                    ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal"' +'>DELETE</button>';

                let editButton = '<button '+'id='+'\"' + 'btn_edit_' + user.id + '\"'+
                    ' type="button" class="btn btn-secondary btn_edit" data-toggle="modal" data-target="#edit-modal"' +'>EDIT</button>';

                let tr_id = 'tr_'+user.id

                let tableRow = '<tr id=\"'+tr_id+"\""+'>'+
                    '<td>'+user.id+'</td>'+
                    '<td>'+user.firstName+'</td>'+
                    '<td>'+user.lastName+'</td>'+
                    '<td>'+user.age+'</td>'+
                    '<td>'+user.username+'</td>'+
                    '<td>'+role+'</td>'+
                    '<td>'+editButton+'</td>'+
                    '<td>'+deleteButton+'</td>'+'</tr>'
                $('#table_body').append(tableRow)
            })
        },
        error: function (error) {
            console.log(error)
        }
    })
})


