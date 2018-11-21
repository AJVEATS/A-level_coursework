var userAnswer = document.getElementById("userAnswer").value;
var answerCorrect = a;
var userScore = 0;
var questionNumber = 0
function pageLoad() {

}
function nextQuestion(){
    while (questionNumber <= 9 && questionNumber >= 0){

    }
}
function AnswerWrong(){
    userScore == userScore + 1
    nextQuestion()
    window.alert("That is the correct answer. The correct answer is" + answerCorrect)
}
function AnswerCorrect(){
    userScore == userScore + 1
    nextQuestion()
    window.alert("That is the correct answer.")
}
function AnswerCheck(){
    if(userAnswer.toLowerCase() == answerCorrect ){
        AnswerCorrect()
    }else{
        AnswerWrong()
    }
}
function GenerateUserScorePercentage(){
    if( userScore > 0){
        var userScorePercentage = ((userScore*10)+"%")
    }else{
        return "0%"
    }
}
function updateUserScore(){
    
}
