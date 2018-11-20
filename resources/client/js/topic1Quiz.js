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
function GenerateUserScorePecentage(){
    userScorePercentage = userScore * 10;
}
resetLoginForm()
resetNewUserForm()