function pageLoad(){
    getQuizAnswers();
}

function getQuizAnswers() {
    console.log("Invoked getQuizAnswers()");
    var quizId = sessionStorage.getItem('quizId');
    $.ajax({
        type: "GET",
        url: "/quizquestion/list",
        data:  {'quizId' : quizId},
        success: response => {
            if (response.toString().startsWith('Error:')){
                alert(response);
            } else {
                formatQuizAnswersList(response);
            }
        }
    });
}

function formatQuizAnswersList(data) {
    console.log(data);
    let answersHTML = '<tr><td>Question</td>' +
        '<td>Correct Answer</td>' +
        '</tr>';
    for (let item of data){
        answersHTML += `<tr>\<td>${item.question}</td>\` +
        <td>${item.answerCorrect}</td></tr>`;
    }
    $('#AnswersList').html(answersHTML);

}

$(".setScore").click(event => {
    const score = $(event.target).attr('data-score');  //from the click event, get the topic
    sessionStorage.setItem("score", score);
    window.open("topicSelection.html", "_self");
});