function pageLoad(){
    getQuizQuestions();
}

function getQuizQuestions(){
    console.log("Invoked getQuizQuestions()");
    var quizId = sessionStorage.getItem('quizId');

    $.ajax({
        type: "GET",
        url: "/quizquestion/list",
        data:  {'quizId' : quizId},
        success: response => {
            if (response.toString().startsWith('Error:')){
                alert(response);
            } else {
                formatQuizQuestionList(response);
            }
        }
    });
}

function formatQuizQuestionList(data){
    console.log(data);
    let dataHTML = '<tr><td>Question</td>' +
        '<td>|      AnswerA      |</td>' +
        '<td>|      AnswerB      |</td>' +
        '<td>|      AnswerC      |</td>' +
        '<td>|      AnswerD      |</td>' +
        '</tr>';
    for (let item of data){
        dataHTML += `<tr>\`<td>${item.question}</td>\` +
             <td>${item.answerA}</td>` +
            `<td>${item.answerB}</td>` +
            `<td>${item.answerC}</td>` +
            `<td>${item.answerD}</td>` +
            '<td><button class="btn btn-lg btn-dark" onclick=\'checkUserAnswer();\'>A</button></td>' +
            '<td><button class="btn btn-lg btn-dark" onclick=\'checkUserAnswer();\'>B</button></td>' +
            '<td><button class="btn btn-lg btn-dark" onclick=\'checkUserAnswer();\'>C</button></td>' +
            '<td><button class="btn btn-lg btn-dark" onclick=\'checkUserAnswer();\'>D</button></td>';
    }
    $('#QuestionList').html(dataHTML);
}

function checkUserAnswer() {

}
