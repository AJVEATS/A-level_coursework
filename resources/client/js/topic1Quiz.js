var userAnswer = document.getElementById("userAnswer").value;
function pageLoad() {
    $.ajax({
        url: '/quiz/quizzes',
        type: 'GET',
        success: questionList => {
            let quizHTML = `<div class ="container">`
                + `<div class="row mb-2 "`
                + `<div class="col-6 bg-dark font-weight-bold">one</div>`
                + `<div class="col-6 bg-dark font-weight-bold">two</div>`
                + `</div>`;
            for(let quiz of questionList){
                quizHTML += `<div class="row mb-2">`
                    + `<div class="col-6">${quiz.one}</div>`
                    + `<div class="col-6">${quiz.two}</div>`
                    + `</div>`
            }
            quizHTML += `</div>`;
            $('#quiz').html(quizHTML);
        }
    });
}
function checkUserAnswer(AnswerCorrect){
    if (userAnswer = AnswerCorrect) {

    }
}
function score(){

}