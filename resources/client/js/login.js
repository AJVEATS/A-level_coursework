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
                window.open("index.html", "_self");
            }
        }
    });
}
function resetNewUserForm() {
    const newUserForm = $('#newUserForm');
    newUserForm.submit(event => {
        event.preventDefault();
        $.ajax({
            url: '/user/new',
            type: 'POST',
            data: newUserForm.serialize(),
            success: response => {
                if (response.startsWith('Error:')) {
                    alert(response);
                } else {
                    Cookies.set("sessionToken", response);
                    window.location.href = "/client/index.html";
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
                    if (currentPage !== 'http://localhost:8081/client/profileOverview.html') {
                        window.location.href = 'http://localhost:8081/client/profileOverview.html';
                    }
                }
            }
        });
    } else {
        if (currentPage !== 'http://localhost:8081/client/profileOverview.html') {
            window.location.href = 'http://localhost:8081/client/profileOverview.html';
        }
    }
}