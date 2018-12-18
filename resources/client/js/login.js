function pageLoad() {

}
function postUserLogin() {
    console.log("Invoked postUserLogin() ");
    const loginForm = $('#loginForm');
    $.ajax({
        type: 'POST',
        url: '/user/login',
        data: loginForm.serialize(),
        success: response => {
            if (response.toString().startsWith('Error:')) {
                alert(response);
            } else {
                Cookies.set("sessionToken", response);
                window.open("topicSelection.html", "_self");
            }
        }
    });
}
function postUserNew() {
    const newUserForm = $('#newUserForm');
    newUserForm.submit(event => {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/user/new',
            data: newUserForm.serialize(),
            success: response => {
                if (response.startsWith('Error:')) {
                    alert(response);
                } else {
                    Cookies.set("sessionToken", response);
                    window.open("topicSelection.html", "_self");
                }
            }
        });
    });
}
function checkLogin() {
    let currentPage = window.location.pathname;
    let token = Cookies.get("sessionToken");
    if (token !== undefined) {
        $.ajax({
            url: '/user/check',
            type: 'GET',
            success: username => {
                if (username === "") {
                    if (currentPage !== '/client/index.html') {
                        window.location.href = '/client/index.html';
                    }
                }
            }
        });
    } else {
        if (currentPage !== '/client/index.html') {
            window.location.href = '/client/index.html';
        }
    }
}
function userLogout(){
    alert("You have been logged out.")
}