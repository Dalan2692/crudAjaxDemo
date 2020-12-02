$(document).ready(function () {
    $('.top_line_logout').on('click', function (event) {
        event.preventDefault()
        topLineLogoutPostAjax()

    });

    function topLineLogoutPostAjax() {
        $.ajax({
            type:'POST',
            url:  "/api/v1/auth/logout",
        }).done(function () {
            console.log('logout')
            localStorage.clear();
            $(location).attr('href','/login');
        })
    }
})