$(document).ready(function() {

    let authResponse = JSON.parse(localStorage.getItem('authResponse'))
    let username = authResponse.username;
    let roles = authResponse.roles;

    let role;
    $.each(roles, function (i, value) {
        role = (value.role === 'ROLE_ADMIN') ? 'ADMIN' : 'USER';
        $('.top_line_username').append(username)
        $('.top_line_role').append(role)
    })


    let adminLinks = '<li class="left_nav_item">' +
                            '<a href="/admin" class="left_nav_link">Admin</a>' +
                     '</li>'+

                     '<li class="left_nav_item">' +
                            '<a href="/user" class="left_nav_link">User</a>' +
                     '</li>'

    let userLinks =  '<li class="left_nav_item">' +
                            '<a href="/user" class="left_nav_link">User</a>' +
                      '</li>';


    if (role === 'ADMIN') {
        $('.left_nav_menu').append(adminLinks)
    }
    if (role === 'USER') {
        $('.left_nav_menu').append(userLinks)
    }

    $(function () {                                      // Когда страница загрузится
        $('.left_nav>ul>li>a').each(function () {             // получаем все нужные нам ссылки
            let location = window.location.href; // получаем адрес страницы
            let link = this.href;                // получаем адрес ссылки
            if(location == link) {               // при совпадении адреса ссылки и адреса окна
                $(this).removeClass('left_nav_active').addClass('left_nav_active');  //добавляем класс
            }
        });
    });


});





