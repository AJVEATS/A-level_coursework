var userAnswer = document.getElementById("userAnswer").value;
var answerCorrect = a;
var userScore = 0;
function pageLoad() {

}
function AnswerCheck(){
    if(userAnswer.toLowerCase() == answerCorrect ){
        userScore = userAnswer + 1
    }
}
function GenerateUserScorePercentage(){
    let userScorePercentage = userScore * 10;
}
resetLoginForm()
resetNewUserForm()