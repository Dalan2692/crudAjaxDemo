
$(document).ready(function () {
    let user_id = 0;
    $(document).on('click','#table_users button.btn_delete',function () {
        let btn_id = (event.srcElement.id);
        user_id = btn_id.split("_")[2];

    })

    $('#modal_delete_btn').on('click',function () {
        deleteUser(user_id)
        location.reload();
    })

    function deleteUser(id) {
        $.ajax({
            url : '/api/v1/admin/'+user_id,
            method : 'DELETE',
            success: function (response) {

                console.log(response)
            },
            error: function () {
                console.log('failed')
            }
        })
    }

})
