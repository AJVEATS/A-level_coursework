



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
                    window.open("welcome.html", "_self");
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

function pageLoad() {

}
