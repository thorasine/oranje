$(function(){
    loadPage('/info');
    $("login-form").submit(function(event) {
        console.log("asd");
        event.preventDefault();
    });
    $("#nav-info").click(function () {
        loadPage('/info');
    });
    $("#nav-user").click(function () {
        loadPage('/user');
    });
    $("#nav-admin").click(function () {
        loadPage('/admin');
    });
    $("#nav-editor").click(function () {
        loadPage('/editor');
    });
    $('.navbar-nav li').click(function(){
        $('.navbar-nav li').removeClass('active');
        $(this).addClass('active');
    });
});

function loadPage(url) {
    $("#container").load(url, function () {
    });
}
