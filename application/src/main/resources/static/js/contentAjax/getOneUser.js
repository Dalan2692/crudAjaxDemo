$(document).ready(function () {
    let user_id = 0
    $(document).on('click','#table_users button.btn_delete', function () {
        let btn_id = (event.srcElement.id);
        user_id = btn_id.split("_")[2];
        $.ajax({
            url : '/api/v1/admin/'+user_id,
            method : 'GET',
            success : function (response) {
                let role;
                $.each(response.roles, function (item,value) {
                    if (value.role === 'ROLE_ADMIN') {
                        role = 'ADMIN'
                    }
                    if (value.role === 'ROLE_USER'){
                        role = 'USER'
                    }
                })
                $('#form_delete_user_id').val(response.id)
                $('#form_delete_user_first_name').val(response.firstName)
                $('#form_delete_user_last_name').val(response.lastName)
                $('#form_delete_user_age').val(response.age)
                $('#form_delete_user_username').val(response.username)
                $('#form_delete_user_role').val(role)
            },
            error: function () {
                console.log('failed')
            }
        })
    })

    $(document).on('click','#table_users button.btn_edit', function () {
        let btn_id = (event.srcElement.id);
        user_id = btn_id.split("_")[2];
        $.ajax({
            url : '/api/v1/admin/'+user_id,
            method : 'GET',
            success : function (response) {
                let role;
                $.each(response.roles, function (item,value) {
                    if (value.role === 'ROLE_ADMIN') {
                        role = 'ADMIN'
                    }
                    if (value.role === 'ROLE_USER'){
                        role = 'USER'
                    }
                })
                $('#form_edit_user_id').val(response.id)
                $('#form_edit_user_first_name').val(response.firstName)
                $('#form_edit_user_last_name').val(response.lastName)
                $('#form_edit_user_age').val(response.age)
                $('#form_add_user_password').val(response.password)
                $('#form_edit_user_username').val(response.username)
                $('#form_edit_user_role').val(role)

            },
            error: function () {
                console.log('failed')
            }
        })
    })
})