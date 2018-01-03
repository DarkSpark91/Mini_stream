$(document).ready(function () {
    loginTab();
});

function loginTab() {
    $("#login").show();
    $("#signup").hide();
}

function signUpTab() {
    $("#signup").show();
    $("#login").hide();
}