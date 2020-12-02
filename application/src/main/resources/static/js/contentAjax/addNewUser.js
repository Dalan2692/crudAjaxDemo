$(document).ready(function () {
    $('#btn_add_new_user').on('click',function () {

        let user = {
            firstName : $('#form_add_user_firstName').val(),
            lastName  : $('#form_add_user_lastName').val(),
            age       : $('#form_add_user_age').val(),
            username  : $('#form_add_user_username').val(),
            password  : $('#form_add_user_password').val(),
            roles     : [{
                      role  : $('#form_add_user_role').val()
                        }]
        }

        $.ajax({
            url: '/api/v1/admin',
            method : 'POST',
            contentType : 'application/json',
            data : JSON.stringify(user),
            dataType: 'json',
            // async : false,
            // cache : false,
            success: function (response) {
                console.log(response)
                window.location = '/admin';
            },
            error: function () {
                console.log('error')
            }
        })
    })
})