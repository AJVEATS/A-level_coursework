function pageLoad() {
    getQuizByTopic();
}

function getQuizByTopic() {
    console.log("Invoked getQuizList() ");
    var topic = sessionStorage.getItem('topic');

    $.ajax({
        type: "GET",
        url: "/quiz/list",
        data:  {'topic' : topic},
        success: response => {
            if (response.toString().startsWith('Error:')) {
                alert(response);
            } else {
                formatQuizList(response);
            }
        }
    });
}

function formatQuizList(data) {
    console.log(data);
    let dataHTML = '<tr><td>Topic</td>' +
        '<td>Quiz Description</td>' +
        '<td>Date Created</td> + < /tr>';
    for (let item of data) {
        dataHTML += `<tr>\`<td>${item.topic}</td>\` +
             <td>${item.quizDescription}</td>` +
            `<td>${item.dateCreated}</td>` +
            `<td>  <button class="trackQuiz btn btn-lg btn-dark"  data-quizID="${item.quizId}" >Take the Test</button> </td></tr>`;
    }
    $('#quizList').html(dataHTML);
    $(".trackQuiz").click(event => {
        const quizId = $(event.target).attr('data-quizId');  //from the click event, get the quizID
        sessionStorage.setItem("quizId", quizId);
        window.open("quiz.html", "_self");
    });
}