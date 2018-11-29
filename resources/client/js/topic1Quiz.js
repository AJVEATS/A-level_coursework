var userAnswer = document.getElementById("userAnswer").value;
function pageLoad() {
    $.ajax({
        url: '/quiz/quizzes',
        type: 'GET',
        success: questionList => {
            let questionsHTML = `<div class ="container">`
            + `<div class="row mb-2 "`
            + `<div class="col-2 bg-light font"`
        }
    })
}

