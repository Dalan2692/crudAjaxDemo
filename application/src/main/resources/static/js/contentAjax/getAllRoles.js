$(document).ready(function () {
    $.ajax({
        url   : '/api/v1/admin/roles',
        method: 'GET',
        success:function (response) {
            let role;
            $.each(response, (item, role) =>{
                if (role.role === 'ROLE_ADMIN') {
                    role = 'ADMIN'
                }
                if (role.role === 'ROLE_USER') {
                    role = 'USER'
                }
                $('#form_add_user_role').append('<option>'+role+'</option>')
                $('#form_edit_user_role').append('<option>'+role+'</option>')
            })


        },
        error: function () {
            console.log('failed')
        }
    })
})