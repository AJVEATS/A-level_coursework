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
        '<td>|   Answer A   |</td>' +
        '<td>|   Answer B   |</td>' +
        '<td>|   Answer C   |</td>' +
        '<td>|   Answer D   |</td>' +
        '<td>|   Your Answer   |</td>' +
        '</tr>';
    for (let item of data){
        dataHTML += `<tr>\<td>${item.question}</td>\` +
             <td>${item.answerA}</td>` +
            `<td>${item.answerB}</td>` +
            `<td>${item.answerC}</td>` +
            `<td>${item.answerD}</td>` +
            `<td><select id = "userAnswer" onchange="checkUserAnswer()"><option value="A">A</option><option value="B">B</option><option value="C">C</option><option value="D">D</option></select></td>`;
    }
    $('#QuestionList').html(dataHTML);
}

function checkUserAnswer() {
    var usersAnswer = document.getElementById("userAnswer");
}
