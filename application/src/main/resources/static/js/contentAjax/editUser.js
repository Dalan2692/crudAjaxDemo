$(document).ready(function () {

    $('#modal_edit_btn').on('click',function (e) {
        e.preventDefault();
        editUser()
        location.href = '/admin'
    })

    function editUser() {

        let id = $('#form_edit_user_id').val();

        let user = {
            firstName : $('#form_edit_user_first_name').val(),
            lastName  : $('#form_edit_user_last_name').val(),
            age       : $('#form_edit_user_age').val(),
            roles     : [{
                       role : $('#form_edit_user_role').val()
            }],
            password  : $('#form_edit_user_password').val(),
            username  : $('#form_edit_user_username').val()
        }

        $.ajax({
            url         : '/api/v1/admin/'+id,
            method      : 'PUT',
            contentType : 'application/json',
            data        : JSON.stringify(user),
            dataType    : 'json',
            success     : function (response) {
                console.log(response)
            },
            error       : function (error) {
                console.log(error)
            }
        })
    }
})