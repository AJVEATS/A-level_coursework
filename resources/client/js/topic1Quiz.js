var userAnswer = document.getElementById("userAnswer").value;
function pageLoad() {
    $.ajax({
        url: '/quiz/quizzes',
        type: 'GET',
        success: questionList => {
            let quizHTML = `<div class ="container">`
            + `<div class="row mb-2 "`
            + `<div class="col-2 bg-light font-weight-bold">one</div>`
            + `<div class="col-2 bg-light font-weight-bold">two</div>`
            + `<div class="col-2 bg-light font-weight-bold">three</div>`
            + `<div class="col-2 bg-light font-weight-bold">four</div>`
            + `<div class="col-2 bg-light font-weight-bold">five</div>`
            + `<div class="col-2 bg-light font-weight-bold">six</div>`
            + `</div>`;

            for(let quiz of questionList){
                quizHTML += `<div class="row mb-2">`
                + `<div class="col-2">${quiz.one}</div>`
                + `<div class="col-2">${quiz.two}</div>`
                + `<div class="col-2">${quiz.three}</div>`
                + `<div class="col-2">${quiz.four}</div>`
                + `<div class="col-2">${quiz.five}</div>`
                + `<div class="col-2">${quiz.six}</div>`
                + `</div>`
            }
            quiz.html += `</div>`;
            $('#quiz').html(quizHTML);
        }
    });
}

