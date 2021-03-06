$(function(){
    loadPage('/home');
    $("#nav-home").click(function () {
        loadPage('/home');
    });
    $("#nav-usercentral").click(function () {
        loadPage('/usercentral');
    });
    $("#nav-admin").click(function () {
        loadPage('/admin');
    });
    $("#nav-edit").click(function () {
        loadPage('/edit');
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