function pageLoad(){    // New function for the web page that is meant to run upon the page loading.
    getQuizQuestions();   // When the function pageLoad() it calls upon the getQuizQuestion() to run also.
}

function getQuizQuestions(){   // New function declared called getQuizQuestions(). It is called upon in the pageLoad() function.
    console.log("Invoked getQuizQuestions()");    // Outputs "Invoked getQuizQuestions" into the browser console/
    var quizId = sessionStorage.getItem('quizId');    // New variable quizID is declared and is set to the same value as the quizId in the session storage.

    $.ajax({
        type: "GET",
        url: "/quizquestion/list",    // The API path for the list function in the quizQuestionController.
        data:  {'quizId' : quizId},    // The data that is being collected using the quizId.
        success: response => {
            if (response.toString().startsWith('Error:')){    // If the response from the server starts with an error, it runs the if statement.
                alert(response);    // If the if statement is run it returns the response from the server.
            } else {    // If the response from the server does not start with and "Error" it runs the next part of the statement.
                formatQuizQuestionList(response);    // It runs the function formatQuizQuestionList() function from below with the response from the server.
            }

        }
    });
}

function formatQuizQuestionList(data){    // New function declared called formatQuizQuestionList() with imported data from the API path.
    console.log(data);    // The server logs the data from the server into the web browser with the data collected from the database.
    let dataHTML = '<tr><td>Question</td>' +    // Formatting how the data will be outputted onto the web page.
        '<td>Answer A</td>' +
        '<td>Answer B</td>' +
        '<td>Answer C</td>' +
        '<td>Answer D</td>' +
        '<td>Your Answer</td>' +
        '</tr>';
    for (let item of data){
        dataHTML += `<tr>\<td>${item.question}</td>\` +    // The name of the data that is being collected from the database.
             <td>${item.answerA}</td>` +
            `<td>${item.answerB}</td>` +
            `<td>${item.answerC}</td>` +
            `<td>${item.answerD}</td>`;
    }
    $('#QuestionList').html(dataHTML);
}

function checkUserAnswer() {    // New function declared called checkUserAnswer() which is to check if the users answer is the same as the questions correct answer.
    console.log("Invoked checkUserAnswer()");
    let usersAnswer = document.getElementById("userAnswer");
    console.log(usersAnswer);
    let answerCorrect = "B";
    let score = 0;
    if (usersAnswer === answerCorrect){
        console.log("Answer Correct has been triggered");
        score = score + 1;
        alert("Answer correct. Your current score is: "  + score);
    } else if(usersAnswer !== answerCorrect) {
        console.log("Answer Wrong has been triggered");
        alert("Answer wrong. Your current score is: "  + score);
    }

}

